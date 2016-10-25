package com.lanou.yueba.dynamic.news.ui;

import com.lanou.yueba.bean.NewsBean;

/**
 * Created by dllo on 16/10/24.
 */

public interface NewsView {
    void showQuestView();

    void showDataView();

    void onResponse(NewsBean result);

    void onError();
}
