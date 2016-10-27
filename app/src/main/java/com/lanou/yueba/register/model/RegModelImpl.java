package com.lanou.yueba.register.model;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.lanou.yueba.threadtools.ThreadTool;

/**
 * Created by dllo on 16/10/25.
 */

public class RegModelImpl implements IRegModel{


    @Override
    public void startRequest(final String username, final String password, final OnFinishedListener listener) {

        ThreadTool.getInstance().executorRunnable(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().createAccount(username, password);//同步方法
                    listener.onFinished(username,password);
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    listener.onError(e);
                }
            }
        });




    }
}
