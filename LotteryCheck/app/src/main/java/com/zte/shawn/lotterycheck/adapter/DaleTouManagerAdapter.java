package com.zte.shawn.lotterycheck.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zte.shawn.lotterycheck.R;
import com.zte.shawn.lotterycheck.bean.DaLeTou;
import com.zte.shawn.lotterycheck.bean.MyDaLeTou;
import com.zte.shawn.lotterycheck.util.Utils;

import java.util.List;

/**
 * Created by 10192984 on 2016/10/31.
 */
public class DaleTouManagerAdapter extends BaseAdapter<DaleTouManagerAdapter.MyViewHolder> {

    private Context context;
    private List<DaLeTou> daLeTouList;


    public DaleTouManagerAdapter(Context context, List<DaLeTou> daLeTouList) {
        this.context = context;
        this.daLeTouList = daLeTouList;
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(
                context).inflate(R.layout.item_daletou, parent,
                false);
    }

    @Override
    public DaleTouManagerAdapter.MyViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new MyViewHolder(realContentView);
    }

    @Override
    public void onMyBindViewHolder(MyViewHolder holder, int position) {
        DaLeTou daLeTou= daLeTouList.get(position);
        holder.red1.setText(daLeTou.getRead1() + "");
        holder.red2.setText(daLeTou.getRead2() + "");
        holder.red3.setText(daLeTou.getRead3() + "");
        holder.red4.setText(daLeTou.getRead4() + "");
        holder.red5.setText(daLeTou.getRead5() + "");
        holder.green1.setText(daLeTou.getGreen1() + "");
        holder.green2.setText(daLeTou.getGreen2() + "");
        holder.date.setText(daLeTou.getDateNum() + "");
        holder.isHit.setText(Utils.caculateHit(daLeTou));
    }

    @Override
    public int getItemCount() {
        return daLeTouList.size();
    }

    class MyViewHolder extends BaseAdapter.ViewHolder{
        TextView red1,red2,red3,red4,red5,green1,green2,date,isHit;

        public MyViewHolder(View view) {
            super(view);
                red1 = (TextView) view.findViewById(R.id.red1);
                red2 = (TextView) view.findViewById(R.id.red2);
                red3 = (TextView) view.findViewById(R.id.red3);
                red4 = (TextView) view.findViewById(R.id.red4);
                red5 = (TextView) view.findViewById(R.id.red5);
                green1 = (TextView) view.findViewById(R.id.green1);
                green2 = (TextView) view.findViewById(R.id.green2);
                date = (TextView) view.findViewById(R.id.date);
                isHit = (TextView) view.findViewById(R.id.isHit);

        }
    }

}
