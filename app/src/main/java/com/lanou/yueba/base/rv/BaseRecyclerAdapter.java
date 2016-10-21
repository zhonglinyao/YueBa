package com.lanou.yueba.base.rv;

import android.content.Context;
import android.view.LayoutInflater;

import java.util.List;

/**
 * Created by dllo on 16/10/18.
 */

public abstract class BaseRecyclerAdapter<T> extends MultiItemTypeAdapter<T> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    public BaseRecyclerAdapter(Context context, final int layoutId, List<T> datas) {
        super(context, datas);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;
        addItemViewDelegate(new ItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(BaseRecyclerViewHolder holder, T t, int position) {
                BaseRecyclerAdapter.this.convert(holder, t, position);
            }
        });
    }
    protected abstract void convert(BaseRecyclerViewHolder holder, T t, int position);

}
