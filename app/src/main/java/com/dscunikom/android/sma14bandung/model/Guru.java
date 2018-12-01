package com.dscunikom.android.sma14bandung.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Guru implements Parcelable {
    private String id_img;
    private String nama;
    private String mapel;
    private String email;
    private String kelas;

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public static Creator<Guru> getCREATOR() {
        return CREATOR;
    }

    private  String jabatan;

    public String getId_img() {
        return id_img;
    }

    public void setId_img(String id_img) {
        this.id_img = id_img;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getMapel() {
        return mapel;
    }

    public void setMapel(String mapel) {
        this.mapel = mapel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public Guru() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id_img);
        dest.writeString(this.nama);
        dest.writeString(this.mapel);
        dest.writeString(this.email);
        dest.writeString(this.kelas);
        dest.writeString(this.jabatan);
    }

    protected Guru(Parcel in) {
        this.id_img = in.readString();
        this.nama = in.readString();
        this.mapel = in.readString();
        this.email = in.readString();
        this.kelas = in.readString();
        this.jabatan = in.readString();
    }

    public static final Creator<Guru> CREATOR = new Creator<Guru>() {
        @Override
        public Guru createFromParcel(Parcel source) {
            return new Guru(source);
        }

        @Override
        public Guru[] newArray(int size) {
            return new Guru[size];
        }
    };
}
