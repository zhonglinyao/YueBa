package com.lanou.yueba.dynamic.news;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;

import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;
import com.lanou.yueba.base.rv.CommonRecyclerAdapter;
import com.lanou.yueba.base.rv.DividerItemDecoration;
import com.lanou.yueba.base.rv.ViewHolder;
import com.lanou.yueba.bean.NewsBean;
import com.lanou.yueba.presenter.NewsPresenter;
import com.lanou.yueba.ui.NewsView;
import com.lanou.yueba.vlaues.UrlValues;

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
public class NewsActivity extends BaseActivity implements NewsView<NewsBean> {
    private NewsPresenter mPresenter;
    private RecyclerView mRv;
    private ViewStub mViewStub;
    private ImageView mImageView;
    private AnimationDrawable mDrawable;

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
        mPresenter = new NewsPresenter<NewsBean>(this);
        String str = "2016-10-25";
        mPresenter.startGetRequset(UrlValues.getNEWS(str), NewsBean.class);
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
        mRv = bindView(R.id.rv_news, view);
    }

    @Override
    public void onResponse(NewsBean newsBean) {

        mRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRv.setLayoutManager(manager);
        ArrayList<NewsBean.DataBean.ItemsBean> list = new ArrayList<>();
        if (1 == newsBean.getSuccess()){
            list.addAll(newsBean.getData().getItems());
            mRv.setAdapter(new CommonRecyclerAdapter<NewsBean.DataBean.ItemsBean>(this,
                    R.layout.layout_news, list) {
                @Override
                protected void convert(ViewHolder holder, NewsBean.DataBean.ItemsBean itemsBean, int position) {
                    holder.setText(R.id.tv_item, itemsBean.getAuthor());
                }
            });
        }
    }

    @Override
    public void onListResponse(List<NewsBean> list) {

    }

    @Override
    public void onError() {

    }
}
