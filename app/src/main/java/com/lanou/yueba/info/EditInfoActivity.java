package com.lanou.yueba.info;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;
import com.lanou.yueba.bean.UserInfoBean;
import com.lanou.yueba.tools.ActivityTools;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by dllo on 16/10/27.
 */

public class EditInfoActivity extends BaseActivity implements View.OnClickListener {
    public static final String EDIT_INFO = "editInfo";
    public static final int RESULT= 111;
    public static final int REQUEST= 105;
    private UserInfoBean mUserInfoBean;
    private ImageView mIvBack;
    private EditText mEtPhone;
    private EditText mEtQQ;
    private EditText mEtSignature;
    private Button mBtnSure;
    private ImageView mIvHead;
    private ImageView mIvUpload;

    @Override
    protected int setLayout() {
        return R.layout.activity_edit_info;
    }

    @Override
    protected void initView() {
        mIvUpload = bindView(R.id.iv_uploading_edit_info);
        mIvBack = bindView(R.id.iv_back_edit_info);
        mIvHead = bindView(R.id.civ_head_edit_info);
        mBtnSure = bindView(R.id.btn_sure_edit_info);
        mEtPhone = bindView(R.id.et_phone_edit_info);
        mEtQQ = bindView(R.id.et_qq_edit_info);
        mEtSignature = bindView(R.id.et_signature_edit_info);

    }

    @Override
    protected void initData() {
        mIvUpload.setVisibility(View.GONE);
        mUserInfoBean = (UserInfoBean) getIntent().getSerializableExtra(EDIT_INFO);
        update();
        mEtSignature.setSelection(mEtSignature.getText().toString().length());
        mEtQQ.setSelection(mEtQQ.getText().toString().length());
        mEtPhone.setSelection(mEtPhone.getText().toString().length());
        mIvBack.setOnClickListener(this);
        mBtnSure.setOnClickListener(this);
        mIvHead.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_edit_info:
                ActivityTools.deleteActivity(this.getClass().getSimpleName());
                break;
            case R.id.btn_sure_edit_info:
                mUserInfoBean.setPhoneNum(mEtPhone.getText().toString().trim());
                mUserInfoBean.setQq(mEtQQ.getText().toString().trim());
                mUserInfoBean.setSignature(mEtSignature.getText().toString().trim());
                dismiss();
                break;
            case R.id.civ_head_edit_info:
                Intent intent = new Intent(
                        Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                );
                startActivityForResult(intent, REQUEST);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST == requestCode && resultCode == RESULT_OK){
            mIvUpload.setVisibility(View.VISIBLE);
            final AnimationDrawable drawable = (AnimationDrawable) mIvUpload.getBackground();
            drawable.start();
            mIvBack.setClickable(false);
            mBtnSure.setClickable(false);
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            final Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
            cursor.close();
            final BmobFile bmobFile = new BmobFile(new File(picturePath));
            bmobFile.uploadblock(new UploadFileListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null){
                        mIvHead.setImageBitmap(bitmap);
                        mUserInfoBean.setPicUrl(bmobFile.getUrl());
                        drawable.stop();
                        mIvUpload.setVisibility(View.GONE);
                        mIvBack.setClickable(true);
                        mBtnSure.setClickable(true);
                        Log.d("EditInfoActivity", "成功" + bmobFile.getUrl());
                    } else {
                        drawable.stop();
                        mIvUpload.setVisibility(View.GONE);
                        Toast.makeText(EditInfoActivity.this, "头像更改失败", Toast.LENGTH_SHORT).show();
                        mIvBack.setClickable(true);
                        mBtnSure.setClickable(true);
                    }
                }
            });
        }
    }

    public void update() {
        if (mUserInfoBean.getPhoneNum() != null) {
            mEtPhone.setText(mUserInfoBean.getPhoneNum());
        }
        if (mUserInfoBean.getPicUrl() != null){
            Glide.with(this)
                    .load(mUserInfoBean.getPicUrl())
//                    .placeholder(R.mipmap.icon)
                    .into(mIvHead);
        }
        if (mUserInfoBean.getQq() != null) {
            mEtQQ.setText(mUserInfoBean.getQq());
        }
        if (mUserInfoBean.getSignature() != null) {
            mEtSignature.setText(mUserInfoBean.getSignature());
        }
    }

    public void dismiss(){
        Intent intent = new Intent();
        intent.putExtra(EDIT_INFO, mUserInfoBean);
        setResult(RESULT, intent);
        ActivityTools.deleteActivity(this.getClass().getSimpleName());
    }
}
