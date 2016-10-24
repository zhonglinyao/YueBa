package com.lanou.yueba.dynamic.video;

import android.support.v7.widget.RecyclerView;

import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseFragment;

/**
 * Created by dllo on 16/10/20.
 */

public class VideoFragment extends BaseFragment{
    private RecyclerView mRecyclerView;
    @Override
    protected int setLayout() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initView() {
        mRecyclerView = bindView(R.id.rv_video);
    }

    @Override
    protected void initData() {
        VideoAdapter adapter = new VideoAdapter();
    }
}
