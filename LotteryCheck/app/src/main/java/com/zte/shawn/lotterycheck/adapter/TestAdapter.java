package com.zte.shawn.lotterycheck.adapter;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zte.shawn.lotterycheck.R;
import com.zte.shawn.lotterycheck.bean.DaLeTou;
import com.zte.shawn.lotterycheck.interfaces.OnMoveAndSwipedListener;
import com.zte.shawn.lotterycheck.interfaces.OnStartDragListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 10192984 on 2016/10/31.
 */
public class TestAdapter extends BaseAdapter<TestAdapter.MyViewHolder> implements OnMoveAndSwipedListener{
    private OnStartDragListener mDragStartListener;
    private Context context;
    private List<String> list = new ArrayList<>();

    public TestAdapter(Context context,OnStartDragListener onStartDragListener) {
        this.context = context;
        this.mDragStartListener = onStartDragListener;
        for (int i = 0; i < 20; i++) {
            list.add("2");
        }
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(
                context).inflate(R.layout.item_test, parent,
                false);
    }

    @Override
    public MyViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new MyViewHolder(realContentView);
    }

    @Override
    public void onMyBindViewHolder(MyViewHolder holder, int position) {
        if(position%2==0){
            holder.handleView.setVisibility(View.VISIBLE);
            holder.handleView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (MotionEventCompat.getActionMasked(event) ==
                            MotionEvent.ACTION_DOWN) {
                        mDragStartListener.onStartDrag(holder);
                    }
                    return false;
                }
            });
        }else{
            holder.handleView.setVisibility(View.GONE);
        }



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        notifyItemMoved(fromPosition,toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(RecyclerView.ViewHolder viewHolder, int position) {

    }

    class MyViewHolder extends BaseAdapter.ViewHolder{
        ImageView handleView;

        public MyViewHolder(View view) {
            super(view);
            handleView = (ImageView) itemView.findViewById(R.id.handle);
        }
    }

}
