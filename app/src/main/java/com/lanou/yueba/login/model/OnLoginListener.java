package com.lanou.yueba.login.model;

/**
 * Created by dllo on 16/10/26.
 */

public interface OnLoginListener {

    void onFinished(String username, String password);

    void onError(int i, String s);
}
