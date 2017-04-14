package com.hr.ui.view.dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.hr.R;

import rx.functions.Action0;


/**
 * Created by zhangjutao on 16/10/9.
 */
public class TipDialog {

    private AlertDialog mAlertDialog;
    private TextView tipTv;
    private TextView okTv;
    private TextView cancleTv;

    public TipDialog(Context context) {
        this.mAlertDialog = new AlertDialog
                .Builder(context)
                .setCancelable(true)
                .create();
        mAlertDialog.show();

        init();
    }
    public TipDialog setTipText(String str){
        tipTv.setText(str);
        return this;
    }

    private void init() {
        Window window = mAlertDialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setLayout(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window.setContentView(R.layout.tip_dialpg_layout);
        tipTv = (TextView) window.findViewById(R.id.tip_text);
        okTv = (TextView) window.findViewById(R.id.ok_click);
        cancleTv = (TextView) window.findViewById(R.id.cancle_click);
        cancleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertDialog.dismiss();
            }
        });
    }

    public TipDialog setSureOnCilck(final Action0 action){
        okTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action.call();
                mAlertDialog.dismiss();
            }
        });
        return this;
    }
}
