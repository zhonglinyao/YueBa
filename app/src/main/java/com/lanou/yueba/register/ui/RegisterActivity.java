package com.lanou.yueba.register.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.hyphenate.EMError;
import com.hyphenate.exceptions.HyphenateException;
import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;
import com.lanou.yueba.login.ui.LoginActivity;
import com.lanou.yueba.main.MainActivity;
import com.lanou.yueba.register.presenter.RegPresenter;
import com.lanou.yueba.tools.ToastTools;

/**
 * Created by dllo on 16/10/24.
 */

public class RegisterActivity extends BaseActivity implements IRegView, View.OnClickListener {

    public static final int RESULT = 202;
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
    public void onResponse(String username,String password) {
        Intent intent = new Intent();
        intent .putExtra(MainActivity.USERNAME,username)
                .putExtra(LoginActivity.PASSWORD,password);
        setResult(RESULT,intent);
        finish();

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
                        ToastTools.showShort(RegisterActivity.this,"网络错误");
                       break;
                    // 用户已存在
                    case EMError.USER_ALREADY_EXIST:
                        ToastTools.showShort(RegisterActivity.this,"用户已存在");
                       break;
                    // 参数不合法，一般情况是username 使用了uuid导致，不能使用uuid注册
                    case EMError.USER_ILLEGAL_ARGUMENT:
                        ToastTools.showShort(RegisterActivity.this,"参数不合法");
                       break;
                    // 服务器未知错误
                    case EMError.SERVER_UNKNOWN_ERROR:
                        ToastTools.showShort(RegisterActivity.this,"服务器未知错误");
                       break;
                    case EMError.USER_REG_FAILED:

                        ToastTools.showShort(RegisterActivity.this,"账户注册失败");

                       break;
                    default:
                        ToastTools.showShort(RegisterActivity.this,"其他错误");

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

                    ToastTools.showShort(RegisterActivity.this,"用户名不能为空");
                    mName.requestFocus();
                    break;
                }
                if (TextUtils.isEmpty(password)) {
                    ToastTools.showShort(RegisterActivity.this,"密码不能为空");
                    mPassword.requestFocus();
                    break;
                }

                if (TextUtils.isEmpty(rePassword)){
                    ToastTools.showShort(RegisterActivity.this,"确定密码不能为空");
                    mSure.requestFocus();
                    break;
                }

                if (!password.equals(rePassword)){
                    ToastTools.showShort(RegisterActivity.this,"两次输入的密码不一致");

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
                finish();
                break;
        }
    }
}
