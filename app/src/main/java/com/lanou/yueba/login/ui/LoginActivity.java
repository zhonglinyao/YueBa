package com.lanou.yueba.login.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;
import com.lanou.yueba.login.presenter.LoginPresenter;
import com.lanou.yueba.main.MainActivity;
import com.lanou.yueba.register.ui.RegisterActivity;

/**
 * Created by dllo on 16/10/24.
 */

public class LoginActivity extends BaseActivity implements ILoginView, View.OnClickListener {

    private Button mButtonSure;
    private LinearLayout mLl;
    private ProgressDialog mDialog;
    private EditText mName;
    private EditText mPassword;
    private TextView mRegister;
    private LoginPresenter mPresenter;

    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        mPresenter = new LoginPresenter(this);
        mButtonSure = bindView(R.id.btn_sure_login);
        mName = bindView(R.id.et_name_login);
        mPassword = bindView(R.id.et_password_login);
        mRegister = bindView(R.id.tv_register_login);
        mDialog = createDialog();
    }

    @Override
    protected void initData() {


        if (EMClient.getInstance().isLoggedInBefore()) {

            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }


        mButtonSure.setOnClickListener(this);
        mRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String username = mName.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        switch (v.getId()) {
            case R.id.btn_sure_login:
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                    mName.requestFocus();
                    break;
                }
                if (TextUtils.isEmpty(password)) {
                    Log.d("RegisterActivity", "密码不能为空");
                    mPassword.requestFocus();
                    break;
                }
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                    mPresenter.startRequest(username, password);
                }
                break;
            case R.id.tv_register_login:
                startActivityForResult(new Intent(this, RegisterActivity.class),101);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (101 == requestCode && 202 == resultCode ){
            mName.setText(data.getStringExtra("username"));
            mPassword.setText(data.getStringExtra("password"));
        }

    }

    @Override
    public void showDialog() {
        mDialog.show();
    }

    @Override
    public void dismissDialog() {
        mDialog.dismiss();
    }

    @Override
    public void onResponse(final String username, final String password) {


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mDialog.dismiss();
                EMClient.getInstance().chatManager().loadAllConversations();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onError(final int i, final String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mDialog.dismiss();
                Log.d("lzan13", "登录失败 Error code:" + i + ", message:" + s);
                /**
                 * 关于错误码可以参考官方api详细说明
                 * http://www.easemob.com/apidoc/android/chat3.0/classcom_1_1hyphenate_1_1_e_m_error.html
                 */
                switch (i) {
                    // 网络异常 2
                    case EMError.NETWORK_ERROR:
                        Toast.makeText(LoginActivity.this, "网络错误 code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
                        break;
                    // 无效的用户名 101
                    case EMError.INVALID_USER_NAME:
                        Toast.makeText(LoginActivity.this, "无效的用户名 code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
                        break;
                    // 无效的密码 102
                    case EMError.INVALID_PASSWORD:
                        Toast.makeText(LoginActivity.this, "无效的密码 code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
                        break;
                    // 用户认证失败，用户名或密码错误 202
                    case EMError.USER_AUTHENTICATION_FAILED:
                        Toast.makeText(LoginActivity.this, "用户认证失败，用户名或密码错误 code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
                        break;
                    // 用户不存在 204
                    case EMError.USER_NOT_FOUND:
                        Toast.makeText(LoginActivity.this, "用户不存在 code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
                        break;
                    // 无法访问到服务器 300
                    case EMError.SERVER_NOT_REACHABLE:
                        Toast.makeText(LoginActivity.this, "无法访问到服务器 code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
                        break;
                    // 等待服务器响应超时 301
                    case EMError.SERVER_TIMEOUT:
                        Toast.makeText(LoginActivity.this, "等待服务器响应超时 code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
                        break;
                    // 服务器繁忙 302
                    case EMError.SERVER_BUSY:
                        Toast.makeText(LoginActivity.this, "服务器繁忙 code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
                        break;
                    // 未知 Server 异常 303 一般断网会出现这个错误
                    case EMError.SERVER_UNKNOWN_ERROR:
                        Toast.makeText(LoginActivity.this, "未知的服务器异常 code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
                        break;
                    default:
                        Toast.makeText(LoginActivity.this, "ml_sign_in_failed code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
    }

    public ProgressDialog createDialog() {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("登录中，请稍后...");
        return dialog;
    }
}
