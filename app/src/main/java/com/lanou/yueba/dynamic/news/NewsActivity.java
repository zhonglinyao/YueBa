package com.lanou.yueba.dynamic.news;

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
import com.lanou.yueba.base.rv.MultiItemTypeRecyclerAdapter;
import com.lanou.yueba.base.rv.ViewHolder;
import com.lanou.yueba.bean.NewsBean;
import com.lanou.yueba.httprequset.OkHttpImpl;
import com.lanou.yueba.presenter.AppPresenter;
import com.lanou.yueba.tools.ActivityTools;
import com.lanou.yueba.vlaues.UrlValues;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
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

    @Override
    protected int setLayout() {
        return R.layout.activity_news;
    }

    @Override
    protected void initView() {
        mViewStub = bindView(R.id.vs_news);
        mImageView = bindView(R.id.iv_loading_news);
    }

    @Override
    protected void initData() {
        OkHttpImpl okHttp = new OkHttpImpl();
        mOkHttpClient = okHttp.getOkHttpClient();
        mDrawable = (AnimationDrawable) mImageView.getBackground();
        mDrawable.start();
        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext(UrlValues.NEWS);
                    }
                })
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String string) {
                        Request request = new Request.Builder().url(string).build();
                        String result = null;
                        try {
                            Response execute = mOkHttpClient.newCall(request).execute();
                            result = execute.body().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return result;
                    }
                })
                .map(new Func1<String, NewsBean>() {
                    @Override
                    public NewsBean call(String string) {
                        Gson gson = new Gson();
                        NewsBean newsBean = gson.fromJson(string, NewsBean.class);
                        return newsBean;
                    }
                })
                .subscribeOn(Schedulers.io())
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
                        show(newsBean);
                    }
                });
    }

    public void show(NewsBean newsBean) {
        if (newsBean.getT1348648517839() != null && newsBean.getT1348648517839().size() > 0) {
            mImageView.setVisibility(View.GONE);
            View view = mViewStub.inflate();
            mRv = bindView(R.id.rv_layout, view);
            mRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mRv.setLayoutManager(manager);
            final ArrayList<NewsBean.T1348648517839Bean> list = new ArrayList<>();
            list.addAll(newsBean.getT1348648517839().subList(1, newsBean.getT1348648517839().size() - 1));
            final CommonRecyclerAdapter<NewsBean.T1348648517839Bean> recyclerAdapter = new CommonRecyclerAdapter<NewsBean.T1348648517839Bean>(this,
                    R.layout.layout_news, list) {
                @Override
                protected void convert(ViewHolder holder, NewsBean.T1348648517839Bean itemsBean, int position) {
                    holder.setText(R.id.tv_layout_title_news, itemsBean.getTitle())
                            .setImage(R.id.iv_layout_pic_news, itemsBean.getImgsrc())
                            .setText(R.id.tv_layout_source_news, itemsBean.getSource())
                            .setText(R.id.tv_layout_votecount_news, itemsBean.getReplyCount() + "人跟帖");
                }
            };
            recyclerAdapter.setOnItemClickListener(new MultiItemTypeRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                    if (recyclerAdapter.getDatas().get(position).getUrl_3w() != null) {
                        Log.d("NewsActivity", recyclerAdapter.getDatas().get(position).getUrl_3w());
                    }

                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                    return false;
                }
            });
            mRv.setAdapter(recyclerAdapter);
        }
    }

    @Override
    public void onBackPressed() {
        ActivityTools.deleteActivity(this.getClass().getSimpleName());
    }
}
