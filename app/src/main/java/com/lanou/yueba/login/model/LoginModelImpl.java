package com.lanou.yueba.login.model;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.lanou.yueba.threadtools.ThreadTool;

/**
 * Created by dllo on 16/10/25.
 */

public class LoginModelImpl implements ILoginModel {

    @Override
    public void startRequest(final String username, final String password, final OnLoginListener listener) {
        ThreadTool.getInstance().executorRunnable(new Runnable() {
            @Override
            public void run() {
                EMClient.getInstance().login(username, password, new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        listener.onFinished();
                    }

                    @Override
                    public void onError(int i, String s) {
                        listener.onError(i,s);
                    }

                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
            }
        });
    }
}
