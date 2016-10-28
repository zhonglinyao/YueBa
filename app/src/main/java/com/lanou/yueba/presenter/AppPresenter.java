package com.lanou.yueba.presenter;

import com.lanou.yueba.model.AppModel;
import com.lanou.yueba.model.AppAppModelImpl;
import com.lanou.yueba.ui.AppView;
import com.lanou.yueba.httprequset.OnCompletedListener;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 16/10/24.
 */

public class AppPresenter {
    private AppView mAppView;
    private AppModel mAppModel;

    public AppPresenter(AppView appView) {
        mAppView = appView;
        mAppModel = new AppAppModelImpl();
    }

    public <T>void startGetRequest(String urlString, Class<T> clazz) {
        mAppView.showQuestView();
        mAppModel.<T>startGetRequest(urlString, clazz, new OnCompletedListener<T>() {
            @Override
            public void onCompleted(T result) {
                mAppView.showDataView();
                mAppView.onResponse(result);
            }

            @Override
            public void onFailed() {

            }

            @Override
            public void onCompleted(List<T> list) {
                mAppView.showDataView();
                mAppView.onListResponse(list);
            }
        });
    }

    public <T>void startGetRequest(String urlString, Map<String, String> header, Class<T> clazz) {
        mAppView.showQuestView();
        mAppModel.<T>startGetRequest(urlString, header, clazz, new OnCompletedListener<T>() {
            @Override
            public void onCompleted(T result) {
                mAppView.showDataView();
                mAppView.onResponse(result);
            }

            @Override
            public void onFailed() {

            }

            @Override
            public void onCompleted(List<T> list) {
                mAppView.showDataView();
                mAppView.onListResponse(list);
            }
        });
    }

    public <T>void startPostRequest(String urlString, Map<String, String> requestBody, Class<T> clazz) {
        mAppView.showQuestView();
        mAppModel.<T>startPostRequest(urlString, requestBody, clazz, new OnCompletedListener<T>() {
            @Override
            public void onCompleted(T result) {
                mAppView.showDataView();
                mAppView.onResponse(result);
            }

            @Override
            public void onFailed() {

            }

            @Override
            public void onCompleted(List<T> list) {
                mAppView.showDataView();
                mAppView.onListResponse(list);
            }
        });
    }

    public <T>void startPostRequest(String urlString, Map<String, String> header, Map<String, String> requestBody, Class<T> clazz) {
        mAppView.showQuestView();
        mAppModel.<T>startPostRequest(urlString, header, requestBody, clazz, new OnCompletedListener<T>() {
            @Override
            public void onCompleted(T result) {
                mAppView.showDataView();
                mAppView.onResponse(result);
            }

            @Override
            public void onFailed() {

            }

            @Override
            public void onCompleted(List<T> list) {
                mAppView.showDataView();
                mAppView.onListResponse(list);
            }
        });
    }

    public <T> void startTypeGetRequset(String urlString, Type type) {
        mAppView.showQuestView();
        mAppModel.<T>startTypeGetRequest(urlString, type, new OnCompletedListener<T>() {
            @Override
            public void onCompleted(T result) {
                mAppView.showDataView();
                mAppView.onResponse(result);
            }

            @Override
            public void onFailed() {

            }

            @Override
            public void onCompleted(List<T> list) {
                mAppView.showDataView();
                mAppView.onListResponse(list);
            }
        });
    }

    public <T>void startTypeGetRequest(String urlString, Type type) {
        mAppView.showQuestView();
        mAppModel.<T>startTypeGetRequest(urlString, type, new OnCompletedListener<T>() {
            @Override
            public void onCompleted(T result) {
                mAppView.showDataView();
                mAppView.onResponse(result);
            }

            @Override
            public void onFailed() {

            }

            @Override
            public void onCompleted(List<T> list) {
                mAppView.showDataView();
                mAppView.onListResponse(list);
            }
        });
    }
}
