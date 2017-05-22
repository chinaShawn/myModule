package com.zte.shawn.lotterycheck.util;

import android.app.Application;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.zte.shawn.lotterycheck.MyApp;
import com.zte.shawn.lotterycheck.R;
import com.zte.shawn.lotterycheck.activity.ThreadWeiXinLoginActivity;
import com.zte.shawn.lotterycheck.adapter.BaseAdapter;
import com.zte.shawn.lotterycheck.bean.DaLeTou;
import com.zte.shawn.lotterycheck.bean.MyDaLeTou;
import com.zte.shawn.lotterycheck.database.DaleTouDataBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 10192984 on 2016/10/31.
 */
public class Utils {

    private static MyApp context  = MyApp.getMyApp();

    public static String caculateHit(DaLeTou daLeTou){
        String hitString ="";
        List<MyDaLeTou>  list = DaleTouDataBase.getInstance().getAllMyDaleTouMatchDaletou(daLeTou);
        for (MyDaLeTou myDaLeTou : list) {
            String hit = caculateHit(daLeTou,myDaLeTou);
            if(!context.getString(R.string.hitno).equals(hit)){
                hitString += hit;
            }
        }
        return hitString;
    }

    public static String caculateHit(DaLeTou daLeTou, MyDaLeTou myDaLeTou){
        int redNum = caculateRed( daLeTou,  myDaLeTou);
        int greenNum = caculateGreen(daLeTou,  myDaLeTou);
        if((redNum==2 && greenNum ==1) || (redNum==0 && greenNum ==2)
                || (redNum==1 && greenNum ==2) || (redNum==3 && greenNum ==0)){
            return context.getString(R.string.hitsix);
        }else if((redNum ==2 && greenNum ==2)|| (redNum==3 && greenNum ==1)
                || (redNum==4 && greenNum==0)){
            return context.getString(R.string.hitfive);
        }else if((redNum==3 && greenNum ==2)|| (redNum==4 && greenNum ==1)
                ){
            return context.getString(R.string.hitfour);
        }else if( (redNum==4 && greenNum ==2) || (redNum ==5 && greenNum ==0)){
            return context.getString(R.string.hitthree);
        }else if(redNum ==5 && greenNum ==1){
            return context.getString(R.string.hittwo);
        }else if(redNum ==5 && greenNum ==2){
            return context.getString(R.string.hitone);
        }else{
            return context.getString(R.string.hitno);
        }
    }


    private static int caculateRed(DaLeTou daLeTou, MyDaLeTou myDaLeTou){
        int[] daleToureds = new int[5];
        int[] myDaleTouReds = new int[5];
        daleToureds[0] = daLeTou.getRead1();
        daleToureds[1] = daLeTou.getRead2();
        daleToureds[2] = daLeTou.getRead3();
        daleToureds[3] = daLeTou.getRead4();
        daleToureds[4] = daLeTou.getRead5();

        myDaleTouReds[0] =  myDaLeTou.getRead1();
        myDaleTouReds[1] =  myDaLeTou.getRead2();
        myDaleTouReds[2] =  myDaLeTou.getRead3();
        myDaleTouReds[3] =  myDaLeTou.getRead4();
        myDaleTouReds[4] =  myDaLeTou.getRead5();

        int result = 0;
        for(int i=0;i<5;i++){
            int reds = daleToureds[i];
            for(int j=0;j<5;j++){
                if(reds == myDaleTouReds[j]){
                    result++;
                    break;
                }
            }
        }
        return result;
    }

    private static int caculateGreen(DaLeTou daLeTou, MyDaLeTou myDaLeTou){
        int[] daleGreen = new int[2];
        int[] myDaleGreen = new int[2];
        daleGreen[0] = daLeTou.getGreen1();
        daleGreen[1] = daLeTou.getGreen2();

        myDaleGreen[0] =  myDaLeTou.getGreen1();
        myDaleGreen[1] =  myDaLeTou.getGreen2();

        int result = 0;
        for(int i=0;i<2;i++){
            int reds = daleGreen[i];
            for(int j=0;j<2;j++){
                if(reds == myDaleGreen[j]){
                    result++;
                    break;
                }
            }
        }
        return result;
    }

    public static String caculateIsOver(MyDaLeTou myDaLeTou,DaLeTou daLeTou){

        if(daLeTou ==null){
            return (myDaLeTou.getTimes())+"";
        }
        int num = myDaLeTou.getTimes()+myDaLeTou.getDateNum()-1;
        if( daLeTou.getDateNum()<num){
            return (num-daLeTou.getDateNum())+"";
        }else {
            return "已完结";
        }
    }




}
