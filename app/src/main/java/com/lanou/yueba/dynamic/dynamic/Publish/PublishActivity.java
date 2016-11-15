package com.lanou.yueba.dynamic.dynamic.publish;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lanou.yueba.R;
import com.lanou.yueba.base.activity.BaseActivity;
import com.lanou.yueba.bean.DynamicBean;
import com.lanou.yueba.bean.UserInfoBean;
import com.lanou.yueba.tools.ActivityTools;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

import static com.lanou.yueba.R.id.iv_add_publish;
import static com.lanou.yueba.R.id.tv_publish_publish;

/**
 * Created by dllo on 16/11/5.
 */

public class PublishActivity extends BaseActivity implements View.OnClickListener {

    public static final String PUBLISH = "publish";
    public static final int REQUSET = 401;
    public static final int RESULT = 102;
    private TextView mTvBack;
    private EditText mEt;
    private TextView mTvPublish;
    private ImageView mIvPublish;
    private DynamicBean mDynamicBean;
    private String mPicturePath;
    private UserInfoBean mUserInfoBean;
    private String mContent;
    private ImageView mIvPublishing;
    private static final String TAG = "PublishActivity";
    private FrameLayout mFrameLayout;

    @Override
    protected int setLayout() {
        return R.layout.activity_publish;
    }

    @Override
    protected void initView() {
        mTvBack = bindView(R.id.tv_back_publish);
        mTvPublish = bindView(tv_publish_publish);
        mEt = bindView(R.id.et_content_publish);
        mIvPublish = bindView(iv_add_publish);
        mIvPublishing = bindView(R.id.iv_publishing);
        mFrameLayout = bindView(R.id.fl_publishing);
    }

    @Override
    protected void initData() {
        mIvPublishing.setVisibility(View.GONE);
        mFrameLayout.setVisibility(View.GONE);
        mUserInfoBean = (UserInfoBean) getIntent().getSerializableExtra(PUBLISH);
        mDynamicBean = new DynamicBean();
        initClickListener();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back_publish:
                ActivityTools.deleteActivity(PublishActivity.this.getClass().getSimpleName());
                break;
            case tv_publish_publish:
                mContent = mEt.getText().toString().trim();
                if (mContent.equals("")) {
                    mContent = null;
                }
                if (mContent != null || mPicturePath != null) {
                    publish();
                } else {
                    Toast.makeText(this, "请正确发表动态", Toast.LENGTH_SHORT).show();
                }
                break;
            case iv_add_publish:
                Intent intent = new Intent(
                        Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                );
                startActivityForResult(intent, REQUSET);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUSET == requestCode && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            mPicturePath = cursor.getString(columnIndex);
            final Bitmap bitmap = BitmapFactory.decodeFile(mPicturePath);
            mIvPublish.setImageBitmap(bitmap);
        }
    }

    public void initClickListener() {
        mTvBack.setOnClickListener(this);
        mIvPublish.setOnClickListener(this);
        mTvPublish.setOnClickListener(this);
    }

    public void publish() {
        Log.d(TAG, "publish: 发表");
        mTvBack.setClickable(false);
        mTvPublish.setClickable(false);
        mFrameLayout.setVisibility(View.VISIBLE);
        mIvPublishing.setVisibility(View.VISIBLE);
        mFrameLayout.setBackgroundColor(getResources().getColor(R.color.colorLine));
        final AnimationDrawable drawable = (AnimationDrawable) mIvPublishing.getBackground();
        drawable.start();
        mDynamicBean.setUserName(mUserInfoBean.getUserName());
        mDynamicBean.setPicUrl(mUserInfoBean.getPicUrl());
        if (mContent != null)
            mDynamicBean.setContent(mContent);
        if (mPicturePath != null) {
            final BmobFile file = new BmobFile(new File(mPicturePath));
            file.uploadblock(new UploadFileListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        mDynamicBean.setImgUrl(file.getUrl());
                        mDynamicBean.save(new SaveListener<String>() {
                            @Override
                            public void done(String string, BmobException e) {
                                if (e == null) {
                                    Log.d(TAG, "done: 成功");
                                    drawable.stop();
                                    Intent intent = new Intent();
                                    intent.putExtra(PUBLISH, mDynamicBean);
                                    setResult(RESULT, intent);
                                    ActivityTools.deleteActivity(PublishActivity.this.getClass().getSimpleName());
                                } else {
                                    Toast.makeText(PublishActivity.this, "发表失败", Toast.LENGTH_SHORT).show();
                                    ActivityTools.deleteActivity(PublishActivity.this.getClass().getSimpleName());
                                }
                            }
                        });
                    } else {
                        drawable.stop();
                        Toast.makeText(PublishActivity.this, "发表失败", Toast.LENGTH_SHORT).show();
                        ActivityTools.deleteActivity(PublishActivity.this.getClass().getSimpleName());
                    }
                }
            });
        } else {
            mDynamicBean.save(new SaveListener<String>() {
                @Override
                public void done(String string, BmobException e) {
                    if (e == null) {
                        drawable.stop();
                        Log.d(TAG, "done: 成功");
                        Intent intent = new Intent();
                        intent.putExtra(PUBLISH, mDynamicBean);
                        setResult(102, intent);
                        ActivityTools.deleteActivity(PublishActivity.this.getClass().getSimpleName());
                    } else {
                        drawable.stop();
                        Log.d(TAG, "done: 失败");
                        ActivityTools.deleteActivity(PublishActivity.this.getClass().getSimpleName());
                    }
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        ActivityTools.deleteActivity(PublishActivity.this.getClass().getSimpleName());
        super.onBackPressed();
    }
}
