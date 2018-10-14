package com.dscunikom.android.sma14bandung.getModel;

import com.dscunikom.android.sma14bandung.model.Acara;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAcara {
    @SerializedName("result")
    @Expose
    private List<Acara> result = null;

    public List<Acara> getResult() {
        return result;
    }

    public void setResult(List<Acara> result) {
        this.result = result;
    }
}
