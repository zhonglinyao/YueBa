package com.lanou.yueba.base;

import android.content.Context;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by dllo on 16/10/20.
 */

public class MultiItemRecyclerAdapter<T> extends BaseRecyclerAdapter<T> {

    MultiItemTypeSupport<T> mTMultiItemTypeSupport;

    public MultiItemRecyclerAdapter(Context context,  List<T> data,MultiItemTypeSupport<T> multiItemTypeSupport) {
        super(context, -1, data);
        mTMultiItemTypeSupport = multiItemTypeSupport;
    }

    @Override
    public void convert(BaseRecyclerViewHolder holder, T t) {

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }
}
