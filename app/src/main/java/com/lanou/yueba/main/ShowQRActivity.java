package com.lanou.yueba.main;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.hyphenate.chat.EMClient;
import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

/**
 * Created by dllo on 16/11/7.
 */
public class ShowQRActivity extends BaseActivity {

    private ImageView qr;

    @Override
    protected int setLayout() {
        return R.layout.activity_qr_show;
    }

    @Override
    protected void initView() {
        qr = (ImageView) findViewById(R.id.iv_qr);
    }

    @Override
    protected void initData() {
        String textContent = EMClient.getInstance().getCurrentUser();
        int hight = getWindowManager().getDefaultDisplay().getHeight();
        Bitmap mBitmap = CodeUtils.createImage(textContent, hight / 3, hight / 3, null);
        qr.setImageBitmap(mBitmap);
    }
}
