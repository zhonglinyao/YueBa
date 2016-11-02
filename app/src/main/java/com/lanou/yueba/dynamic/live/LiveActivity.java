package com.lanou.yueba.dynamic.live;

import android.content.res.Configuration;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;
import com.lanou.yueba.customview.LiveMediaController;
import com.lanou.yueba.tools.ActivityTools;
import com.lanou.yueba.vlaues.StringVlaues;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by dllo on 16/11/1.
 */

public class LiveActivity extends BaseActivity implements Runnable{

    private VideoView mVideoView;
    private static final int TIME = 0;
    private LiveMediaController mLiveMediaController;
    private MediaController mMediaController;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (TIME == msg.what) {
                mLiveMediaController.setTime(msg.obj.toString());
            }
        }

    };


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
    }

    @Override
    protected void initData() {
        String url = getIntent().getStringExtra(StringVlaues.LIVEURL);
        Log.d("LiveActivity", url);
//        String str = "http://flvdl75220c16.live.126.net/live/98cab3a2d96f4fb8ad80a50d19a8f63b.flv?netease=flvdl75220c16.live.126.net";
        mVideoView.setVideoPath(url);
        mMediaController = new MediaController(this);
        mLiveMediaController = new LiveMediaController(this, mVideoView, this);
        mLiveMediaController.setFileName("aaaaaa");
        mVideoView.setMediaController(mLiveMediaController);
        mVideoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);//高画质
        mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_STRETCH, 1.0f);
//        mMediaController.show(5000);
        mVideoView.requestFocus();

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setPlaybackSpeed(1.0f);
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
}
