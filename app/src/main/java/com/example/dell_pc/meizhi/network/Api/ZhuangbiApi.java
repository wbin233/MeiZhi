package com.example.dell_pc.meizhi.network.Api;


import com.example.dell_pc.meizhi.Model.ZhuangbiModel;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wbin on 2016/8/16.
 */
public interface ZhuangbiApi {
    @GET("search")
    Observable<List<ZhuangbiModel>> search(@Query("q") String key);
}
