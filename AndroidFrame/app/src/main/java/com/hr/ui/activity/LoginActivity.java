package com.hr.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hr.R;
import com.hr.base.BaseActivity;
import com.hr.bean.LoginInfo;
import com.hr.network.api.SubjectPostApi;
import com.hr.utils.SpUtil;
import com.hr.utils.net.http.HttpManager;
import com.hr.utils.net.listener.HttpOnNextListener;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Han on 2017/3/31.
 */

public class LoginActivity extends BaseActivity {

    @Bind(R.id.userName)
    EditText userName;
    @Bind(R.id.userPassword)
    EditText userPassword;
    @Bind(R.id.login)
    Button login;
    @Bind(R.id.register)
    Button register;

    @Override
    protected int getLayoutRes() {
        return R.layout.login;
    }

    //初始化
    @Override
    protected void init() {

    }


    @OnClick({R.id.login, R.id.register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                //调到主页面
                userLogin();
                break;
            case R.id.register:
                //注册界面
                startActivity(new Intent(this, RegisterActivity.class));
                //overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                break;
        }
    }

    private void userLogin() {
        String username = userName.getText().toString().trim();
        String userpassword = userPassword.getText().toString().trim();
        // TODO: 2017/4/10 账户信息待定
        //检测手机号是否正确
        //        if (TextUtils.isEmpty(username)) {
        //            ToastUtil.showSnackBar(this, "请输入手机号", null);
        //            return;
        //        } else if (!RegularUtil.isMobile(username)) {
        //            ToastUtil.showSnackBar(this, "请输入正确的手机号", null);
        //            return;
        //        } else if (TextUtils.isEmpty(userpassword)) {
        //            ToastUtil.showSnackBar(this, "请输入密码", null);
        //            return;
        //        } else {
        //            //"zpty1001", "123"
        //            SubjectPostApi postEntity = new SubjectPostApi(loginListener, this, SubjectPostApi.LOGIN)
        //                    .setLogin(username, userpassword);
        //            HttpManager manager = HttpManager.getInstance();
        //            manager.doHttpDeal(postEntity);
        //
        //        }

        SubjectPostApi postEntity = new SubjectPostApi(loginListener, this, SubjectPostApi.LOGIN)
                .setLogin("zpty1001", "123");
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(postEntity);
    }

    HttpOnNextListener<LoginInfo> loginListener = new HttpOnNextListener<LoginInfo>() {
        @Override
        public void onNext(LoginInfo loginInfo) {
            if (loginInfo != null) {
                SpUtil.putString("tocken", loginInfo.getLoginToken());
                SpUtil.putBoolean("isLogin", true);
                SpUtil.putString("account",loginInfo.getUserAccount());
                startActivity(new Intent(mActivity, MainActivity.class));
                finish();
            }
        }


    };
}
