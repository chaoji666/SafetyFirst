package com.example.common.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class Fragment extends android.support.v4.app.Fragment {
    protected  View mRoot;
    protected Unbinder mRootUnbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //初始化参数
        initArgs(getArguments());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(mRoot == null){
            int layId = getContentLayoutId();
            //初始化当前的根布局，但是不在创建时就添加到container里边
            View root = inflater.inflate(layId,container,false);
            initWidget(root);
            mRoot = root;
        }else{
            if(mRoot.getParent() != null){
                //把当前root从其父控件中移除
                ((ViewGroup)mRoot.getParent()).removeView(mRoot);
            }
        }

        return mRoot;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //  当view创建完成后初始化数据
        initDate();
    }

    /*
     * 初始化相关参数
     * */
    protected void initArgs(Bundle bundle){

    }

    /*
    * 得到当前界面的资源文件ID
    * @return 资源文件ID
    * */
    protected abstract int getContentLayoutId();

    /*
     * 初始化控件
     * */
    protected  void initWidget(View root){
       mRootUnbinder = ButterKnife.bind(this,root);
    }

    /*
     * 初始化数据
     * */
    protected void initDate(){

    }
    /*
    * 返回按键触发时调用
    * @return 返回true代表我已处理返回逻辑，activity不用自己finish
    * 返回false代表我没有处理逻辑，activity自己走自己的逻辑
    * */
    public boolean onBackPressed(){
        return false;
    }
}
