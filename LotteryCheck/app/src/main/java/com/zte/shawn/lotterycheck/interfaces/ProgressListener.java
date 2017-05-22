package com.zte.shawn.lotterycheck.interfaces;

/**
 * Created by 10192984 on 2017/1/22.
 */
public interface ProgressListener {
        void onProgress(int precent, long total, boolean done);
}
