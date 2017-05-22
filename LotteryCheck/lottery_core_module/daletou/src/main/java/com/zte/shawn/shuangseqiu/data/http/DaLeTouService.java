package com.zte.shawn.shuangseqiu.data.http;

import com.zte.shawn.networkmodule.bean.DaLeTouNetWork;
import com.zte.shawn.networkmodule.bean.KaiCaiWangBaseBean;

import io.reactivex.Observable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;


/**
 * Created by 10192984 on 2017/1/6.
 */
public interface DaLeTouService {

    @GET("dlt-20.json")
    Observable<KaiCaiWangBaseBean<DaLeTouNetWork>> getDaleTouNetWork();
}
