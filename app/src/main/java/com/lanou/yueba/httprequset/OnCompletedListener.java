package com.lanou.yueba.httprequset;

import java.util.List;

/**
 * Created by dllo on 16/10/25.
 */

public interface OnCompletedListener<T> {
    void onCompleted(T result);
    void onFailed();
    void onCompleted(List<T> list);
}
