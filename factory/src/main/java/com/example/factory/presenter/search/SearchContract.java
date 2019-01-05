package com.example.factory.presenter.search;

import com.example.factory.model.card.GroupCard;
import com.example.factory.model.card.UserCard;
import com.example.factory.presenter.BaseContract;

import java.util.List;

/**
 * @author linzx
 * @date 2019/1/5
 */
public interface SearchContract {
    interface Presenter extends BaseContract.Presenter {
        // 搜索内容
        void search(String content);
    }

    // 搜索人的界面
    interface UserView extends BaseContract.View<Presenter> {
        void onSearchDone(List<UserCard> userCards);
    }

    // 搜索群的界面
    interface GroupView extends BaseContract.View<Presenter> {
        void onSearchDone(List<GroupCard> groupCards);
    }
}
