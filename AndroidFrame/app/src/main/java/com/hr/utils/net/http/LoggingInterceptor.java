package com.hr.utils.net.http;

import com.hr.utils.LogUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by aaa on 2017/4/10.
 */

public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        //if (BuildConfig.DEBUG) {
            LogUtil.e(String.format("发送请求 %s on %s%n%s",
                    request.url()+"?"+request.body(), chain.connection(), request.headers()));
       // }
        return chain.proceed(request);
    }
}
