package com.zte.shawn.lotterycheck.activity;

import android.os.Message;
import android.os.RecoverySystem;

import com.zte.shawn.lotterycheck.bean.MyDaLeTouDao;
import com.zte.shawn.lotterycheck.bean.ProgressBean;
import com.zte.shawn.lotterycheck.interfaces.ProgressListener;

/**
 * Created by 10192984 on 2017/1/19.
 */
public class DownloadProgressHandler extends ProgressHandler {
    private static final int DOWNLOAD_PROGRESS = 1;
    private ProgressListener listener;

    public DownloadProgressHandler(ProgressListener listener) {
        this.listener = listener;
    }

    public ProgressListener getListener() {
        return listener;
    }

    public DownloadProgressHandler setListener(ProgressListener listener) {
        this.listener = listener;
        return this;
    }

    @Override
    public  void sendMessage(ProgressBean t) {
        this.obtainMessage(DOWNLOAD_PROGRESS,t).sendToTarget();
    }

    @Override
    protected void handleProgressMessage(Message message) {
        switch (message.what){
            case DOWNLOAD_PROGRESS:
                ProgressBean progressBean = (ProgressBean)message.obj;
                listener.onProgress(progressBean.getPercent(),progressBean.getFullSize(),progressBean.isDone());
        }
    }

}
