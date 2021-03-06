package com.dscunikom.android.sma14bandung.rest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.dscunikom.android.sma14bandung.activity.MainActivity;
import com.dscunikom.android.sma14bandung.activity.PreloadActivity;
import com.dscunikom.android.sma14bandung.model.Berita;

import java.util.HashMap;
import java.util.List;

public class SessionManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    //    Context context;
    int mode = 0;
    ApiInterface mApiInterface;
    List<Berita> mUserList;

    private static final String PREF_NAME = "SessionPref";
    private static final String IS_LOGIN = "is_login";
    public static final String USERNAME = "username";
    public static final String KECAMATAN = "kecamatan";
    public static final String KELURAHAN = "kelurahan";
    public static final String ID_PENGADUAN = "id_pengaduan";
    public static final String ID_BERITA = "id_berita";
    public static final String ID_ACARA = "id_acara";
    public  static  final String ID_EKSTRA ="id_estra";
    public  static  final String ID_FASILITAS ="id_fasilitas";
    public  static  final String ID_PRESTASI ="id_prestasi";
    public static final String ID_GURU = "id_guru";


    public static final String LOGIN_TYPE = "login_type";

    public SessionManager(Context _context) {
        this._context = _context;
        pref = _context.getSharedPreferences(PREF_NAME,mode);
        editor = pref.edit();
    }


    public void createIdBerita(String id_berita){
        editor.putString(ID_BERITA,id_berita);
        editor.commit();
    }
    public void createIdAcara(String id_acara){
        editor.putString(ID_ACARA,id_acara);
        editor.commit();
    }
    public void createIdEkstra(String id_ekstra){
        editor.putString(ID_EKSTRA,id_ekstra);
        editor.commit();
    }
    public void createdIdFasilitas(String id_fasilitas){
        editor.putString(ID_FASILITAS,id_fasilitas);
        editor.commit();
    }
    public void createdIdPrestasi(String id_prestasi){
        editor.putString(ID_PRESTASI,id_prestasi);
        editor.commit();
    }
    public void createIdGuru(String id_guru) {
        editor.putString(ID_GURU,id_guru);
        editor.commit();
    }
    public void checkLogin(){
        if(!this.is_login()){
            Intent i = new Intent(_context,MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }

    private boolean is_login(){
        return pref.getBoolean(IS_LOGIN,false);
    }


    public HashMap<String, String> getUserDetils(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(ID_BERITA,pref.getString(ID_BERITA,null));
        user.put(ID_ACARA,pref.getString(ID_ACARA,null));
        user.put(ID_EKSTRA,pref.getString(ID_EKSTRA,null));
        user.put(ID_FASILITAS,pref.getString(ID_FASILITAS,null));
        user.put(ID_PRESTASI,pref.getString(ID_PRESTASI,null));
        user.put(ID_GURU, pref.getString(ID_GURU,null));
        return user;

    }
}
