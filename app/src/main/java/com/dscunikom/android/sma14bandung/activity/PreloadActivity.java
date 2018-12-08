package com.dscunikom.android.sma14bandung.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Toast;

import com.dscunikom.android.sma14bandung.R;
import com.dscunikom.android.sma14bandung.activity.MainActivity;
import com.dscunikom.android.sma14bandung.getModel.GetResponse;
import com.dscunikom.android.sma14bandung.rest.Api;
import com.dscunikom.android.sma14bandung.rest.ApiInterface;
import com.dscunikom.android.sma14bandung.rest.SessionManager;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Callback;
import retrofit2.Response;

public class PreloadActivity extends AppCompatActivity {
    private String ANDROID_ID;
    SessionManager sessionManager;
    private boolean mStarted = false;
    static final int REQUEST_LOCATION = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseMessaging.getInstance().subscribeToTopic("helsan");

        if(ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }


        ANDROID_ID = Settings.Secure.getString(this.getApplication().getContentResolver(),Settings.Secure.ANDROID_ID);
        sessionManager = new SessionManager(getApplicationContext());
        if(!isConnected(PreloadActivity.this)) buildDialog(PreloadActivity.this).show();
        else {
            Toast.makeText(PreloadActivity.this,"Welcome", Toast.LENGTH_SHORT).show();
            setContentView(R.layout.activity_preload);
            sessionManager = new SessionManager(getApplicationContext());
//          sessionManager.getUser();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    sessionManager.checkLogin();
                    postID();
                    finish();
                }
            }, 2000);
        }
        }
    @Override
    protected void onStart() {
        // the activity becomes at least partially visible
        mStarted = true;

        super.onStart();
    }
    @Override
    protected void onStop() {
        // the activity is no longer visible
        mStarted = false;

        super.onStop();
    }



    public boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetInfo = cm.getActiveNetworkInfo();

        if (mNetInfo != null && mNetInfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if ((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
                return true;
            else return false;
        } else
            return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("Tidak Ada Koneksi Internet");
        builder.setMessage("Anda perlu cek kembali jaringan internet anda . Tekan ok untuk keluar");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        return builder;
    }
    private void postID(){
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        retrofit2.Call<GetResponse> post = apiInterface.postRespone(ANDROID_ID);
        post.enqueue(new Callback<GetResponse>() {
            @Override
            public void onResponse(retrofit2.Call<GetResponse> call, Response<GetResponse> response) {
                if(response.body().getResponse().equals("success")){
                    Toast.makeText(getApplicationContext(),"Berhasil",Toast.LENGTH_LONG);
                }else{
                    finish();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<GetResponse> call, Throwable t) {

            }
        });
    }
}
