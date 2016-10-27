package com.lanou.yueba.info;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;
import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;
import com.lanou.yueba.bean.UserInfoBean;
import com.lanou.yueba.login.ui.LoginActivity;
import com.lanou.yueba.main.MainActivity;
import com.lanou.yueba.tools.ActivityTools;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by dllo on 16/10/26.
 */

public class InfoActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mIvBack;
    private TextView mTvEdit;
    private TextView mTvExit;
    private TextView mTvUsername;
    private TextView mTvSignature;
    private TextView mTvQQ;
    private TextView mTvPhone;
    private UserInfoBean mUserInfoBean;
    private EditInfoFragment mEditInfoFragment;

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_info;
    }

    @Override
    protected void initView() {
        mIvBack = bindView(R.id.iv_back_info);
        mTvEdit = bindView(R.id.tv_edit_info);
        mTvExit = bindView(R.id.tv_exit_info);
        mTvUsername = bindView(R.id.tv_username_info);
        mTvSignature = bindView(R.id.tv_signature);
        mTvQQ = bindView(R.id.tv_qq_info);
        mTvPhone = bindView(R.id.tv_phone_info);
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        mUserInfoBean = (UserInfoBean) getIntent().getSerializableExtra("info");
        mTvUsername.setText(EMClient.getInstance().getCurrentUser().toString());
        update();
        initListener();
    }



    public void initListener() {
        mIvBack.setOnClickListener(this);
        mTvEdit.setOnClickListener(this);
        mTvExit.setOnClickListener(this);
    }

    public void update() {
        if (mUserInfoBean.getPhoneNum() == null) {
            mTvPhone.setText("还没有电话号码");
        } else {
            mTvPhone.setText(mUserInfoBean.getPhoneNum());
        }
        if (mUserInfoBean.getQq() == null) {
            mTvQQ.setText("还没有QQ号");
        } else {
            mTvQQ.setText(mUserInfoBean.getQq());
        }
        if (mUserInfoBean.getSignature() == null) {
            mTvSignature.setText("还没有个性签名");
        } else {
            mTvSignature.setText(mUserInfoBean.getSignature());
        }
    }

    private void editInfo() {
        if (mEditInfoFragment == null) {
            mEditInfoFragment = new EditInfoFragment();
        }
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        mEditInfoFragment.setUserInfoBean(mUserInfoBean);
        transaction.addToBackStack(null).replace(R.id.fl_info, mEditInfoFragment).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_info:
                dismiss();
                break;
            case R.id.tv_edit_info:
                editInfo();
                break;
            case R.id.tv_exit_info:
                EMClient.getInstance().logout(true);
                startActivity(new Intent(this, LoginActivity.class));
                ActivityTools.deleteActivity(MainActivity.class.getSimpleName());
                ActivityTools.deleteActivity(this.getClass().getSimpleName());
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void editInfoEvent(UserInfoBean userInfoBean){
        mUserInfoBean = userInfoBean;
        update();
        mUserInfoBean.update(mUserInfoBean.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Log.d("InfoActivity", "成功");
                } else {
                    Log.d("InfoActivity", "失败");
                }
            }
        });
    }

    public void dismiss(){
        Intent intent = new Intent();
        intent.putExtra("info", mUserInfoBean);
        setResult(101, intent);
        ActivityTools.deleteActivity(this.getClass().getSimpleName());
    }

    @Override
    public void onBackPressed() {
        dismiss();
    }

}
