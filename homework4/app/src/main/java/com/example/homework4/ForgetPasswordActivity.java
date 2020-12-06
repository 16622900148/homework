package com.example.homework4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homework4.adapter.UserTypeAdapter;
import com.example.homework4.bean.User;
import com.example.homework4.util.CommonUtil;
import com.example.homework4.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class ForgetPasswordActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int REQUEST_CODE = 100;android:exported="true"

    private EditText mEtMobileNumber;
    private EditText mEtNewPassword;
    private EditText mEtConfirmPassword;
    private EditText mEtVerificationCode;

    private Button mBtnGetVerificationCode;
    private Button mBtnConfirm;
    private String mVerifyCode;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        mEtMobileNumber = findViewById(R.id.et_mobile_number);
        mEtNewPassword = findViewById(R.id.et_new_password);
        mEtConfirmPassword = findViewById(R.id.et_confirm_password);
        mEtVerificationCode = findViewById(R.id.et_verification_code);

        mBtnGetVerificationCode = findViewById(R.id.btn_get_verification_code);
        mBtnConfirm = findViewById(R.id.btn_confirm);
        initListener();
    }

    private void initListener(){
        mBtnGetVerificationCode.setOnClickListener(this);
        mBtnConfirm.setOnClickListener(this);
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
                    CommonUtil.hideKeyBoard(ForgetPasswordActivity.this, mEtMobileNumber);
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
                    CommonUtil.hideKeyBoard(ForgetPasswordActivity.this, mEtVerificationCode);
            }
        });
        mEtNewPassword.addTextChangedListener(new TextWatcher() {
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
                    CommonUtil.hideKeyBoard(ForgetPasswordActivity.this, mEtNewPassword);
            }
        });
        mEtConfirmPassword.addTextChangedListener(new TextWatcher() {
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
                    CommonUtil.hideKeyBoard(ForgetPasswordActivity.this, mEtConfirmPassword);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm://确定
                confirm();
                break;

            case R.id.btn_get_verification_code://获取验证码
                getVerificationCode();
                break;
        }
    }

    private void confirm() {
        String mobileNumberText = mEtMobileNumber.getText().toString();
        String newPasswordText = mEtNewPassword.getText().toString();
        String confirmPasswordText = mEtConfirmPassword.getText().toString();

        String verificationCodeText = mEtVerificationCode.getText().toString();
        if (TextUtils.isEmpty(mobileNumberText)) {
            ToastUtil.showToast(this, "手机号不能为空！");
            return;
        }
        if (TextUtils.isEmpty(newPasswordText)) {
            ToastUtil.showToast(this, "输入新密码！");
            return;
        }
        if (TextUtils.isEmpty(confirmPasswordText)) {
            ToastUtil.showToast(this, "确认密码不能为空！");
            return;
        }
        if (!newPasswordText.equals(confirmPasswordText)) {
            ToastUtil.showToast(this, "两次密码不一致！");
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
        ToastUtil.showToast(this, "修改成功！");
        Intent intent = getIntent();
        intent.putExtra("new_password", newPasswordText);
        setResult(Activity.RESULT_OK, intent);
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
}