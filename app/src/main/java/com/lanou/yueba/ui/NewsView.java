package com.lanou.yueba.ui;

import java.util.List;

/**
 * Created by dllo on 16/10/24.
 */

public interface NewsView<T> {
    void showQuestView();

    void showDataView();

    void onResponse(T t);

    void onListResponse(List<T> list);

    void onError();
}
