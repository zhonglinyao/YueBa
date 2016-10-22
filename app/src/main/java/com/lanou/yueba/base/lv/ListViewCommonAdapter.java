package com.lanou.yueba.base.lv;

import android.content.Context;

import java.util.List;

public abstract class ListViewCommonAdapter<T> extends MultiItemTypeListViewAdapter<T> {

    public ListViewCommonAdapter(Context context, final int layoutId, List<T> datas) {
        super(context, datas);

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
            public void convert(ViewHolderListView holder, T t, int position) {
                ListViewCommonAdapter.this.convert(holder, t, position);
            }
        });
    }

    protected abstract void convert(ViewHolderListView viewHolder, T item, int position);

}
