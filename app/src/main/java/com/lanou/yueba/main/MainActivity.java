package com.lanou.yueba.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.controller.EaseUI;
import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;
import com.lanou.yueba.bean.UserInfoBean;
import com.lanou.yueba.contact.ContactFragment;
import com.lanou.yueba.dbtools.LiteOrmTools;
import com.lanou.yueba.dynamic.DynamicFragment;
import com.lanou.yueba.dynamic.dynamic.DynamicActivity;
import com.lanou.yueba.info.InfoActivity;
import com.lanou.yueba.main.addcontact.AddContactActivity;
import com.lanou.yueba.message.MessageFragment;
import com.lanou.yueba.vlaues.StringVlaues;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.lanou.yueba.R.id.iv_more_toolbar;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private RadioButton mIvContact;
    private RadioButton mIvDynamic;
    private RadioButton mIvMessage;
    private MessageFragment mMessageFragment;
    private ContactFragment mContactFragment;
    private DynamicFragment mDynamicFragment;

    private CircleImageView mCircleImageView;
    private TextView mTvToolBar;
    private int index = 1;
    private ImageView mIvToolBar;
    private TextView mTvAddToolBar;
    private TextView mTvMoreToolBar;
    private UserInfoBean mUserInfoBean;
    private String mUserName;
    private Bitmap mHeadimage;

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
        mIvToolBar = bindView(iv_more_toolbar);
        mTvAddToolBar = bindView(R.id.tv_add_toolbar);
        mTvMoreToolBar = bindView(R.id.tv_more_toolbar);
    }


    EMMessageListener mListener = new EMMessageListener() {
        @Override
        public void onMessageReceived(List<EMMessage> list) {
            EaseUI.getInstance().getNotifier().onNewMesg(list);
            refreshUIWithMessage();

        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> list) {

        }

        @Override
        public void onMessageReadAckReceived(List<EMMessage> list) {

        }

        @Override
        public void onMessageDeliveryAckReceived(List<EMMessage> list) {

        }

        @Override
        public void onMessageChanged(EMMessage emMessage, Object o) {

        }
    };

    private void refreshUIWithMessage(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (index == 1){
                    if (mMessageFragment != null){
                        mMessageFragment.refresh();
                    }
                }
            }
        });
    }
    @Override
    protected void initData() {

        EMClient.getInstance().chatManager().addMessageListener(mListener);

        mUserName = getIntent().getStringExtra(StringVlaues.username);
        changeToolBar();
        initClickListener();
        mIvMessage.setChecked(true);
        mMessageFragment = new MessageFragment();
        mContactFragment = new ContactFragment();
        mDynamicFragment = new DynamicFragment();
        mDynamicFragment.setDynamicCallBack(new DynamicFragment.DynamicCallBack() {
            @Override
            public void callBack() {
                Intent intent = new Intent(MainActivity.this, DynamicActivity.class);
                intent.putExtra(StringVlaues.DYNAMIC, mUserInfoBean);
                startActivity(intent);
            }
        });
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_main, mMessageFragment);
        transaction.commit();
        queryDb();
    }

    private void initClickListener() {
        mIvContact.setOnClickListener(this);
        mIvDynamic.setOnClickListener(this);
        mIvMessage.setOnClickListener(this);
        mIvToolBar.setOnClickListener(this);
        mTvAddToolBar.setOnClickListener(this);
        mTvMoreToolBar.setOnClickListener(this);
        mCircleImageView.setOnClickListener(this);
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
                Intent infoIntent = new Intent(this, InfoActivity.class);
                infoIntent.putExtra(StringVlaues.INFO, mUserInfoBean);
                startActivityForResult(infoIntent, 201);
                break;
            case iv_more_toolbar:
                showPop(v);
                break;
            case R.id.tv_add_toolbar:

                Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_more_toolbar:
                break;
            default:
                break;
        }
        changeToolBar();
        transaction.commit();
    }

    private void showPop(View v) {
        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(this).inflate(
                R.layout.pop_window, null);
        // 设置按钮的点击事件
        Button addPop,createPop;
        addPop= (Button) contentView.findViewById(R.id.btn_add_friend_pop);
        createPop = (Button) contentView.findViewById(R.id.btn_create_qr_pop);

        addPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQR();
            }
        });
        createPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ShowQRActivity.class);
                startActivity(intent);
            }
        });
        PopupWindow popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        popupWindow.setFocusable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        popupWindow.setBackgroundDrawable(dw);
        // 设置popWindow的显示和消失动画
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 设置好参数之后再show
        popupWindow.showAsDropDown(v);

    }
    //二维码
    private int QR_REQUEST_CODE = 102;
    private void showQR() {
        Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
        startActivityForResult(intent, QR_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (201 == requestCode && 101 == resultCode) {
            mUserInfoBean = (UserInfoBean) data.getSerializableExtra("info");
            if (mUserInfoBean.getPicUrl() != null) {
                Glide.with(MainActivity.this)
                        .load(mUserInfoBean.getPicUrl())
                        .placeholder(R.mipmap.icon)
                        .into(mCircleImageView);
            }
        }

        if (QR_REQUEST_CODE == requestCode) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {

                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    BmobQuery<UserInfoBean> beanBmobQuery =new BmobQuery<>();
                    beanBmobQuery.addWhereEqualTo("userName",result);
                    beanBmobQuery.findObjects(new FindListener<UserInfoBean>() {
                        @Override
                        public void done(List<UserInfoBean> list, BmobException e) {
                            if (e == null){
                                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                                intent.putExtra("info", list.get(0));
                                startActivity(intent);
                            }
                        }
                    });
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
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

    private void queryDb() {
        LiteOrmTools.getInstance().queryTab(UserInfoBean.class, new LiteOrmTools.CallBack<UserInfoBean>() {
            @Override
            public void callBack(List<UserInfoBean> list) {
                Log.d("MainActivity", "数据库");
                if (list == null || 0 == list.size()) {
                    Log.d("MainActivity", "DB没数据");
                    bmobQuery();
                } else {
                    Log.d("MainActivity", "DB有数据");
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getUserName().equals(mUserName)) {
                            mUserInfoBean = list.get(i);
                            break;
                        }
                    }
                    updateHead();
                }
            }
        });
    }

    private void bmobQuery() {

        BmobQuery<UserInfoBean> query = new BmobQuery<>();

        query.addWhereEqualTo("userName", mUserName);

        query.findObjects(new FindListener<UserInfoBean>() {

            @Override
            public void done(List<UserInfoBean> list, BmobException e) {
                if (e == null) {
                    Log.d("MainActivity", "aaa");
                    mUserInfoBean = list.get(0);
                    updateHead();
                } else {
                    updateHead();
                    mHeadimage = BitmapFactory.decodeResource(getResources(), R.mipmap.icon);
                }
            }
        });
    }

    public void updateHead() {
        if (mUserInfoBean.getPicUrl() != null) {
            Glide.with(MainActivity.this)
                    .load(mUserInfoBean.getPicUrl())
//                    .placeholder(R.mipmap.icon)
                    .error(R.mipmap.icon)
                    .into(mCircleImageView);
        } else {
            mCircleImageView.setImageResource(R.mipmap.icon);
        }
    }

    @Override
    protected void onDestroy() {
//        EMClient.getInstance().logout(true);
        super.onDestroy();
    }

    @Override
    protected void onStop() {
//        EMClient.getInstance().logout(true);
        super.onStop();
    }
}
