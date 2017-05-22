package com.zte.shawn.shuangseqiu.data.repository;

import android.content.Context;

import com.zte.shawn.networkmodule.bean.DaLeTouNetWork;
import com.zte.shawn.networkmodule.bean.KaiCaiWangBaseBean;
import com.zte.shawn.networkmodule.http.SalashCallback;
import com.zte.shawn.shuangseqiu.data.datasource.DaleTouDataSource;
import com.zte.shawn.shuangseqiu.data.remote.DaleTouRemoteSource;

/**
 * Created by 10192984 on 2017/5/22.
 */

public class DaleTouRepository implements DaleTouDataSource {
    private DaleTouRepository() {
        remoteSource = new DaleTouRemoteSource();
    }

    private DaleTouRemoteSource remoteSource;
    private static class SingleInstance{
        private static DaleTouRepository instance = new DaleTouRepository();
    }

    public static DaleTouRepository getInstance() {
        return SingleInstance.instance;
    }

    @Override
    public void getDaleTouFromNetWork(Context context) {
            remoteSource.getDaleTouFromNetWork(context);
    }

    @Override
    public void getDaleTouFromNetWork(Context context, SalashCallback<KaiCaiWangBaseBean<DaLeTouNetWork>> callback) {
          remoteSource.getDaleTouFromNetWork(context,callback);
    }
}
