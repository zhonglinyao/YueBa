package com.lanou.yueba.login;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;
import com.lanou.yueba.main.MainActivity;

/**
 * Created by dllo on 16/10/24.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private Button mButtonSure;
    private LinearLayout mLl;

    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        mButtonSure = bindView(R.id.btn_sure_login);
        mLl = bindView(R.id.ll_login);
    }

    @Override
    protected void initData() {
        mButtonSure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sure_login:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}
