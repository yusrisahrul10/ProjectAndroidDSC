package com.dscunikom.android.sma14bandung.rest;

import com.dscunikom.android.sma14bandung.getModel.GetBerita;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("berita")
    Call<GetBerita> getBerita();
}
