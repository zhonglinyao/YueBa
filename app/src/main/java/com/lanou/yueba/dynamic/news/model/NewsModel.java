package com.lanou.yueba.dynamic.news.model;

/**
 * Created by dllo on 16/10/24.
 */

public interface NewsModel {
    void startRequset(String urlString, OnResultListener onResultListener);
}
