package com.example.administrator.safetyfirst.push;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Property;
import android.view.View;

import com.example.administrator.safetyfirst.push.activities.AccountActivity;
import com.example.administrator.safetyfirst.push.activities.MainActivity;
import com.example.administrator.safetyfirst.push.frags.assist.PermissionsFragment;
import com.example.common.app.Activity;
import com.example.factory.persistence.Account;
import com.igexin.sdk.PushManager;

import net.qiujuer.genius.res.Resource;
import net.qiujuer.genius.ui.compat.UiCompat;

/**
 * @author linzx
 * @date 2018/12/27
 */
public class LaunchActivity extends Activity {
    // Drawable
    private ColorDrawable mBgDrawable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 初始化个推
        Log.i("msg ","LaunchActivity->oncreate");
        PushManager.getInstance().initialize(this.getApplicationContext(), GeTuiPushService.class);
        // GeTuiIntentService 为第三方自定义的推送服务事件接收类
        // 在个推SDK初始化后，注册上述 GeTuiIntentService 类
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), GeTuiIntentService.class);
//        String cid = PushManager.getInstance().getClientid(this);
//        Log.i("msg","clientid==="+cid);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_launch;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        // 拿到跟布局
        View root = findViewById(R.id.activity_launch);
        // 获取颜色
        int color = UiCompat.getColor(getResources(), R.color.colorPrimary);
        // 创建一个Drawable
        ColorDrawable drawable = new ColorDrawable(color);
        // 设置给背景
        root.setBackground(drawable);
        mBgDrawable = drawable;

    }

    @Override
    protected void initDate() {
        super.initDate();
        // 动画进入到50%等待PushId获取到
        startAnim(0.5f, new Runnable() {
            @Override
            public void run() {
                // 检查等待状态
                waitPushReceiverId();
            }
        });
    }

    /**
     * 等待个推框架对我们的PushId设置好值
     */
    private void waitPushReceiverId() {
        Log.i("msg","waitPushReceiverId");
        if (Account.isLogin()) {
            Log.i("msg","waitPushReceiverId->login");
            // 已经登录情况下，判断是否绑定
            // 如果没有绑定则等待广播接收器进行绑定
            if (Account.isBind()) {
                Log.i("msg","waitPushReceiverId->login->bind");
                skip();
                return;
            }
        } else {
            Log.i("msg","waitPushReceiverId->unlogin");
            Log.i("msg","pushId"+Account.getPushId());
            // 没有登录
            // 如果拿到了PushId, 没有登录是不能绑定PushId的
            if (!TextUtils.isEmpty(Account.getPushId())) {
                Log.i("msg","waitPushReceiverId->unlogin->getpushId");
                // 跳转
                skip();
                return;
            }
        }

        // 循环等待
        getWindow().getDecorView()
                .postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        waitPushReceiverId();
                    }
                }, 500);
    }


    /**
     * 在跳转之前需要把剩下的50%进行完成
     */
    private void skip() {
        Log.i("msg","skip");
        startAnim(1f, new Runnable() {
            @Override
            public void run() {
                reallySkip();
            }
        });
    }

    /**
     * 真实的跳转
     */
    private void reallySkip() {
        Log.i("msg","reallySkip");
        // 权限检测，跳转
        if (PermissionsFragment.haveAll(this, getSupportFragmentManager())) {
            // 检查跳转到主页还是登录
            if (Account.isLogin()) {
                MainActivity.show(this);
            } else {
                AccountActivity.show(this);
            }
            finish();
        }
    }

    /**
     * 给背景设置一个动画
     *
     * @param endProgress 动画的结束进度
     * @param endCallback 动画结束时触发
     */
    private void startAnim(float endProgress, final Runnable endCallback) {
        // 获取一个最终的颜色
        int finalColor = Resource.Color.WHITE; // UiCompat.getColor(getResources(), R.color.white);
        // 运算当前进度的颜色
        ArgbEvaluator evaluator = new ArgbEvaluator();
        int endColor = (int) evaluator.evaluate(endProgress, mBgDrawable.getColor(), finalColor);
        // 构建一个属性动画
        ValueAnimator valueAnimator = ObjectAnimator.ofObject(this, property, evaluator, endColor);
        valueAnimator.setDuration(1500); // 时间
        valueAnimator.setIntValues(mBgDrawable.getColor(), endColor); // 开始结束值
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                // 结束时触发
                endCallback.run();
            }
        });
        valueAnimator.start();
    }


    private final Property<LaunchActivity, Object> property = new Property<LaunchActivity, Object>(Object.class, "color") {
        @Override
        public void set(LaunchActivity object, Object value) {
            object.mBgDrawable.setColor((Integer) value);
        }

        @Override
        public Object get(LaunchActivity object) {
            return object.mBgDrawable.getColor();
        }
    };
}
