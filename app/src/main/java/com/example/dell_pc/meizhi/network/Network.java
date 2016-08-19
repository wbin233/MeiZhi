package com.example.dell_pc.meizhi.network;

import com.example.dell_pc.meizhi.network.Api.ImgApi;
import com.example.dell_pc.meizhi.network.Api.MeizhiApi;
import com.example.dell_pc.meizhi.network.Api.ZhuangbiApi;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wbin on 2016/8/16.
 */
public class Network {
    private static ZhuangbiApi zhuangbiApi;
    private static MeizhiApi meizhiApi;
    private static ImgApi imgApi;
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    public static ZhuangbiApi getZhuangbiApi() {
        if (zhuangbiApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://zhuangbi.info/")
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            zhuangbiApi = retrofit.create(ZhuangbiApi.class);
        }
        return zhuangbiApi;
    }

    public static MeizhiApi getMeizhiApi() {
        if (meizhiApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(new OkHttpClient())
                    .baseUrl("http://gank.io/api/search/query/listview/category/")
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            meizhiApi = retrofit.create(MeizhiApi.class);
        }
        return meizhiApi;
    }

    public static ImgApi getImgApi() {
        if (imgApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("/")
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            imgApi = retrofit.create(ImgApi.class);
        }
        return imgApi;
    }
}
