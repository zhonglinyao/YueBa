package com.lanou.yueba.dynamic.dynamic;

import com.lanou.yueba.bean.DynamicBean;
import com.lanou.yueba.bean.FriendBean;

import java.util.List;

/**
 * Created by dllo on 16/11/8.
 */

public interface OnQueryListener {
    void onResponseFriend(List<FriendBean> friendBeen);

    void onResponseDynamic(List<DynamicBean> dynamicBeen);

    void onFailed();

}
