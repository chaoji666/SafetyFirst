package com.example.administrator.safetyfirst.push;

import com.example.common.app.Application;
import com.example.factory.Factory;
import com.igexin.sdk.PushManager;

/**
 * @author linzx
 * @date 2018/12/27
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // 调用Factory进行初始化
        Factory.setup();
        // 推送进行初始化
        //PushManager.getInstance().initialize(this.getApplicationContext(), GeTuiPushService.class);
    }
}
