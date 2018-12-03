package com.dscunikom.android.sma14bandung.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.dscunikom.android.sma14bandung.R;
import com.dscunikom.android.sma14bandung.adapter.AdapterJanuary;
import com.dscunikom.android.sma14bandung.getModel.GetKalender;
import com.dscunikom.android.sma14bandung.model.Kalender;
import com.dscunikom.android.sma14bandung.rest.Api;
import com.dscunikom.android.sma14bandung.rest.ApiInterface;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KalendarActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    WebView webView;
    RecyclerView rvJanuary,rvFebuary,rvMaret,rvApril,rvMei,rvJuni,rvJuli,rvAgustus,rvSeptember,rvOktober,rvNopember,rvDesember;
    TextView tv_kalender_bulan;
    String URL = "http://projectdsc.ahdirdiysarm.com/uploads/kalender/f83ff9c15f76a8c470e4831503156d44.pdf";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalendar);
        FirebaseMessaging.getInstance().subscribeToTopic("helsan");

        rvJanuary = findViewById(R.id.januari);
        rvJanuary.setLayoutManager(new LinearLayoutManager(this));

        rvFebuary = findViewById(R.id.februari);
        rvFebuary.setLayoutManager(new LinearLayoutManager(this));

        rvMaret = findViewById(R.id.maret);
        rvMaret.setLayoutManager(new LinearLayoutManager(this));

        rvApril = findViewById(R.id.april);
        rvApril.setLayoutManager(new LinearLayoutManager(this));

        rvMei = findViewById(R.id.mei);
        rvMei.setLayoutManager(new LinearLayoutManager(this));

        rvJuni = findViewById(R.id.juni);
        rvJuni.setLayoutManager(new LinearLayoutManager(this));

        rvJuli = findViewById(R.id.juli);
        rvJuli.setLayoutManager(new LinearLayoutManager(this));

        rvAgustus = findViewById(R.id.agustus);
        rvAgustus.setLayoutManager(new LinearLayoutManager(this));

        rvSeptember = findViewById(R.id.september);
        rvSeptember.setLayoutManager(new LinearLayoutManager(this));

        rvOktober = findViewById(R.id.oktober);
        rvOktober.setLayoutManager(new LinearLayoutManager(this));

        rvNopember = findViewById(R.id.november);
        rvNopember.setLayoutManager(new LinearLayoutManager(this));

        rvDesember = findViewById(R.id.desember);
        rvDesember.setLayoutManager(new LinearLayoutManager(this));



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        getDataJanuary();
        getDataFebuary();
        getDataMaret();
        getDataApril();
        getDataMei();
        getDataJuni();
        getDataJuli();
        getDataAgustus();
        getDataSeptember();
        getDataOktober();
        getDataNovember();
        getDataDesember();

    }

    private void getDataMaret(){
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<GetKalender> call = apiInterface.getKalender();
        call.enqueue(new Callback<GetKalender>() {
            @Override
            public void onResponse(Call<GetKalender> call, Response<GetKalender> response) {
                List<Kalender> mList = response.body().getMaret();
//                Kalender kalender = new Kalender();
//                tv_kalender_bulan.setText(kalender.getNamaBulan());

                AdapterJanuary adapterJanuary = new AdapterJanuary(KalendarActivity.this);
                adapterJanuary.setmListKalender(mList);
                rvMaret.setAdapter(adapterJanuary);
            }


            @Override
            public void onFailure(Call<GetKalender> call, Throwable t) {

            }
        });
    }

    private void getDataApril(){
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<GetKalender> call = apiInterface.getKalender();
        call.enqueue(new Callback<GetKalender>() {
            @Override
            public void onResponse(Call<GetKalender> call, Response<GetKalender> response) {
                List<Kalender> mList = response.body().getApril();
//                Kalender kalender = new Kalender();
//                tv_kalender_bulan.setText(kalender.getNamaBulan());

                AdapterJanuary adapterJanuary = new AdapterJanuary(KalendarActivity.this);
                adapterJanuary.setmListKalender(mList);
                rvApril.setAdapter(adapterJanuary);
            }

            @Override
            public void onFailure(Call<GetKalender> call, Throwable t) {

            }
        });
    }

    private void getDataMei(){
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<GetKalender> call = apiInterface.getKalender();
        call.enqueue(new Callback<GetKalender>() {
            @Override
            public void onResponse(Call<GetKalender> call, Response<GetKalender> response) {
                List<Kalender> mList = response.body().getMei();
//                Kalender kalender = new Kalender();
//                tv_kalender_bulan.setText(kalender.getNamaBulan());

                AdapterJanuary adapterJanuary = new AdapterJanuary(KalendarActivity.this);
                adapterJanuary.setmListKalender(mList);
                rvMei.setAdapter(adapterJanuary);
            }

            @Override
            public void onFailure(Call<GetKalender> call, Throwable t) {

            }
        });
    }

    private void getDataJuni(){
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<GetKalender> call = apiInterface.getKalender();
        call.enqueue(new Callback<GetKalender>() {
            @Override
            public void onResponse(Call<GetKalender> call, Response<GetKalender> response) {
                List<Kalender> mList = response.body().getJuni();
//                Kalender kalender = new Kalender();
//                tv_kalender_bulan.setText(kalender.getNamaBulan());

                AdapterJanuary adapterJanuary = new AdapterJanuary(KalendarActivity.this);
                adapterJanuary.setmListKalender(mList);
                rvJuni.setAdapter(adapterJanuary);
            }

            @Override
            public void onFailure(Call<GetKalender> call, Throwable t) {

            }
        });
    }

    private void getDataJuli(){
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<GetKalender> call = apiInterface.getKalender();
        call.enqueue(new Callback<GetKalender>() {
            @Override
            public void onResponse(Call<GetKalender> call, Response<GetKalender> response) {
                List<Kalender> mList = response.body().getJuli();
//                Kalender kalender = new Kalender();
//                tv_kalender_bulan.setText(kalender.getNamaBulan());

                AdapterJanuary adapterJanuary = new AdapterJanuary(KalendarActivity.this);
                adapterJanuary.setmListKalender(mList);
                rvJuli.setAdapter(adapterJanuary);
            }

            @Override
            public void onFailure(Call<GetKalender> call, Throwable t) {

            }
        });
    }

    private void getDataAgustus(){
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<GetKalender> call = apiInterface.getKalender();
        call.enqueue(new Callback<GetKalender>() {
            @Override
            public void onResponse(Call<GetKalender> call, Response<GetKalender> response) {
                List<Kalender> mList = response.body().getAgustus();
//                Kalender kalender = new Kalender();
//                tv_kalender_bulan.setText(kalender.getNamaBulan());

                AdapterJanuary adapterJanuary = new AdapterJanuary(KalendarActivity.this);
                adapterJanuary.setmListKalender(mList);
                rvAgustus.setAdapter(adapterJanuary);
            }

            @Override
            public void onFailure(Call<GetKalender> call, Throwable t) {

            }
        });
    }

    private void getDataSeptember(){
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<GetKalender> call = apiInterface.getKalender();
        call.enqueue(new Callback<GetKalender>() {
            @Override
            public void onResponse(Call<GetKalender> call, Response<GetKalender> response) {
                List<Kalender> mList = response.body().getSeptember();
//                Kalender kalender = new Kalender();
//                tv_kalender_bulan.setText(kalender.getNamaBulan());

                AdapterJanuary adapterJanuary = new AdapterJanuary(KalendarActivity.this);
                adapterJanuary.setmListKalender(mList);
                rvSeptember.setAdapter(adapterJanuary);
            }

            @Override
            public void onFailure(Call<GetKalender> call, Throwable t) {

            }
        });
    }
    private void getDataOktober(){
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<GetKalender> call = apiInterface.getKalender();
        call.enqueue(new Callback<GetKalender>() {
            @Override
            public void onResponse(Call<GetKalender> call, Response<GetKalender> response) {
                List<Kalender> mList = response.body().getOktober();
//                Kalender kalender = new Kalender();
//                tv_kalender_bulan.setText(kalender.getNamaBulan());

                AdapterJanuary adapterJanuary = new AdapterJanuary(KalendarActivity.this);
                adapterJanuary.setmListKalender(mList);
                rvOktober.setAdapter(adapterJanuary);
            }

            @Override
            public void onFailure(Call<GetKalender> call, Throwable t) {

            }
        });
    }
    private void getDataNovember(){
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<GetKalender> call = apiInterface.getKalender();
        call.enqueue(new Callback<GetKalender>() {
            @Override
            public void onResponse(Call<GetKalender> call, Response<GetKalender> response) {
                List<Kalender> mList = response.body().getNovember();
//                Kalender kalender = new Kalender();
//                tv_kalender_bulan.setText(kalender.getNamaBulan());

                AdapterJanuary adapterJanuary = new AdapterJanuary(KalendarActivity.this);
                adapterJanuary.setmListKalender(mList);
                rvNopember.setAdapter(adapterJanuary);
            }

            @Override
            public void onFailure(Call<GetKalender> call, Throwable t) {

            }
        });
    }


    private void getDataDesember(){
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<GetKalender> call = apiInterface.getKalender();
        call.enqueue(new Callback<GetKalender>() {
            @Override
            public void onResponse(Call<GetKalender> call, Response<GetKalender> response) {
                List<Kalender> mList = response.body().getDesember();
//                Kalender kalender = new Kalender();
//                tv_kalender_bulan.setText(kalender.getNamaBulan());

                AdapterJanuary adapterJanuary = new AdapterJanuary(KalendarActivity.this);
                adapterJanuary.setmListKalender(mList);
                rvDesember.setAdapter(adapterJanuary);
            }

            @Override
            public void onFailure(Call<GetKalender> call, Throwable t) {

            }
        });
    }

    private void getDataFebuary(){
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<GetKalender> call = apiInterface.getKalender();
        call.enqueue(new Callback<GetKalender>() {
            @Override
            public void onResponse(Call<GetKalender> call, Response<GetKalender> response) {
                List<Kalender> mList = response.body().getFebuary();
//                Kalender kalender = new Kalender();
//                tv_kalender_bulan.setText(kalender.getNamaBulan());

                AdapterJanuary adapterJanuary = new AdapterJanuary(KalendarActivity.this);
                adapterJanuary.setmListKalender(mList);
                rvFebuary.setAdapter(adapterJanuary);
            }

            @Override
            public void onFailure(Call<GetKalender> call, Throwable t) {

            }
        });
    }


    private void getDataJanuary(){
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<GetKalender> call = apiInterface.getKalender();
        call.enqueue(new Callback<GetKalender>() {
            @Override
            public void onResponse(Call<GetKalender> call, Response<GetKalender> response) {
                List<Kalender> mList = response.body().getJanuary();
                AdapterJanuary adapterJanuary = new AdapterJanuary(KalendarActivity.this);
                adapterJanuary.setmListKalender(mList);
                rvJanuary.setAdapter(adapterJanuary);
            }

            @Override
            public void onFailure(Call<GetKalender> call, Throwable t) {

            }
        });
    }


    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            Intent goToMainActivity = new Intent(getApplicationContext(), MainActivity.class);
            goToMainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Will clear out your activity history stack till now
            startActivity(goToMainActivity);
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.



        int id = item.getItemId();
        item.setChecked(true);

        if (id == R.id.nav_home) {
            // Handle the camera action
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            ActivityCompat.finishAffinity(KalendarActivity.this);
        } else if (id == R.id.nav_teacher) {
            Intent intent = new Intent(this, GuruActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            ActivityCompat.finishAffinity(KalendarActivity.this);


        } else if (id == R.id.nav_calendar) {


        } else if (id == R.id.nav_contact) {
            Intent intent = new Intent(this, KontakActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(intent);
            ActivityCompat.finishAffinity(KalendarActivity.this);

        }
        else if (id == R.id.nav_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(intent);
            ActivityCompat.finishAffinity(KalendarActivity.this);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
