package com.lanou.yueba.model;

import com.lanou.yueba.httprequset.HttpManger;
import com.lanou.yueba.httprequset.OnCompletedListener;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by dllo on 16/10/24.
 */

public class AppAppModelImpl implements AppModel {

    @Override
    public <T>void startGetRequest(String urlString, Class<T> clazz, OnCompletedListener<T> listener) {
        HttpManger.getInstance().<T>getRequest(urlString, clazz, listener);
    }

    @Override
    public <T>void startGetRequest(String urlString, Map<String, String> header, Class<T> clazz, OnCompletedListener<T> listener) {
        HttpManger.getInstance().<T>getRequest(urlString, header, clazz, listener);
    }

    @Override
    public <T>void startPostRequest(String urlString, Map<String, String> requestBody, Class<T> clazz, OnCompletedListener<T> listener) {
        HttpManger.getInstance().<T>postRequest(urlString, requestBody, clazz, listener);
    }

    @Override
    public <T>void startPostRequest(String urlString, Map<String, String> headers, Map<String, String> requestBody, Class<T> clazz, OnCompletedListener<T> listener) {
        HttpManger.getInstance().<T>postRequest(urlString, headers, requestBody, clazz, listener);
    }

    @Override
    public <T> void startTypeGetRequest(String urlString, Type type, OnCompletedListener<T> listener) {
        HttpManger.getInstance().<T>typeGetRequest(urlString, type, listener);
    }

    @Override
    public <T>void insertIntoDB(T t) {

    }
}
