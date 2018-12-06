package com.dscunikom.android.sma14bandung.getModel;

import com.dscunikom.android.sma14bandung.model.GambarFasilitas;
import com.dscunikom.android.sma14bandung.model.Prestasi;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPrestasi {
    @SerializedName("result")
    @Expose
    private List<Prestasi> result = null;
    @SerializedName("gambar")
    @Expose
    private List<GambarFasilitas> gambar = null;

    public void setResult(List<Prestasi> result) {
        this.result = result;
    }

    public List<Prestasi> getResult() {

        return result;
    }

    public List<GambarFasilitas> getGambar() {
        return gambar;
    }

    public void setGambar(List<GambarFasilitas> gambar) {
        this.gambar = gambar;
    }
}

