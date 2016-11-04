package com.lanou.yueba.dynamic.dynamic;

import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;
import com.lanou.yueba.base.rv.CommonRecyclerAdapter;
import com.lanou.yueba.base.rv.ViewHolder;
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
    }

    @Override
    protected void initData() {
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityTools.deleteActivity(DynamicActivity.this.getClass().getSimpleName());
            }
        });
        final int height = getWindowManager().getDefaultDisplay().getHeight();
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.d("DynamicActivity", "verticalOffset:" + verticalOffset);
                if (verticalOffset < -(height / 4)){
                    Log.d("DynamicActivity", "1");
                    mRelativeLayoutBar.setBackgroundColor(getResources().getColor(R.color.colorMain01));
                }
                if (verticalOffset < -(height / 3.2)){
                    Log.d("DynamicActivity", "2");
                    mRelativeLayoutBar.setBackgroundColor(getResources().getColor(R.color.colorMain02));
                }
                if (verticalOffset < -(height / 2.0)){
                    Log.d("DynamicActivity", "4");
                    mRelativeLayoutBar.setBackgroundColor(getResources().getColor(R.color.colorMain));
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

    public void upadteScoll(){

    }

    @Override
    public void onBackPressed() {
        ActivityTools.deleteActivity(this.getClass().getSimpleName());
        super.onBackPressed();
    }
}
