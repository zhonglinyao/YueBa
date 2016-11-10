package com.lanou.yueba.dynamic.live;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;
import com.lanou.yueba.tools.ActivityTools;
import com.lanou.yueba.widget.LiveMediaController;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by dllo on 16/11/1.
 */

public class LiveActivity extends BaseActivity implements Runnable{

    public static final String LIVE_URL = "liveurl";
    private VideoView mVideoView;
    private static final int TIME = 0;
    private static LiveMediaController mLiveMediaController;
    private Handler mHandler;
    private ImageView mIvPrepare;

    @Override
    protected int setLayout() {
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //获得当前窗体对象
        Window window = LiveActivity.this.getWindow();
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        return R.layout.activity_live;
    }

    @Override
    protected void initView() {
        mVideoView = bindView(R.id.vv_live);
        mIvPrepare = bindView(R.id.iv_prepare_live);
    }

    @Override
    protected void initData() {
        final AnimationDrawable drawable = (AnimationDrawable) mIvPrepare.getBackground();
        drawable.start();
        mHandler = new LiveHandler(this);
        String url = getIntent().getStringExtra(LIVE_URL);
        Log.d("LiveActivity", url);
        String str = "http://live-play.acgvideo.com/live/716/live_6810019_9448733.flv?wsSecret=7c41e262be5f70706740c98cd1513b19&wsTime=57f89d6f";
        mVideoView.setVideoPath(url);
        mLiveMediaController = new LiveMediaController(this, mVideoView, this);
        mLiveMediaController.setFileName("aaaaaa");
        mVideoView.setMediaController(mLiveMediaController);
        mVideoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);//高画质
        mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_STRETCH, 1.0f);
        mVideoView.requestFocus();

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setPlaybackSpeed(1.0f);
                mp.start();
                drawable.stop();
                mIvPrepare.setVisibility(View.GONE);
            }
        });

        mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Toast.makeText(LiveActivity.this, "直播错误", Toast.LENGTH_SHORT).show();
                ActivityTools.deleteActivity(LiveActivity.class.getSimpleName());
                return true;
            }
        });
        new Thread(this).start();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        if (mVideoView != null){
            mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        ActivityTools.deleteActivity(this.getClass().getSimpleName());
    }

    @Override
    public void run() {
        while (true) {
            //时间读取线程
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String str = sdf.format(new Date());
            Message msg = new Message();
            msg.obj = str;
            msg.what = TIME;
            mHandler.sendMessage(msg);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class LiveHandler extends Handler{
        private WeakReference<Activity> wk;
        public LiveHandler(Activity activity) {
            wk = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (wk != null && TIME == msg.what) {
                mLiveMediaController.setTime(msg.obj.toString());
            }
        }
    }
}
