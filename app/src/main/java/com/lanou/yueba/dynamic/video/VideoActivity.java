package com.lanou.yueba.dynamic.video;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;
import com.lanou.yueba.base.lv.ListViewCommonAdapter;
import com.lanou.yueba.base.rv.CommonRecyclerAdapter;
import com.lanou.yueba.base.rv.DividerItemDecoration;
import com.lanou.yueba.base.rv.ViewHolder;
import com.lanou.yueba.bean.NewsBean;
import com.lanou.yueba.bean.VideoBean;
import com.lanou.yueba.presenter.NewsPresenter;
import com.lanou.yueba.ui.NewsView;
import com.lanou.yueba.vlaues.UrlValues;

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
public class VideoActivity extends BaseActivity implements NewsView<VideoBean> {
    private ViewStub mViewStub;
    private ImageView mImageView;
    private AnimationDrawable mDrawable;
    private RecyclerView mRv;
    private List<VideoBean> mVideoBeen = new ArrayList<>();
    private NewsPresenter<VideoBean> mPresenter;

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
        mPresenter = new NewsPresenter<>(this);
        Type type = new TypeToken<List<VideoBean>>() {}.getType();
        mPresenter.startTypeGetRequest(UrlValues.VIDEO, type);


//        mRVVideo.setAdapter
//                (mAdapter = new ListViewCommonAdapter<VideoBean>
//                        (getApplicationContext(), R.layout.item_video, mVideoBeen) {
//            @Override
//            protected void convert(ViewHolderListView viewHolder, VideoBean item, int position) {
//                RelativeLayout relativeLayout = viewHolder.getView(R.id.item_rl_video);
//                if (relativeLayout != null){
//                    RelativeLayout.LayoutParams layoutParams = (LayoutParams) relativeLayout.getLayoutParams();
//                    layoutParams.height = (int) (SuperPlayerUtils.getScreenWidth((Activity) mContext) * 0.5652f);
//                    relativeLayout.setLayoutParams(layoutParams);
//                }
//                Glide.with(VideoActivity.this).load(item.getAvatar()).into((ImageView) viewHolder.getView(R.id.item_avatar_video));
//                viewHolder.setText(R.id.item_channelName_video, item.getChannelName());
//                viewHolder.setMax(R.id.item_playCount_video, item.getPlayCount());
//            }
//        });

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
