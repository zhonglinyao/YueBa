package com.lanou.yueba.main;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;
import com.lanou.yueba.R;
import com.lanou.yueba.base.activity.BaseActivity;
import com.lanou.yueba.tools.ActivityTools;
import com.uuzuche.lib_zxing.activity.CodeUtils;

/**
 * Created by dllo on 16/11/7.
 */
public class ShowQRActivity extends BaseActivity implements View.OnClickListener {

    private ImageView qr;
    private TextView back;

    @Override
    protected int setLayout() {
        return R.layout.activity_qr_show;
    }

    @Override
    protected void initView() {
        qr = (ImageView) findViewById(R.id.iv_qr);
        back = (TextView) findViewById(R.id.tv_back_qr_show);
    }

    @Override
    protected void initData() {


        String textContent = EMClient.getInstance().getCurrentUser();
        int hight = getWindowManager().getDefaultDisplay().getHeight();
        Bitmap mBitmap = CodeUtils.createImage(textContent, hight / 3, hight / 3, null);
        qr.setImageBitmap(mBitmap);

        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back_qr_show:
                ActivityTools.deleteActivity(ShowQRActivity.this.getClass().getSimpleName());
                break;
        }
    }
}
