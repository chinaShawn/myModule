package com.zte.shawn.lotterycheck.adapter;

import android.content.ClipData;
import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.zte.shawn.lotterycheck.interfaces.OnMoveAndSwipedListener;

/**
 * Created by 10192984 on 2016/11/5.
 */
public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {
    OnMoveAndSwipedListener onMoveAndSwipedListener;



    public SimpleItemTouchHelperCallback(OnMoveAndSwipedListener onMoveAndSwipedListener){
        this.onMoveAndSwipedListener = onMoveAndSwipedListener;
    }

    /**
     * 这个方法是用来设置我们拖动的方向以及侧滑的方向的
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //设置拖拽方向为上下
        final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        //设置侧滑方向为从左到右和从右到左都可以
        final int swipeFlags = ItemTouchHelper.ACTION_STATE_IDLE;
        //将方向参数设置进去
        return makeMovementFlags(dragFlags, swipeFlags);

    }

    /**当我们拖动item时会回调此方法*/
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        //如果两个item不是一个类型的，我们让他不可以拖拽
        if (viewHolder.getItemViewType() != target.getItemViewType()){
            return false;
        }
        //回调adapter中的onItemMove方法
        onMoveAndSwipedListener.onItemMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
        return true;
    }
    /**当我们侧滑item时会回调此方法*/
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        //回调adapter中的onItemDismiss方法
        onMoveAndSwipedListener.onItemDismiss(viewHolder,viewHolder.getAdapterPosition());
    }

    /**这个方法可以判断当前是拖拽还是侧滑*/
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if(actionState == ItemTouchHelper.ACTION_STATE_DRAG){

        }

        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
            //根据侧滑的位移来修改item的透明度
            final float alpha = 1 - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
            viewHolder.itemView.setAlpha(alpha);
            viewHolder.itemView.setTranslationX(dX);
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
    }
}
