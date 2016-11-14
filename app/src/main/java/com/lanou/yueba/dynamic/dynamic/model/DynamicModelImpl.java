package com.lanou.yueba.dynamic.dynamic.model;

import com.lanou.yueba.bean.DynamicBean;
import com.lanou.yueba.bean.FriendBean;
import com.lanou.yueba.dynamic.dynamic.OnQueryListener;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by dllo on 16/11/8.
 */

public class DynamicModelImpl implements DynamicModel{


    @Override
    public void queryFriend(String username, final OnQueryListener queryListener) {
        final BmobQuery<FriendBean> query = new BmobQuery<>("FriendBean");
        query.addWhereEqualTo("username", username);
        query.addWhereEqualTo("isFriend", true);
        query.findObjects(new FindListener<FriendBean>() {
            @Override
            public void done(List<FriendBean> list, BmobException e) {
                if (e == null) {
                    if (list != null && list.size() > 0)
                        queryListener.onResponseFriend(list);
                    else queryListener.onResponseFriend(new ArrayList<FriendBean>());
                } else {
                    queryListener.onFailed();
                }
            }
        });
    }

    @Override
    public void queryDynamic(List<String> user, final OnQueryListener queryListener) {
        BmobQuery<DynamicBean> query = new BmobQuery<>("DynamicBean");
//        List<String> strings = new ArrayList<>();
//        strings.add(mUserInfoBean.getUserName());
//        if (list.size() > 0) {
//            for (FriendBean friendBean : list) {
//                strings.add(friendBean.getFriendname());
//            }
//        }

        query.addWhereContainedIn("userName", user);
        query.order("-createdAt");
        query.findObjects(new FindListener<DynamicBean>() {
            @Override
            public void done(List<DynamicBean> list, BmobException e) {
                if (e == null && list != null && list.size() > 0) {
                    queryListener.onResponseDynamic(list);

                } else {
                    queryListener.onFailed();
                }
            }
        });
    }
}
