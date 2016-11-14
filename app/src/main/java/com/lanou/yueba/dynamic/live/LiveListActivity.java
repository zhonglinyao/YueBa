package com.lanou.yueba.dynamic.live;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;
import com.lanou.yueba.base.rv.CommonRecyclerAdapter;
import com.lanou.yueba.base.rv.MultiItemTypeRecyclerAdapter;
import com.lanou.yueba.base.rv.ViewHolder;
import com.lanou.yueba.tools.ActivityTools;
import com.lanou.yueba.vlaues.StringVlaues;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/11/1.
 */

public class LiveListActivity extends BaseActivity{

    private ImageView mIvBack;
    private RecyclerView mRv;

    @Override
    protected int setLayout() {
        return R.layout.activity_live_list;
    }

    @Override
    protected void initView() {
        mIvBack = bindView(R.id.iv_back_livelist);
        mRv = bindView(R.id.rv_livelist);
    }

    @Override
    protected void initData() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityTools.deleteActivity(LiveListActivity.this.getClass().getSimpleName());
            }
        });
        final List<String> strings = new ArrayList<>();
        for (String s : StringVlaues.liveList) {
            strings.add(s);
        }
        CommonRecyclerAdapter<String> adapter = new CommonRecyclerAdapter<String>(this,R.layout.layout_live, strings) {
            @Override
            protected void convert(ViewHolder holder, String string, int position) {
                holder.setText(R.id.tv_list_live, string);
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(LiveListActivity.this, LiveActivity.class);
                intent.putExtra(LiveActivity.LIVE_URL, StringVlaues.liveUrlList[position]);
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRv.setLayoutManager(manager);
        mRv.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        ActivityTools.deleteActivity(LiveListActivity.this.getClass().getSimpleName());
    }
}
