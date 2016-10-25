package com.lanou.yueba.dynamic.news;

import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;

import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;
import com.lanou.yueba.bean.NewsBean;
import com.lanou.yueba.presenter.NewsPresenter;
import com.lanou.yueba.ui.NewsView;
import com.lanou.yueba.vlaues.UrlValues;

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
    //    private RecyclerView mRv;
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
        mImageView = bindView(R.id.iv_loading);
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

    }

    @Override
    public void onResponse(NewsBean newsBean) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mImageView.setVisibility(View.GONE);
                        mViewStub.inflate();
                    }
                });
            }
        }).start();


//        Rec view = findViewById(R.id.vs_news);
//        view = findViewById(R.id.rv_news);
//        mRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        mRv.setLayoutManager(manager);
//        ArrayList<NewsBean.DataBean.ItemsBean> list = new ArrayList<>();
//        if (1 == newsBean.getSuccess()){
//            list.addAll(newsBean.getData().getItems());
//            mRv.setAdapter(new CommonRecyclerAdapter<NewsBean.DataBean.ItemsBean>(this,
//                    R.layout.layout_news, list) {
//                @Override
//                protected void convert(ViewHolder holder, NewsBean.DataBean.ItemsBean itemsBean, int position) {
//                    holder.setText(R.id.tv_item, itemsBean.getAuthor());
//                }
//            });
//        }
    }

    @Override
    public void onListResponse(List<NewsBean> list) {

    }

    @Override
    public void onError() {

    }
}
