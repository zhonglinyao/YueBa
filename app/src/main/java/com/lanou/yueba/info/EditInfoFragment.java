package com.lanou.yueba.info;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseFragment;
import com.lanou.yueba.bean.UserInfoBean;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by dllo on 16/10/27.
 */

public class EditInfoFragment extends BaseFragment implements View.OnClickListener {
    private UserInfoBean mUserInfoBean;
    private ImageView mIvBack;
    private EditText mEtPhone;
    private EditText mEtQQ;
    private EditText mEtSignature;
    private Button mBtnSure;

    public void setUserInfoBean(UserInfoBean userInfoBean) {
        mUserInfoBean = userInfoBean;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_edit_info;
    }

    @Override
    protected void initView() {
        mIvBack = bindView(R.id.iv_back_edit_info);
        mBtnSure = bindView(R.id.btn_sure_edit_info);
        mEtPhone = bindView(R.id.et_phone_edit_info);
        mEtQQ = bindView(R.id.et_qq_edit_info);
        mEtSignature = bindView(R.id.et_signature_edit_info);
    }

    @Override
    protected void initData() {
        update();
        mIvBack.setOnClickListener(this);
        mBtnSure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_edit_info:
                dismiss();
                break;
            case R.id.btn_sure_edit_info:
                mUserInfoBean.setPhoneNum(mEtPhone.getText().toString().trim());
                mUserInfoBean.setQq(mEtQQ.getText().toString().trim());
                mUserInfoBean.setSignature(mEtSignature.getText().toString().trim());
                EventBus.getDefault().post(mUserInfoBean);
                dismiss();
                break;
        }
    }

    public void update() {
        if (mUserInfoBean.getPhoneNum() != null) {
            mEtPhone.setText(mUserInfoBean.getPhoneNum());
        }
        if (mUserInfoBean.getQq() != null) {
            mEtQQ.setText(mUserInfoBean.getQq());
        }
        if (mUserInfoBean.getSignature() != null) {
            mEtSignature.setText(mUserInfoBean.getSignature());
        }
    }

    public void dismiss(){
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.remove(this);
        transaction.commit();
    }
}
