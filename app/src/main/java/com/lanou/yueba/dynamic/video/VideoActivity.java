package com.lanou.yueba.dynamic.video;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;
import com.lanou.yueba.base.lv.ListViewCommonAdapter;
import com.lanou.yueba.base.rv.CommonRecyclerAdapter;
import com.lanou.yueba.base.rv.ViewHolder;
import com.lanou.yueba.presenter.NewsPresenter;
import com.lanou.yueba.ui.NewsView;

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
    private RecyclerView mRVVideo;
    private ListViewCommonAdapter<VideoBean> mAdapter;
    private List<VideoBean> mVideoBeen = new ArrayList<>();
    private NewsPresenter<VideoBean> mPresenter;

    @Override
    protected int setLayout() {
        return R.layout.activity_video;
    }

    @Override
    protected void initView() {
//        mRVVideo = bindView(R.id.rv_video);
    }

    @Override
    protected void initData() {
        mPresenter = new NewsPresenter<>(this);
    }

    @Override
    public void showQuestView() {

    }

    @Override
    public void showDataView() {

    }

    @Override
    public void onResponse(VideoBean videoBean) {

    }

    @Override
    public void onListResponse(List<VideoBean> list) {
        Log.d("VideoActivity", list.get(0).toString());
        mRVVideo.setAdapter(new CommonRecyclerAdapter<VideoBean>(VideoActivity.this, R.layout.layout_video, list) {

            @Override
            protected void convert(ViewHolder holder, VideoBean videoBean, int position) {

            }
        });

    }

    @Override
    public void onError() {

    }
}
