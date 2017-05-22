package com.zte.shawn.utiltools.toast;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class MyToast {
    private static final String TAG = "MyToast";
    private static Toast mToast;
    private static Handler mHandler = new Handler();
    private static Runnable r = new Runnable() {
        public void run() {
            if (mToast != null) {
                mToast.cancel();
            }
        }
    };

    /**
     * text 为显示的消息
     */
    public static void showToast(Context mContext, String text, int duration) {
        try {
            if (mHandler == null) {
                mHandler = new Handler();
            }
            if (r == null) {
                return;
            }
            mHandler.removeCallbacks(r);

            if (mToast != null) {
                mToast.setText(text);
            } else {
                mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
            }
            mHandler.postDelayed(r, duration);

            if (mToast != null) {
                mToast.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG,"on error",e);
        }
    }

    /**
     * 使用资源文件时候用的
     *
     * @throws Exception
     * @throws NotFoundException
     */
    public static void showToast(Context mContext, int resId, int duration) {
        showToast(mContext, mContext.getResources().getString(resId), duration);
    }

}
