package com.zte.shawn.lotterycheck.progress;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.RecoverySystem;
import android.widget.Toast;

import com.zte.shawn.lotterycheck.R;
import com.zte.shawn.lotterycheck.activity.DownloadProgressHandler;
import com.zte.shawn.lotterycheck.bean.ProgressBean;
import com.zte.shawn.lotterycheck.interfaces.ProgressListener;

/**
 * Created by 10192984 on 2017/1/19.
 */
public class HorizontalDialog extends com.zte.shawn.lotterycheck.progress.ProgressDialog<android.app.ProgressDialog>{



    public HorizontalDialog(Context context, String message, ProgressListener listener) {
        super(listener);
        this.t = new android.app.ProgressDialog(context);
        t.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);// 设置进度条的形式为圆形转动的进度条
        t.setCancelable(false);// 设置是否可以通过点击Back键取消
        t.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
        t.setIcon(R.mipmap.ic_launcher);//
        // 设置提示的title的图标，默认是没有的，如果没有设置title的话只设置Icon是不会显示图标的
        t.setTitle("提示");
        t.setMessage(message);
        //设置可点击的按钮，最多有三个(默认情况下)
        t.setButton(DialogInterface.BUTTON_POSITIVE, "进入后台",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context,"进入后台下载", Toast.LENGTH_SHORT).show();
                    }
                });
    }



}
