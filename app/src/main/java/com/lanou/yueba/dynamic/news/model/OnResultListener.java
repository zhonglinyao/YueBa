package com.lanou.yueba.dynamic.news.model;

/**
 * Created by dllo on 16/10/24.
 */

public interface OnResultListener<T> {
    void onResult(T result);
    void onError();
}
