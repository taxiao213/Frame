package com.hr.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hr.R;


/**
 * Created by zhangjutao on 16/9/18.
 */
public class TitleBarView extends RelativeLayout {

    /**
     * 中间标题
     */
    private String title;
    /**
     * 左边文字标题
     */
    private String leftTitle;
    /**
     * 右边标题文字
     */
    private String rightTitle;
    /**
     * 标题文字尺寸
     */
    private float titleTextSize;
    /**
     * 左边文字尺寸
     */
    private float leftTextSize;
    /**
     * 右边文字尺寸
     */
    private float rightTextSize;
    /**
     * 标题文字颜色
     */
    private int titleTextColor;
    /**
     * 左边标题文字颜色
     */
    private int leftTextColor;
    /**
     * 右边标题文字颜色
     */
    private int rightTextColor;
    /**
     * 左边按钮图片
     */
    private Drawable leftBtnImg;
    /**
     * 右边按钮图片
     */
    private Drawable rightBtnImage;
    private int leftPaddingLeft;
    private int leftPaddingRight;
    private int leftDrawablePadding;
    private int rightPaddingLeft;
    private int rightPaddingRight;
    private TextView rightTv;

    private OnLeftClick onLeftClick;
    private OnRightClick onRightClick;
    private OnCenterClick onCenterClick;

    public TitleBarView(Context context) {
        super(context, null);
    }

    public TitleBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
        initView(context);
    }

    public void setRightTv_visibility(boolean flag) {
        if (rightTv != null) {
            if (flag) {
                rightTv.setVisibility(VISIBLE);
            } else {
                rightTv.setVisibility(GONE);
            }
        }
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Title_bar);
        title = typedArray.getString(R.styleable.Title_bar_titleText);
        leftTitle = typedArray.getString(R.styleable.Title_bar_leftBtnText);
        rightTitle = typedArray.getString(R.styleable.Title_bar_rightBtnText);
        titleTextSize = typedArray.getDimension(R.styleable.Title_bar_titleTextSize, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
        leftTextSize = typedArray.getDimension(R.styleable.Title_bar_leftBtnTextSize, 28f);
        rightTextSize = typedArray.getDimension(R.styleable.Title_bar_rightBtnTextSize, 28f);
        titleTextColor = typedArray.getColor(R.styleable.Title_bar_titleColor, 0);
        leftTextColor = typedArray.getColor(R.styleable.Title_bar_leftTextColor, 0);
        rightTextColor = typedArray.getColor(R.styleable.Title_bar_rightTExtColor, 0);
        leftBtnImg = typedArray.getDrawable(R.styleable.Title_bar_leftBtnImage);
        rightBtnImage = typedArray.getDrawable(R.styleable.Title_bar_rightBtnImage);
        leftPaddingLeft = typedArray.getDimensionPixelOffset(R.styleable.Title_bar_left_padding_left, 0);
        leftPaddingRight = typedArray.getDimensionPixelOffset(R.styleable.Title_bar_left_padding_right, 0);
        leftDrawablePadding = typedArray.getDimensionPixelOffset(R.styleable.Title_bar_left_drawable_padding, 0);
        rightPaddingLeft = typedArray.getDimensionPixelOffset(R.styleable.Title_bar_right_padding_left, 0);
        rightPaddingRight = typedArray.getDimensionPixelOffset(R.styleable.Title_bar_right_padding_right, 0);
        typedArray.recycle();
    }


    private void initView(Context context) {
        if (title != null) {
            final TextView titleTv = new TextView(context);
            LayoutParams titleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            titleParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            titleTv.setGravity(Gravity.CENTER);
            titleTv.setText(title);
            titleTv.setTextColor(titleTextColor);
            titleTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize);
            titleTv.setLayoutParams(titleParams);
            addView(titleTv);
            titleTv.setClickable(true);
            titleTv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onCenterClick != null) {
                        onCenterClick.onClick(titleTv);
                    }
                }
            });
        }

        if (leftTitle != null && leftBtnImg != null) {
            final TextView leftTv = new TextView(context);
            LayoutParams leftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            leftTv.setText(leftTitle);
            leftTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, leftTextSize);
            leftTv.setTextColor(leftTextColor);
            leftTv.setGravity(Gravity.CENTER);
            leftTv.setPadding(leftPaddingLeft, 0, leftPaddingRight, 0);
            leftBtnImg.setBounds(0, 0, leftBtnImg.getMinimumWidth(), leftBtnImg.getMinimumHeight());
            leftTv.setCompoundDrawables(leftBtnImg, null, null, null);
            leftTv.setCompoundDrawablePadding(leftDrawablePadding);
            leftTv.setLayoutParams(leftParams);
            addView(leftTv);
            leftTv.setClickable(true);
            leftTv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onLeftClick != null) {
                        onLeftClick.onClick(leftTv);
                    }
                }
            });

        } else if (leftTitle == null && leftBtnImg != null) {
            final ImageView leftImg = new ImageView(context);
            LayoutParams leftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            leftImg.setImageDrawable(leftBtnImg);
            leftImg.setScaleType(ImageView.ScaleType.CENTER);
            leftImg.setPadding(leftPaddingLeft, 0, leftPaddingRight, 0);
            leftImg.setLayoutParams(leftParams);
            addView(leftImg);
            leftImg.setClickable(true);
            leftImg.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onLeftClick != null) {
                        onLeftClick.onClick(leftImg);
                    }
                }
            });
        } else if (leftTitle != null && leftBtnImg == null) {
            final TextView leftTv = new TextView(context);
            LayoutParams leftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            leftTv.setGravity(Gravity.CENTER);
            leftTv.setPadding(leftPaddingLeft, 0, leftPaddingRight, 0);
            leftTv.setText(leftTitle);
            leftTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, leftTextSize);
            leftTv.setTextColor(leftTextColor);
            leftTv.setLayoutParams(leftParams);
            addView(leftTv);
            leftTv.setClickable(true);
            leftTv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onLeftClick != null) {
                        onLeftClick.onClick(leftTv);
                    }
                }
            });
        }

        if (rightTitle != null) {
            rightTv = new TextView(context);
            LayoutParams rightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            rightTv.setText(rightTitle);
            rightTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, rightTextSize);
            rightTv.setTextColor(rightTextColor);
            rightTv.setGravity(Gravity.CENTER);
            rightTv.setPadding(rightPaddingLeft, 0, rightPaddingRight, 0);
            rightTv.setLayoutParams(rightParams);
            addView(rightTv);
            rightTv.setClickable(true);
            rightTv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onRightClick != null) {
                        onRightClick.onClick(rightTv);
                    }
                }
            });
        } else if (rightBtnImage != null) {
            final ImageView rightImg = new ImageView(context);
            LayoutParams rightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            rightImg.setImageDrawable(rightBtnImage);
            rightImg.setScaleType(ImageView.ScaleType.CENTER);
            rightImg.setPadding(rightPaddingLeft, 0, rightPaddingRight, 0);
            rightImg.setLayoutParams(rightParams);
            addView(rightImg);
            rightImg.setClickable(true);
            rightImg.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onRightClick != null) {
                        onRightClick.onClick(rightImg);
                    }
                }
            });
        }
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setLeftTitle(String leftTitle) {
        this.leftTitle = leftTitle;
    }

    public void setRightTitle(String rightTitle) {
        this.rightTitle = rightTitle;
    }

    public void setTitleTextSize(float titleTextSize) {
        this.titleTextSize = titleTextSize;
    }

    public void setLeftTextSize(float leftTextSize) {
        this.leftTextSize = leftTextSize;
    }

    public void setRightTextSize(float rightTextSize) {
        this.rightTextSize = rightTextSize;
    }

    public void setTitleTextColor(int titleTextColor) {
        this.titleTextColor = titleTextColor;
    }

    public void setLeftTextColor(int leftTextColor) {
        this.leftTextColor = leftTextColor;
    }

    public void setRightTextColor(int rightTextColor) {
        this.rightTextColor = rightTextColor;
    }

    public void setLeftBtnImg(Drawable leftBtnImg) {
        this.leftBtnImg = leftBtnImg;
    }

    public void setRightBtnImage(Drawable rightBtnImage) {
        this.rightBtnImage = rightBtnImage;
    }

    public void setLeftPaddingLeft(int leftPaddingLeft) {
        this.leftPaddingLeft = leftPaddingLeft;
    }

    public void setLeftPaddingRight(int leftPaddingRight) {
        this.leftPaddingRight = leftPaddingRight;
    }

    public void setLeftDrawablePadding(int leftDrawablePadding) {
        this.leftDrawablePadding = leftDrawablePadding;
    }

    public void setRightPaddingLeft(int rightPaddingLeft) {
        this.rightPaddingLeft = rightPaddingLeft;
    }

    public void setRightPaddingRight(int rightPaddingRight) {
        this.rightPaddingRight = rightPaddingRight;
    }

    public void setOnLeftClick(OnLeftClick onLeftClick) {
        this.onLeftClick = onLeftClick;
    }

    public void setOnRightClick(OnRightClick onRightClick) {
        this.onRightClick = onRightClick;
    }

    public void setOnCenterClick(OnCenterClick onCenterClick) {
        this.onCenterClick = onCenterClick;
    }

    /**
     * 左边控件的监听
     */
    public interface OnLeftClick {
        void onClick(View view);
    }

    /**
     * 右边控件的监听
     */
    public interface OnRightClick {
        void onClick(View view);
    }

    /**
     * 中间控件的监听
     */
    public interface OnCenterClick {
        void onClick(View view);
    }


    /**
     * <!--自定义标题栏--> 把这些放到attrs里
     <declare-styleable name="Title_bar">
     <attr name="titleText" format="string|reference"/>
     <attr name="titleTextSize" format="dimension|reference"/>
     <attr name="titleColor" format="color|reference"/>

     <attr name="leftBtnText" format="string|reference"/>
     <attr name="leftBtnTextSize" format="dimension|reference"/>
     <attr name="leftBtnImage" format="reference"/>
     <attr name="leftTextColor" format="color|reference"/>
     <attr name="left_padding_left" format="dimension|reference"/>
     <attr name="left_padding_right" format="dimension|reference"/>
     <attr name="left_drawable_padding" format="dimension|reference"/>

     <attr name="rightBtnText" format="string|reference"/>
     <attr name="rightBtnTextSize" format="dimension|reference"/>
     <attr name="rightTExtColor" format="color|reference"/>
     <attr name="rightBtnImage" format="reference"/>
     <attr name="right_padding_left" format="dimension|reference"/>
     <attr name="right_padding_right" format="dimension|reference"/>
     </declare-styleable>
     */

}
