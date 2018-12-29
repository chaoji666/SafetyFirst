package com.example.administrator.safetyfirst;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.safetyfirst.adapter.FragmentPagerAdapter;
import com.example.administrator.safetyfirst.fragment.MapFragment;
import com.example.administrator.safetyfirst.fragment.PersonalFragment;
import com.example.administrator.safetyfirst.listener.FragmentChangeListener;
import com.example.administrator.safetyfirst.push.R;
import com.example.administrator.safetyfirst.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
1229
    private NoScrollViewPager viewPager;
    private List<Fragment> mviews;
    ImageView map_img;  //底部标签图标
    ImageView personal_img; //底部标签图标
    LinearLayout map_layout;    //底部标签
    LinearLayout personal_layout;   //底部标签
//    FragmentTransaction ft;
//    Fragment personalFragment;
//    Fragment mapFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
    }
    //初始化视图
    public void initView(){
        mviews = new ArrayList<>();
        viewPager = findViewById(R.id.viewpager);
        map_img = findViewById(R.id.map_img);
        personal_img = findViewById(R.id.personal_img);
        map_layout = findViewById(R.id.map);
        personal_layout = findViewById(R.id.personal);

//        personalFragment = new PersonalFragment();
//        mapFragment = new MapFragment();
//        ft = getSupportFragmentManager().beginTransaction();
//        ft.add(R.id.main_layout,mapFragment);
//        ft.add(R.id.main_layout,personalFragment);
//        ft.show(mapFragment);
//        ft.hide(personalFragment);
//        ft.commit();
        //初始化主要页面
        Fragment mapfragment = new MapFragment();
        Fragment personalFragment = new PersonalFragment();
        mviews.add(mapfragment);
        mviews.add(personalFragment);

        //设置viewpager适配器与监听
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager(),mviews);
        viewPager.setAdapter(adapter);
        FragmentChangeListener listener = new FragmentChangeListener();
        viewPager.addOnPageChangeListener(listener);
        viewPager.setNoScroll(true); //设置不可滑动

    }

    //初始化事件
    public void initEvent(){
        map_layout.setOnClickListener(this);
        personal_layout.setOnClickListener(this);
    }

    //初始化底部标签图片
    public void resetImg(){
        map_img.setBackgroundColor(Color.RED);
        personal_img.setBackgroundColor(Color.RED);
    }

    //底部bottom点击事件
    @Override
    public void onClick(View v) {
        resetImg();
        switch (v.getId()){
            case R.id.map:
                viewPager.setCurrentItem(0);
//                ft.hide(personalFragment);
//                ft.show(mapFragment);
//                ft.commit();
                map_img.setBackgroundColor(Color.BLUE);
                break;
            case R.id.personal:
                viewPager.setCurrentItem(1);
//                ft.hide(mapFragment);
//                ft.show(personalFragment);
//                ft.commit();
                personal_img.setBackgroundColor(Color.BLUE);
                break;
        }


    }

}
