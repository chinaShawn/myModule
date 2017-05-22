package com.zte.shawn.lotterycheck.activity;

import android.os.Handler;
import android.os.Message;

import com.zte.shawn.lotterycheck.bean.ProgressBean;

/**
 * Created by 10192984 on 2017/1/19.
 */
public abstract class ProgressHandler extends Handler{

    protected abstract void sendMessage(ProgressBean t);

    protected abstract void handleProgressMessage(Message message);


    @Override
    public void handleMessage(Message msg) {
        handleProgressMessage(msg);
    }

}
