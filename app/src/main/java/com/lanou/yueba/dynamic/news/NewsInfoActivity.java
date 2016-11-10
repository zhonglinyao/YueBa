package com.lanou.yueba.dynamic.news;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;
import com.lanou.yueba.tools.ActivityTools;

/**
 * Created by dllo on 16/10/29.
 */

public class NewsInfoActivity extends BaseActivity {

    public static final String NEW_URL = "newsUrl";
    private ImageView mIvBack;
    private WebView mWebView;
    private Dialog mDialog;

    @Override
    protected int setLayout() {
        return R.layout.activity_news_info;
    }

    @Override
    protected void initView() {
        mWebView = bindView(R.id.wv_news_info);
        mIvBack = bindView(R.id.iv_back_news_info);
    }

    @Override
    protected void initData() {
        mIvBack.setVisibility(View.INVISIBLE);
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityTools.deleteActivity(NewsInfoActivity.this.getClass().getSimpleName());
            }
        });
        mDialog = new ProgressDialog(this);
        mDialog.show();
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        mWebView.requestFocusFromTouch();
        mWebView.loadUrl(getIntent().getStringExtra(NEW_URL));
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                Log.d("NewsInfoActivity", "newProgress:" + newProgress);
                if (100 == newProgress) {
                    mDialog.dismiss();
                } else {
                    mDialog.show();
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                Log.d("NewsInfoActivity", title);
            }
        });
    }

    @Override
    public void onBackPressed() {
        ActivityTools.deleteActivity(this.getClass().getSimpleName());
    }
}
