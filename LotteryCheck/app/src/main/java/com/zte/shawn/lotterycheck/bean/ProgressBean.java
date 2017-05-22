package com.zte.shawn.lotterycheck.bean;

/**
 * Created by 10192984 on 2017/1/19.
 */
public class ProgressBean {
    private boolean isDone;
    private int percent;
    private long fullSize;
    private long compliteSize;

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public long getFullSize() {
        return fullSize;
    }

    public void setFullSize(long fullSize) {
        this.fullSize = fullSize;
    }

    public long getCompliteSize() {
        return compliteSize;
    }

    public void setCompliteSize(long compliteSize) {
        this.compliteSize = compliteSize;
    }
}
