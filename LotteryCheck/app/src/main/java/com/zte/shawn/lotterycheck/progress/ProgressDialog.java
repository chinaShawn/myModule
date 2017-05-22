package com.zte.shawn.lotterycheck.progress;

import android.app.Activity;
import android.content.DialogInterface;

import com.zte.shawn.lotterycheck.activity.DownloadProgressHandler;
import com.zte.shawn.lotterycheck.bean.ProgressBean;
import com.zte.shawn.lotterycheck.interfaces.ProgressListener;

/**
 * Created by 10192984 on 2017/1/22.
 */
public class ProgressDialog<T extends android.app.ProgressDialog> {
    protected T t;
    private DownloadProgressHandler handler;

    public ProgressDialog(ProgressListener listener) {
        this.handler = new DownloadProgressHandler(listener);
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener listener){
        t.setOnDismissListener(listener);
    }


    public void setOnKeyListener(DialogInterface.OnKeyListener listener){
        t.setOnKeyListener(listener);
    }

    public void setOnCancelListener(DialogInterface.OnCancelListener listener){
        t.setOnCancelListener(listener);
    }

    public void setOnCancelButton(DialogInterface.OnClickListener listener){
        t.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", listener);
    }

    public void onShow(Activity activity){
        if (activity != null && !activity.isFinishing() && t != null) {
            t.show();
        }
    }

    public void onDismiss(){
        t.dismiss();
    }

    public  void updateProgress(int progress){
        t.setProgress(progress);
    }

    public  void setProgress(ProgressBean bean){
        handler.sendMessage(bean);
    }
}
