package com.zte.shawn.shuangseqiu.data.http;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.zte.shawn.networkmodule.bean.DaLeTouNetWork;
import com.zte.shawn.networkmodule.bean.KaiCaiWangBaseBean;
import com.zte.shawn.networkmodule.http.OkHttpManager;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


/**
 * Created by 10192984 on 2017/1/3.
 */
public class DaLeTouHttp {

    private  Context context ;
    private static DaLeTouHttp instance;
    private OkHttpClient httpClient;
    public  String baseUrl = "http://f.apiplus.cn/";



    private DaLeTouHttp(Context context) {
        this.context = context;
    }

    public static DaLeTouHttp getInstance(Context context){
        if(instance==null){
            synchronized (DaLeTouHttp.class){
                if(instance==null){
                    instance = new DaLeTouHttp(context);
                }
            }
        }
        return instance;
    }

    private DaLeTouHttp() {
    }

    public Observable<KaiCaiWangBaseBean<DaLeTouNetWork>> getDaLeTouHistoryWithoutCache() {
        DaLeTouService retrofitService = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(OkHttpManager.getInstance(context).getNoCacheHttpClient())
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(DaLeTouService.class);

        return retrofitService.getDaleTouNetWork()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread());
    }




}
