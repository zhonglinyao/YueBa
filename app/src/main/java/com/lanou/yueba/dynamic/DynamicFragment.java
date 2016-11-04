package com.lanou.yueba.dynamic;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseFragment;
import com.lanou.yueba.dynamic.dynamic.DynamicActivity;
import com.lanou.yueba.dynamic.live.LiveListActivity;
import com.lanou.yueba.dynamic.news.NewsActivity;
import com.lanou.yueba.dynamic.video.VideoActivity;

/**
 * Created by dllo on 16/10/20.
 */

public class DynamicFragment extends BaseFragment implements OnClickListener {

    private RelativeLayout mRlVideo;
    private RelativeLayout mRlNews;
    private LinearLayout mLlLive;
    private LinearLayout mLlDynamic;

    @Override
    protected int setLayout() {
        return R.layout.fragment_dynamic;
    }

    @Override
    protected void initView() {
        mRlVideo = bindView(R.id.rl_video_dynamic);
        mRlNews = bindView(R.id.rl_news_dynamic);
        mLlLive = bindView(R.id.ll_live_dynamic);
        mLlDynamic = bindView(R.id.ll_dynamic_dynamic);
    }

    @Override
    protected void initData() {
        initClickListener();
    }

    public void initClickListener(){
        mRlVideo.setOnClickListener(this);
        mRlNews.setOnClickListener(this);
        mLlLive.setOnClickListener(this);
        mLlDynamic.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_video_dynamic:
                startActivity(new Intent(mContext, VideoActivity.class));
                break;
            case R.id.rl_news_dynamic:
                startActivity(new Intent(mContext, NewsActivity.class));
                break;
            case R.id.ll_live_dynamic:
                startActivity(new Intent(mContext, LiveListActivity.class));
                break;
            case R.id.ll_dynamic_dynamic:
                startActivity(new Intent(mContext, DynamicActivity.class));
                break;
        }
    }
}
