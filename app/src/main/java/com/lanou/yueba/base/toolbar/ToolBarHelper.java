package com.lanou.yueba.base.toolbar;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.lanou.yueba.R;

/**
 * Created by dllo on 16/10/21.
 */

public class ToolBarHelper {
    private Context mContext;
    private Toolbar mToolbar;
    private LayoutInflater mInflater;
    private View mCustomView;
    private FrameLayout mContentView;
    private View mViewLeft;
    private View mViewCenter;
    private View mViewRight;

    private static int[] ATTRS = {
            R.attr.windowActionBarOverlay,
            R.attr.actionBarSize
    };

    public FrameLayout getContentView() {
        return mContentView;
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    public ToolBarHelper(Context context, int layoutId) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        initContentView();
        initUserView(layoutId);
        initToolBar();
    }

    private void initContentView(){
        mContentView = new FrameLayout(mContext);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mContentView.setLayoutParams(params);
    }

    private void initUserView(int layoutId){
        mCustomView = mInflater.inflate(layoutId, null);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
//        TypedArray typedArray = mContext.getTheme().obtainStyledAttributes(ATTRS);
//        Boolean overly = typedArray.getBoolean(0, false);
        mContentView.addView(mCustomView, params);

    }

    private void initToolBar(){
        View view = mInflater.inflate(R.layout.toolbar, mContentView);
        mToolbar = (Toolbar) view.findViewById(R.id.tool_bar);
    }


}
