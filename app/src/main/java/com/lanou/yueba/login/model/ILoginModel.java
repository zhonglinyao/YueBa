package com.lanou.yueba.login.model;

/**
 * Created by dllo on 16/10/26.
 */

public interface ILoginModel {
    void startRequest(String username,String password,OnLoginListener listener);
}
