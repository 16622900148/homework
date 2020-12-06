package com.example.homework4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.homework4.adapter.UserTypeAdapter;
import com.example.homework4.bean.User;
import com.example.homework4.util.CommonUtil;
import com.example.homework4.util.SPUtil;
import com.example.homework4.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static String TAG = LoginActivity.class.getSimpleName();

    private EditText mEtMobileNumber;
    private EditText mEtVerificationCode;
    private Spinner mSpinner;
    private LinearLayout mLlVerificationContainer;
    private LinearLayout mLlRememberPassword;
    private LinearLayout mLlPwdContainer;
    private EditText mEtPassword;
    private Button mBtnForgetPassword;

    private Button mBtnGetVerificationCode;
    private Button mBtnLogin;
    private Switch mSwitchRememberPassword;
    private RadioButton mRbPasswordLogin;
    private RadioButton mRbVerificationCode;

    private final static int PASSWORD_LOGIN = 1;
    private final static int VERIFICATION_CODE_LOGIN = 2;
    private int mCurrentMode = PASSWORD_LOGIN;
    private String mVerifyCode;
    private String mPassword;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mRbPasswordLogin = findViewById(R.id.rb_password_login);
        mRbVerificationCode = findViewById(R.id.rb_verification_code_login);

        mEtMobileNumber = findViewById(R.id.et_mobile_number);
        mLlPwdContainer = findViewById(R.id.ll_pwd_container);
        mEtPassword = findViewById(R.id.et_password);
        mBtnForgetPassword = findViewById(R.id.btn_forget_password);

        mSpinner = findViewById(R.id.spinner_list);
        mLlVerificationContainer = findViewById(R.id.ll_verification_container);
        mEtVerificationCode = findViewById(R.id.et_verification_code);
        mBtnGetVerificationCode = findViewById(R.id.btn_get_verification_code);

        mLlRememberPassword = findViewById(R.id.ll_remember_password);
        mSwitchRememberPassword = findViewById(R.id.switch_remember_password);
        mBtnLogin = findViewById(R.id.btn_login);
        initData();
        initListener();
    }

    private void initData() {
        setSpinnerData();
        setLoginMode(PASSWORD_LOGIN);
        String account = SPUtil.getInstance(App.getInstance()).getString(SPUtil.ACCOUNT);
        if (!TextUtils.isEmpty(account)) {
            mEtMobileNumber.setText(account);
            mEtMobileNumber.setSelection(account.length());
        }

        if (SPUtil.getInstance(App.getInstance()).getBoolean(SPUtil.REMEMBER_PASSWORD, false)) {
            mSwitchRememberPassword.setChecked(true);
            String password = SPUtil.getInstance(App.getInstance()).getString(SPUtil.PASSWORD);
            mEtPassword.setText(password);
            mEtPassword.setSelection(password.length());
        }
    }

    private void initListener() {
        mRbPasswordLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLoginMode(PASSWORD_LOGIN);
            }
        });
        mRbVerificationCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLoginMode(VERIFICATION_CODE_LOGIN);
            }
        });

        mEtMobileNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editable.toString();
                if (11 == text.length())
                    CommonUtil.hideKeyBoard(LoginActivity.this, mEtMobileNumber);
            }
        });
        mEtVerificationCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editable.toString();
                if (6 == text.length())
                    CommonUtil.hideKeyBoard(LoginActivity.this, mEtVerificationCode);
            }
        });

        mEtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editable.toString();
                if (6 == text.length())
                    CommonUtil.hideKeyBoard(LoginActivity.this, mEtPassword);
            }
        });

        mSwitchRememberPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean check) {
                Log.i(TAG, " mSwitchRememberPassword onCheckedChanged check=" + check);
                SPUtil.getInstance(App.getInstance()).pubBoolean(SPUtil.REMEMBER_PASSWORD, check);
            }
        });
        mBtnLogin.setOnClickListener(this);
        mBtnGetVerificationCode.setOnClickListener(this);
        mBtnForgetPassword.setOnClickListener(this);
    }

    private void setLoginMode(int mode) {
        mCurrentMode = mode;
        if (PASSWORD_LOGIN == mode) {
            mLlPwdContainer.setVisibility(View.VISIBLE);
            mLlVerificationContainer.setVisibility(View.GONE);
            mRbPasswordLogin.setChecked(true);
            mRbVerificationCode.setChecked(false);
            mLlRememberPassword.setVisibility(View.VISIBLE);

        } else {
            mLlPwdContainer.setVisibility(View.GONE);
            mLlVerificationContainer.setVisibility(View.VISIBLE);
            mRbPasswordLogin.setChecked(false);
            mRbVerificationCode.setChecked(true);
            mLlRememberPassword.setVisibility(View.GONE);
        }
    }

    private void setSpinnerData() {
        List<User> mSelectList = new ArrayList<>();
        mSelectList.add(new User("个人用户"));
        mSelectList.add(new User("企业用户"));
        mSelectList.add(new User("超级管理员"));
        mSelectList.add(new User("张晓雨-18990303"));
        UserTypeAdapter adapter = new UserTypeAdapter(this, mSelectList);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mSpinner.setSelection(mSelectList.size()-1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login://登录
                login();
                break;

            case R.id.btn_get_verification_code://获取验证码
                getVerificationCode();
                break;

            case R.id.btn_forget_password://忘记密码
                forgetPassword();
                break;
        }
    }

    private void login() {
        if (PASSWORD_LOGIN == mCurrentMode) {
            String mobileNumberText = mEtMobileNumber.getText().toString();
            String passwordText = mEtPassword.getText().toString();
            if (TextUtils.isEmpty(mobileNumberText)) {
                ToastUtil.showToast(this, "手机号不能为空！");
                return;
            }
            if (TextUtils.isEmpty(passwordText)) {
                ToastUtil.showToast(this, "密码不能为空！");
                return;
            }
            SPUtil.getInstance(App.getInstance()).putString(SPUtil.ACCOUNT, mobileNumberText);
            if (mSwitchRememberPassword.isChecked()) {
                SPUtil.getInstance(App.getInstance()).putString(SPUtil.PASSWORD, passwordText);
            }
        } else {
            String mobileNumberText = mEtMobileNumber.getText().toString();
            String verificationCodeText = mEtVerificationCode.getText().toString();
            if (TextUtils.isEmpty(mobileNumberText)) {
                ToastUtil.showToast(this, "手机号不能为空！");
                return;
            }
            if (TextUtils.isEmpty(verificationCodeText)) {
                ToastUtil.showToast(this, "验证码不能为空！");
                return;
            }
            if (!verificationCodeText.equals(mVerifyCode)) {
                ToastUtil.showToast(this, "验证码输入错误！");
                return;
            }

            SPUtil.getInstance(App.getInstance()).putString(SPUtil.ACCOUNT, mobileNumberText);
        }
        ToastUtil.showToast(this, "登录成功！");
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void getVerificationCode() {
        String mobileNumberText = mEtMobileNumber.getText().toString();
        if (mobileNumberText == null || mobileNumberText.length() < 11) {
            Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        // 生成六位随机数字的验证码
        mVerifyCode = String.format("%06d", (int) ((Math.random() * 9 + 1) * 100000));
        // 弹出提醒对话框，提示用户六位验证码数字
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请记住验证码");
        builder.setMessage("手机号" + mobileNumberText + "，本次验证码是" + mVerifyCode + "，请输入验证码");
        builder.setPositiveButton("好的", null);
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void forgetPassword() {
        startActivityForResult(new Intent(this, ForgetPasswordActivity.class), ForgetPasswordActivity.REQUEST_CODE);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ForgetPasswordActivity.REQUEST_CODE && resultCode == Activity.RESULT_OK){
            if(data != null){
                mPassword = data.getStringExtra("new_password");
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mEtPassword.setText("");
    }
}