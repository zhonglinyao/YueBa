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

import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;
import com.lanou.yueba.base.rv.CommonRecyclerAdapter;
import com.lanou.yueba.base.rv.ViewHolder;
import com.lanou.yueba.bean.UserInfoBean;
import com.lanou.yueba.tools.ActivityTools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/11/4.
 */

public class DynamicActivity extends BaseActivity{

    private TextView mTvBack;
    private RecyclerView mRv;
    private AppBarLayout mAppBarLayout;
    private RelativeLayout mRelativeLayoutBar;
    private ImageView mIvPublish;
    private UserInfoBean mUserInfoBean;
    private String TAG = "DynamicActivity";

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
    }

    @Override
    protected void initData() {
        mUserInfoBean = (UserInfoBean) getIntent().getSerializableExtra("dynamic");
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityTools.deleteActivity(DynamicActivity.this.getClass().getSimpleName());
            }
        });
        mIvPublish.setImageResource(R.mipmap.publish_dynamic_no_slide);
        mIvPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DynamicActivity.this, PublishActivity.class);
                intent.putExtra("push", mUserInfoBean);
                startActivityForResult(intent, 101);
            }
        });
        final int height = getWindowManager().getDefaultDisplay().getHeight();
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset < -(height / 10)){
                    mRelativeLayoutBar.setBackgroundColor(getResources().getColor(R.color.colorMain00));
                    mIvPublish.setImageResource(R.mipmap.publish_dynamic_no_slide);
                }
                if (verticalOffset < -(height / 8)){
                    mRelativeLayoutBar.setBackgroundColor(getResources().getColor(R.color.colorMain01));
                }
                if (verticalOffset < -(height / 6)){
                    mRelativeLayoutBar.setBackgroundColor(getResources().getColor(R.color.colorMain02));
                }
                if (verticalOffset < -(height / 4)){
                    mRelativeLayoutBar.setBackgroundColor(getResources().getColor(R.color.colorMain));
                    mIvPublish.setImageResource(R.mipmap.publish_dynamic_slide );
                }
            }
        });
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            strings.add("aa" + i);
        }
        CommonRecyclerAdapter<String> adapter = new CommonRecyclerAdapter<String>(this, R.layout.layout_dynamic_dynamic, strings) {
            @Override
            protected void convert(ViewHolder holder, String string, int position) {
//                holder.setText(R.id.tv_dynamic_item, string);
            }
        };
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRv.setLayoutManager(manager);
        mRv.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (101 == requestCode && 102 == resultCode){
            Log.d(TAG, "onActivityResult: 发表动态成功返回数据");
        }
    }

    @Override
    public void onBackPressed() {
        ActivityTools.deleteActivity(this.getClass().getSimpleName());
        super.onBackPressed();
    }
}
