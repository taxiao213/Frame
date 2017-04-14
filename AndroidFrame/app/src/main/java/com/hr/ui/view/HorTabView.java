package com.hr.ui.view;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hr.R;
import com.hr.utils.UIUtil;

import java.util.List;

import rx.functions.Action2;
import rx.functions.Func2;

/**
 * Created by aaa on 2017/4/6.
 */

public class HorTabView<T> {

    private Context context;
    private LinearLayout mOutLin;
    private LinearLayout mTabLin;
    private LinearLayout.LayoutParams params;
    private FrameLayout mHeadFrame;
    private List<T> mData;
    private View mVerLineView;

    private Action2<T,Integer> onItenClickListener;

    private int mHorLineWidth = 1;
    private int mHorLinColor = android.R.color.darker_gray;
    private int mVerLineWidth = 1;
    private int mVerLinColor = android.R.color.holo_red_dark;

    public HorTabView(Context context, LinearLayout.LayoutParams params,List<T> mData) {
        this.context = context;
        this.params = params;
        this.mData = mData;
        initView();
    }

    private void initView(){
        mOutLin = new LinearLayout(context);
        mOutLin.setLayoutParams(params);
        mOutLin.setOrientation(LinearLayout.VERTICAL);
        View view = LayoutInflater.from(context).inflate(R.layout.hor_tab_view,null);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mOutLin.addView(view);
        mTabLin = (LinearLayout) view.findViewById(R.id.out_hor_lin);
        mHeadFrame = (FrameLayout) view.findViewById(R.id.hor_tab_head);
//        mVerLineView = view.findViewById(R.id.ver_line_view);
    }

    public void setVerLinColorAndWidth(int colorRes,int width){
        mVerLineView.setBackgroundColor(UIUtil.getColor(colorRes));
        mVerLineView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,UIUtil.dip2px(width)));
    }

    public void setOnItenClickListener(Action2<T, Integer> onItenClickListener) {
        this.onItenClickListener = onItenClickListener;
    }

    public void setHorLineColorAndWidth(int color, int width){
        mHorLinColor = color;
        mHorLineWidth = width;
    }

    public void addHeadView(int res,int width){
        View view = LayoutInflater.from(context).inflate(res,null);
        if (view!=null){
            view.setLayoutParams(new FrameLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT));
            mHeadFrame.addView(view);
            mTabLin.addView(addHorLine());
        }
    }

    public <D extends View> D getHeaderView(int res){
        return (D) mHeadFrame.findViewById(res);
    }

    public void addItem(int res, Func2<T,View,View> func2){
        LayoutInflater inflater = LayoutInflater.from(context);
        if (mData.size()<1){
            TextView nullTv = new TextView(context);
            nullTv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            nullTv.setText("今天没有医生值班");
            nullTv.setPadding(UIUtil.dip2px(20),0,UIUtil.dip2px(20),0);
            nullTv.setGravity(Gravity.CENTER);
            //mTabLin.addView(nullTv);
        }
        for (int i = 0; i < mData.size(); i++) {
            final int pos = i;
            View view = inflater.inflate(res,null);
            view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            mTabLin.addView(func2.call(mData.get(i),view));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItenClickListener.call(mData.get(pos),pos);
                }
            });
            mTabLin.addView(addHorLine());
        }
    }



    private View addHorLine(){
        View linwView = new View(context);
        linwView.setLayoutParams(new LinearLayout.LayoutParams(UIUtil.dip2px(mHorLineWidth), ViewGroup.LayoutParams.MATCH_PARENT));
        linwView.setBackgroundColor(UIUtil.getColor(mHorLinColor));
        return linwView;
    }

    private View addVerLine(){
        View linwView = new View(context);
        linwView.setLayoutParams(new LinearLayout.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT,UIUtil.dip2px(mVerLineWidth)));
        linwView.setBackgroundColor(UIUtil.getColor(mVerLinColor));
        return linwView;
    }

    public LinearLayout show(){
        return mOutLin;
    }



}
