package com.lanou.yueba.info;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;
import com.lanou.yueba.bean.UserInfoBean;
import com.lanou.yueba.tools.ActivityTools;
import com.lanou.yueba.vlaues.StringVlaues;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by dllo on 16/10/27.
 */

public class EditInfoActivity extends BaseActivity implements View.OnClickListener {
    private UserInfoBean mUserInfoBean;
    private ImageView mIvBack;
    private EditText mEtPhone;
    private EditText mEtQQ;
    private EditText mEtSignature;
    private Button mBtnSure;
    private ImageView mIvHead;

    @Override
    protected int setLayout() {
        return R.layout.activity_edit_info;
    }

    @Override
    protected void initView() {
        mIvBack = bindView(R.id.iv_back_edit_info);
        mIvHead = bindView(R.id.civ_head_edit_info);
        mBtnSure = bindView(R.id.btn_sure_edit_info);
        mEtPhone = bindView(R.id.et_phone_edit_info);
        mEtQQ = bindView(R.id.et_qq_edit_info);
        mEtSignature = bindView(R.id.et_signature_edit_info);
    }

    @Override
    protected void initData() {
        mUserInfoBean = (UserInfoBean) getIntent().getSerializableExtra(StringVlaues.EDIT_INFO);
        update();
        mEtSignature.setSelection(mEtSignature.getText().toString().length());
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
                startActivityForResult(intent, 105);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (105 == requestCode && resultCode == RESULT_OK){
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            final Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
            mIvHead.setImageBitmap(bitmap);
            cursor.close();
            final BmobFile bmobFile = new BmobFile(new File(picturePath));
            bmobFile.uploadblock(new UploadFileListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null){
                        mUserInfoBean.setPicUrl(bmobFile.getUrl());
                        Log.d("EditInfoActivity", "成功" + bmobFile.getUrl());
                    } else {
                        Log.d("EditInfoActivity", "失败");
                    }
                }
            });
//            ThreadTool.getInstance().executorRunnable(new Runnable() {
//                @Override
//                public void run() {
//                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
//                    bitmap.recycle();
//                    byte[] bytes = outputStream.toByteArray();
//                    try {
//                        outputStream.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    String s = bytes.toString();
//                }
//            });
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
        intent.putExtra(StringVlaues.EDIT_INFO, mUserInfoBean);
        setResult(111, intent);
        ActivityTools.deleteActivity(this.getClass().getSimpleName());
    }
}
