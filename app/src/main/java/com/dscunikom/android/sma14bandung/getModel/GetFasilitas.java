package com.dscunikom.android.sma14bandung.getModel;

import com.dscunikom.android.sma14bandung.model.Fasilitas;
import com.dscunikom.android.sma14bandung.model.GambarFasilitas;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetFasilitas {
    @SerializedName("result")
    @Expose
    private List<Fasilitas> result = null;



    public List<Fasilitas> getResult() {
        return result;
    }

    public void setResult(List<Fasilitas> result) {
        this.result = result;
    }
}
