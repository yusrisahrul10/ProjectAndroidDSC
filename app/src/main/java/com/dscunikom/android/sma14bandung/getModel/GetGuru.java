package com.dscunikom.android.sma14bandung.getModel;

import com.dscunikom.android.sma14bandung.model.Guru;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetGuru {

    @SerializedName("result")
    @Expose
    private List<Guru> result = null;

    public List<Guru> getResult() {
        return result;
    }

    public void setResult(List<Guru> result) {
        this.result = result;
    }
}
