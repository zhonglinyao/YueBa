package com.lanou.yueba.message;

import com.hyphenate.easeui.controller.EaseUI;
import com.hyphenate.easeui.domain.EaseUser;
import com.lanou.yueba.bean.UserInfoBean;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by dllo on 16/11/10.
 */

public class UserProfileProvide implements EaseUI.EaseUserProfileProvider {


    @Override
    public EaseUser getUser(String username) {
        EaseUser easeUser = null;
        easeUser = new EaseUser(username);
        easeUser.setAvatar(queryImg(username));
        return easeUser;
    }

    String img;

    private String queryImg(String username) {

        BmobQuery<UserInfoBean> query = new BmobQuery<>();
        query.addWhereEqualTo("username", username);
        query.findObjects(new FindListener<UserInfoBean>() {
            @Override
            public void done(List<UserInfoBean> list, BmobException e) {
                if (e == null) {
                    UserInfoBean userInfoBean = list.get(0);
                    img = userInfoBean.getPicUrl();
                }

            }
        });


        return img;
    }


}
