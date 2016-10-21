package com.lanou.yueba.message;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseFragment;
import com.lanou.yueba.base.rv.BaseRecyclerAdapter;
import com.lanou.yueba.base.rv.MultiItemTypeAdapter;
import com.lanou.yueba.bean.TestBean;
import com.lanou.yueba.warpper.RecyclerHeaderFooterWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/10/20.
 */

public class MessageFragment extends BaseFragment {

    private RecyclerView mRv;
    private BaseRecyclerAdapter<TestBean> mAdapter;
    private List<TestBean> mDatas = new ArrayList<>();
    private RecyclerHeaderFooterWrapper mRecyclerHeaderFooterWrapper;

    @Override
    protected int setLayout() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initView() {
        mRv = bindView(R.id.rv);
    }

    @Override
    protected void initData() {

        for (int i = 0; i < 100; i++) {
            if ((i % 2) == 0) {
                mDatas.add(new TestBean("我的" + i));
            } else {
                mDatas.add(new TestBean("他的" + i));
            }
        }

        ChatRv chatRv = new ChatRv(mContext, mDatas);

        mRv.setLayoutManager(new LinearLayoutManager(mContext));


//        mRecyclerHeaderFooterWrapper = new RecyclerHeaderFooterWrapper(chatRv);
//        TextView t1 = new TextView(mContext);
//        t1.setText("Header 1");
//        TextView t2 = new TextView(mContext);
//        t2.setText("Header 2");
//        mRecyclerHeaderFooterWrapper.addHeaderView(t1);
//        mRecyclerHeaderFooterWrapper.addHeaderView(t2);

        chatRv.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        mRv.setAdapter(chatRv);

    }
}
