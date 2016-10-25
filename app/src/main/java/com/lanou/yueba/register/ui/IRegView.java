package com.lanou.yueba.register.ui;

/**
 * Created by dllo on 16/10/25.
 */

public interface IRegView {

    void showDialog();

    void dismissDialog();

    void onResponse();

    void onError(Throwable error);
}
