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

    @SerializedName("Maret")
    @Expose
    private List<Kalender> maret = null;
    @SerializedName("April")
    @Expose
    private List<Kalender> april = null;
    @SerializedName("Mei")
    @Expose
    private List<Kalender> mei = null;
    @SerializedName("Juni")
    @Expose
    private List<Kalender> juni = null;
    @SerializedName("Juli")
    @Expose
    private List<Kalender> juli = null;
    @SerializedName("Agustus")
    @Expose
    private List<Kalender> agustus = null;
    @SerializedName("September")
    @Expose
    private List<Kalender> september = null;
    @SerializedName("Oktober")
    @Expose
    private List<Kalender> oktober = null;
    @SerializedName("November")
    @Expose
    private List<Kalender> november = null;
    @SerializedName("Desember")
    @Expose
    private List<Kalender> desember = null;

    public List<Kalender> getMaret() {
        return maret;
    }

    public void setMaret(List<Kalender> maret) {
        this.maret = maret;
    }

    public List<Kalender> getApril() {
        return april;
    }

    public void setApril(List<Kalender> april) {
        this.april = april;
    }

    public List<Kalender> getMei() {
        return mei;
    }

    public void setMei(List<Kalender> mei) {
        this.mei = mei;
    }

    public List<Kalender> getJuni() {
        return juni;
    }

    public void setJuni(List<Kalender> juni) {
        this.juni = juni;
    }

    public List<Kalender> getJuli() {
        return juli;
    }

    public void setJuli(List<Kalender> juli) {
        this.juli = juli;
    }

    public List<Kalender> getAgustus() {
        return agustus;
    }

    public void setAgustus(List<Kalender> agustus) {
        this.agustus = agustus;
    }

    public List<Kalender> getSeptember() {
        return september;
    }

    public void setSeptember(List<Kalender> september) {
        this.september = september;
    }

    public List<Kalender> getOktober() {
        return oktober;
    }

    public void setOktober(List<Kalender> oktober) {
        this.oktober = oktober;
    }

    public List<Kalender> getNovember() {
        return november;
    }

    public void setNovember(List<Kalender> november) {
        this.november = november;
    }

    public List<Kalender> getDesember() {
        return desember;
    }

    public void setDesember(List<Kalender> desember) {
        this.desember = desember;
    }

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
