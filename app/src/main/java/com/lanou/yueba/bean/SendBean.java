package com.lanou.yueba.bean;

import android.util.Log;

import com.lanou.yueba.R;
import com.lanou.yueba.base.rv.BaseRecyclerViewHolder;
import com.lanou.yueba.base.rv.ItemViewDelegate;

/**
 * Created by dllo on 16/10/21.
 */

public class SendBean implements ItemViewDelegate<TestBean> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.test_send;
    }

    @Override
    public boolean isForViewType(TestBean item, int position) {
        return true;
    }

    @Override
    public void convert(BaseRecyclerViewHolder holder, TestBean testBean, int position) {
        Log.d("SendBean", testBean.getString());
        holder.setText(R.id.tv_send, testBean.getString());
    }
}
