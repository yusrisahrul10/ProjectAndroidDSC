package com.dscunikom.android.sma14bandung.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Prestasi {
    @SerializedName("id_prestasi")
    @Expose
    private String idPrestasi;
    @SerializedName("nama_prestasi")
    @Expose
    private String namaPrestasi;
    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("image")
    @Expose
    private String image;

    public String getIdPrestasi() {
        return idPrestasi;
    }

    public void setIdPrestasi(String idPrestasi) {
        this.idPrestasi = idPrestasi;
    }

    public String getNamaPrestasi() {
        return namaPrestasi;
    }

    public void setNamaPrestasi(String namaPrestasi) {
        this.namaPrestasi = namaPrestasi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
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
