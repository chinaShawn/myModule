package com.zte.shawn.networkmodule.http;

import android.content.Context;
import android.util.Log;

import com.zte.shawn.networkmodule.MyException.ServiceException;
import com.zte.shawn.networkmodule.R;
import com.zte.shawn.networkmodule.bean.BaseBean;
import com.zte.shawn.utiltools.EventMessage.DaleTouErrorEvent;
import com.zte.shawn.utiltools.EventMessage.DaleTouEvent;
import com.zte.shawn.utiltools.constants.AppConstants;
import com.zte.shawn.utiltools.toast.MyToast;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by 10192984 on 2017/3/21.
 */
public class HttpManager {

    private static final String TAG = "HttpManager";

    public static <T extends BaseBean> void getFromNetWork(Context context, Observable<T> observable, SalashCallback<T> callback) {
        observable.subscribe(new Observer<T>() {
            private T t;

            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("start");
            }

            @Override
            public void onNext(T value) {
                this.t = value;
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ", e);
                callback.onError(e);
            }

            @Override
            public void onComplete() {
                callback.onSuccess(t);
            }
        });
    }


    public static <T extends BaseBean> void getFromNetWork(Context context, Observable<T> observable) {
        observable.subscribe(new Observer<T>() {
            private T t;

            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "start");
            }

            @Override
            public void onNext(T value) {
                this.t = value;
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ", e);
                EventBus.getDefault().post(new DaleTouErrorEvent());
            }

            @Override
            public void onComplete() {
                DaleTouEvent event = new DaleTouEvent();
                event.setValue(t);
                EventBus.getDefault().post(event);
            }
        });
    }


}
