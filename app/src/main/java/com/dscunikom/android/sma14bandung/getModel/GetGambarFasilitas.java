package com.dscunikom.android.sma14bandung.getModel;

import com.dscunikom.android.sma14bandung.model.GambarFasilitas;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetGambarFasilitas {
    @SerializedName("gambar")
    @Expose
    private List<GambarFasilitas> gambarFasilitas = null;

    public List<GambarFasilitas> getGambarFasilitas() {
        return gambarFasilitas;
    }

    public void setGambarFasilitas(List<GambarFasilitas> gambarFasilitas) {
        this.gambarFasilitas = gambarFasilitas;
    }
}
