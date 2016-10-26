package com.lanou.yueba.register.ui;

import android.app.ProgressDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.hyphenate.EMError;
import com.hyphenate.exceptions.HyphenateException;
import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;
import com.lanou.yueba.register.presenter.RegPresenter;

/**
 * Created by dllo on 16/10/24.
 */

public class RegisterActivity extends BaseActivity implements IRegView, View.OnClickListener {

    private EditText mName;
    private EditText mPassword;
    private EditText mSure;
    private Button mBtnSure;
    private ImageView mBack;
    private ProgressDialog mDialog;
    private RegPresenter mPresenter;

    @Override
    protected int setLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {

        mPresenter = new RegPresenter(this);
        mName = bindView(R.id.et_name_register);
        mPassword = bindView(R.id.et_password_register);
        mSure = bindView(R.id.et_password_sure_register);
        mBtnSure = bindView(R.id.btn_sure_register);
        mBack = bindView(R.id.iv_back_register);
        mDialog = createDialog();

    }

    @Override
    protected void initData() {

        mBtnSure.setOnClickListener(this);
        mBack.setOnClickListener(this);

    }

    public ProgressDialog createDialog() {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("注册中，请稍后...");
        return dialog;
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
    public void onResponse() {
        Log.d("RegisterActivity", "注册成功");
    }

    @Override
    public void onError(final Throwable error) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                HyphenateException e = (HyphenateException) error;
                int errorCode = e.getErrorCode();
                String message = e.getMessage();
                Log.d("register", String.format("sign up - errorCode:%d, errorMsg:%s", errorCode, e.getMessage()));
                switch (errorCode) {
                    // 网络错误
                    case EMError.NETWORK_ERROR:
                        Toast.makeText(RegisterActivity.this, "网络错误 code: " + errorCode + ", message:" + message, Toast.LENGTH_LONG).show();
                        break;
                    // 用户已存在
                    case EMError.USER_ALREADY_EXIST:
                        Toast.makeText(RegisterActivity.this, "用户已存在 code: " + errorCode + ", message:" + message, Toast.LENGTH_LONG).show();
                        break;
                    // 参数不合法，一般情况是username 使用了uuid导致，不能使用uuid注册
                    case EMError.USER_ILLEGAL_ARGUMENT:
                        Toast.makeText(RegisterActivity.this, "参数不合法，一般情况是username 使用了uuid导致，不能使用uuid注册 code: " + errorCode + ", message:" + message, Toast.LENGTH_LONG).show();
                        break;
                    // 服务器未知错误
                    case EMError.SERVER_UNKNOWN_ERROR:
                        Toast.makeText(RegisterActivity.this, "服务器未知错误 code: " + errorCode + ", message:" + message, Toast.LENGTH_LONG).show();
                        break;
                    case EMError.USER_REG_FAILED:
                        Toast.makeText(RegisterActivity.this, "账户注册失败 code: " + errorCode + ", message:" + message, Toast.LENGTH_LONG).show();
                        break;
                    default:
                        Toast.makeText(RegisterActivity.this, "ml_sign_up_failed code: " + errorCode + ", message:" + message, Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });


    }


    @Override
    public void onClick(View v) {

        String username = mName.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        String rePassword = mSure.getText().toString().trim();

        switch (v.getId()) {
            case R.id.btn_sure_register:

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

                if (TextUtils.isEmpty(rePassword)){
                    Log.d("RegisterActivity", "确定密码不能为空");
                    mSure.requestFocus();
                    break;
                }

                if (!password.equals(rePassword)){
                    Toast.makeText(this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                    mSure.setText("");
                    mSure.requestFocus();
                    break;

                }

                if (!TextUtils.isEmpty(username)&&!TextUtils.isEmpty(password) && password.equals(rePassword)){
                    mPresenter.startRequest(username, password);
                }
                break;
            case R.id.iv_back_register:
                onBackPressed();
                break;


        }
    }
}
