package com.zte.shawn.lotterycheck.database;

import android.util.Log;

import com.zte.shawn.lotterycheck.MyApp;
import com.zte.shawn.lotterycheck.bean.DaLeTou;
import com.zte.shawn.lotterycheck.bean.DaLeTouDao;
import com.zte.shawn.lotterycheck.bean.DaoSession;
import com.zte.shawn.lotterycheck.bean.MyDaLeTou;
import com.zte.shawn.lotterycheck.bean.MyDaLeTouDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 10192984 on 2016/10/29.
 */
public class DaleTouDataBase {
    private String TAG = "DaleTouDataBase";
    private DaLeTouDao daLeTouDao;
    private MyDaLeTouDao myDaLeTouDao;

    public static DaleTouDataBase instance;

    public static DaleTouDataBase getInstance(){
        if(instance == null){
            synchronized (DaleTouDataBase.class){
                if(instance == null){
                    instance = new DaleTouDataBase();
                }
            }
        }
       return instance;
    }

    DaleTouDataBase(){
        DaoSession daoSession = MyApp.getMyApp().getDaoSession();
        daLeTouDao = daoSession.getDaLeTouDao();
        myDaLeTouDao = daoSession.getMyDaLeTouDao();
    }

    public void saveDaLeTou(DaLeTou daLeTou){
        try{
            daLeTouDao.insertOrReplace(daLeTou);
        }catch (Exception e){
            Log.e(TAG, "saveDaLeTou error: "+e.getStackTrace() );
        }

    }

    public void deleteDaLeTou(DaLeTou daLeTou){
        daLeTouDao.delete(daLeTou);
    }


    public List<DaLeTou> getAllDaleTouInChoice(int begin,int times){
        int end = begin + times -1;
        List<DaLeTou> userList = daLeTouDao.queryBuilder()
                .where(DaLeTouDao.Properties.DateNum.ge(begin), DaLeTouDao.Properties.DateNum.le(end))
                .orderDesc(DaLeTouDao.Properties.DateNum)
                .build().list();
        return userList;
    }

    public DaLeTou getLatestDaleTouInChoice(int begin,int times){
        int end = begin + times -1;
        DaLeTou userList = daLeTouDao.queryBuilder()
                .where(DaLeTouDao.Properties.DateNum.ge(begin), DaLeTouDao.Properties.DateNum.le(end))
                .orderDesc(DaLeTouDao.Properties.DateNum)
                .limit(1)
                .build().unique();
        return userList;
    }


    public List<DaLeTou> getAllDaleTou(){
        List<DaLeTou> userList = daLeTouDao.queryBuilder()
                .orderDesc(DaLeTouDao.Properties.DateNum)
                .build().list();
        return userList;
    }

    public void saveMyDaLeTou(MyDaLeTou myDaLeTou){
        try{
            myDaLeTouDao.insertOrReplace(myDaLeTou);
        }catch (Exception e){
            Log.e(TAG, "saveMyDaLeTou error:"+e.getStackTrace() );
        }

    }

    public void deleteMyDaLeTou(MyDaLeTou myDaLeTou){
        try{
            myDaLeTouDao.delete(myDaLeTou);
        }catch (Exception e){
            Log.e(TAG, "deleteMyDaLeTou error:"+e.getStackTrace() );
        }

    }

    public List<MyDaLeTou> getAllMyDaleTou(){
        List<MyDaLeTou> userList = myDaLeTouDao.queryBuilder()
                .orderDesc(MyDaLeTouDao.Properties.DateNum)
                .build().list();
        return userList;
    }

    public List<MyDaLeTou> getAllMyDaleTouMatchDaletou(DaLeTou daletou){
        List<MyDaLeTou> userList = getAllMyDaleTou();
        List<MyDaLeTou> returnList = new ArrayList<>();
        for (MyDaLeTou myDaLeTou : userList) {
            if((myDaLeTou.getTimes()+myDaLeTou.getDateNum()-1)>=daletou.getDateNum() && daletou.getDateNum()<=myDaLeTou.getDateNum()){
                returnList.add(myDaLeTou);
            }
        }
        return returnList;
    }

}
