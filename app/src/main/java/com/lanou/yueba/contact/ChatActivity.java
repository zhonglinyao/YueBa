package com.lanou.yueba.contact;


import com.hyphenate.easeui.ui.EaseChatFragment;
import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;

/**
 * Created by dllo on 16/10/26.
 */
public class ChatActivity extends BaseActivity {


    @Override
    protected int setLayout() {
        return R.layout.activity_chat;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        EaseChatFragment easeChatFragment = new EaseChatFragment();
        easeChatFragment.setArguments(getIntent().getExtras());

        getSupportFragmentManager().beginTransaction().add(R.id.fl_chat,easeChatFragment).commit();



    }
}
