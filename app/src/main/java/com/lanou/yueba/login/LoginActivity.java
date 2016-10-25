package com.lanou.yueba.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;
import com.lanou.yueba.main.MainActivity;
import com.lanou.yueba.register.RegisterActivity;

/**
 * Created by dllo on 16/10/24.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private Button mButtonSure;
    private LinearLayout mLl;
    private ProgressDialog mDialog;
    private EditText mName;
    private EditText mPassword;
    private TextView mRegister;

    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        mButtonSure = bindView(R.id.btn_sure_login);
        mLl = bindView(R.id.ll_login);
        mName = bindView(R.id.et_name_login);
        mPassword = bindView(R.id.et_password_login);
        mRegister = bindView(R.id.tv_register_login);
    }

    @Override
    protected void initData() {
        mButtonSure.setOnClickListener(this);
<<<<<<< HEAD
        mLl.setBackgroundResource(R.mipmap.login_background);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.login_background);
//        if (bitmap != null ){
//            Drawable drawable = new BitmapDrawable(getResources(), Tools.changackgroundImage(bitmap, 20.2f));
//            mLl.setBackground(drawable);
//        }
=======
        mRegister.setOnClickListener(this);
>>>>>>> 4ec1c96347526f1d426b66adfc348e26943ae846
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sure_login:
//                startActivity(new Intent(this, MainActivity.class));
                login();
                break;
            case R.id.tv_register_login:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }

    public void login() {
        mDialog = new ProgressDialog(this);
        mDialog.setMessage("正在登陆，请稍后...");
        mDialog.show();

        EMClient.getInstance().login(mName.getText().toString().trim(), mPassword.getText().toString().trim(), new EMCallBack() {
            @Override
            public void onSuccess() {
                //
                //                EMClient.getInstance().groupManager().loadAllGroups();
                //                EMClient.getInstance().chatManager().loadAllConversations();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mDialog.dismiss();
                        EMClient.getInstance().chatManager().loadAllConversations();
                        //                        Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });


            }

            @Override
            public void onError(final int i, final String s) {
                Log.d("MainActivity", "登录失败");
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

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }
}
