package com.lanou.yueba.dynamic;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseFragment;
import com.lanou.yueba.dynamic.news.ui.NewsActivity;
import com.lanou.yueba.dynamic.video.VideoActivity;

/**
 * Created by dllo on 16/10/20.
 */

public class DynamicFragment extends BaseFragment implements OnClickListener {

    private RelativeLayout mRlVideo;
    private RelativeLayout mRlNews;

    @Override
    protected int setLayout() {
        return R.layout.fragment_dynamic;
    }

    @Override
    protected void initView() {
        mRlVideo = bindView(R.id.rl_video_dynamic);
        mRlNews = bindView(R.id.rl_news_dynamic);
    }

    @Override
    protected void initData() {
        mRlVideo.setOnClickListener(this);
        mRlNews.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_video_dynamic:
                startActivity(new Intent(getContext(), VideoActivity.class));
                break;
            case R.id.rl_news_dynamic:
                startActivity(new Intent(getContext(), NewsActivity.class));
                break;
        }

    }
}
