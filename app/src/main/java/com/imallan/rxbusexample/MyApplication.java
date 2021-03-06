package com.imallan.rxbusexample;

import android.app.Application;

import com.imallan.rxbus.Bus;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

public class MyApplication extends Application {

    private Subscription mSubscription;
    private static Bus sBus;

    public static Bus getBus() {
        return sBus;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sBus = Bus.create();
        mSubscription = Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        sBus.send(new MainActivity.MyEvent(aLong.toString()));
                        sBus.send(new MyView.ViewEvent(aLong.toString()));
                    }
                });
        sBus.sendPersist(new MainActivity.MyEvent("Starting"));
    }

    @Override
    public void onTerminate() {
        mSubscription.unsubscribe();
        super.onTerminate();
    }
}
