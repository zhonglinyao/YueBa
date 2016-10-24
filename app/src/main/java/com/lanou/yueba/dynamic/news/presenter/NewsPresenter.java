package com.lanou.yueba.dynamic.news.presenter;

import com.lanou.yueba.bean.NewsBean;
import com.lanou.yueba.dynamic.news.model.NewsModel;
import com.lanou.yueba.dynamic.news.model.NewsModelImpl;
import com.lanou.yueba.dynamic.news.model.OnResultListener;
import com.lanou.yueba.dynamic.news.ui.NewsView;

/**
 * Created by dllo on 16/10/24.
 */

public class NewsPresenter {
    private NewsModel mNewsModel;
    private NewsView<NewsBean> mNewsView;

    public NewsPresenter(NewsView<NewsBean> newsView) {
        mNewsView = newsView;
        mNewsModel = new NewsModelImpl();
    }

    public void startRequset(String urlString){
        mNewsView.showQuestView();
        mNewsModel.startRequset(urlString, new OnResultListener<NewsBean>() {

            @Override
            public void onResult(NewsBean result) {
                mNewsView.onResponse(result);
            }

            @Override
            public void onError() {

            }
        });
    }
}
