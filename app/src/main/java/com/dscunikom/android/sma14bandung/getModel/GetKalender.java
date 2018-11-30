package com.dscunikom.android.sma14bandung.getModel;

import com.dscunikom.android.sma14bandung.model.Kalender;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetKalender {


    @SerializedName("January")
    @Expose
    private List<Kalender> january = null;
    @SerializedName("Febuary")
    @Expose
    private List<Kalender> febuary = null;

    public List<Kalender> getJanuary() {
        return january;
    }

    public void setJanuary(List<Kalender> january) {
        this.january = january;
    }

    public List<Kalender> getFebuary() {
        return febuary;
    }

    public void setFebuary(List<Kalender> febuary) {
        this.febuary = febuary;
    }
}
