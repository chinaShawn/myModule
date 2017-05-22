package com.zte.shawn.lotterycheck.interfaces;

import android.support.v7.widget.RecyclerView;

/**
 * Created by 10192984 on 2017/2/22.
 */
public interface OnStartDragListener {


    /**

     * Called when a view is requesting a start of a drag.

     *

     * @param viewHolder The holder of the view to drag.

     */

    void onStartDrag(RecyclerView.ViewHolder viewHolder);
}
