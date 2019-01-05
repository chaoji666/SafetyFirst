package com.example.factory.presenter.search;

import com.example.factory.presenter.BasePresenter;

/**
 * 搜索群的逻辑实现
 * @author linzx
 * @date 2019/1/5
 */
public class SearchGroupPresenter extends BasePresenter<SearchContract.GroupView>
        implements SearchContract.Presenter{
    public SearchGroupPresenter(SearchContract.GroupView view) {
        super(view);
    }

    @Override
    public void search(String content) {

    }
}
