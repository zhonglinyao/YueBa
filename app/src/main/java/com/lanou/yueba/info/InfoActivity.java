package com.lanou.yueba.info;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hyphenate.chat.EMClient;
import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;
import com.lanou.yueba.bean.FriendBean;
import com.lanou.yueba.bean.UserInfoBean;
import com.lanou.yueba.dbtools.LiteOrmTools;
import com.lanou.yueba.login.ui.LoginActivity;
import com.lanou.yueba.main.MainActivity;
import com.lanou.yueba.tools.ActivityTools;
import com.lanou.yueba.tools.ToastTools;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by dllo on 16/10/26.
 */

public class InfoActivity extends BaseActivity implements View.OnClickListener {

    public static final String INFO = "info";
    public static final int RESULT = 101;
    public static final int REQUEST = 222;

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
        mUserInfoBean = (UserInfoBean) getIntent().getSerializableExtra(INFO);
        update();
        initListener();

        if (mUserInfoBean.getUserName() != null && mCurrentUser.equals(mUserInfoBean.getUserName())) {
            USERINFO = 0;
            mTvExit.setText(getString(R.string.exit_username));
            mTvEdit.setVisibility(View.VISIBLE);
        } else {
            USERINFO = 1;
            mTvExit.setText(getString(R.string.add_friend));
            mTvEdit.setVisibility(View.GONE);
            isFriend(mUserInfoBean.getUserName(),mCurrentUser);


        }

    }

    private void isFriend(String friend,String user) {

        BmobQuery<FriendBean> isFriend = new BmobQuery<>();
        isFriend.addWhereEqualTo("isFriend",true).addWhereEqualTo("friendname",friend).addWhereEqualTo("username",user);
        isFriend.findObjects(new FindListener<FriendBean>() {
            @Override
            public void done(List<FriendBean> list, BmobException e) {
                if (e == null){
                    if (list.size() == 0){
                        mTvExit.setVisibility(View.GONE);
                    } else {
                        mTvExit.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    public void initListener() {
        mIvBack.setOnClickListener(this);
        mTvEdit.setOnClickListener(this);
        mTvExit.setOnClickListener(this);
    }

    public void update() {
        if (mUserInfoBean != null) {
            if (mUserInfoBean.getUserName() != null)
                mTvUsername.setText(mUserInfoBean.getUserName());

            if (mUserInfoBean.getPicUrl() != null) {
                Glide.with(this)
                        .load(mUserInfoBean.getPicUrl())
                        .into(mIvHead);
            } else {
                mIvHead.setImageResource(R.mipmap.icon);
            }

            if (mUserInfoBean.getPhoneNum() == null) {
                mTvPhone.setText(getString(R.string.no_phone));
            } else {
                mTvPhone.setText(mUserInfoBean.getPhoneNum());
            }
            if (mUserInfoBean.getQq() == null) {
                mTvQQ.setText(getString(R.string.no_qq));
            } else {
                mTvQQ.setText(mUserInfoBean.getQq());
            }
            if (mUserInfoBean.getSignature() == null) {
                mTvSignature.setText(getString(R.string.no_signature));
            } else {
                mTvSignature.setText(mUserInfoBean.getSignature());
            }
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
                intent.putExtra(EditInfoActivity.EDIT_INFO, mUserInfoBean);
                startActivityForResult(intent, REQUEST);
                break;
            case R.id.tv_exit_info:
                if (USERINFO == 0) {
                    EMClient.getInstance().logout(true);
                    startActivity(new Intent(this, LoginActivity.class));
                    ActivityTools.deleteActivity(MainActivity.class.getSimpleName());
                    ActivityTools.deleteActivity(this.getClass().getSimpleName());
                    break;
                } else {
                    addToBmob();
                }

                break;
        }
    }

    private void addToBmob() {
        FriendBean friendBean = new FriendBean();
        friendBean.setUsername(mCurrentUser);
        friendBean.setFriendname(mUserInfoBean.getUserName());
        friendBean.setFriend(false);
        friendBean.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {

                if (e == null){
                    ToastTools.showShort( InfoActivity.this, getString(R.string.add_success_wait));

                } else {
                    ToastTools.showShort( InfoActivity.this, getString(R.string.add_fail));
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST == requestCode && EditInfoActivity.RESULT == resultCode) {
            mUserInfoBean = (UserInfoBean) data.getSerializableExtra(EditInfoActivity.EDIT_INFO);
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
        intent.putExtra(INFO, mUserInfoBean);
        setResult(RESULT, intent);
        ActivityTools.deleteActivity(this.getClass().getSimpleName());
    }

    @Override
    public void onBackPressed() {
        dismiss();
    }

}
