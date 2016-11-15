package com.lanou.yueba.base.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.lanou.yueba.tools.ActivityTools;

/**
 * Created by dllo on 16/10/18.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().
                    setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        ActivityTools.addActivity(this.getClass().getSimpleName(), this);
        initView();
        initData();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    protected abstract int setLayout();

    protected abstract void initView();

    protected abstract void initData();

    protected <T extends View> T bindView(int id) {
        return (T) findViewById(id);
    }

    protected <T extends View> T bindView(int id, View v) {
        return (T) v.findViewById(id);
    }

}
