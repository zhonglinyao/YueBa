package com.lanou.yueba.httprequset;

import java.lang.reflect.Type;
import java.util.Map;

import okhttp3.Response;

/**
 * Created by dllo on 16/10/25.
 */

public class HttpManger implements IHttpRequest {
    private IHttpRequest mIHttpRequest;

    @Override
    public <T> void getRequest(String url, Class<T> clazz, OnCompletedListener<T> listener) {
        mIHttpRequest.getRequest(url, clazz, listener);
    }

    @Override
    public <T> void getRequest(String url, Map<String, String> headers, Class<T> clazz, OnCompletedListener<T> listener) {
        mIHttpRequest.getRequest(url, headers, clazz, listener);

    }

    @Override
    public <T> void postRequest(String url, Map<String, String> requestBody, Class<T> clazz, OnCompletedListener listener) {
        mIHttpRequest.postRequest(url, requestBody, clazz, listener);
    }

    @Override
    public <T> void postRequest(String url, Map<String, String> headers, Map<String, String> requestBody, Class<T> clazz, OnCompletedListener listener) {
        mIHttpRequest.postRequest(url, headers, requestBody, clazz, listener);
    }

    @Override
    public <T> void typeGetRequest(String url, Type type, OnCompletedListener<T> listener) {
        mIHttpRequest.typeGetRequest(url, type, listener);
    }

    @Override
    public Response syncGetRequest(String url) {
        return mIHttpRequest.syncGetRequest(url);
    }

    private static final class HttpManagerHolder {
        private static final HttpManger sManger = new HttpManger();
    }

    public static HttpManger getInstance() {
        return HttpManagerHolder.sManger;
    }

    private HttpManger() {
        mIHttpRequest = new OkHttpImpl();
    }
}
