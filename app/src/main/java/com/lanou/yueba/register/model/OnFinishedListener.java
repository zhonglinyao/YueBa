package com.lanou.yueba.register.model;

/**
 * Created by dllo on 16/10/25.
 */

public interface OnFinishedListener {

    void onFinished(String username,String password);
    void onError(Throwable error);


}
