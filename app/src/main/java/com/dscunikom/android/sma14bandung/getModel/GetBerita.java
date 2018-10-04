package com.dscunikom.android.sma14bandung.getModel;

import com.dscunikom.android.sma14bandung.model.Berita;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetBerita {
    @SerializedName("result")
   public List<Berita> getBerita;

    public List<Berita> getGetBerita() {
        return getBerita;
    }
}
