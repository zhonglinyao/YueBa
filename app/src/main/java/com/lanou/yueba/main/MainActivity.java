package com.lanou.yueba.main;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;

import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;
import com.lanou.yueba.contact.ContactFragment;
import com.lanou.yueba.dynamic.DynamicFragment;
import com.lanou.yueba.message.MessageFragment;
import com.lanou.yueba.news.NewsFragment;
import com.lanou.yueba.video.VideoFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mIvContact;
    private ImageView mIvDynamic;
    private ImageView mIvVideo;
    private ImageView mIvNews;
    private ImageView mIvMessage;
    private MessageFragment mMessageFragment;
    private ContactFragment mContactFragment;
    private DynamicFragment mDynamicFragment;
    private NewsFragment mNewsFragment;
    private VideoFragment mVideoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mIvContact = bindView(R.id.iv_contact_main);
        mIvDynamic = bindView(R.id.iv_dynamic_main);
        mIvVideo = bindView(R.id.iv_video_main);
        mIvNews = bindView(R.id.iv_news_main);
        mIvMessage = bindView(R.id.iv_message_main);
    }

    @Override
    protected void initData() {
        mIvContact.setOnClickListener(this);
        mIvDynamic.setOnClickListener(this);
        mIvVideo.setOnClickListener(this);
        mIvNews.setOnClickListener(this);
        mIvMessage.setOnClickListener(this);
        mMessageFragment = new MessageFragment();
        mContactFragment = new ContactFragment();
        mDynamicFragment = new DynamicFragment();
        mNewsFragment = new NewsFragment();
        mVideoFragment = new VideoFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_main, mMessageFragment);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (v.getId()){
            case R.id.iv_contact_main:
                transaction.replace(R.id.fl_main, mContactFragment);
                break;
            case R.id.iv_dynamic_main:
                transaction.replace(R.id.fl_main, mDynamicFragment);
                break;
            case R.id.iv_video_main:
                transaction.replace(R.id.fl_main, mVideoFragment);
                break;
            case R.id.iv_news_main:
                transaction.replace(R.id.fl_main, mNewsFragment);
                break;
            case R.id.iv_message_main:
                transaction.replace(R.id.fl_main, mMessageFragment);
                break;
        }
        transaction.commit();
    }
}
