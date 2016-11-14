package com.lanou.yueba.dynamic.dynamic.persenter;

import com.lanou.yueba.bean.DynamicBean;
import com.lanou.yueba.bean.FriendBean;
import com.lanou.yueba.dynamic.dynamic.OnQueryListener;
import com.lanou.yueba.dynamic.dynamic.model.DynamicModel;
import com.lanou.yueba.dynamic.dynamic.model.DynamicModelImpl;
import com.lanou.yueba.dynamic.dynamic.ui.DynamicView;

import java.util.List;

/**
 * Created by dllo on 16/11/8.
 */

public class DynamicPresenter implements OnQueryListener{
    private DynamicModel mModel;
    private DynamicView mDynamicView;

    public DynamicPresenter(DynamicView dynamicView) {
        mDynamicView = dynamicView;
        mModel = new DynamicModelImpl();
    }

    public void queryFriend(String username){
        mDynamicView.startQueryFriend();
        mModel.queryFriend(username, this);
    }

    public void queryDynamic(List<String> user){
        mDynamicView.startQueryDynamic();
        mModel.queryDynamic(user, this);
    }

    @Override
    public void onResponseFriend(List<FriendBean> friendBeen) {
        mDynamicView.onResponseFriend(friendBeen);
    }

    @Override
    public void onResponseDynamic(List<DynamicBean> dynamicBeen) {
        mDynamicView.onResponseDynamic(dynamicBeen);
    }

    @Override
    public void onFailed() {
        mDynamicView.onError();
    }
}
