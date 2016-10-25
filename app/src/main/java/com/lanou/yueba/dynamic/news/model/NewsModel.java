package com.lanou.yueba.dynamic.news.model;

import com.lanou.yueba.bean.NewsBean;
import com.lanou.yueba.httprequset.OnCompletedListener;

import java.util.Map;

/**
 * Created by dllo on 16/10/24.
 */

public interface NewsModel {
    void startGetRequest(String urlString, Class<NewsBean> clazz, OnCompletedListener<NewsBean> listener);

    void startGetRequest(String urlString, Map<String, String> header, Class<NewsBean> clazz, OnCompletedListener<NewsBean> listener);

    void startPostRequest(String urlString, Map<String, String> requestBody, Class<NewsBean> clazz, OnCompletedListener<NewsBean> listener);

    void startPostRequest(String urlString, Map<String, String> headers, Map<String, String> requestBody, Class<NewsBean> clazz, OnCompletedListener<NewsBean> listener);

    void insertIntoDB(NewsBean newsBean);
}
