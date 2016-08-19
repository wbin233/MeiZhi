package com.example.dell_pc.meizhi.network.Api;

import com.example.dell_pc.meizhi.Model.MeizhiResult;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by wbin on 2016/8/16.
 */
public interface MeizhiApi {
    @GET("福利/count/{count}/page/{page}")
    Observable<MeizhiResult> getImgs(@Path("count") int count, @Path("page") int page);
}
