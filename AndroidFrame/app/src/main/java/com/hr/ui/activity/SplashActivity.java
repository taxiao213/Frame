package com.hr.ui.activity;

import android.content.Intent;

import com.hr.R;
import com.hr.base.BaseActivity;
import com.hr.utils.SpUtil;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends BaseActivity {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (SpUtil.getBoolean("isLogin",false)){
                    startActivity(new Intent(mActivity,MainActivity.class));
                    finish();
                }else {
                    startActivity(new Intent(mActivity,LoginActivity.class));
                    finish();
                }
            }
        },3000);
    }
}
