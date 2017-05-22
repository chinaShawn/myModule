package com.zte.shawn.lotterycheck.interfaces;

import android.support.v7.widget.RecyclerView;

/**
 * Created by 10192984 on 2016/11/5.
 */
public interface OnMoveAndSwipedListener {
    boolean onItemMove(int fromPosition , int toPosition);
    void onItemDismiss(RecyclerView.ViewHolder viewHolder, int position);
}
