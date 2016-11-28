package com.example.thaianhit.citytravel;

import android.content.Context;


import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {
    public static final String BASE_URL = "http://citytravel-2.apphb.com/api/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient(Context context) {
        SiCookieStore2 siCookieStore = new SiCookieStore2(context);
        CookieManager cookieManager = new CookieManager(siCookieStore, CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(cookieManager);
        OkHttpClient client = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(cookieManager)).build();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).client(client)
                    .build();
        }
        return retrofit;
    }
}
