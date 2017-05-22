package com.zte.shawn.networkmodule.http;

/**
 * Created by 10192984 on 2017/3/21.
 */
public interface SalashCallback<T> {

    public void onError(Throwable e);
    public void onSuccess(T value);
}
