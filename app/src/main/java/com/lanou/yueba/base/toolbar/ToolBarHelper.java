package com.lanou.yueba.base.toolbar;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.lanou.yueba.R;

/**
 * Created by dllo on 16/10/21.
 */

public class ToolBarHelper {
    private AppCompatActivity mActivity;
    private Toolbar mToolbar;
    private LayoutInflater mInflater;
    private RelativeLayout mContentView;

    public RelativeLayout getContentView() {
        return mContentView;
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    public ToolBarHelper(AppCompatActivity activity) {
        mActivity = activity;
        mInflater = LayoutInflater.from(activity);
        initToolBar();
        initContentView();
    }

    private void initToolBar() {
        mToolbar = (Toolbar) mActivity.findViewById(R.id.tool_bar);
        mActivity.setSupportActionBar(mToolbar);
    }

    private void initContentView() {
        mContentView = (RelativeLayout) mToolbar.findViewById(R.id.toolbar_content);
    }

    public void addCustomView(View view) {
        mContentView.addView(view);
    }

    public void removeAllCustomView() {
        mContentView.removeAllViews();
    }

    public void removeCustomView(View childView) {
        mContentView.removeView(childView);
    }

    public void removeCustomView(int index) {
        mContentView.removeViewAt(index);
    }
}
