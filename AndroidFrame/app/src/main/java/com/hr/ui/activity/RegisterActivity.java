package com.hr.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.hr.R;
import com.hr.base.BaseActivity;
import com.hr.ui.view.TitleBarView;
import com.hr.utils.ToastUtil;
import com.hr.utils.text.RegularUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Han on 2017/3/31.
 */

public class RegisterActivity extends BaseActivity {

    @Bind(R.id.et_registerUserName)
    EditText etRegisterUserName;
    @Bind(R.id.et_registerUserPassword)
    EditText etRegisterUserPassword;
    @Bind(R.id.et_registerUserPasswordSecond)
    EditText etRegisterUserPasswordSecond;
    @Bind(R.id.titleBar)
    TitleBarView titleBarView;

    @Override
    protected int getLayoutRes() {
        return R.layout.register;
    }

    @Override
    protected void init() {
        titleBarView.setOnLeftClick(new TitleBarView.OnLeftClick() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @OnClick(R.id.bt_register)
    public void onClick() {
        //获取手机号
        String phone = getPhone();
        //获取密码
        String Password = etRegisterUserPassword.getText().toString().trim();
        String SecondPassword = etRegisterUserPasswordSecond.getText().toString().trim();
        //检测手机号是否正确
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showSnackBar(this, "请输入手机号", null);
            return;
        } else if (!RegularUtil.isMobile(phone)) {
            ToastUtil.showSnackBar(this, "请输入正确的手机号", null);
            return;
        } else if (TextUtils.isEmpty(Password) && TextUtils.isEmpty(SecondPassword)) {
            ToastUtil.showSnackBar(this, "密码不能为空", null);
            return;
        } else if (!TextUtils.equals(Password, SecondPassword)) {
            ToastUtil.showSnackBar(this, "密码不一致，请重新输入", null);
            return;
        } else {
            // TODO: 2017/4/1 并保存信息
            ToastUtil.showSnackBar(this, "注册成功", null);
            startActivity(new Intent(this, MainActivity.class));
        }

    }


    /**
     * 获取手机号
     *
     * @return
     */
    private String getPhone() {
        String phone = etRegisterUserName.getText().toString().trim();
        return phone;
    }


}
