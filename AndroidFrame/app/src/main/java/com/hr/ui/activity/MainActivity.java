package com.hr.ui.activity;

import android.content.Intent;
import android.view.View;

import com.hr.R;
import com.hr.base.BaseActivity;
import com.hr.ui.view.dialog.TipDialog;
import com.hr.utils.SpUtil;
import com.hr.utils.ToastUtil;
import com.hr.utils.UIUtil;

import butterknife.OnClick;
import rx.functions.Action0;

public class MainActivity extends BaseActivity {

    /** 双击返回按键退出的间隔时间 */
    public static final int TIME_INTERVAL_QUITE = 2000;
    private long mLastQuitTime;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
    }

    @OnClick({R.id.hos_register,R.id.register_manager,R.id.edit,R.id.search_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.hos_register:
                //进入科室选择
                startActivity(new Intent(this, ReservationActivity.class));
                break;
            case R.id.register_manager:
                //管理通讯录
                startActivity(new Intent(this, AddressBookActivity.class).putExtra("isChooseReservation","0"));
                break;
            case R.id.edit:
                //退出
                new TipDialog(mActivity)
                        .setTipText(UIUtil.getString(R.string.edit_tip))
                        .setSureOnCilck(new Action0() {
                            @Override
                            public void call() {
                                SpUtil.putBoolean("isLogin",false);
                                startActivity(new Intent(mActivity,LoginActivity.class));
                            }
                        });
                break;
            case R.id.search_register:
                startActivity(new Intent(mActivity,RegRecordActivity.class));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        long currTime = System.currentTimeMillis();
        if (currTime - mLastQuitTime > TIME_INTERVAL_QUITE) {
            mLastQuitTime = currTime;
            ToastUtil.showToast(getString(R.string.msg_press_again_quit));
            return;
        }
        this.finish();
        super.onBackPressed();
    }




}
