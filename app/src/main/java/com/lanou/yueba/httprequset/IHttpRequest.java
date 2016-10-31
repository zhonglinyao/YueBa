package com.lanou.yueba.httprequset;

import java.lang.reflect.Type;
import java.util.Map;

import okhttp3.Response;

/**
 * Created by dllo on 16/10/25.
 */

public interface IHttpRequest {

    /**
     * 普通的get请求
     *
     * @param url
     * @param clazz
     * @param listener
     * @param <T>
     */
    <T> void getRequest(String url, Class<T> clazz, OnCompletedListener<T> listener);

    /**
     * 带请求头的get请求
     *
     * @param url
     * @param headers
     * @param clazz
     * @param listener
     * @param <T>
     */
    <T> void getRequest(String url, Map<String, String> headers,
                        Class<T> clazz, OnCompletedListener<T> listener);

    /**
     * 普通的post请求
     *
     * @param url
     * @param requestBody
     * @param clazz
     * @param listener
     * @param <T>
     */
    <T> void postRequest(String url, Map<String, String> requestBody,
                         Class<T> clazz, OnCompletedListener listener);

    /**
     * 带请求头的post请求
     *
     * @param url
     * @param headers
     * @param requestBody
     * @param clazz
     * @param listener
     * @param <T>
     */
    <T> void postRequest(String url, Map<String, String> headers, Map<String, String> requestBody,
                         Class<T> clazz, OnCompletedListener listener);


    <T> void typeGetRequest(String url, Type type, OnCompletedListener<T> listener);

    Response syncGetRequest(String url);

}
