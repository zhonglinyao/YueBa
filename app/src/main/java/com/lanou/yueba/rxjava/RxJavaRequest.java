package com.lanou.yueba.rxjava;

import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by dllo on 16/10/29.
 */

public class RxJavaRequest {
    public static Observable rxJavaOkHttpGetBean(final OkHttpClient okHttpClient, final String url){
        return Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext(url);
                    }
                })
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String string) {
                        Request request = new Request.Builder().url(string).build();
                        String result = null;
                        Response execute = null;
                        try {
                            execute = okHttpClient.newCall(request).execute();
                            result = execute.body().string();
                            Log.d("RxJavaRequest", result);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            execute.close();
                        }
                        return result;
                    }
                });
    }
}
