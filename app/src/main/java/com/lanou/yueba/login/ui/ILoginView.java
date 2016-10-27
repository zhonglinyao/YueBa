package com.lanou.yueba.login.ui;

/**
 * Created by dllo on 16/10/26.
 */

public interface ILoginView {

    void showDialog();

    void dismissDialog();

    void onResponse(String username, String password);

    void onError(int i, String s);

}
