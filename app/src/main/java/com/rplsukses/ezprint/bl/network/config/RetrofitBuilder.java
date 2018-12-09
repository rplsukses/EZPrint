package com.rplsukses.ezprint.bl.network.config;

import android.content.Context;

import com.rplsukses.ezprint.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

import static retrofit2.converter.gson.GsonConverterFactory.create;

public class RetrofitBuilder {
    public static Retrofit builder(Context ctx){
        OkHttpClient.Builder okhttpBuilder = new OkHttpClient().newBuilder();
        okhttpBuilder.connectTimeout(60, TimeUnit.SECONDS);
        okhttpBuilder.writeTimeout(60, TimeUnit.SECONDS);
        okhttpBuilder.readTimeout(60, TimeUnit.SECONDS);

        //ga tau ini ukuran apa
        int cahceSize = 10 * 1024 * 1024;
        Cache cache = new Cache(ctx.getCacheDir(), cahceSize);
        okhttpBuilder.cache(cache);

        if (BuildConfig.DEBUG){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okhttpBuilder.addInterceptor(interceptor);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .client(okhttpBuilder.build())
                .addConverterFactory(create())
                .build();

        return retrofit;
    }
}
