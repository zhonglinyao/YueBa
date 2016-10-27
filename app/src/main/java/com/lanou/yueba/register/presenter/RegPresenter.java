package com.lanou.yueba.register.presenter;

import android.util.Log;

import com.lanou.yueba.bean.UserInfoBean;
import com.lanou.yueba.register.model.IRegModel;
import com.lanou.yueba.register.model.OnFinishedListener;
import com.lanou.yueba.register.model.RegModelImpl;
import com.lanou.yueba.register.ui.IRegView;
import com.lanou.yueba.threadtools.ThreadTool;
import com.lanou.yueba.tools.MD5Util;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by dllo on 16/10/25.
 */

public class RegPresenter {

    private IRegModel mModel;
    private IRegView mView;

    public RegPresenter(IRegView view) {
        mView = view;
        mModel = new RegModelImpl();
    }

    public void startRequest(final String username, final String password) {
        mView.showDialog();
        mModel.startRequest(username, password, new OnFinishedListener() {
            @Override
            public void onFinished(final String username, final String password) {
                mView.dismissDialog();
                mView.onResponse(username,password);

                ThreadTool.getInstance().executorRunnable(new Runnable() {
                    @Override
                    public void run() {
                        UserInfoBean userInfoBean = new UserInfoBean();
                        userInfoBean.setUserName(username);
                        userInfoBean.setMd5UserPassWord(MD5Util.getMD5String(password));
                        userInfoBean.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if (e == null) {
                                    Log.d("RegPresenter", "插入成功");
                                } else {
                                    Log.d("RegPresenter", "插入失败");
                                }
                            }
                        });
                    }
                });



            }

            @Override
            public void onError(Throwable error) {
                mView.dismissDialog();
                mView.onError(error);
            }


        });
    }
}
