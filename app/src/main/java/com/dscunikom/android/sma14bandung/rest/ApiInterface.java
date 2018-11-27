package com.dscunikom.android.sma14bandung.rest;

import com.dscunikom.android.sma14bandung.getModel.GetAcara;
import com.dscunikom.android.sma14bandung.getModel.GetBerita;
import com.dscunikom.android.sma14bandung.getModel.GetEkstra;
import com.dscunikom.android.sma14bandung.getModel.GetFasilitas;
import com.dscunikom.android.sma14bandung.getModel.GetPrestasi;
import com.dscunikom.android.sma14bandung.getModel.GetResponse;
import com.dscunikom.android.sma14bandung.model.Acara;
import com.dscunikom.android.sma14bandung.model.Ekstrakulikuler;
import com.dscunikom.android.sma14bandung.model.Prestasi;
import com.dscunikom.android.sma14bandung.getModel.GetGambarFasilitas;

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

    @GET("fasilitas")
    Call<GetFasilitas> getFasilitas();

    @GET("fasilitas/get_gambar")
    Call<GetGambarFasilitas>getDetailFasilitas(@Query("id_fasilitas")String id_fasilitas);


    @GET("ekstrakulikuler")
    Call<GetEkstra> getEkstra();
    
    @GET("ekstrakulikuler")
    Call<Ekstrakulikuler>getDetailEkstra(@Query("id_ekstra")String id_ekstra);

    @GET("acara")
    Call<GetAcara> getAcara();

    @GET("acara")
    Call<Acara> getDetailAcara(@Query("id_acara") String id_acara);

    @GET("prestasi")
    Call<GetPrestasi> getPrestasi();

    @GET("prestasi/limit")
    Call<GetPrestasi> getPrestasiLimit();

    @GET("prestasi")
    Call<Prestasi> getDetailPrestasi(@Query("id_prestasi") String id_prestasi);

}
