package com.renxiaoxiao.developtools.common.okhttp;

import com.renxiaoxiao.developtools.common.okhttp.cookie.SimpleCookieJar;
import com.renxiaoxiao.developtools.common.okhttp.listener.DisposeDataHandle;
import com.renxiaoxiao.developtools.common.okhttp.response.CommonFileCallback;
import com.renxiaoxiao.developtools.common.okhttp.response.CommonJsonCallback;
import com.renxiaoxiao.developtools.common.okhttp.ssl.HttpsUtils;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by renxiaoxiao on 2016/11/1.
 * 用于发送get，post请求的工具类
 */

public class CommonOkHttpClient {

    private static final int TIME_OUT = 30;
    private static OkHttpClient mOkHttpClient;

    static {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });

        builder.cookieJar(new SimpleCookieJar());
        builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.followRedirects(true);
        /**
         * trust all the https point
         */
        builder.sslSocketFactory(HttpsUtils.getSslSocketFactory(), HttpsUtils.getX509TrustManager());

        mOkHttpClient = builder.build();
    }

    /**
     * 指定cilent信任指定证书
     */
    public static void setCertificates(InputStream... certificates)
    {
        mOkHttpClient.newBuilder().sslSocketFactory(HttpsUtils.getSslSocketFactory(certificates, null, null), HttpsUtils.getX509TrustManager()).build();
    }

    /**
     * 指定client信任所有证书
     */
    public static void setCertificates()
    {
        mOkHttpClient.newBuilder().sslSocketFactory(HttpsUtils.getSslSocketFactory(), HttpsUtils.getX509TrustManager());
    }

    /**
     * 通过构造好的Request,Callback去发送请求
     */
    public static Call get(Request request, DisposeDataHandle handle)
    {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }

    public static Call post(Request request, DisposeDataHandle handle)
    {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }

    public static Call downloadFile(Request request, DisposeDataHandle handle)
    {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonFileCallback(handle));
        return call;
    }
}
