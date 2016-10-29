package com.lanou.yueba.dynamic.news;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;
import com.lanou.yueba.base.rv.CommonRecyclerAdapter;
import com.lanou.yueba.base.rv.DividerItemDecoration;
import com.lanou.yueba.base.rv.LoadMoreWrapper;
import com.lanou.yueba.base.rv.MultiItemTypeRecyclerAdapter;
import com.lanou.yueba.base.rv.ViewHolder;
import com.lanou.yueba.bean.NewsBean;
import com.lanou.yueba.httprequset.OkHttpImpl;
import com.lanou.yueba.presenter.AppPresenter;
import com.lanou.yueba.rxjava.RxJavaRequest;
import com.lanou.yueba.tools.ActivityTools;
import com.lanou.yueba.vlaues.UrlValues;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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
    private AppPresenter mPresenter;
    private RecyclerView mRv;
    private ViewStub mViewStub;
    private ImageView mImageView;
    private AnimationDrawable mDrawable;
    private OkHttpClient mOkHttpClient;
    private ImageView mIvBack;
    private LoadMoreWrapper<NewsBean> mLoadMoreWrapper;
    private CommonRecyclerAdapter<NewsBean.T1348648517839Bean> mRecyclerAdapter;
    private ArrayList<NewsBean.T1348648517839Bean> mList;
    private int url = 0;
    private Gson mGson;

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
                ActivityTools.deleteActivity(this.getClass().getSimpleName());
            }
        });

        OkHttpImpl okHttp = new OkHttpImpl();
        mOkHttpClient = okHttp.getOkHttpClient();
        mGson = new Gson();
        mDrawable = (AnimationDrawable) mImageView.getBackground();
        mDrawable.start();
        startRequest();

    }

    public void startRequest() {
        RxJavaRequest
                .rxJavaOkHttpGetBean(mOkHttpClient, UrlValues.getNews(url))
                .map(new Func1<String, NewsBean>() {
                    @Override
                    public NewsBean call(String string) {
                        Log.d("NewsActivity", "2");
                        Log.d("NewsActivity", string);
                        NewsBean newsBean = mGson.fromJson(string, NewsBean.class);
                        Log.d("NewsActivity", newsBean.getT1348648517839().get(0).getTitle().toString());
                        return newsBean;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(NewsBean newsBean) {
                        Log.d("NewsActivity", "走了");
                        if (0 == url) {
                            url += 20;
                            showDataView(newsBean);
                        } else {
                            url += 20;
                            refresh(newsBean);
                        }

                    }
                });
    }

    public void showDataView(NewsBean newsBean) {
        if (newsBean.getT1348648517839() != null && newsBean.getT1348648517839().size() > 0) {
            mImageView.setVisibility(View.GONE);
            View view = mViewStub.inflate();
            mRv = bindView(R.id.rv_layout, view);
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
                    Log.d("NewsActivity", "刷新");
                    startRequest();
                }
            });
            mRecyclerAdapter.setOnItemClickListener(new MultiItemTypeRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                    if (mRecyclerAdapter.getDatas().get(position).getUrl_3w() != null) {
                        Log.d("NewsActivity", mRecyclerAdapter.getDatas().get(position).getUrl_3w());
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
        Log.d("TAG", "refresh: ");
        mList.addAll(newsBean.getT1348648517839().subList(1, newsBean.getT1348648517839().size() - 1));
        mLoadMoreWrapper.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        ActivityTools.deleteActivity(this.getClass().getSimpleName());
    }


}
