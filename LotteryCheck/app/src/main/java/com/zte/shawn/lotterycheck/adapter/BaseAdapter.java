package com.zte.shawn.lotterycheck.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;
import com.zte.shawn.lotterycheck.R;
import com.zte.shawn.lotterycheck.interfaces.OnItemClickListener;

/**
 * Created by 10192984 on 2016/12/6.
 */
public abstract class BaseAdapter<VH extends BaseAdapter.ViewHolder> extends SwipeMenuAdapter<VH> {

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }


    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.setOnItemClickListener(onItemClickListener);
        onMyBindViewHolder(holder,position);
    }

    public abstract void onMyBindViewHolder(VH holder, int position);

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnItemClickListener mOnItemClickListener;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            view.setBackgroundResource(R.drawable.list_view_item_select_white_backgroud);
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View v) {
            if (this.mOnItemClickListener != null) {
                this.mOnItemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }
}
