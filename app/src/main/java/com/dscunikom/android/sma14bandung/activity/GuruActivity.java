package com.dscunikom.android.sma14bandung.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dscunikom.android.sma14bandung.R;
import com.dscunikom.android.sma14bandung.adapter.AdapterGuru;
import com.dscunikom.android.sma14bandung.getModel.GetGuru;
import com.dscunikom.android.sma14bandung.model.Guru;
import com.dscunikom.android.sma14bandung.rest.Api;
import com.dscunikom.android.sma14bandung.rest.ApiInterface;
import com.dscunikom.android.sma14bandung.rest.ItemClickSupport;
import com.dscunikom.android.sma14bandung.rest.SessionManager;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuruActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView rvCategory;
    ImageView img;
    TextView tvkepsek;

    SessionManager sessionManager;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guru);
        ButterKnife.bind(this);
        FirebaseMessaging.getInstance().subscribeToTopic("helsan");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rvCategory = findViewById(R.id.rv_list_guru);
        rvCategory.setHasFixedSize(true);
        rvCategory.setLayoutManager(new GridLayoutManager(this, 3));

        sessionManager = new SessionManager(GuruActivity.this.getApplicationContext());
        progressBar = findViewById(R.id.progressbarguru);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        getGuru();

        img = findViewById(R.id.imgKlik);
        tvkepsek = findViewById(R.id.kepsek);


        getKepalaSekolah();

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

    private void clickItemDetail(Guru guru) {
        Intent intent = new Intent(GuruActivity.this, DetailGuruActivity.class);
        intent.putExtra("id_guru",guru.getIdGuru());
        startActivity(intent);
        this.overridePendingTransition(0, 0);

    }

    private void getGuru(){
        final AdapterGuru adapterGuru = new AdapterGuru(GuruActivity.this);
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<GetGuru> call = apiInterface.getGuru();
        call.enqueue(new Callback<GetGuru>() {
            @Override
            public void onResponse(Call<GetGuru> call, Response<GetGuru> response) {
                progressBar.setVisibility(View.GONE);
                List<Guru> listGuru = response.body().getResult();
                adapterGuru.setmListGuru(listGuru);
                reloadView(adapterGuru, listGuru);
            }

            @Override
            public void onFailure(Call<GetGuru> call, Throwable t) {

            }
        });

    }
    private void getKepalaSekolah(){
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<Guru> call = apiInterface.getKepalaSekolah();
        call.enqueue(new Callback<Guru>() {
            @Override
            public void onResponse(Call<Guru> call, final Response<Guru> response) {
                assert response.body() != null;
                String namaKepsek = response.body().getNamaGuru();
                String id_guru = response.body().getIdGuru();
                sessionManager.createIdGuru(id_guru);
                Glide.with(GuruActivity.this)
                        .load("http://sman14bdg.dscunikom.com/uploads/guru/".concat(response.body().getImage()))
                        .into(img);
                tvkepsek.setText(namaKepsek);

                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(GuruActivity.this, DetailGuruActivity.class);
                        intent.putExtra("id_guru",response.body().getIdGuru());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<Guru> call, Throwable t) {

            }
        });
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
            ActivityCompat.finishAffinity(GuruActivity.this);

        } else if (id == R.id.nav_teacher) {


        } else if (id == R.id.nav_calendar) {
            Intent intent = new Intent(this, KalendarActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            ActivityCompat.finishAffinity(GuruActivity.this);

        } else if (id == R.id.nav_contact) {
            Intent intent = new Intent(this, KontakActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            ActivityCompat.finishAffinity(GuruActivity.this);

        } else if (id == R.id.nav_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            ActivityCompat.finishAffinity(GuruActivity.this);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public void reloadView(RecyclerView.Adapter adapter, final List<Guru> list) {
        rvCategory.setAdapter(adapter);
        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Guru listGuru = list.get(position);
                String id_guru = listGuru.getIdGuru();
                sessionManager.createIdGuru(id_guru);
                clickItemDetail(list.get(position));
            }
        });
    }
}

