package com.lanou.yueba.base;

/**
 * Created by dllo on 16/10/20.
 */

public interface MultiItemTypeSupport<T> {

    int getLayoutId(int itemType);

    int getItemViewType(int position, T t);

}
