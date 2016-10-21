package com.lanou.yueba.bean;

import com.lanou.yueba.R;
import com.lanou.yueba.base.rv.BaseRecyclerViewHolder;
import com.lanou.yueba.base.rv.ItemViewDelegate;

/**
 * Created by dllo on 16/10/21.
 */

public class FromBean implements ItemViewDelegate<TestBean> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.test_from;
    }

    @Override
    public boolean isForViewType(TestBean item, int position) {
        return true;
    }

    @Override
    public void convert(BaseRecyclerViewHolder holder, TestBean testBean, int position) {
        holder.setText(R.id.tv_from, testBean.getString());
    }
}
