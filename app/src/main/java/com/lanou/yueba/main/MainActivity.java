package com.lanou.yueba.main;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;
import com.lanou.yueba.contact.ContactFragment;
import com.lanou.yueba.dynamic.DynamicFragment;
import com.lanou.yueba.message.MessageFragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private RadioButton mIvContact, mIvDynamic, mIvMessage;
    private MessageFragment mMessageFragment;
    private ContactFragment mContactFragment;
    private DynamicFragment mDynamicFragment;

    private int windowWidth;
    private int windowHeight;
    private CircleImageView mCircleImageView;
    private TextView mTvToolBar;
    private int index = 1;
    private ImageView mIvToolBar;
    private TextView mTvAddToolBar;
    private TextView mTvMoreToolBar;


    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mIvContact = bindView(R.id.iv_contact_main);
        mIvDynamic = bindView(R.id.iv_dynamic_main);
        mIvMessage = bindView(R.id.iv_message_main);

        mCircleImageView = bindView(R.id.civ_toolbar);
        mTvToolBar = bindView(R.id.tv_title_toolbar);
        mIvToolBar = bindView(R.id.iv_more_toolbar);
        mTvAddToolBar = bindView(R.id.tv_add_toolbar);
        mTvMoreToolBar = bindView(R.id.tv_more_toolbar);
    }

    @Override
    protected void initData() {
        changeToolBar();
        mCircleImageView.setImageResource(R.mipmap.icon);
        windowWidth = this.getWindowManager().getDefaultDisplay().getWidth();
        windowHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        mIvContact.setOnClickListener(this);
        mIvDynamic.setOnClickListener(this);
        mIvMessage.setOnClickListener(this);
        mIvMessage.setChecked(true);
        mMessageFragment = new MessageFragment();
        mContactFragment = new ContactFragment();
        mDynamicFragment = new DynamicFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_main, mMessageFragment);
        transaction.commit();

    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (v.getId()) {
            case R.id.iv_message_main:
                index = 1;
                transaction.replace(R.id.fl_main, mMessageFragment);
                break;
            case R.id.iv_contact_main:
                index = 2;
                transaction.replace(R.id.fl_main, mContactFragment);
                break;
            case R.id.iv_dynamic_main:
                index = 3;
                transaction.replace(R.id.fl_main, mDynamicFragment);
                break;
            case R.id.civ_toolbar:
                break;
            case R.id.iv_more_toolbar:
                break;
            case R.id.tv_add_toolbar:
                break;
            case R.id.tv_more_toolbar:
                break;
            default:
                break;
        }
        changeToolBar();
        transaction.commit();
    }

    private void changeToolBar() {
        switch (index) {
            case 1:
                mTvToolBar.setText("会话");
                mIvToolBar.setVisibility(View.VISIBLE);
                mTvAddToolBar.setVisibility(View.INVISIBLE);
                mTvMoreToolBar.setVisibility(View.INVISIBLE);
                break;
            case 2:
                mTvToolBar.setText("联系人");
                mIvToolBar.setVisibility(View.INVISIBLE);
                mTvAddToolBar.setVisibility(View.VISIBLE);
                mTvMoreToolBar.setVisibility(View.INVISIBLE);
                break;
            case 3:
                mTvToolBar.setText("动态");
                mIvToolBar.setVisibility(View.INVISIBLE);
                mTvAddToolBar.setVisibility(View.INVISIBLE);
                mTvMoreToolBar.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }
}
