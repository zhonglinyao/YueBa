package com.lanou.yueba.dynamic.news;

import android.support.v7.widget.RecyclerView;

import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseFragment;

/**
 * Created by dllo on 16/10/20.
 */

public class NewsFragment extends BaseFragment{
    private RecyclerView mRecyclerView;
    @Override
    protected int setLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView() {
        mRecyclerView = bindView(R.id.rv_news);
    }

    @Override
    protected void initData() {

        NewsAdapter adapter = new NewsAdapter();


    }
}
