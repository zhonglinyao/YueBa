package com.lanou.yueba.dynamic.news.presenter;

import com.lanou.yueba.bean.NewsBean;
import com.lanou.yueba.dynamic.news.model.Model;
import com.lanou.yueba.dynamic.news.model.NewsModelImpl;
import com.lanou.yueba.dynamic.news.ui.NewsView;
import com.lanou.yueba.httprequset.OnCompletedListener;

import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 16/10/24.
 */

public class NewsPresenter {
    private NewsView mNewsView;
    private Model mModel;

    public NewsPresenter(NewsView newsView) {
        mNewsView = newsView;
        mModel = new NewsModelImpl();
    }

    public void startGetRequset(String urlString, Class<NewsBean> clazz) {
        mNewsView.showQuestView();
        mModel.startGetRequest(urlString, clazz, mListener);
    }

    public void startGetRequset(String urlString, Map<String, String> header, Class<NewsBean> clazz) {
        mNewsView.showQuestView();
        mModel.startGetRequest(urlString, header, clazz, mListener);
    }

    public void startPostRequset(String urlString, Map<String, String> requestBody, Class<NewsBean> clazz) {
        mNewsView.showQuestView();
        mModel.startPostRequest(urlString, requestBody, clazz, mListener);
    }

    public void startPostRequset(String urlString, Map<String, String> header, Map<String, String> requestBody, Class<NewsBean> clazz) {
        mNewsView.showQuestView();
        mModel.startPostRequest(urlString, header, requestBody, clazz, mListener);
    }

    OnCompletedListener<NewsBean> mListener = new OnCompletedListener<NewsBean>() {
        @Override
        public void onCompleted(NewsBean result) {
            mNewsView.showDataView();
            mNewsView.onResponse(result);
        }

        @Override
        public void onFailed() {

        }

        @Override
        public void onCompleted(List<NewsBean> list) {

        }
    };
}
