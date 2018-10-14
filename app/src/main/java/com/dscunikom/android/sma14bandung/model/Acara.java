package com.dscunikom.android.sma14bandung.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Acara {
    @SerializedName("id_acara")
    @Expose
    private String idAcara;
    @SerializedName("nama_acara")
    @Expose
    private String namaAcara;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("image")
    @Expose
    private String image;

    public String getIdAcara() {
        return idAcara;
    }

    public void setIdAcara(String idAcara) {
        this.idAcara = idAcara;
    }

    public String getNamaAcara() {
        return namaAcara;
    }

    public void setNamaAcara(String namaAcara) {
        this.namaAcara = namaAcara;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
