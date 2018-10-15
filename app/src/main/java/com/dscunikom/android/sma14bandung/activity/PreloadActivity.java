package com.dscunikom.android.sma14bandung.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.provider.Settings;
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
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Callback;
import retrofit2.Response;

public class PreloadActivity extends AppCompatActivity {
    private String ANDROID_ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preload);
        FirebaseMessaging.getInstance().subscribeToTopic("helsan");
        ANDROID_ID = Settings.Secure.getString(this.getApplication().getContentResolver(),Settings.Secure.ANDROID_ID);

//        if(!isConnected(PreloadActivity.this)) buildDialog(PreloadActivity.this).show();
//        else {
//            Toast.makeText(PreloadActivity.this,"Welcome", Toast.LENGTH_SHORT).show();
//            setContentView(R.layout.activity_preload);


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

        postID();
        Intent intent = new Intent(PreloadActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
                }
            }, 5000);
        }
//    }



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
