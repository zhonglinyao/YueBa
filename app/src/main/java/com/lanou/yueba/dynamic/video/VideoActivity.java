package com.lanou.yueba.dynamic.video;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnChildAttachStateChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.reflect.TypeToken;
import com.lanou.yueba.R;
import com.lanou.yueba.base.activity.BaseActivity;
import com.lanou.yueba.base.recyclerview.CommonRecyclerAdapter;
import com.lanou.yueba.base.recyclerview.DividerItemDecoration;
import com.lanou.yueba.base.recyclerview.MultiItemTypeRecyclerAdapter.OnItemClickListener;
import com.lanou.yueba.base.recyclerview.ViewHolder;
import com.lanou.yueba.bean.VideoBean;
import com.lanou.yueba.base.presenter.AppPresenter;
import com.lanou.yueba.tools.ActivityTools;
import com.lanou.yueba.base.ui.AppView;
import com.lanou.yueba.values.UrlValues;
import com.superplayer.library.SuperPlayer;
import com.superplayer.library.SuperPlayerManage;
import com.superplayer.library.mediaplayer.IjkVideoView;
import com.superplayer.library.utils.SuperPlayerUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 　　　　　　　　┏┓　　　┏┓+ +
 * 　　　　　　　┏┛┻━━━┛┻┓ + +
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃ ++ + + +
 * 　　　　　　 ████━████ ┃+
 * 　　　　　　　┃　　　　　　　┃ +
 * 　　　　　　　┃　　　┻　　　┃
 * 　　　　　　　┃　　　　　　　┃ + +
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃ + + + +
 * 　　　　　　　　　┃　　　┃　　　　Code is far away from bug with the animal protecting
 * 　　　　　　　　　┃　　　┃ + 　　　　神兽保佑,代码无bug
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃　　+
 * 　　　　　　　　　┃　 　　┗━━━┓ + +
 * 　　　　　　　　　┃ 　　　　　　　┣┓
 * 　　　　　　　　　┃ 　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛+ + + +
 * <p/>
 * Created by 程洪运 on 16/10/24.
 */
public class VideoActivity extends BaseActivity implements AppView<VideoBean> {
    private ViewStub mViewStub;
    private ImageView mImageView;
    private AnimationDrawable mDrawable;
    private RecyclerView mRv;
    private AppPresenter mPresenter;
    private SuperPlayer player;
    private int position = -1;
    private int lastPosition = -1;
    private CommonRecyclerAdapter<VideoBean> mAdapter;
    private List<VideoBean> mList = new ArrayList<>();
    private RelativeLayout mRlVideo;
    private LinearLayoutManager mManager;
    private LinearLayout mLl;


    @Override
    protected int setLayout() {
        return R.layout.activity_video;
    }

    @Override
    protected void initView() {
        mViewStub = bindView(R.id.vs_video);
        mImageView = bindView(R.id.iv_loading_video);
        mLl = bindView(R.id.ll_back_add_video);
    }

    @Override
    protected void initData() {
        mPresenter = new AppPresenter(this);
        mLl.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityTools.deleteActivity(VideoActivity.this.getClass().getSimpleName());
            }
        });
        Type type = new TypeToken<List<VideoBean>>() {}.getType();
        mPresenter.startTypeGetRequset(UrlValues.VIDEO, type);
        initPlayer();

    }

    /**
     * 初始化播放器
     */
    private void initPlayer() {
        player = SuperPlayerManage.getSuperManage().initialize(this);
        player.setShowTopControl(false).setSupportGesture(false);
    }


    @Override
    public void showQuestView() {
        mDrawable = (AnimationDrawable) mImageView.getBackground();
        mDrawable.start();
    }

    @Override
    public void showDataView() {
        mImageView.setVisibility(View.GONE);
        View view = mViewStub.inflate();
        mRv = bindView(R.id.rv_layout, view);
        mRlVideo = bindView(R.id.rl_video);
    }

    @Override
    public void onResponse(VideoBean videoBean) {

    }

    @Override
    public void onListResponse(final List<VideoBean> list) {
        Log.d("VideoActivity", "8888888" + list.get(0).toString());
        mRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mManager = new LinearLayoutManager(this);
        mManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRv.setLayoutManager(mManager);
        mAdapter = new CommonRecyclerAdapter<VideoBean>(VideoActivity.this, R.layout.layout_video, list) {
            @Override
            protected void convert(ViewHolder holder, VideoBean videoBean, int position) {
                Glide.with(VideoActivity.this).load(videoBean.getAvatar()).into((ImageView) holder.getView(R.id.iv_layout_avatar_video));
                holder.setText(R.id.tv_layout_channelName_video, videoBean.getChannelName());
                holder.setText(R.id.tv_layout_playCount_video, String.valueOf(videoBean.getPlayCount()) + "次播放");
                holder.setText(R.id.tv_layout_title_video, videoBean.getTitle());
                Glide.with(VideoActivity.this).load(videoBean.getCover()).into((ImageView) holder.getView(R.id.iv_layout_cover_video));
//                holder.setText(R.id.tv_layout_uploadTime_video, String.valueOf(videoBean.getUploadTime()));
                videoBean.setLinkMp4(videoBean.getLinkMp4());
                mList.add(videoBean);


                RelativeLayout rlayPlayer = (RelativeLayout) findViewById(R.id.rl_layout_video);
                if (rlayPlayer!=null){
                    LayoutParams layoutParams =  rlayPlayer.getLayoutParams();
                    layoutParams.height = (int) (SuperPlayerUtils.getScreenWidth((Activity) mContext) * 0.5652f);
                    rlayPlayer.setLayoutParams(layoutParams);
                }

                Log.d("VideoActivity", "aaa");
            }

        };
        mRv.setAdapter(mAdapter);
        initAdapter();
    }

    private void initAdapter() {
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Log.d("VideoActivity", "position:" + position);
                view.findViewById(R.id.rl_layout_player_control_video).setVisibility(View.GONE);
                if (player.isPlaying() && lastPosition == position){
                    return;
                }

                VideoActivity.this.position = position;
                if (player.getVideoStatus() == IjkVideoView.STATE_PAUSED) {
                    if (position != lastPosition) {
                        player.stopPlayVideo();
                        player.release();
                    }
                }
                if (lastPosition != -1) {
                    player.showView(R.id.rl_layout_player_control_video);
                }

                FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.fl_layout_super_video);
                frameLayout.removeAllViews();
                player.showView(R.id.rl_layout_player_control_video);
                frameLayout.addView(player);
                player.play(mList.get(position).getLinkMp4());
                Toast.makeText(VideoActivity.this, "position:"+position, Toast.LENGTH_SHORT).show();
                lastPosition = position;

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });


        /**
         * 播放完设置还原播放界面
         */
        player.onComplete(new Runnable() {
            @Override
            public void run() {
                ViewGroup last = (ViewGroup) player.getParent();
                if (last != null && last.getChildCount() > 0) {
                    last.removeAllViews();
                    View itemView = (View) last.getParent();
                    if (itemView != null) {
                        itemView.findViewById(R.id.rl_layout_player_control_video).setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        /***
         * 监听列表的下拉滑动
         */
        mRv.addOnChildAttachStateChangeListener(new OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
                int index = mRv.getChildAdapterPosition(view);
                View controlView = view.findViewById(R.id.rl_layout_player_control_video);
                if (controlView == null) {
                    return;
                }
                view.findViewById(R.id.rl_layout_player_control_video).setVisibility(View.VISIBLE);
                if (index == position) {
                    FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.fl_layout_super_video);
                    frameLayout.removeAllViews();
                    if (player != null &&
                            ((player.isPlaying()) || player.getVideoStatus() == IjkVideoView.STATE_PAUSED)) {
                        view.findViewById(R.id.rl_layout_player_control_video).setVisibility(View.GONE);
                    }
                    if (player.getVideoStatus() == IjkVideoView.STATE_PAUSED) {
                        if (player.getParent() != null)
                            ((ViewGroup) player.getParent()).removeAllViews();
                        frameLayout.addView(player);
                        return;
                    }
                }
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                int index = mRv.getChildAdapterPosition(view);
                if ((index) == position) {
                    if (true) {
                        if (player != null) {
                            player.stop();
                            player.release();
                            player.showView(R.id.rl_layout_player_control_video);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (player != null) {
            player.onConfigurationChanged(newConfig);
            if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
                mRlVideo.setVisibility(View.GONE);
                mRlVideo.removeAllViews();
                mRv.setVisibility(View.VISIBLE);
                if (position <= mManager.findLastVisibleItemPosition()
                        && position >= mManager.findFirstVisibleItemPosition()) {
                    View view = mRv.findViewHolderForAdapterPosition(position).itemView;
                    FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.fl_layout_super_video);
                    frameLayout.removeAllViews();
                    ViewGroup last = (ViewGroup) player.getParent();//找到videoitemview的父类，然后remove
                    if (last != null) {
                        last.removeAllViews();
                    }
                    frameLayout.addView(player);
                }
                int mShowFlags =
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                mRlVideo.setSystemUiVisibility(mShowFlags);
            } else {
                ViewGroup viewGroup = (ViewGroup) player.getParent();
                if (viewGroup == null)
                    return;
                viewGroup.removeAllViews();
                mRlVideo.addView(player);
                mRlVideo.setVisibility(View.VISIBLE);
                int mHideFlags =
                        View.SYSTEM_UI_FLAG_LOW_PROFILE
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        ;
                mRlVideo.setSystemUiVisibility(mHideFlags);
            }
        } else {
            mRlVideo.setVisibility(View.GONE);
        }
    }



    @Override
    public void onError() {

    }


    @Override
    protected void onPause() {
        super.onPause();
        if (player != null) {
            player.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (player != null) {
            player.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.onDestroy();
        }
    }

    @Override
    public void onBackPressed() {
        if (player != null && player.onBackPressed()) {
            return;
        } else {
            ActivityTools.deleteActivity(this.getClass().getSimpleName());
        }
        super.onBackPressed();
    }
}
