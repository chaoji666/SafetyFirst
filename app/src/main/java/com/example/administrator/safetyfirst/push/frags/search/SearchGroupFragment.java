package com.example.administrator.safetyfirst.push.frags.search;

import com.example.administrator.safetyfirst.push.R;
import com.example.administrator.safetyfirst.push.activities.SearchActivity;
import com.example.common.app.Fragment;

/**
 * 搜索群的界面实现
 * @author linzx
 * @date 2019/1/1
 */
public class SearchGroupFragment extends Fragment
        implements SearchActivity.SearchFragment {


    public SearchGroupFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_search_group;
    }

    @Override
    public void search(String content) {

    }
}
