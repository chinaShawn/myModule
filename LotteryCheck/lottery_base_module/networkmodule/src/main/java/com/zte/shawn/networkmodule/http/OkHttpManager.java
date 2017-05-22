package com.zte.shawn.networkmodule.http;

import android.content.Context;
import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.zte.shawn.networkmodule.util.utils;
import com.zte.shawn.persistence.PersistPathManager;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by 10192984 on 2017/2/10.
 */
public class OkHttpManager {
    private static OkHttpManager instance;
    private Context context;
    private Cache cache;
    //缓存容量
    private  long SIZE_OF_CACHE = 10 * 1024 * 1024; // 10 MiB
    //缓存路径
    private String cacheFile;

    private static final int TIMEOUT_CONNECT = 5; //5秒
    private static final int TIMEOUT_DISCONNECT = 60 * 60 * 24 * 7; //7天

    private OkHttpManager(Context context) {
        this.context = context;
        cacheFile = PersistPathManager.getInstance(context).privateExternalPath()+"/http";
        cache = new Cache(new File(cacheFile), SIZE_OF_CACHE);
    }

    public static OkHttpManager getInstance(Context context){
        if(instance==null){
            synchronized (OkHttpManager.class){
                if(instance==null){
                    instance = new OkHttpManager(context);
                }
            }
        }
        return instance;
    }

    public  final Interceptor REWRITE_RESPONSE_INTERCEPTOR = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            //获取retrofit @headers里面的参数，参数可以自己定义，在本例我自己定义的是cache，跟@headers里面对应就可以了
            String cache = chain.request().header("cache");
            okhttp3.Response originalResponse = chain.proceed(chain.request());
            String cacheControl = originalResponse.header("Cache-Control");
            //如果cacheControl为空，就让他TIMEOUT_CONNECT秒的缓存，本例是5秒，方便观察。注意这里的cacheControl是服务器返回的
            if (cacheControl == null) {
                //如果cache没值，缓存时间为TIMEOUT_CONNECT，有的话就为cache的值
                if (cache == null || "".equals(cache)) {
                    cache = TIMEOUT_CONNECT + "";
                }
                originalResponse = originalResponse.newBuilder()
                        .header("Cache-Control", "public, max-age=" + cache)
                        .build();
                return originalResponse;
            } else {
                return originalResponse;
            }
        }
    };

    public final Interceptor REWRITE_RESPONSE_INTERCEPTOR_OFFLINE = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            //离线的时候为7天的缓存。
            if (utils.checkNet(context)) {
                request = request.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale="+TIMEOUT_DISCONNECT)
                        .build();
            }
            return chain.proceed(request);
        }
    };

    public OkHttpClient getNoCacheHttpClient(){
        return new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor(message -> Log.i("RetrofitLog","retrofitBack = "+message))
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .addNetworkInterceptor(new StethoInterceptor())
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    public OkHttpClient getCacheHttpClient(){
        return new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor(message -> Log.i("RetrofitLog","retrofitBack = "+message))
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(REWRITE_RESPONSE_INTERCEPTOR)
                .addNetworkInterceptor(REWRITE_RESPONSE_INTERCEPTOR)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .cache(cache)
                .build();
    }

}
