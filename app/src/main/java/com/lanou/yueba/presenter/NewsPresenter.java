package com.lanou.yueba.presenter;

import com.lanou.yueba.model.Model;
import com.lanou.yueba.model.NewsModelImpl;
import com.lanou.yueba.ui.NewsView;
import com.lanou.yueba.httprequset.OnCompletedListener;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 16/10/24.
 */

public class NewsPresenter<T> {
    private NewsView mNewsView;
    private Model mModel;

    public NewsPresenter(NewsView newsView) {
        mNewsView = newsView;
        mModel = new NewsModelImpl();
    }

    public <T>void startGetRequset(String urlString, Class<T> clazz) {
        mNewsView.showQuestView();
        mModel.startGetRequest(urlString, clazz, mListener);
    }

    public void startGetRequset(String urlString, Map<String, String> header, Class<T> clazz) {
        mNewsView.showQuestView();
        mModel.startGetRequest(urlString, header, clazz, mListener);
    }

    public void startPostRequset(String urlString, Map<String, String> requestBody, Class<T> clazz) {
        mNewsView.showQuestView();
        mModel.startPostRequest(urlString, requestBody, clazz, mListener);
    }

    public void startPostRequset(String urlString, Map<String, String> header, Map<String, String> requestBody, Class<T> clazz) {
        mNewsView.showQuestView();
        mModel.startPostRequest(urlString, header, requestBody, clazz, mListener);
    }

    public <T>void startTypeGetRequset(String urlString, Type type) {
        mNewsView.showQuestView();
        mModel.<T>startTypeGetRequest(urlString, type, mListener);
    }

    OnCompletedListener mListener = new OnCompletedListener<T>() {
        @Override
        public void onCompleted(T result) {
            mNewsView.showDataView();
            mNewsView.onResponse(result);
        }

        @Override
        public void onFailed() {

        }

        @Override
        public void onCompleted(List<T> list) {
            mNewsView.showDataView();
            mNewsView.onListResponse(list);
        }
    };
}
