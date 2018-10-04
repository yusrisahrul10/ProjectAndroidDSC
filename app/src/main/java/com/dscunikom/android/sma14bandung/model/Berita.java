package com.dscunikom.android.sma14bandung.model;

import com.google.gson.annotations.SerializedName;

public class Berita {
    @SerializedName("id_berita")
    private String id_berita;

    @SerializedName("judul_berita")
    private String judul_berita;

    @SerializedName("isi_berita")
    private String isi_berita;

    @SerializedName("tanggal")
    private String tanggal;

    @SerializedName("image")
    private String image;

    public String getId_berita() {
        return id_berita;
    }

    public String getJudul_berita() {
        return judul_berita;
    }

    public String getIsi_berita() {
        return isi_berita;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getImage() {
        return image;
    }
}
