package com.lanou.yueba.register.presenter;

import com.lanou.yueba.register.model.IRegModel;
import com.lanou.yueba.register.ui.IRegView;
import com.lanou.yueba.register.model.OnFinishedListener;
import com.lanou.yueba.register.model.RegModelImpl;

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

    public void startRequest(String username, String password){
        mView.showDialog();
        mModel.startRequest(username, password, new OnFinishedListener() {
            @Override
            public void onFinished() {
                mView.dismissDialog();
                mView.onResponse();
            }

            @Override
            public void onError(Throwable error) {
                mView.dismissDialog();
                mView.onError(error);

            }
        });
    }


}
