package com.lanou.yueba.model;

import com.lanou.yueba.httprequset.OnCompletedListener;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by dllo on 16/10/24.
 */

public interface Model {
    <T> void startGetRequest(String urlString, Class<T> clazz, OnCompletedListener<T> listener);

    <T> void startGetRequest(String urlString, Map<String, String> header, Class<T> clazz, OnCompletedListener<T> listener);

    <T> void startPostRequest(String urlString, Map<String, String> requestBody, Class<T> clazz, OnCompletedListener<T> listener);

    <T> void startPostRequest(String urlString, Map<String, String> headers, Map<String, String> requestBody, Class<T> clazz, OnCompletedListener<T> listener);

    <T> void startTypeGetRequest(String urlString, Type type, OnCompletedListener<T> listener);

    <T> void insertIntoDB(T t);
}
