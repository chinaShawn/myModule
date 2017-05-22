package com.zte.shawn.lotterycheck.adapter;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zte.shawn.lotterycheck.R;
import com.zte.shawn.lotterycheck.activity.BaseActivity;
import com.zte.shawn.lotterycheck.interfaces.OnMoveAndSwipedListener;
import com.zte.shawn.lotterycheck.interfaces.OnStartDragListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 10192984 on 2016/10/31.
 */
public class PerfectAdapter extends BaseAdapter<PerfectAdapter.MyViewHolder> {
    private Context context;
    private List<String> list = new ArrayList<>();

    public PerfectAdapter(Context context) {
        this.context = context;
        for (int i = 0; i < 40; i++) {
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

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends BaseAdapter.ViewHolder{
        ImageView handleView;

        public MyViewHolder(View view) {
            super(view);
            handleView = (ImageView) itemView.findViewById(R.id.handle);
        }
    }

}
