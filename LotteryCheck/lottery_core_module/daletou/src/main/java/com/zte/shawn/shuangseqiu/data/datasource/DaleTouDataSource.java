package com.zte.shawn.shuangseqiu.data.datasource;

import android.content.Context;

import com.zte.shawn.networkmodule.bean.DaLeTouNetWork;
import com.zte.shawn.networkmodule.bean.KaiCaiWangBaseBean;
import com.zte.shawn.networkmodule.http.SalashCallback;

/**
 * Created by 10192984 on 2017/5/22.
 */

public interface DaleTouDataSource {

    void getDaleTouFromNetWork(Context context);
    void getDaleTouFromNetWork(Context context, SalashCallback<KaiCaiWangBaseBean<DaLeTouNetWork>> callback);
}
