package com.dscunikom.android.sma14bandung.rest;

import com.dscunikom.android.sma14bandung.getModel.GetBerita;
import com.dscunikom.android.sma14bandung.getModel.GetResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("berita")
    Call<GetBerita> getBerita();
    @GET("berita")
    Call<GetBerita> getDetailBerita(@Query("id_berita") String id_berita);

    @FormUrlEncoded
    @POST("trafic/auth")
    Call<GetResponse> postRespone(@Field("android_id") String android_id);
}
