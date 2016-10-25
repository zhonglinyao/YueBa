package com.lanou.yueba.dynamic.news.model;

import com.lanou.yueba.bean.NewsBean;
import com.lanou.yueba.httprequset.HttpManger;
import com.lanou.yueba.httprequset.OnCompletedListener;

import java.util.Map;

/**
 * Created by dllo on 16/10/24.
 */

public class NewsModelImpl implements Model<NewsBean> {

    @Override
    public void startGetRequest(String urlString, Class<NewsBean> clazz, OnCompletedListener<NewsBean> listener) {
        HttpManger.getInstance().getRequest(urlString, clazz, listener);
    }

    @Override
    public void startGetRequest(String urlString, Map<String, String> header, Class<NewsBean> clazz, OnCompletedListener<NewsBean> listener) {
        HttpManger.getInstance().getRequest(urlString, header, clazz, listener);
    }

    @Override
    public void startPostRequest(String urlString, Map<String, String> requestBody, Class<NewsBean> clazz, OnCompletedListener<NewsBean> listener) {
        HttpManger.getInstance().postRequest(urlString, requestBody, clazz, listener);
    }

    @Override
    public void startPostRequest(String urlString, Map<String, String> headers, Map<String, String> requestBody, Class<NewsBean> clazz, OnCompletedListener<NewsBean> listener) {
        HttpManger.getInstance().postRequest(urlString, headers, requestBody, clazz, listener);
    }

    @Override
    public void insertIntoDB(NewsBean newsBean) {

    }
}
