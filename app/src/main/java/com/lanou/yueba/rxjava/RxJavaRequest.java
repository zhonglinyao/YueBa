package com.lanou.yueba.rxjava;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by dllo on 16/10/29.
 */

public class RxJavaRequest {
    public static Observable rxJavaOkHttpGetBean(final String url, final Class clazz, final Gson gson) {
        return Observable
                .just(url)
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String string) {
                        StringBuffer stringBuffer = new StringBuffer();
                        try {
                            URL url1 = new URL(url);
                            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
                            InputStream inputStream = connection.getInputStream();
                            InputStreamReader reader = new InputStreamReader(inputStream);
                            BufferedReader bufferedReader = new BufferedReader(reader);
                            String line;
                            while ((line = bufferedReader.readLine()) != null) {
                                stringBuffer.append(line);
                            }
                            bufferedReader.close();
                            reader.close();
                            inputStream.close();
                            connection.disconnect();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {

                        }
                        return stringBuffer.toString();
                    }
                })
                .map(new Func1<String, Object>() {
                    @Override
                    public Object call(String string) {
                        Object object = gson.fromJson(string, clazz);
                        return object;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
