package com.lanou.yueba.info;

import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;

/**
 * Created by dllo on 16/10/26.
 */

public class InfoActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mIvBack;
    private TextView mTvEdit;

    @Override
    protected int setLayout() {
        return R.layout.activity_info;
    }

    @Override
    protected void initView() {
        mIvBack = bindView(R.id.iv_back_info);
        mTvEdit = bindView(R.id.tv_edit_info);
    }

    @Override
    protected void initData() {
        initListener();
    }

    public void initListener() {
        mIvBack.setOnClickListener(this);
        mTvEdit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_info:
                finish();
                break;
            case R.id.tv_edit_info:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
