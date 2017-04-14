package com.hr.ui.view.dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.hr.R;
import com.hr.bean.DocInfo;
import com.hr.utils.text.TimeUtil;

import rx.functions.Action1;

/**
 * Created by aaa on 2017/4/6.
 */

public class DocTabDetialDialog  {

    private AlertDialog mAlertDialog;
    private DocInfo info;
    private TextView sure;

    public DocTabDetialDialog(Context context,DocInfo info) {
        this.info = info;
        this.mAlertDialog = new AlertDialog
                .Builder(context)
                .setCancelable(true)
                .create();
        mAlertDialog.show();
        init();
    }

    private void init() {
        Window window = mAlertDialog.getWindow();
        window.setBackgroundDrawableResource(R.color.transportant);
        window.setGravity(Gravity.CENTER);
        window.setLayout(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window.setContentView(R.layout.doc_tab_detail);
        TextView regTv = (TextView) window.findViewById(R.id.doc_reg);
        regTv.setText(info.getReggrade());
        TextView nameTv = (TextView) window.findViewById(R.id.doc_name);
        nameTv.setText(info.getScheduleDoctorname());
        TextView midTime = (TextView) window.findViewById(R.id.time_mid);
        if (info.getMidday()==1){
            midTime.setText("上午");
        }else if (info.getMidday()==2){
            midTime.setText("下午");
        }else {
            midTime.setText("晚上");
        }
        TextView time = (TextView) window.findViewById(R.id.start_and_end);
        time.setText(info.getStartTime()+"—"+info.getEndTime());
        TextView num = (TextView) window.findViewById(R.id.limit_num);
        num.setText("剩余"+info.getNetLimit()+"张号源");
        TextView cancle = (TextView) window.findViewById(R.id.cancle);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertDialog.dismiss();
            }
        });
        sure = (TextView) window.findViewById(R.id.sure);
        View lineView = window.findViewById(R.id.bottom_line);
        if (info.isNumIsOut()){
            sure.setVisibility(View.GONE);
            lineView.setVisibility(View.GONE);
        }else {
            if (TimeUtil.getDayInWeek()==info.getWeek()){
                if (info.isTimeIsOut()){
                    sure.setVisibility(View.GONE);
                    lineView.setVisibility(View.GONE);
                }
            }
        }
    }

    public DocTabDetialDialog setOnSureOnclick(final Action1<DocInfo> action0){
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action0.call(info);
                mAlertDialog.dismiss();
            }
        });
        return this;
    }
}
