package com.lanou.yueba.main;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;
import com.lanou.yueba.bean.FriendBean;
import com.lanou.yueba.bean.UserInfoBean;
import com.lanou.yueba.info.InfoActivity;

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
    private TextView personAdd, groupAdd;
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_person_add:

                queryByContact();

                break;
            case R.id.tv_group_add:
                Toast.makeText(this, "123", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    UserInfoBean userInfoBean;

    boolean queryByContact() {


        BmobQuery<UserInfoBean> friendQuery = new BmobQuery<>();
        String user = query.getText().toString().trim();

        friendQuery.addWhereEqualTo("userName", user);
        friendQuery.findObjects(new FindListener<UserInfoBean>() {
            @Override
            public void done(List<UserInfoBean> list, BmobException e) {
                if (e == null && list != null && list.size() > 0) {
                    userInfoBean = list.get(0);
                    Intent intent = new Intent(AddContactActivity.this, InfoActivity.class);
                    intent.putExtra("info", userInfoBean);
                    startActivity(intent);
                } else {
                    Toast.makeText(AddContactActivity.this, "当前用户不存在", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return true;


    }


    void queryFriend() {
        BmobQuery<FriendBean> friendQuery = new BmobQuery<>();
        String user = EMClient.getInstance().getCurrentUser();

        friendQuery.addWhereEqualTo("username", user);
        friendQuery.findObjects(new FindListener<FriendBean>() {
            @Override
            public void done(List<FriendBean> list, BmobException e) {
                if (e == null && list != null && list.size() > 0) {

                }
            }
        });
    }

    private void addContact(final String username) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().contactManager().addContact(username, "加好友");

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("AddContactActivity", "233");
                        }
                    });
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
