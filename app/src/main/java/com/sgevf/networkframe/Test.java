package com.sgevf.networkframe;

import com.sgevf.network.basic.entitiy.Response;

import io.reactivex.Observable;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface Test {
    @POST("T")
    Observable<Response<String>> test();
}
