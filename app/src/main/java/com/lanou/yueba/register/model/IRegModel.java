package com.lanou.yueba.register.model;

/**
 * Created by dllo on 16/10/25.
 */

public interface IRegModel {


    void startRequest(String username,String password,OnFinishedListener listener);
}
