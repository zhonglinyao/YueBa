package com.lanou.yueba.base.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class MultiItemTypeListViewAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mDatas;

    private ItemViewDelegateManager mItemViewDelegateManager;


    public MultiItemTypeListViewAdapter(Context context, List<T> datas) {
        this.mContext = context;
        this.mDatas = datas;
        mItemViewDelegateManager = new ItemViewDelegateManager();
    }

    public MultiItemTypeListViewAdapter addItemViewDelegate(ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(itemViewDelegate);
        return this;
    }

    private boolean useItemViewDelegateManager() {
        return mItemViewDelegateManager.getItemViewDelegateCount() > 0;
    }

    @Override
    public int getViewTypeCount() {
        if (useItemViewDelegateManager())
            return mItemViewDelegateManager.getItemViewDelegateCount();
        return super.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (useItemViewDelegateManager()) {
            int viewType = mItemViewDelegateManager.getItemViewType(mDatas.get(position), position);
            return viewType;
        }
        return super.getItemViewType(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemViewDelegate itemViewDelegate = mItemViewDelegateManager.getItemViewDelegate(mDatas.get(position), position);
        int layoutId = itemViewDelegate.getItemViewLayoutId();
        ViewHolderListView viewHolderListView = null;
        if (convertView == null) {
            View itemView = LayoutInflater.from(mContext).inflate(layoutId, parent,
                    false);
            viewHolderListView = new ViewHolderListView(mContext, itemView, parent, position);
            viewHolderListView.mLayoutId = layoutId;
            onViewHolderCreated(viewHolderListView, viewHolderListView.getConvertView());
        } else {
            viewHolderListView = (ViewHolderListView) convertView.getTag();
            viewHolderListView.mPosition = position;
        }

        convert(viewHolderListView, getItem(position), position);
        return viewHolderListView.getConvertView();
    }

    protected void convert(ViewHolderListView viewHolderListView, T item, int position) {
        mItemViewDelegateManager.convert(viewHolderListView, item, position);
    }

    public void onViewHolderCreated(ViewHolderListView holder, View itemView) {
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
