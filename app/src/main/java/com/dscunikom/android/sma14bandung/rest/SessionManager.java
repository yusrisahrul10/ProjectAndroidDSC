package com.dscunikom.android.sma14bandung.rest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

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


    public static final String LOGIN_TYPE = "login_type";

    public SessionManager(Context _context) {
        this._context = _context;
        pref = _context.getSharedPreferences(PREF_NAME,mode);
        editor = pref.edit();
    }

//    public void createSession(String username , String role , String kecamatan , String kelurahan ){
//        editor.putBoolean(IS_LOGIN,true);
//        editor.putString(USERNAME,username);
//        editor.putString(LOGIN_TYPE,role);
//        editor.putString(KECAMATAN,kecamatan);
//        editor.putString(KELURAHAN,kelurahan);
////        editor.putString(ID_PENGADUAN,id_pengaduan);
//
//        editor.commit();
//    }
//    public void createPengaduan(String id_pengaduan){
//        editor.putString(ID_PENGADUAN,id_pengaduan);
//        editor.commit();
//    }
    public void createIdBerita(String id_berita){
        editor.putString(ID_BERITA,id_berita);
        editor.commit();
    }

//    public void checkLogin(){
//        if(!this.is_login()){
//            Intent i = new Intent(_context,MainActivity.class);
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            _context.startActivity(i);
//        }else{
//            Intent i = new Intent(_context, HomeActivity.class);
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            _context.startActivity(i);
//        }
//    }

    private boolean is_login(){
        return pref.getBoolean(IS_LOGIN,false);
    }

//    public void logout(){
//        editor.clear();
//        editor.commit();
//        Intent intent = new Intent(_context, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        _context.startActivity(intent);
//    }

    public HashMap<String, String> getUserDetils(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(ID_BERITA,pref.getString(ID_BERITA,null));


        return user;

    }
}
