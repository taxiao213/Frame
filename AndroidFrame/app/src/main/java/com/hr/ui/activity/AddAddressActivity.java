package com.hr.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.hr.R;
import com.hr.base.BaseActivity;
import com.hr.network.api.SubjectPostApi;
import com.hr.ui.view.TitleBarView;
import com.hr.utils.LogUtil;
import com.hr.utils.ToastUtil;
import com.hr.utils.net.Api.BaseResultEntity;
import com.hr.utils.net.http.HttpManager;
import com.hr.utils.net.listener.HttpOnNextListener;
import com.hr.utils.text.RegularUtil;

import butterknife.Bind;
import butterknife.OnClick;

public class AddAddressActivity extends BaseActivity {
    // 登录账户，患者名字，患者电话，身份证，预约日期，科室姓名，医生，级别，医生编码。
    private String account, listname, listphone, sfz_text, datepre, deptname, doctor, reggrade, doctorcode, isChooseReservation;
    // 科室编码，午别（上午，下午）
    private int deptcode, midday;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity__add_address;
    }

    // 查看通讯录图片
    @Bind(R.id.go_address_book_image)
    ImageView go_address_imageView;
    // 名字输入框
    @Bind(R.id.name_ed_text)
    EditText name_ed_text;
    // 电话输入框
    @Bind(R.id.phone_ed_text)
    EditText phone_ed_text;
    // 身份证输入框
    @Bind(R.id.sfz_ed_text)
    EditText sfz_ed_text;
    // 家庭地址输入框
    @Bind(R.id.family_ed_text)
    EditText family_ed_text;
    // 确定预约按钮
    @Bind(R.id.reture_save_btn)
    Button reture_save_btn;
    // 标题栏
    @Bind(R.id.titleBar)
    TitleBarView mTitleBarView;

    @Override
    protected void init() {
        //标题栏 左右键处理
        mTitleBarView.setOnLeftClick(new TitleBarView.OnLeftClick() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mTitleBarView.setOnRightClick(new TitleBarView.OnRightClick() {
            @Override
            public void onClick(View view) {
                if (islegal()) {
                    request_Add_Addressbook(account, listname, listphone, sfz_text);
                }
            }
        });
        Intent intent = getIntent();
        // flag 判断是从新建过来还是通讯录过来
        String flag = intent.getStringExtra("flag");
        // 登录用户名 不是通讯录用户名
        account = intent.getStringExtra("account");
        // 这个标识是判断是否从科室传过来。
        isChooseReservation = intent.getStringExtra("isChooseReservation");
        if (isChooseReservation != null & isChooseReservation.equals("1")) {
            datepre = intent.getStringExtra("datepre");
            midday = intent.getIntExtra("midday", 0);
            deptname = intent.getStringExtra("deptname");
            doctor = intent.getStringExtra("doctor");
            reggrade = intent.getStringExtra("reggrade");
            deptcode = intent.getIntExtra("deptcode", 0);
            doctorcode = intent.getStringExtra("doctorcode");
        } else {
            reture_save_btn.setVisibility(View.GONE);
        }
        if (flag.equals("0")) {
            // 通讯录过来设置不可点击、
            mTitleBarView.setRightTv_visibility(false);
            name_ed_text.setText(intent.getStringExtra("listname"));
            name_ed_text.setClickable(false);
            name_ed_text.setEnabled(false);
            phone_ed_text.setText(intent.getStringExtra("listphone"));
            phone_ed_text.setClickable(false);
            phone_ed_text.setEnabled(false);
            sfz_ed_text.setText(intent.getStringExtra("certificate"));
            sfz_ed_text.setClickable(false);
            sfz_ed_text.setEnabled(false);
            family_ed_text.setClickable(false);
            family_ed_text.setEnabled(false);
        } else if (flag.equals("1")) {
            // 新建过来保存可见
            //save_text.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 判断输入框输入的内容是否合法
     *
     * @return boolean
     */
    private boolean islegal() {
        listname = name_ed_text.getText().toString();
        listphone = phone_ed_text.getText().toString();
        sfz_text = sfz_ed_text.getText().toString();
        if (!TextUtils.isEmpty(listname) && !TextUtils.isEmpty(listphone) && !TextUtils.isEmpty(sfz_text)) {
            // 验证一下身份证信息跟手机号信息
            if (RegularUtil.isMobile(listphone) && RegularUtil.isIDCard(sfz_text)) {
                return true;
            } else {
                ToastUtil.showToast("填写信息格式不合法");
                return false;
            }
        } else {
            ToastUtil.showToast("请填写必要信息");
            return false;
        }
    }

    @OnClick({R.id.go_address_book_image, R.id.reture_save_btn})
    public void OnClick(View view) {
        switch (view.getId()) {
            // 预约保存
            case R.id.reture_save_btn:
                if (islegal()) {
                    request_save_order(account, listname, listphone, sfz_text, datepre, midday, deptname, deptcode, doctor, doctorcode, reggrade);
                } else {
                    //
                }
                break;
            // 返回上一页面
            case R.id.go_address_book_image:
                startActivity(new Intent(mActivity,AddressBookActivity.class));
                AddAddressActivity.this.finish();
                break;
            default:
                break;
        }
    }

    /**
     * 添加请求方法
     */
    private void request_Add_Addressbook(String account, String listname, String listphone, String certificate) {
        SubjectPostApi postEntity = new SubjectPostApi(simpleOnNextListener, this, SubjectPostApi.ADD_ADDRESS)
                .setAddAdressBook(account, listname, listphone, certificate);
        HttpManager httpManager = HttpManager.getInstance();
        httpManager.doHttpDeal(postEntity);
    }

    /**
     * 保存预约方法
     */
    private void request_save_order(String account, String listname, String listphone, String certficate, String datepre, int midday, String deptname, int deptcode, String doctor, String doctorcode, String reggrade) {
        SubjectPostApi postApi = new SubjectPostApi(OnNextListener, this, SubjectPostApi.SAVE_ORDER_INFORMATION)
                .saveOrderInformation(account, listname, listphone, certficate, datepre, midday, deptcode, deptname, doctorcode, doctor, reggrade);
        HttpManager httpManager = HttpManager.getInstance();
        httpManager.doHttpDeal(postApi);
    }

    HttpOnNextListener<BaseResultEntity> simpleOnNextListener = new HttpOnNextListener<BaseResultEntity>() {
        @Override
        public void onNext(BaseResultEntity baseResultEntity) {
            //Toast.makeText(AddAddressActivity.this, "0" + , Toast.LENGTH_LONG).show();
            ToastUtil.showToast("" + baseResultEntity.getResMsg());
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            LogUtil.e(e.getMessage());
        }
    };
    HttpOnNextListener<BaseResultEntity> OnNextListener = new HttpOnNextListener<BaseResultEntity>() {
        @Override
        public void onNext(BaseResultEntity baseResultEntity) {
            //Toast.makeText(AddAddressActivity.this, "0" + , Toast.LENGTH_LONG).show();
              ToastUtil.showToast("" + baseResultEntity.getResMsg());
            // 跳转结果类，清除栈上activity信息
              startActivity(new Intent(mActivity, RegRecordActivity.class));
//            Intent intent = new Intent(mActivity,ReservationActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(intent);
              mActivity.finish();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            LogUtil.e(e.getMessage());
        }
    };
}
