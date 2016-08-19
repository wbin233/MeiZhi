package com.example.dell_pc.meizhi.network.Api;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by wbin on 2016/8/17.
 */
public interface ImgApi {
    @GET
    Observable<ResponseBody> getImg(@Url String url);
}
