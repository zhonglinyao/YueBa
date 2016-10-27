package com.lanou.yueba.login.presenter;

import com.lanou.yueba.login.model.ILoginModel;
import com.lanou.yueba.login.model.LoginModelImpl;
import com.lanou.yueba.login.model.OnLoginListener;
import com.lanou.yueba.login.ui.ILoginView;

/**
 * Created by dllo on 16/10/26.
 */

public class LoginPresenter {

    private ILoginModel mModel;
    private ILoginView mView;


    public LoginPresenter(ILoginView view) {
        mView = view;
        mModel = new LoginModelImpl();
    }

    public void startRequest(String username, String password){
        mView.showDialog();


        mModel.startRequest(username, password, new OnLoginListener() {
            @Override
            public void onFinished(String username, String password) {
                mView.dismissDialog();
                mView.onResponse(username, password);
            }

            @Override
            public void onError(int i, String s) {
                mView.onError(i,s);
            }


        });
    }
}
