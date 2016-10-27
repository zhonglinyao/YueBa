package com.lanou.yueba.dynamic.video;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.reflect.TypeToken;
import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;
import com.lanou.yueba.base.rv.CommonRecyclerAdapter;
import com.lanou.yueba.base.rv.DividerItemDecoration;
import com.lanou.yueba.base.rv.MultiItemTypeRecyclerAdapter.OnItemClickListener;
import com.lanou.yueba.base.rv.ViewHolder;
import com.lanou.yueba.bean.VideoBean;
import com.lanou.yueba.presenter.AppPresenter;
import com.lanou.yueba.ui.AppView;
import com.lanou.yueba.vlaues.UrlValues;
import com.superplayer.library.SuperPlayer;

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
    private SuperPlayer player;
    private AppPresenter<VideoBean> mPresenter;
    private CommonRecyclerAdapter<VideoBean> mAdapter;
    private List<String> mList = new ArrayList<>();

    @Override
    protected int setLayout() {
        return R.layout.activity_video;
    }

    @Override
    protected void initView() {
        mViewStub = bindView(R.id.vs_video);
        mImageView = bindView(R.id.iv_loading_video);
    }

    @Override
    protected void initData() {
        mPresenter = new AppPresenter<>(this);
        Type type = new TypeToken<List<VideoBean>>(){}.getType();
        mPresenter.<VideoBean>startTypeGetRequset(UrlValues.VIDEO, type);





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
    }

    @Override
    public void onResponse(VideoBean videoBean) {


    }

    @Override
    public void onListResponse(List<VideoBean> list) {
        Log.d("VideoActivity", "8888888"+list.get(0).toString());
        mRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRv.setLayoutManager(manager);
        mAdapter = new CommonRecyclerAdapter<VideoBean>(VideoActivity.this, R.layout.layout_video, list) {
            @Override
            protected void convert(ViewHolder holder, VideoBean videoBean, int position) {
                Glide.with(VideoActivity.this).load(videoBean.getAvatar()).into((ImageView) holder.getView(R.id.iv_layout_avatar_video));
                holder.setText(R.id.tv_layout_channelName_video, videoBean.getChannelName());
                holder.setText(R.id.tv_layout_playCount_video, String.valueOf(videoBean.getPlayCount())+"次播放");
                holder.setText(R.id.tv_layout_title_video, videoBean.getTitle());
//                holder.setText(R.id.tv_layout_uploadTime_video, String.valueOf(videoBean.getUploadTime()));
                mList.add(videoBean.getLinkMp4());
                Log.d("VideoActivity", "aaa");
            }

        };





        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Log.d("VideoActivity", "position:" + position);
                view.setSystemUiVisibility(View.GONE);




            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        mRv.setAdapter(mAdapter);
    }

    @Override
    public void onError() {

    }
}
