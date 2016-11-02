package com.lanou.yueba.dynamic.live;

import android.util.Log;

import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;
import com.lanou.yueba.tools.ActivityTools;
import com.lanou.yueba.vlaues.StringVlaues;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by dllo on 16/11/1.
 */

public class LiveActivity extends BaseActivity{

    private VideoView mVideoView;

    @Override
    protected int setLayout() {
        return R.layout.activity_live;
    }

    @Override
    protected void initView() {
        mVideoView = bindView(R.id.vv_live);
    }

    @Override
    protected void initData() {
        Vitamio.isInitialized(getApplicationContext());
        String url = getIntent().getStringExtra(StringVlaues.LIVEURL);
        Log.d("LiveActivity", url);
//        String str = "http://flvdl75220c16.live.126.net/live/98cab3a2d96f4fb8ad80a50d19a8f63b.flv?netease=flvdl75220c16.live.126.net";
        mVideoView.setVideoPath(url);
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
        mVideoView.requestFocus();

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setPlaybackSpeed(1.0f);
            }
        });
    }
    @Override
    public void onBackPressed() {
        ActivityTools.deleteActivity(this.getClass().getSimpleName());
    }
}
