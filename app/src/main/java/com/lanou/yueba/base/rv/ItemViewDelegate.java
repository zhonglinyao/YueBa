package com.lanou.yueba.base.rv;


/**
 * Created by zhy on 16/6/22.
 */
public interface ItemViewDelegate<T> {

    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(BaseRecyclerViewHolder holder, T t, int position);

}
