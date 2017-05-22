package com.zte.shawn.lotterycheck.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zte.shawn.lotterycheck.database.DaleTouDataBase;
import com.zte.shawn.lotterycheck.MyApp;
import com.zte.shawn.lotterycheck.R;
import com.zte.shawn.lotterycheck.bean.DaLeTou;
import com.zte.shawn.lotterycheck.bean.MyDaLeTou;
import com.zte.shawn.lotterycheck.util.Utils;

import java.util.List;

/**
 * Created by 10192984 on 2016/10/31.
 */
public class MyDaleTouAdapter extends BaseAdapter<MyDaleTouAdapter.MyViewHolder>{
    //public static final int TYPE_HEADER = 0;
    // public static final int TYPE_NORMAL = 1;

    private Context context;
    private List<MyDaLeTou> myDaLeTouList;


    public MyDaleTouAdapter(Context context, List<MyDaLeTou> myDaLeTouList) {
        this.context = context;
        this.myDaLeTouList = myDaLeTouList;
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(
                context).inflate(R.layout.item_mydaletou, parent,
                false);
    }

    @Override
    public MyViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new MyViewHolder(realContentView);
    }

    @Override
    public void onMyBindViewHolder(MyViewHolder holder, int position) {
        MyDaLeTou myDaLeTou = myDaLeTouList.get(position);
        holder.red1.setText(myDaLeTou.getRead1() + "");
        holder.red2.setText(myDaLeTou.getRead2() + "");
        holder.red3.setText(myDaLeTou.getRead3() + "");
        holder.red4.setText(myDaLeTou.getRead4() + "");
        holder.red5.setText(myDaLeTou.getRead5() + "");
        holder.green1.setText(myDaLeTou.getGreen1() + "");
        holder.green2.setText(myDaLeTou.getGreen2() + "");
        holder.date.setText(myDaLeTou.getDateNum() + "");

        DaLeTou daLeTou = DaleTouDataBase.getInstance().getLatestDaleTouInChoice(myDaLeTou.getDateNum(),myDaLeTou.getTimes());
        holder.times.setText(Utils.caculateIsOver(myDaLeTou,daLeTou) + "");
    }


    @Override
    public int getItemCount() {
        return myDaLeTouList.size();
    }

    class MyViewHolder extends BaseAdapter.ViewHolder {
        TextView red1,red2,red3,red4,red5,green1,green2,date,times;

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
            times = (TextView) view.findViewById(R.id.times);
        }
    }

}
