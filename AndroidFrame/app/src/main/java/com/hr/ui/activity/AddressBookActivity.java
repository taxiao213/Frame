package com.hr.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.hr.R;
import com.hr.base.BaseActivity;
import com.hr.base.BaseAdapter;
import com.hr.base.RecyclerViewHolder;
import com.hr.bean.AddressBook;
import com.hr.bean.AdressListInfo;
import com.hr.network.api.SubjectPostApi;
import com.hr.ui.view.TitleBarView;
import com.hr.ui.view.dialog.TipDialog;
import com.hr.utils.LogUtil;
import com.hr.utils.ToastUtil;
import com.hr.utils.diver.RecycleViewDivider;
import com.hr.utils.net.Api.BaseResultEntity;
import com.hr.utils.net.http.HttpManager;
import com.hr.utils.net.listener.HttpOnNextListener;

import java.util.List;

import butterknife.Bind;
import rx.functions.Action0;

/**
 * Created by syx on 2017/3/31.
 */
public class AddressBookActivity extends BaseActivity {
    // 通讯录集合
    private List<AddressBook> data;
    HttpManager httpManager;
    // 预约日期，科室名称，医生code，医生级别，医生，是否为通讯录过来的标识。
    private String datepre, deptname, doctorcode, reggrade, doctor, isChooseReservation;
    // 科室code，午别。
    private int deptcode, midday;
    @Override
    protected int getLayoutRes() {
        return R.layout.addressbook;
    }
    // recyclerView 集合控件
    @Bind(R.id.addressbook_recyclerview)
    RecyclerView recyclerView;
    // 标题栏
    @Bind(R.id.titleBar)
    TitleBarView mTitleBarView;

    @Override
    protected void init() {
        Intent intent = getIntent();
        isChooseReservation = intent.getStringExtra("isChooseReservation");
        // 从上一个页面获取数值，参数数据。
        if (!TextUtils.isEmpty(isChooseReservation) && "1".equals(isChooseReservation)) {
            datepre = intent.getStringExtra("datepre");
            midday = intent.getIntExtra("midday", 0);
            deptname = intent.getStringExtra("deptname");
            doctorcode = intent.getStringExtra("doctorcode");
            reggrade = intent.getStringExtra("reggrade");
            deptcode = intent.getIntExtra("deptcode", 0);
            doctor = intent.getStringExtra("doctor");
        }
        // 进入页面请求通讯录接口
        requestAddressbook();
        // 标题栏处理
        mTitleBarView.setOnLeftClick(new TitleBarView.OnLeftClick() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mTitleBarView.setOnRightClick(new TitleBarView.OnRightClick() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mActivity, AddAddressActivity.class);
                // 有待解决,
                intent.putExtra("account", data.get(0).getMoblie());
                // 1 代表 新建，会有保存按钮
                intent.putExtra("flag", "1");
                intent = startintent(intent);
                isjulde(isChooseReservation, intent);
                startActivity(intent);

            }
        });
    }
    private void creatRv(List<AddressBook> list) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        BaseAdapter<AddressBook> adapter = new BaseAdapter<AddressBook>(mActivity, list) {
            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.address_item;
            }

            @Override
            public void bindData(RecyclerViewHolder holder, final int position, final AddressBook item) {
                holder.setText(R.id.name_text, item.getName());
                holder.setText(R.id.tel_num_text, "Tel:" + item.getPhone());
                holder.setText(R.id.sfz_text, "身份证：" + item.getCertificate());
                holder.getTextView(R.id.detele_address_item_text).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new TipDialog(mActivity).setTipText("确认删除？").setSureOnCilck(new Action0() {
                            @Override
                            public void call() {
                                Log.e("listid", "call:id= " + item.getCurrent_id());
                                requestDeteleAddressBook(item.getCurrent_id());
                            }
                        });
                    }
                });
            }
        };
        recyclerView.addItemDecoration(new RecycleViewDivider(
                mActivity, LinearLayoutManager.VERTICAL, R.drawable.address_item_diver));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pos) {
                Intent intent = new Intent(AddressBookActivity.this, AddAddressActivity.class);
                // 区分是 通讯录是原有的还是新建，以便保存按钮的显示与否。
                intent.putExtra("flag", "0");
                intent.putExtra("account", data.get(pos).getMoblie());
                intent.putExtra("listname", data.get(pos).getName());
                intent.putExtra("listphone", data.get(pos).getPhone());
                intent.putExtra("certificate", data.get(pos).getCertificate());
                // 因为好多重复调用的intent，所以定义了一个方法。
                intent = startintent(intent);
                isjulde(isChooseReservation, intent);
                startActivity(intent);
            }
        });
    }
    /**
     * 请求通讯录接口
     */
    private void requestAddressbook() {
        SubjectPostApi postEntity = new SubjectPostApi(simpleOnNextListener, this, SubjectPostApi.GET_ADDRESS)
                .setGetAdressBook("zpty1001");
        httpManager = HttpManager.getInstance();
        httpManager.doHttpDeal(postEntity);
    }
    /**
     * 删除联系人
     */
    private void requestDeteleAddressBook(String listid) {
        SubjectPostApi postEntity = new SubjectPostApi(deteleOnNextListener, mActivity, SubjectPostApi.DETEDE_ADDRESS).deteleAddAdressBook(listid);
        httpManager = HttpManager.getInstance();
        httpManager.doHttpDeal(postEntity);
    }
    // 因为返回参数不同，实体类不同，所以定义两个listener，相同可以复用一个。
    HttpOnNextListener<AdressListInfo> simpleOnNextListener = new HttpOnNextListener<AdressListInfo>() {
        @Override
        public void onNext(AdressListInfo adressListInfo) {
            data = adressListInfo.getList();
            creatRv(adressListInfo.getList());
        }
        @Override
        public void onError(Throwable e) {
            super.onError(e);
            LogUtil.e(e.getMessage());
        }
    };
    HttpOnNextListener<BaseResultEntity> deteleOnNextListener = new HttpOnNextListener<BaseResultEntity>() {
        @Override
        public void onNext(BaseResultEntity baseResultEntity) {
            ToastUtil.showToast("" + baseResultEntity.getResMsg());
        }
        @Override
        public void onError(Throwable e) {
            super.onError(e);
            LogUtil.e(e.getMessage());
        }
    };
    /**
     * 给intent加put属性
     *
     * @param intent
     * @return intent
     */
    private Intent startintent(Intent intent) {
        intent.putExtra("datepre", datepre);
        intent.putExtra("midday", midday);
        intent.putExtra("deptname", deptname);
        intent.putExtra("doctorcode", doctorcode);
        intent.putExtra("reggrade", reggrade);
        intent.putExtra("deptcode", deptcode);
        intent.putExtra("doctor", doctor);
        return intent;
    }

    /**
     * 判断标示
     *
     * @param isChooseReservation intent
     * @return boolean
     */
    private void isjulde(String isChooseReservation, Intent intent) {
        if (!TextUtils.isEmpty(isChooseReservation)) {
            if (isChooseReservation.equals("1")) {
                intent.putExtra("isChooseReservation", "1");
            } else if (isChooseReservation.equals("0")) {
                intent.putExtra("isChooseReservation", "0");
            }
        } else {
        }
    }
}
