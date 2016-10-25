package com.lanou.yueba.dynamic.news.ui;

/**
 * Created by dllo on 16/10/24.
 */

public interface NewsView<T> {
    void showQuestView();

    void showDataView();

    void onResponse(T t);

    void onError();
}
