package com.lanou.yueba.base;

import android.content.Context;

import java.util.List;

/**
 * Created by dllo on 16/10/20.
 */

public class TestAdapter<T> extends BaseRecyclerAdapter<T> {

    List<T> mData;

    public TestAdapter(Context context, int mLayoutId, List<T> data) {
        super(context, mLayoutId, data);
        mData = data;
    }

    @Override
    public void convert(BaseRecyclerViewHolder holder, T t) {
        
    }


}
