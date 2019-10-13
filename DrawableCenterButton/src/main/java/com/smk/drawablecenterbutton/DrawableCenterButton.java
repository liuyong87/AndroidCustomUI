package com.smk.drawablecenterbutton;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by win on 2019/10/13.
 */

/**
 * @attr
 */
public class DrawableCenterButton extends RelativeLayout {
    private Context mContext;
    private View mContentView;
    private TextView tv_content;


    // 设置文字
    // 设置文字颜色
    // 设置文字大小
    // 设置背景
    // 设置文字背景宽
    // 设置文字背景高

    public DrawableCenterButton(Context context) {
        this(context, null);
    }

    public DrawableCenterButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initViews();
        initAttrs(attrs);
    }

    private void initAttrs(AttributeSet attrs) {
        CharSequence text = "";
        ColorStateList textColor;
        int textSize = 15;
        Drawable textBackground;
        int textWidth = -1;
        int textHeight = -1;

        TypedArray ta = mContext.obtainStyledAttributes(attrs,R.styleable.DrawableCenterButton);
        if(null == ta){
            return;
        }
        int n = ta.getIndexCount();
        for(int i=0;i<n;i++){
            int attr = ta.getIndex(i);
            switch (attr){
                case R.styleable.DrawableCenterButton_attr_text:
                    text = ta.getText(attr);
                    if(null != text && null != tv_content){
                        tv_content.setText(text);
                    }
                    break;
                case R.styleable.DrawableCenterButton_attr_textColor:
                    textColor = ta.getColorStateList(attr);
                    if(null != textColor && null != tv_content){
                        tv_content.setTextColor(textColor);
                    }
                    break;
                case R.styleable.DrawableCenterButton_attr_textSize:
                    textSize = ta.getDimensionPixelSize(attr,textSize);
                    if(null != tv_content){
                        tv_content.setTextSize(textSize);
                    }
                    break;
                case R.styleable.DrawableCenterButton_attr_textBackground:
                    textBackground = ta.getDrawable(attr);
                    if(null != textBackground && null != tv_content){
                        tv_content.setBackgroundDrawable(textBackground);
                    }
                    break;
                case R.styleable.DrawableCenterButton_attr_textWidth:
                    textWidth = ta.getDimensionPixelOffset(attr,textWidth);
                    if(null != tv_content){
                        tv_content.setWidth(textWidth);
                    }
                    break;
                case R.styleable.DrawableCenterButton_attr_textHeight:
                    textHeight = ta.getDimensionPixelOffset(attr,textHeight);
                    if(null != tv_content){
                        tv_content.setHeight(textHeight);
                    }
                    break;
                case R.styleable.DrawableCenterButton_attr_textFocus:
                    boolean focus = ta.getBoolean(attr,false);
                    tv_content.setFocusable(focus);
                    break;
            }
        }
        ta.recycle();
    }

    private void initViews() {
        mContentView = LayoutInflater.from(mContext).inflate(R.layout.drawable_center_button, this);
        tv_content = (TextView) mContentView.findViewById(R.id.tv_content);
    }


}
