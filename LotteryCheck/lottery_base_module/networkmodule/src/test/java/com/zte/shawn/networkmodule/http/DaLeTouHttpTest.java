package com.zte.shawn.networkmodule.http;

import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import io.reactivex.Flowable;

import static org.junit.Assert.*;

/**
 * Created by 10192984 on 2017/1/20.
 */
public class DaLeTouHttpTest {

    @Test
    public void testGetDaLeTouHistory() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("url1");
        list.add("url2");
        list.add("url3");
        Flowable.fromArray(list)
                .flatMap(urls->Flowable.fromIterable(urls))
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        System.out.println("start");
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println(s);
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("error");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("over");
                    }
                });



        new Thread(new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 2;
            }
        })).start();
        Runnable myRunable = new Runnable() {
            @Override
            public void run() {

            }
        };
        FutureTask<Integer> ss = new FutureTask<Integer>(myRunable,null);


//        Observable.fromArray(list)
//                .flatMap(urls->Observable.fromIterable(urls))
//                .subscribe(new Observer<String>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        System.out.println(d.isDisposed());
//                    }
//
//                    @Override
//                    public void onNext(String value) {
//                        System.out.println(value);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        System.out.println("error");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        System.out.println("over");
//                    }
//                });
    }
}