package com.dscunikom.android.sma14bandung.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Guru implements Parcelable {

    @SerializedName("id_guru")
    @Expose
    private String idGuru;
    @SerializedName("nama_guru")
    @Expose
    private String namaGuru;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mapel")
    @Expose
    private String mapel;
    @SerializedName("kelas_ajar")
    @Expose
    private String kelasAjar;
    @SerializedName("jabatan")
    @Expose
    private String jabatan;
    @SerializedName("image")
    @Expose
    private String image;

    public String getIdGuru() {
        return idGuru;
    }

    public void setIdGuru(String idGuru) {
        this.idGuru = idGuru;
    }

    public String getNamaGuru() {
        return namaGuru;
    }

    public void setNamaGuru(String namaGuru) {
        this.namaGuru = namaGuru;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMapel() {
        return mapel;
    }

    public void setMapel(String mapel) {
        this.mapel = mapel;
    }

    public String getKelasAjar() {
        return kelasAjar;
    }

    public void setKelasAjar(String kelasAjar) {
        this.kelasAjar = kelasAjar;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static Creator<Guru> getCREATOR() {
        return CREATOR;
    }

    public Guru() {
    }

    protected Guru(Parcel in) {
        idGuru = in.readString();
        namaGuru = in.readString();
        email = in.readString();
        mapel = in.readString();
        kelasAjar = in.readString();
        jabatan = in.readString();
        image = in.readString();
    }

    public static final Creator<Guru> CREATOR = new Creator<Guru>() {
        @Override
        public Guru createFromParcel(Parcel in) {
            return new Guru(in);
        }

        @Override
        public Guru[] newArray(int size) {
            return new Guru[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idGuru);
        dest.writeString(namaGuru);
        dest.writeString(email);
        dest.writeString(mapel);
        dest.writeString(kelasAjar);
        dest.writeString(jabatan);
        dest.writeString(image);
    }
}
