package com.lanou.yueba.dynamic.video;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;

import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;
import com.lanou.yueba.base.rv.CommonRecyclerAdapter;
import com.lanou.yueba.base.rv.ViewHolder;
import com.lanou.yueba.bean.VideoBean;
import com.lanou.yueba.presenter.AppPresenter;
import com.lanou.yueba.ui.AppView;

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
    private AppPresenter<VideoBean> mPresenter;

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
        mRv.setAdapter(new CommonRecyclerAdapter<VideoBean>(VideoActivity.this, R.layout.layout_video, list) {
            @Override
            protected void convert(ViewHolder holder, VideoBean videoBean, int position) {
                holder.setText(R.id.tv_layout_channelName_video, videoBean.getChannelName());
            }
        });
    }

    @Override
    public void onError() {

    }
}
