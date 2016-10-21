package com.lanou.yueba.base;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dllo on 16/10/19.
 */

public class BaseRecyclerAdapterWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int BASE_ITEM_TYPE_HEADER = 1000;
    private static final int BASE_ITEM_TYPE_FOOTER = 2000;

    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
    private SparseArrayCompat<View> mFootViews = new SparseArrayCompat<>();

    private RecyclerView.Adapter mAdapter;

    public BaseRecyclerAdapterWrapper(RecyclerView.Adapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderViewPos(position)) {
            return mHeaderViews.keyAt(position);
        } else if (isFooterViewPos(position)) {
            return mFootViews.keyAt(position - getHeadersCount() - getRealItemCount());
        }
        return mAdapter.getItemViewType(position - getHeadersCount());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderViews.get(viewType) != null) {
            BaseRecyclerViewHolder holder = BaseRecyclerViewHolder.createViewHolder(parent.getContext(), mHeaderViews.get(viewType));
            return holder;

        } else if (mFootViews.get(viewType) != null) {
            BaseRecyclerViewHolder holder = BaseRecyclerViewHolder.createViewHolder(parent.getContext(), mFootViews.get(viewType));
            return holder;
        }
        return mAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isHeaderViewPos(position)) {
            return;
        }
        if (isFooterViewPos(position)) {
            return;
        }
        mAdapter.onBindViewHolder(holder, position - getHeadersCount());
    }

    @Override
    public int getItemCount() {
        return getHeadersCount() + getFootersCount() + getRealItemCount();
    }

    public void addHeaderView(View view) {
        mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, view);
    }

    public void addFootView(View view) {
        mFootViews.put(mFootViews.size() + BASE_ITEM_TYPE_FOOTER, view);
    }

    private boolean isHeaderViewPos(int position) {
        return position < getHeadersCount();
    }

    private boolean isFooterViewPos(int position) {
        return position >= getFootersCount() + getRealItemCount();
    }

    private int getRealItemCount() {
        return mAdapter.getItemCount();
    }

    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    public int getFootersCount() {
        return mFootViews.size();
    }
}
