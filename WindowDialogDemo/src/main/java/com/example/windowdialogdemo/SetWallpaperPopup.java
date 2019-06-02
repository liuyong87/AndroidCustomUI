package com.example.windowdialogdemo;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by win on 2019/6/2.
 */

public class SetWallpaperPopup {
    private static final String TAG = "SetWallpaperPopup";

    public static final int STATE_SET_WALLPAPER = 1;
    public static final int STATE_SET_SCREENSAVER = 2;
    public static final int STATE_SET_BOTH =3;

    private static View mPopupContentView = null;
    private static WindowManager mWindowManager = null;
    private static Context mContext = null;
    public static Boolean isShown = false;




    /**
     * Popup window显示弹出框
     * @param context
     */
    public void showPopupWindow(final Context context) {
        if (isShown) {
            Log.i(TAG, "return cause already shown");
            return;
        }

        Log.i(TAG, "showPopupWindow");
        mPopupContentView = initPopupView(context);

        mContext = context.getApplicationContext();
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        // WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;// 类型
        /**设置flag
         *  FLAG_NOT_TOUCH_MODAL不阻塞事件传递到后面的窗口
         *  设置 FLAG_NOT_FOCUSABLE 悬浮窗口较小时，后面的应用图标由不可长按变为可长按
         *  不设置这个flag的话，home页的划屏会有问题
         *  | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
         *  如果设置了WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE，弹出的View收不到Back键的事件
         */

        int flags = WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;
        params.flags = flags;
        // 不设置这个弹出框的透明遮罩显示为黑色
        params.format = PixelFormat.TRANSLUCENT;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.CENTER;
        mWindowManager.addView(mPopupContentView, params);
        isShown = true;
    }

    /**
     * 隐藏弹出框
     */
    public static void hidePopupWindow() {
        Log.i(TAG, "hide " + isShown + ", " + mPopupContentView);
        if (isShown && null != mPopupContentView) {
            Log.i(TAG, "hidePopupWindow");
            mWindowManager.removeView(mPopupContentView);
            isShown = false;
        }
    }

    private View initPopupView(final Context context) {
        Log.i(TAG, "setUp view");
        View view = LayoutInflater.from(context).inflate(R.layout.popwindow_set_wallpaper, null);
        ImageButton btn_cancle = (ImageButton) view.findViewById(R.id.btn_cancle);
        TextView tv_set_wallpaper = (TextView)view.findViewById(R.id.tv_set_wallpaper);
        TextView tv_set_screensaver = (TextView)view.findViewById(R.id.tv_set_screensaver);
        TextView tv_set_both = (TextView)view.findViewById(R.id.tv_set_both);
        btn_cancle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i(TAG, "cancel on click");
                hidePopupWindow();
            }
        });
        tv_set_wallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null != mOnItemClicListener){
                    mOnItemClicListener.onItemClick(STATE_SET_WALLPAPER);
                }
            }
        });

        tv_set_screensaver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null != mOnItemClicListener){
                    mOnItemClicListener.onItemClick(STATE_SET_SCREENSAVER);
                }
            }
        });
        tv_set_both.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null != mOnItemClicListener){
                    mOnItemClicListener.onItemClick(STATE_SET_BOTH);
                }
            }
        });

        // 点击窗口外部区域可消除
        // 这点的实现主要将悬浮窗设置为全屏大小，外层有个透明背景，中间一部分视为内容区域
        // 所以点击内容区域外部视为点击悬浮窗外部
        final View popupContentWindowView = view.findViewById(R.id.ll_popup_content_layout);// 非透明的内容区域

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.i(TAG, "onTouch");
                int x = (int) event.getX();
                int y = (int) event.getY();
                Rect rect = new Rect();
                popupContentWindowView.getGlobalVisibleRect(rect);
                if (!rect.contains(x, y)) {
                    hidePopupWindow();
                }
                Log.i(TAG, "onTouch : " + x + ", " + y + ", rect: "
                        + rect);
                return false;
            }
        });

        // 点击back键可消除
        view.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                switch (keyCode) {
                    case KeyEvent.KEYCODE_BACK:
                        hidePopupWindow();
                        return true;
                    default:
                        return false;
                }
            }
        });
        return view;
    }

    public interface OnItemClicListener{
        void onItemClick(int state);
    }

    private OnItemClicListener mOnItemClicListener;
    public void setOnItemClicListener(OnItemClicListener l){
        mOnItemClicListener = l;
    }


}
