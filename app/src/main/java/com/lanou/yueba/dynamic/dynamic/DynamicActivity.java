package com.lanou.yueba.dynamic.dynamic;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;
import com.lanou.yueba.base.rv.CommonRecyclerAdapter;
import com.lanou.yueba.base.rv.ViewHolder;
import com.lanou.yueba.bean.DynamicBean;
import com.lanou.yueba.bean.FriendBean;
import com.lanou.yueba.bean.UserInfoBean;
import com.lanou.yueba.tools.ActivityTools;
import com.lanou.yueba.vlaues.StringVlaues;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by dllo on 16/11/4.
 */

public class DynamicActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTvBack;
    private RecyclerView mRv;
    private AppBarLayout mAppBarLayout;
    private RelativeLayout mRelativeLayoutBar;
    private ImageView mIvPublish;
    private UserInfoBean mUserInfoBean;
    private String TAG = "DynamicActivity";
    private List<DynamicBean> mDynamicBeanList;
    private CommonRecyclerAdapter<DynamicBean> mAdapter;
    private ImageView mImageView;

    @Override
    protected int setLayout() {
        return R.layout.activity_dynamic;
    }

    @Override
    protected void initView() {
        mTvBack = bindView(R.id.tv_back_dynamic);
        mRv = bindView(R.id.rv_dynamic_dynamic);
        mAppBarLayout = bindView(R.id.abl_dynamic);
        mRelativeLayoutBar = bindView(R.id.rl_dynamic_dynamic);
        mIvPublish = bindView(R.id.iv_publish_dynamic);
        mImageView = bindView(R.id.civ_dynamic);
    }

    @Override
    protected void initData() {
        mUserInfoBean = (UserInfoBean) getIntent().getSerializableExtra(StringVlaues.DYNAMIC);
        Glide.with(this).load(mUserInfoBean.getPicUrl()).into(mImageView);
        mTvBack.setOnClickListener(this);
        mIvPublish.setImageResource(R.mipmap.publish_dynamic_no_slide);
        mIvPublish.setOnClickListener(this);
        final int height = getWindowManager().getDefaultDisplay().getHeight();
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset < -(height / 10)) {
                    mRelativeLayoutBar.setBackgroundColor(getResources().getColor(R.color.colorMain00));
                    mIvPublish.setImageResource(R.mipmap.publish_dynamic_no_slide);
                }
                if (verticalOffset < -(height / 8)) {
                    mRelativeLayoutBar.setBackgroundColor(getResources().getColor(R.color.colorMain01));
                }
                if (verticalOffset < -(height / 6)) {
                    mRelativeLayoutBar.setBackgroundColor(getResources().getColor(R.color.colorMain02));
                }
                if (verticalOffset < -(height / 4)) {
                    mRelativeLayoutBar.setBackgroundColor(getResources().getColor(R.color.colorMain));
                    mIvPublish.setImageResource(R.mipmap.publish_dynamic_slide);
                }
            }
        });
        queryFriend();
    }

    public void queryFriend() {
        final BmobQuery<FriendBean> query = new BmobQuery<>("FriendBean");
        query.addWhereEqualTo("username", mUserInfoBean.getUserName());
        query.addWhereEqualTo("isFriend", true);
        query.findObjects(new FindListener<FriendBean>() {
            @Override
            public void done(List<FriendBean> list, BmobException e) {
                if (e == null) {
                    Log.d(TAG, "done: 好友");
                    Log.d("DynamicActivity", "list.size():" + list.size());
                    if (list != null && list.size() > 0)
                        queryDynamic(list);
                    else queryDynamic(new ArrayList<FriendBean>());
                } else {
                    queryDynamic(new ArrayList<FriendBean>());
                }
            }
        });
    }

    public void queryDynamic(List<FriendBean> list) {
        BmobQuery<DynamicBean> query = new BmobQuery<>("DynamicBean");
        query.addWhereEqualTo("userName", mUserInfoBean.getUserName());
        if (list.size() > 0) {
            for (FriendBean friendBean : list) {
                query.addWhereEqualTo("userName", friendBean.getFriendname());
            }
        }
        query.order("createdAt");
        query.findObjects(new FindListener<DynamicBean>() {
            @Override
            public void done(List<DynamicBean> list, BmobException e) {
                Log.d(TAG, "done: 动态");
                if (e == null && list != null && list.size() > 0) {
                    Log.d(TAG, "done: 有数据");
                    mDynamicBeanList = list;
                    updateView();
                } else {
                    mDynamicBeanList = new ArrayList<>();
                    updateView();
                }
            }
        });
    }

    public void updateView() {
        mAdapter = new CommonRecyclerAdapter<DynamicBean>(this, R.layout.layout_dynamic_dynamic, mDynamicBeanList) {
            @Override
            protected void convert(ViewHolder holder, DynamicBean dynamicBean, int position) {
                holder
                        .setText(R.id.tv_name_dynamic_item, dynamicBean.getUserName())
                        .setText(R.id.tv_date_dynamic_item, dynamicBean.getCreatedAt())
                        .setImage(R.id.civ_dynamic_item, dynamicBean.getPicUrl(), R.mipmap.image_error);
                String content = dynamicBean.getContent();
                String imgUrl = dynamicBean.getImgUrl();
                if (content != null && content.length() > 0) {
                    holder.setText(R.id.tv_content_dynamic_item, content);
                } else {
                    holder.getView(R.id.tv_content_dynamic_item).setVisibility(View.GONE);
                }
                if (imgUrl != null && imgUrl.length() > 0) {
                    holder.setImage(R.id.iv_img_dynamic_item, imgUrl, R.mipmap.image_error);
                } else {
                    holder.getView(R.id.iv_img_dynamic_item).setVisibility(View.GONE);
                }
            }
        };
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRv.setLayoutManager(manager);
        mRv.setAdapter(mAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (101 == requestCode && 102 == resultCode) {
            Log.d(TAG, "onActivityResult: 发表动态成功返回数据");
            DynamicBean dynamicBean = (DynamicBean) data.getSerializableExtra(StringVlaues.PUBLISH);
            if (mDynamicBeanList == null) {
                mDynamicBeanList = new ArrayList<>();
            }
            mDynamicBeanList.add(0, dynamicBean);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {
        ActivityTools.deleteActivity(this.getClass().getSimpleName());
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back_dynamic:
                ActivityTools.deleteActivity(DynamicActivity.this.getClass().getSimpleName());
                break;
            case R.id.iv_publish_dynamic:
                Intent intent = new Intent(DynamicActivity.this, PublishActivity.class);
                intent.putExtra(StringVlaues.PUBLISH, mUserInfoBean);
                startActivityForResult(intent, 101);
                break;
        }
    }
}
