package com.lanou.yueba.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by dllo on 16/10/18.
 */

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {


    protected Context mContext;
    protected LayoutInflater mLayoutInflater;
    protected int mLayoutId;
    protected List<T> mData;

    public BaseRecyclerAdapter(Context context, int mLayoutId, List<T> data) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        this.mLayoutId = mLayoutId;
        mData = data;
    }


    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        BaseRecyclerViewHolder baseRecyclerViewHolder = BaseRecyclerViewHolder.createViewHolder(mContext, parent, mLayoutId);
        return baseRecyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        convert(holder, mData.get(position));
    }


    public abstract void convert(BaseRecyclerViewHolder holder, T t);

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }


}
