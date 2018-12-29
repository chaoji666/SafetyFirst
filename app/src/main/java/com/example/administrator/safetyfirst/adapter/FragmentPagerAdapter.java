package com.example.administrator.safetyfirst.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {
    List<Fragment> mview;
    public FragmentPagerAdapter(FragmentManager fm, List<Fragment> mview) {
        super(fm);
        this.mview = mview;
    }

    @Override
    public Fragment getItem(int i) {
        return mview.get(i);
    }

    @Override
    public int getCount() {
        return mview.size();
    }
}
