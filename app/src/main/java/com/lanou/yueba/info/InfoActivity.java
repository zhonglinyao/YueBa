package com.lanou.yueba.info;

import android.view.KeyEvent;

import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;

/**
 * Created by dllo on 16/10/26.
 */

public class InfoActivity extends BaseActivity{
    @Override
    protected int setLayout() {
        return R.layout.activity_info;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
