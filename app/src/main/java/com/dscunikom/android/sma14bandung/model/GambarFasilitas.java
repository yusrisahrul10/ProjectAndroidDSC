package com.dscunikom.android.sma14bandung.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GambarFasilitas {
    @SerializedName("id_gambar_fasilitas")
    @Expose
    private String idGambarFasilitas;
    @SerializedName("id_fasilitas")
    @Expose
    private String idFasilitas;
    @SerializedName("image")
    @Expose
    private String image;

    public String getIdGambarFasilitas() {
        return idGambarFasilitas;
    }

    public void setIdGambarFasilitas(String idGambarFasilitas) {
        this.idGambarFasilitas = idGambarFasilitas;
    }
    public String getIdFasilitas() {
        return idFasilitas;
    }

    public void setIdFasilitas(String idFasilitas) {
        this.idFasilitas = idFasilitas;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}