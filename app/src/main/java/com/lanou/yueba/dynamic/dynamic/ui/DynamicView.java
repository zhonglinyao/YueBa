package com.lanou.yueba.dynamic.dynamic.ui;

import com.lanou.yueba.bean.DynamicBean;
import com.lanou.yueba.bean.FriendBean;

import java.util.List;

/**
 * Created by dllo on 16/11/8.
 */

public interface DynamicView {
    void startQueryFriend();

    void startQueryDynamic();

    void onResponseFriend(List<FriendBean> friendBeen);

    void onResponseDynamic(List<DynamicBean> dynamicBeen);

    void onError();

}
