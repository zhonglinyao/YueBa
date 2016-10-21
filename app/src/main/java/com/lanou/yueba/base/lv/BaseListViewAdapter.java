package com.lanou.yueba.base.lv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/10/18.
 */
public abstract class BaseListViewAdapter<T> extends BaseAdapter {
    protected List<T> mDataList;
    protected Context mContext;
    protected LayoutInflater mInflater;
    protected int layoutId;//item的Id

    public BaseListViewAdapter(Context context, List<T> list, int layoutId) {
        this.layoutId = layoutId;
        mDataList = new ArrayList<>();
        mContext = context;
        mDataList.addAll(list);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @Override
    public T getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseListViewViewHolder viewHolder = BaseListViewViewHolder.get(mContext, convertView, parent, layoutId, position);
        convent(viewHolder, getItem(position));
        return viewHolder.getConvertView();
    }

    public abstract void convent(BaseListViewViewHolder viewHolder, T t);
}
