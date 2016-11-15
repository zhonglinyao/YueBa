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

import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;
import com.lanou.yueba.login.presenter.LoginPresenter;
import com.lanou.yueba.main.MainActivity;
import com.lanou.yueba.register.ui.RegisterActivity;
import com.lanou.yueba.tools.ActivityTools;

import com.lanou.yueba.tools.ToastTools;


/**
 * Created by dllo on 16/10/24.
 */

public class LoginActivity extends BaseActivity implements ILoginView, View.OnClickListener {

    public static final String PASSWORD = "password";
    private static final int REQUEST = 101;
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
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(MainActivity.USERNAME, EMClient.getInstance().getCurrentUser());
            startActivity(intent);
            ActivityTools.deleteActivity(this.getClass().getSimpleName());
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

                    ToastTools.showShort(LoginActivity.this, "用户名不能为空");
                    mName.requestFocus();
                    break;
                }
                if (TextUtils.isEmpty(password)) {
                    ToastTools.showShort(LoginActivity.this, "密码不能为空");
                    mPassword.requestFocus();
                    break;
                }
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                    mPresenter.startRequest(username, password);
                }
                break;
            case R.id.tv_register_login:

                startActivityForResult(new Intent(this, RegisterActivity.class), REQUEST);

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (REQUEST == requestCode && RegisterActivity.RESULT == resultCode) {
            mName.setText(data.getStringExtra(MainActivity.USERNAME));
            mPassword.setText(data.getStringExtra(PASSWORD));

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
        mDialog.dismiss();
        EMClient.getInstance().chatManager().loadAllConversations();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);

        intent.putExtra(MainActivity.USERNAME, username);

        startActivity(intent);
        ActivityTools.deleteActivity(this.getClass().getSimpleName());
    }

    @Override
    public void onError(final int i, final String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mDialog.dismiss();
                Log.d("失败", "登录失败 Error code:" + i + ", message:" + s);
                switch (i) {
                    // 网络异常 2
                    case EMError.NETWORK_ERROR:
                        ToastTools.showShort(LoginActivity.this, "网络错误");
                        break;
                    // 无效的用户名 101
                    case EMError.INVALID_USER_NAME:
                        ToastTools.showShort(LoginActivity.this, "无效的用户名");
                        break;
                    // 无效的密码 102
                    case EMError.INVALID_PASSWORD:
                        ToastTools.showShort(LoginActivity.this, "无效的密码");
                        break;
                    // 用户认证失败，用户名或密码错误 202
                    case EMError.USER_AUTHENTICATION_FAILED:
                        ToastTools.showShort(LoginActivity.this, "用户认证失败，用户名或密码错误");
                        break;
                    // 用户不存在 204
                    case EMError.USER_NOT_FOUND:
                        ToastTools.showShort(LoginActivity.this, "用户不存在");
                        break;
                    // 无法访问到服务器 300
                    case EMError.SERVER_NOT_REACHABLE:
                        ToastTools.showShort(LoginActivity.this, "无法访问到服务器");
                        break;
                    // 等待服务器响应超时 301
                    case EMError.SERVER_TIMEOUT:
                        ToastTools.showShort(LoginActivity.this, "等待服务器响应超时");
                        break;
                    // 服务器繁忙 302
                    case EMError.SERVER_BUSY:
                        ToastTools.showShort(LoginActivity.this, "服务器繁忙");
                        break;
                    // 未知 Server 异常 303 一般断网会出现这个错误
                    case EMError.SERVER_UNKNOWN_ERROR:
                        ToastTools.showShort(LoginActivity.this, "未知的服务器异常");
                        break;
                    default:
                        ToastTools.showShort(LoginActivity.this, "其他错误");
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
