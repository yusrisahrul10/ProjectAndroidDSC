package com.dscunikom.android.sma14bandung.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ekstrakulikuler {
    @SerializedName("id_ekstra")
    @Expose
    private String idEkstra;
    @SerializedName("nama_ekstra")
    @Expose
    private String namaEkstra;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("image")
    @Expose
    private String image;

    public String getIdEkstra() {
        return idEkstra;
    }

    public void setIdEkstra(String idEkstra) {
        this.idEkstra = idEkstra;
    }

    public String getNamaEkstra() {
        return namaEkstra;
    }

    public void setNamaEkstra(String namaEkstra) {
        this.namaEkstra = namaEkstra;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
