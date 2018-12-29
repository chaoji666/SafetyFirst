package com.example.administrator.safetyfirst.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.transition.Slide;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.administrator.safetyfirst.push.R;

public class MapFragment extends Fragment {
    View chat_fragment; //聊天页面
    BottomSheetBehavior<View> behavior;  //底部滑出动作
    View bottom_sheet_bar; //滑出页面工具条
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_fragment,null);
        initView(view);
        return view;
    }
    //初始化视图
    public void initView(View view){
        bottom_sheet_bar = view.findViewById(R.id.bottom_sheet_bar);
        chat_fragment = view.findViewById(R.id.bottom_sheet);
        behavior = BottomSheetBehavior.from(chat_fragment);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                String state = "null";
                switch (i) {
                    case 1:
                        state = "STATE_DRAGGING";//过渡状态此时用户正在向上或者向下拖动bottom sheet
                        break;
                    case 2:
                        state = "STATE_SETTLING"; // 视图从脱离手指自由滑动到最终停下的这一小段时间
                        break;
                    case 3:
                        state = "STATE_EXPANDED"; //处于完全展开的状态
                        //完全展开时工具条为透明度为1的白色
                        bottom_sheet_bar.setBackgroundColor(Color.WHITE);
                        break;
                    case 4:
                        state = "STATE_COLLAPSED"; //默认的折叠状态
                        //折叠状态时工具条为透明度为1的白色
                        bottom_sheet_bar.setBackgroundColor(Color.WHITE);
                        break;
                    case 5:
                        state = "STATE_HIDDEN"; //下滑动完全隐藏 bottom sheet
                        //下滑完全隐藏时，设置折叠高度为250，并跳转到折叠状态
                        //下滑完全隐藏时，工具条为透明度为0.5的灰色
                        behavior.setPeekHeight(250);
                        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        bottom_sheet_bar.setBackgroundColor(Color.GRAY);
                        bottom_sheet_bar.setAlpha((float) 0.5);
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {
                //滑动过程中，将bottomsheet的折叠高度设置为420
                behavior.setPeekHeight(420);
            }
        });

    }

}
