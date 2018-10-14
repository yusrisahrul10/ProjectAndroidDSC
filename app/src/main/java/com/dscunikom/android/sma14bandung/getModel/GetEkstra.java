package com.dscunikom.android.sma14bandung.getModel;

import com.dscunikom.android.sma14bandung.model.Ekstrakulikuler;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetEkstra {
    @SerializedName("result")
    @Expose
    private List<Ekstrakulikuler> result = null;

    public List<Ekstrakulikuler> getResult() {
        return result;
    }

    public void setResult(List<Ekstrakulikuler> result) {
        this.result = result;
    }
}
