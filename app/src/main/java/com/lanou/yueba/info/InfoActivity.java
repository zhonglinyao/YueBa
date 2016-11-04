package com.lanou.yueba.info;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;
import com.lanou.yueba.bean.FriendBean;
import com.lanou.yueba.bean.UserInfoBean;
import com.lanou.yueba.dbtools.LiteOrmTools;
import com.lanou.yueba.login.ui.LoginActivity;
import com.lanou.yueba.main.MainActivity;
import com.lanou.yueba.tools.ActivityTools;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
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
    private String mCurrentUser;
    private ImageView mIvHead;

//    private TextView mName;

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
        mIvHead = bindView(R.id.civ_info);

    }

    private int USERINFO = 0; //当前用户

    @Override
    protected void initData() {
        mCurrentUser = EMClient.getInstance().getCurrentUser().toString();
        mUserInfoBean = (UserInfoBean) getIntent().getSerializableExtra("info");
        update();
        initListener();
        if (mCurrentUser.equals(mUserInfoBean.getUserName())) {
            USERINFO = 0;
            mTvExit.setText("退出当前账号");
            mTvEdit.setVisibility(View.VISIBLE);

        } else {
            USERINFO = 1;
            mTvExit.setText("添加好友");
            mTvEdit.setVisibility(View.GONE);
        }


//        Intent intent = getIntent();
//        mName.setText(intent.getStringExtra("username"));
    }

    public void initListener() {
        mIvBack.setOnClickListener(this);
        mTvEdit.setOnClickListener(this);
        mTvExit.setOnClickListener(this);
    }

    public void update() {
        if (mUserInfoBean != null) {
            mTvUsername.setText(mUserInfoBean.getUserName());
        }

        if (mUserInfoBean.getPicUrl() != null) {
            Log.d("InfoActivity", "bbb");
            Glide.with(this)
                    .load(mUserInfoBean.getPicUrl())
                    .into(mIvHead);
        }

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_info:
                dismiss();
                break;
            case R.id.tv_edit_info:
                Intent intent = new Intent(this, EditInfoActivity.class);
                intent.putExtra("editInfo", mUserInfoBean);
                startActivityForResult(intent, 222);
                break;
            case R.id.tv_exit_info:
                if (USERINFO == 0) {
                    EMClient.getInstance().logout(true);
                    startActivity(new Intent(this, LoginActivity.class));
                    ActivityTools.deleteActivity(MainActivity.class.getSimpleName());
                    ActivityTools.deleteActivity(this.getClass().getSimpleName());
                    break;
                } else {

                    EMClient.getInstance().contactManager().aysncAddContact(mUserInfoBean.getUserName(), "添加好友", new EMCallBack() {
                        @Override
                        public void onSuccess() {
                            addToBmob();
                        }

                        @Override
                        public void onError(int i, String s) {

                        }

                        @Override
                        public void onProgress(int i, String s) {

                        }
                    });


                }

                break;
        }
    }

    private void addToBmob() {
        FriendBean friendBean = new FriendBean();
        friendBean.setUsername(mCurrentUser);
        friendBean.setFriendname(mUserInfoBean.getUserName());
        friendBean.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    Log.d("InfoActivity", "添加成功");
                } else {
                    Log.d("InfoActivity", "添加失败");
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (222 == requestCode && 111 == resultCode) {
            mUserInfoBean = (UserInfoBean) data.getSerializableExtra("editInfo");
            update();
            LiteOrmTools.getInstance().deleteTab(UserInfoBean.class);
            LiteOrmTools.getInstance().insertInfo(mUserInfoBean);
            mUserInfoBean.update(mUserInfoBean.getObjectId(), new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        Log.d("InfoActivity", "修改成功");

                    } else {
                        Log.d("InfoActivity", "修改失败");
                    }
                }
            });
        }
    }

    public void dismiss() {

        if (1 == USERINFO) {
            ActivityTools.deleteActivity(this.getClass().getSimpleName());
            return;
        }
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
