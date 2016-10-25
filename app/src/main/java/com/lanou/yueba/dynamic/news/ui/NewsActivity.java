package com.lanou.yueba.dynamic.news.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;
import com.lanou.yueba.base.rv.CommonRecyclerAdapter;
import com.lanou.yueba.base.rv.DividerItemDecoration;
import com.lanou.yueba.base.rv.ViewHolder;
import com.lanou.yueba.bean.NewsBean;
import com.lanou.yueba.dynamic.news.presenter.NewsPresenter;
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
public class NewsActivity extends BaseActivity implements NewsView {
    private NewsPresenter mPresenter;
    private RecyclerView mRv;

    @Override
    protected int setLayout() {
        return R.layout.activity_news;
    }

    @Override
    protected void initView() {
        mRv = bindView(R.id.rv_news);
    }

    @Override
    protected void initData() {
        mPresenter = new NewsPresenter(this);
        String str = "2016-10-25";
        mPresenter.startGetRequset(UrlValues.getNEWS(str), NewsBean.class);
        mRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRv.setLayoutManager(manager);
        List<NewsBean> list = new ArrayList<>();
        Type type = new TypeToken<List<NewsBean>>(){}.getType();
        Gson gson = new Gson();
        list = gson.fromJson(str,type);
    }

    @Override
    public void showQuestView() {

    }

    @Override
    public void showDataView() {

    }

    @Override
    public void onResponse(NewsBean newsBean) {
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
    public void onError() {

    }
}
