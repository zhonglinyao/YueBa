package com.lanou.yueba.main.addcontact;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.lanou.yueba.R;
import com.lanou.yueba.base.activity.BaseActivity;
import com.lanou.yueba.bean.FriendBean;
import com.lanou.yueba.bean.UserInfoBean;
import com.lanou.yueba.info.InfoActivity;
import com.lanou.yueba.main.MainActivity;
import com.lanou.yueba.tools.ToastTools;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by dllo on 16/10/26.
 */
public class AddContactActivity extends BaseActivity implements View.OnClickListener {

    private EditText query;
    private ImageButton clearSearch;
    private TextView personAdd, groupAdd,backAdd;
    private LinearLayout ll;

    @Override
    protected int setLayout() {
        return R.layout.add_contact;
    }

    @Override
    protected void initView() {
        query = bindView(R.id.query);
        clearSearch = bindView(R.id.search_clear);
        personAdd = bindView(R.id.tv_person_add);
        groupAdd = bindView(R.id.tv_group_add);
        ll = bindView(R.id.ll_add_contact);
        backAdd = bindView(R.id.tv_back_add_contact);


    }

    @Override
    protected void initData() {

        query.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {

                    clearSearch.setVisibility(View.VISIBLE);
                    ll.setVisibility(View.VISIBLE);
                    personAdd.setText("找人:" + s);
                    groupAdd.setText("找群:" + s);

                } else {
                    clearSearch.setVisibility(View.INVISIBLE);
                    ll.setVisibility(View.INVISIBLE);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        clearSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query.getText().clear();
            }
        });


        personAdd.setOnClickListener(this);
        groupAdd.setOnClickListener(this);
        backAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_person_add:
                queryByContact();
                break;
            case R.id.tv_group_add:

                ToastTools.showShort(AddContactActivity.this,"群功能目前还没添加");
                break;
            case R.id.tv_back_add_contact:
                finish();
                break;
        }
    }

    UserInfoBean userInfoBean;

    boolean queryByContact() {


        BmobQuery<UserInfoBean> friendQuery = new BmobQuery<>();
        BmobQuery<FriendBean> isFriend = new BmobQuery<>();
        String user = query.getText().toString().trim();

        friendQuery.addWhereEqualTo("userName", user);
        isFriend.addWhereEqualTo("userName",user).addWhereEqualTo("isFriend",false);


        friendQuery.findObjects(new FindListener<UserInfoBean>() {
            @Override
            public void done(List<UserInfoBean> list, BmobException e) {
                if (e == null && list != null && list.size() > 0) {
                    userInfoBean = list.get(0);
                    Intent intent = new Intent(AddContactActivity.this, InfoActivity.class);
                    intent.putExtra("info", userInfoBean);
                    startActivity(intent);
                } else {
                    ToastTools.showShort(AddContactActivity.this,"当前用户不存在");
                    }
            }
        });
        return true;
    }


    void queryFriend() {
        BmobQuery<FriendBean> friendQuery = new BmobQuery<>();
        String user = EMClient.getInstance().getCurrentUser();

        friendQuery.addWhereEqualTo(MainActivity.USERNAME, user);
        friendQuery.findObjects(new FindListener<FriendBean>() {
            @Override
            public void done(List<FriendBean> list, BmobException e) {
                if (e == null && list != null && list.size() > 0) {

                }
            }
        });
    }

    private void addContact(final String username) {

        EMClient.getInstance().contactManager().aysncAddContact(username, "加好友", new EMCallBack() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(int i, String s) {

            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }
}
