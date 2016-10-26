package com.lanou.yueba.main;

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

/**
 * Created by dllo on 16/10/26.
 */
public class AddContactActivity extends BaseActivity implements View.OnClickListener {

    private EditText query;
    private ImageButton clearSearch;
    private TextView personAdd,groupAdd;
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
                    personAdd.setText("找人:"+s);
                    groupAdd.setText("找群:"+s);

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
        switch (v.getId()){
            case R.id.tv_person_add:


//              new EaseAlertDialog(this,R.string.Please_enter_a_username);
                addContact(query.getText().toString().trim());

                break;
            case R.id.tv_group_add:
                Toast.makeText(this, "123", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void addContact(final String username) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().contactManager().addContact(username,"加好友");

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
