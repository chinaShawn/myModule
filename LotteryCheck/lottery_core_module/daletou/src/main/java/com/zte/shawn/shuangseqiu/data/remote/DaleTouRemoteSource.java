package com.zte.shawn.shuangseqiu.data.remote;

import android.content.Context;

import com.zte.shawn.networkmodule.bean.DaLeTouNetWork;
import com.zte.shawn.networkmodule.bean.KaiCaiWangBaseBean;
import com.zte.shawn.networkmodule.http.HttpManager;
import com.zte.shawn.networkmodule.http.SalashCallback;
import com.zte.shawn.shuangseqiu.data.datasource.DaleTouDataSource;
import com.zte.shawn.shuangseqiu.data.http.DaLeTouHttp;

import io.reactivex.Observable;

/**
 * Created by 10192984 on 2017/5/22.
 */

public class DaleTouRemoteSource implements DaleTouDataSource {

    @Override
    public void getDaleTouFromNetWork(Context context) {
        Observable<KaiCaiWangBaseBean<DaLeTouNetWork>> observable = DaLeTouHttp.getInstance(context).getDaLeTouHistoryWithoutCache();
        HttpManager.getFromNetWork(context,observable);
    }

    @Override
    public void getDaleTouFromNetWork(Context context, SalashCallback<KaiCaiWangBaseBean<DaLeTouNetWork>> callback) {
        Observable<KaiCaiWangBaseBean<DaLeTouNetWork>> observable = DaLeTouHttp.getInstance(context).getDaLeTouHistoryWithoutCache();
        HttpManager.getFromNetWork(context,observable,callback);
    }
}
