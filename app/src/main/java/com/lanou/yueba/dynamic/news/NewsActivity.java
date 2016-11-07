package com.lanou.yueba.dynamic.news;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.Toast;

import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;
import com.lanou.yueba.base.rv.CommonRecyclerAdapter;
import com.lanou.yueba.base.rv.DividerItemDecoration;
import com.lanou.yueba.base.rv.LoadMoreWrapper;
import com.lanou.yueba.base.rv.MultiItemTypeRecyclerAdapter;
import com.lanou.yueba.base.rv.ViewHolder;
import com.lanou.yueba.base.rxjava.RxJavaRequest;
import com.lanou.yueba.bean.NewsBean;
import com.lanou.yueba.tools.ActivityTools;
import com.lanou.yueba.vlaues.UrlValues;

import java.util.ArrayList;

import rx.Observer;

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

public class NewsActivity extends BaseActivity {
    private RecyclerView mRv;
    private ViewStub mViewStub;
    private ImageView mImageView;
    private AnimationDrawable mDrawable;
    private ImageView mIvBack;
    private LoadMoreWrapper<NewsBean> mLoadMoreWrapper;
    private CommonRecyclerAdapter<NewsBean.T1348648517839Bean> mRecyclerAdapter;
    private ArrayList<NewsBean.T1348648517839Bean> mList;
    private int url = 0;

    @Override
    protected int setLayout() {
        return R.layout.activity_news;
    }

    @Override
    protected void initView() {
        mViewStub = bindView(R.id.vs_news);
        mImageView = bindView(R.id.iv_loading_news);
        mIvBack = bindView(R.id.iv_back_news);
    }

    @Override
    protected void initData() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityTools.deleteActivity(NewsActivity.this.getClass().getSimpleName());
            }
        });
        mDrawable = (AnimationDrawable) mImageView.getBackground();
        mDrawable.start();
        startRequest();
    }

    public void startRequest() {
        RxJavaRequest
                .<NewsBean>rxJavaOkHttpGetBean(UrlValues.getNews(url), NewsBean.class)
                .subscribe(new Observer<NewsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(NewsBean newsBean) {
                        if (0 == url) {
                            showDataView(newsBean);
                        } else {
                            refresh(newsBean);
                        }
                    }
                });
    }

    public void showDataView(NewsBean newsBean) {
        if (newsBean.getT1348648517839() != null && newsBean.getT1348648517839().size() > 0) {
            url += 20;
            mImageView.setVisibility(View.GONE);
            View view = mViewStub.inflate();
            mRv = bindView(R.id.rv_layout_news, view);
            mRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mRv.setLayoutManager(manager);
            mList = new ArrayList<>();
            mList.addAll(newsBean.getT1348648517839().subList(1, newsBean.getT1348648517839().size() - 1));
            mRecyclerAdapter = new CommonRecyclerAdapter<NewsBean.T1348648517839Bean>(this,
                    R.layout.layout_news, mList) {
                @Override
                protected void convert(ViewHolder holder, NewsBean.T1348648517839Bean itemsBean, int position) {
                    holder.setText(R.id.tv_layout_title_news, itemsBean.getTitle())
                            .setImage(R.id.iv_layout_pic_news, itemsBean.getImgsrc())
                            .setText(R.id.tv_layout_source_news, itemsBean.getSource())
                            .setText(R.id.tv_layout_votecount_news, itemsBean.getReplyCount() + "人跟帖");
                }
            };
            mLoadMoreWrapper = new LoadMoreWrapper<>(mRecyclerAdapter);
            mLoadMoreWrapper.setLoadMoreView(R.layout.layout_loading);
            mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    startRequest();
                }
            });
            mRecyclerAdapter.setOnItemClickListener(new MultiItemTypeRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                    if (mRecyclerAdapter.getDatas().get(position).getUrl_3w() != null) {
                        Intent intent = new Intent(NewsActivity.this, NewsInfoActivity.class);
                        intent.putExtra("newsUrl", mRecyclerAdapter.getDatas().get(position).getUrl_3w());
                        startActivity(intent);
                    }
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                    return false;
                }
            });
            mRv.setAdapter(mLoadMoreWrapper);
        }
    }

    private void refresh(NewsBean newsBean) {
        if (newsBean.getT1348648517839() != null && newsBean.getT1348648517839().size() > 0) {
            mList.addAll(newsBean.getT1348648517839().subList(1, newsBean.getT1348648517839().size() - 1));
            mLoadMoreWrapper.notifyDataSetChanged();
            url += 20;
        } else {
            mLoadMoreWrapper.setLoadMoreView(0);
            mLoadMoreWrapper.notifyDataSetChanged();
            Toast.makeText(this, "没有更多", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        ActivityTools.deleteActivity(this.getClass().getSimpleName());
    }
}
