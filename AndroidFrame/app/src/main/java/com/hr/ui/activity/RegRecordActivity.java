package com.hr.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hr.R;
import com.hr.base.BaseActivity;
import com.hr.base.BaseAdapter;
import com.hr.base.RecyclerViewHolder;
import com.hr.bean.ListDataInfo;
import com.hr.bean.PreRegisterInfo;
import com.hr.network.api.SubjectPostApi;
import com.hr.ui.view.dialog.TipDialog;
import com.hr.ui.view.recyclerview.menu.OnActivityTouchListener;
import com.hr.ui.view.recyclerview.menu.RecyclerTouchListener;
import com.hr.utils.ToastUtil;
import com.hr.utils.net.Api.BaseResultEntity;
import com.hr.utils.net.http.HttpManager;
import com.hr.utils.net.listener.HttpOnNextListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.functions.Action0;

public class RegRecordActivity extends BaseActivity implements RecyclerTouchListener.RecyclerTouchListenerHelper{

    @Bind(R.id.record_rlv)RecyclerView mRlv;
    private List<PreRegisterInfo> mItemList;
    private BaseAdapter<PreRegisterInfo> adapter;

    private String json = "{\"data\":[{\"preregisterNo\":\"20170410001768\",\"preregisterIsnet\":1,\"preregisterIsphone\":0,\"preregisterDept\":\"8523\",\"preregisterExpert\":\"110016\",\"preregisterDate\":\"2017-04-14 00:00:00\",\"preregisterCertificatesno\":\"456789123480584331\",\"preregisterName\":\"dhdhhd\",\"preregisterPhone\":\"15745628534\",\"midday\":1,\"seeFlag\":2,\"appFlag\":2,\"isFirst\":1,\"status\":1,\"missNumber\":0,\"preregisterDeptName\":\"鼻科一\",\"preregisterExpertName\":\"赵玉林\",\"preregisterGradeName\":\"主治医师\",\"id\":\"402880a15b577c0b015b5783bfb60003\",\"createUser\":\"zpty1001\",\"createTime\":\"2017-04-10 18:58:23\",\"stop_flg\":0,\"del_flg\":0},{\"preregisterNo\":\"20170410001769\",\"preregisterIsnet\":1,\"preregisterIsphone\":0,\"preregisterDept\":\"8523\",\"preregisterExpert\":\"110016\",\"preregisterDate\":\"2017-04-14 00:00:00\",\"preregisterCertificatesno\":\"456789123480582569\",\"preregisterName\":\"dhdhhd\",\"preregisterPhone\":\"15745628534\",\"midday\":1,\"seeFlag\":2,\"appFlag\":2,\"isFirst\":1,\"status\":1,\"missNumber\":0,\"preregisterDeptName\":\"鼻科一\",\"preregisterExpertName\":\"赵玉林\",\"preregisterGradeName\":\"主治医师\",\"id\":\"402880a15b577c0b015b578431040004\",\"createUser\":\"zpty1001\",\"createTime\":\"2017-04-10 18:58:52\",\"stop_flg\":0,\"del_flg\":0}],\"resCode\":\"0\",\"resMsg\":\"预约信息查询成功！\"}";
    private RecyclerTouchListener onTouchListener;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_reg_record;
    }

    @Override
    protected void init() {
        mItemList = new ArrayList<>();
//        mItemList.addAll(new Gson().fromJson(json, Test.class).getData());
//        mItemList.addAll(new Gson().fromJson(json, Test.class).getData());
//        mItemList.addAll(new Gson().fromJson(json, Test.class).getData());
        creatRlv();
        creatData();
    }

    private void creatData(){
        SubjectPostApi api = new SubjectPostApi(httpOnNextListener,mActivity,SubjectPostApi.GET_REG_LIST)
                .getPreRegisterList();
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(api);
    }

    private void creatRlv(){
        mRlv.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new BaseAdapter<PreRegisterInfo>(mActivity,mItemList) {
            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.reg_item_layout;
            }

            @Override
            public void bindData(RecyclerViewHolder holder, int position, PreRegisterInfo item) {
                holder.setText(R.id.reg_no,String.valueOf("编号："+item.getPreregisterNo()));
                holder.setText(R.id.doc_name,String.valueOf("医生："+item.getPreregisterExpertName()));
                holder.setText(R.id.doc_category,String.valueOf("类别："+item.getPreregisterGradeName()));
                holder.setText(R.id.doc_department,String.valueOf("科室："+item.getPreregisterDeptName()));
                holder.setText(R.id.doc_time,String.valueOf("就诊时间："+item.getPreregisterDate()+"\b"
                        +(item.getMidday()==1?"上午":item.getMidday()==2?"下午":"晚上")));
                holder.setText(R.id.patient_name,String.valueOf("挂号人姓名："+item.getPreregisterName()));
                holder.setText(R.id.patient_phone,String.valueOf("联系电话："+item.getPreregisterPhone()));
                holder.setText(R.id.patient_code,String.valueOf("证件号码："+item.getPreregisterCertificatesno()));
            }
        };
        mRlv.setAdapter(adapter);
        onTouchListener = new RecyclerTouchListener(this, mRlv);
        onTouchListener
                .setClickable(new RecyclerTouchListener.OnRowClickListener() {
                    @Override
                    public void onRowClicked(int position) {
                        ToastUtil.showToast("这是");
                    }

                    @Override
                    public void onIndependentViewClicked(int independentViewID, int position) {
                    }
                })
                .setSwipeOptionViews(R.id.delete_tv)
                .setSwipeable(R.id.content, R.id.rowBG,new RecyclerTouchListener.OnSwipeOptionsClickListener() {
                    @Override
                    public void onSwipeOptionClicked(int viewID, int position) {
                        final PreRegisterInfo info = mItemList.get(position);
                        if (viewID==R.id.delete_tv){
                            new TipDialog(mActivity)
                                    .setTipText("是否确认删除")
                                    .setSureOnCilck(new Action0() {
                                        @Override
                                        public void call() {
                                            HttpManager.getInstance()
                                                    .doHttpDeal(new SubjectPostApi(delPreregisterListener
                                                            ,mActivity,SubjectPostApi.DEL_PREREGISTER).delPreregister(info));
                                        }
                                    });
                        }
                    }
                });
    }

    HttpOnNextListener<BaseResultEntity> delPreregisterListener = new HttpOnNextListener<BaseResultEntity>() {
        @Override
        public void onNext(BaseResultEntity baseResultEntity) {
            ToastUtil.showToast(baseResultEntity.getResMsg());
            creatData();
        }
    };

    HttpOnNextListener<ListDataInfo<PreRegisterInfo>> httpOnNextListener = new HttpOnNextListener<ListDataInfo<PreRegisterInfo>>() {
        @Override
        public void onNext(ListDataInfo<PreRegisterInfo> preRegisterInfoListDataInfo) {
            mItemList.clear();
            mItemList.addAll(preRegisterInfoListDataInfo.getData());
            adapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        mRlv.addOnItemTouchListener(onTouchListener); }

    @Override
    protected void onPause() {
        super.onPause();
        mRlv.removeOnItemTouchListener(onTouchListener);
    }
    @Override
    public void setOnActivityTouchListener(OnActivityTouchListener listener) {

    }
}
