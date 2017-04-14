package com.hr.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hr.R;
import com.hr.base.BaseActivity;
import com.hr.bean.DocInfo;
import com.hr.bean.ListDataInfo;
import com.hr.network.api.SubjectPostApi;
import com.hr.ui.view.HorTabView;
import com.hr.ui.view.TitleBarView;
import com.hr.ui.view.dialog.DocTabDetialDialog;
import com.hr.utils.LogUtil;
import com.hr.utils.ToastUtil;
import com.hr.utils.net.http.HttpManager;
import com.hr.utils.net.listener.HttpOnNextListener;
import com.hr.utils.text.TimeUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.functions.Func2;

public class DayTabActivity extends BaseActivity {

    @Bind(R.id.main_lin)LinearLayout mMianLin;
    @Bind(R.id.titleBar)TitleBarView mTitleBar;

    private SparseArray<List<DocInfo>> mDayDocMap;
    private SparseArray<String> mDateMap;
    private String code;

    private long mOneTime;


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_day_tab;
    }

    @Override
    protected void init() {
        mTitleBar.setOnLeftClick(new TitleBarView.OnLeftClick() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mDayDocMap = new SparseArray<>();
        mDateMap = new SparseArray<>();
        code = getIntent().getStringExtra("code");
        if (code !=null){
            registerData(Integer.valueOf(code));
        }else {
            ToastUtil.showToast("程序异常！");
            finish();
        }
    }

    private void creatTabLin(List<DocInfo> listDataInfo){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,0);
        params.weight = 1;
        creatDocList(listDataInfo);
        int day = TimeUtil.getDayInWeek();
        for (int i = 0;i<7-day+1;i++){
            int tabDay = day+i;
            LogUtil.d("aadfasdf",System.currentTimeMillis()+"----"+mOneTime);
            mDateMap.put(tabDay,TimeUtil.getDateAfter(mOneTime!=0?mOneTime:System.currentTimeMillis(),i));
            if (mDayDocMap.get(tabDay)==null){
                mDayDocMap.put(tabDay,new ArrayList<DocInfo>());
            }
            HorTabView<DocInfo> horTabView = new HorTabView<>(mActivity,params,mDayDocMap.get(tabDay));
            horTabView.addHeadView(R.layout.day_item, LinearLayout.LayoutParams.WRAP_CONTENT);
            TextView tv = horTabView.getHeaderView(R.id.day_text);
            TextView dateTv = horTabView.getHeaderView(R.id.date_text);
            dateTv.setText(mDateMap.get(tabDay));
            tv.setText(String.valueOf("星期"+tabDay));
            horTabView.addItem(R.layout.tab_item, new Func2<DocInfo, View, View>() {
                @Override
                public View call(DocInfo docInfo, View view) {
                    TextView regTv = (TextView) view.findViewById(R.id.reg_tv);
                    regTv.setText(docInfo.getReggrade());
                    TextView nameTv = (TextView) view.findViewById(R.id.name_tv);
                    nameTv.setText(docInfo.getScheduleDoctorname());
                    if (docInfo.getNetLimit()>0){
                        docInfo.setNumIsOut(false);
                        if (docInfo.getWeek()==TimeUtil.getDayInWeek()){
                            if (timeIsOut(docInfo.getEndTime())){
                                docInfo.setTimeIsOut(false);
                                view.setBackgroundColor(0xff9988aa);
                            }else {
                                docInfo.setTimeIsOut(true);
                                view.setBackgroundColor(0xffa45866);
                            }
                        }else {
                            view.setBackgroundColor(0xff9988aa);
                        }
                    }else {
                        docInfo.setNumIsOut(true);
                        view.setBackgroundColor(0xffa45866);
                    }
                    return view;
                }
            });
            horTabView.setOnItenClickListener(new Action2<DocInfo, Integer>() {
                @Override
                public void call(DocInfo docInfo, Integer integer) {
                    new DocTabDetialDialog(mActivity,docInfo)
                            .setOnSureOnclick(new Action1<DocInfo>() {
                                @Override
                                public void call(DocInfo docInfo) {
                                    Intent intent = new Intent(mActivity, AddressBookActivity.class);
                                    //intent.putExtra("f",docInfo.getda);
                                    intent.putExtra("isChooseReservation","1");
                                    intent.putExtra("datepre",mDateMap.get(docInfo.getWeek()));
                                    intent.putExtra("midday",docInfo.getMidday());
                                    intent.putExtra("deptcode",docInfo.getScheduleWorkdept());
                                    intent.putExtra("deptname",docInfo.getScheduleDeptname());
                                    intent.putExtra("doctorcode",docInfo.getDoctor());
                                    intent.putExtra("doctor",docInfo.getScheduleDoctorname());
                                    intent.putExtra("reggrade",docInfo.getReggrade());;
                                    startActivity(intent);
                                }
                            });

                }
            });
            mMianLin.addView(horTabView.show());
        }
    }

    private boolean timeIsOut(String endTime){
        String endHour = endTime.substring(0,endTime.indexOf(":"));
        LogUtil.d("tab",endHour+"");
        Integer eh = Integer.valueOf(endHour);
        if (eh>TimeUtil.getHourOfDay()){
            return true;
        }else if (eh==TimeUtil.getHourOfDay()){
            String endMin = endTime.substring(endTime.indexOf(":")+1,endTime.length());
            LogUtil.d("tab",endMin+"");
            Integer em = Integer.valueOf(endMin);
            return em >= TimeUtil.getMinOfHour();
        }else {
            return false;
        }
    }

    private void registerData(int code){
        LogUtil.d("asdfa",code+"");
        SubjectPostApi api = new SubjectPostApi(httpOnNextListener,mActivity,SubjectPostApi.GET_DOC_LIST)
                .setGetDocList(code);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(api);
    }

    private void creatDocList(List<DocInfo> docInfos){
        for (DocInfo docInfo : docInfos) {
            if (mDayDocMap.get(docInfo.getWeek())==null){
                mDayDocMap.put(docInfo.getWeek(),new ArrayList<DocInfo>());
            }
            mDayDocMap.get(docInfo.getWeek()).add(docInfo);
        }
    }

    HttpOnNextListener<ListDataInfo<DocInfo>> httpOnNextListener = new HttpOnNextListener<ListDataInfo<DocInfo>>() {
        @Override
        public void onNext(ListDataInfo<DocInfo> docInfoListDataInfo) {
            mOneTime = docInfoListDataInfo.getNowDate();
            creatTabLin(docInfoListDataInfo.getData());
        }

        @Override
        public void onError(Throwable e) {
            creatTabLin(new ArrayList<DocInfo>());
            super.onError(e);

        }
    };

}
