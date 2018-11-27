package com.dscunikom.android.sma14bandung.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dscunikom.android.sma14bandung.R;
import com.dscunikom.android.sma14bandung.model.Acara;
import com.dscunikom.android.sma14bandung.model.Fasilitas;
import com.dscunikom.android.sma14bandung.model.Prestasi;
import com.dscunikom.android.sma14bandung.rest.Api;
import com.dscunikom.android.sma14bandung.rest.ApiInterface;
import com.dscunikom.android.sma14bandung.rest.SessionManager;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPrestasiActivity extends AppCompatActivity {

    SessionManager sessionManager;
    @BindView(R.id.txtNamaPrestasi)
    TextView tvNamaPrestasi;
    @BindView(R.id.txtKeteranganPrestasi)
    TextView tvKeterangan;
    @BindView(R.id.txtTanggalPrestasi)
    TextView tvTanggal;
    @BindView(R.id.imageDetailPrestasi)
    ImageView imgDetail;

    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_prestasi);

        progressBar = findViewById(R.id.progressbarprestasi);

        ButterKnife.bind(this);
        FirebaseMessaging.getInstance().subscribeToTopic("helsan");
        getData();

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void getData(){
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String,String> user = sessionManager.getUserDetils();
        String id = user.get(SessionManager.ID_PRESTASI);
        Call<Prestasi> call = apiInterface.getDetailPrestasi(id);

        call.enqueue(new Callback<Prestasi>() {
            @Override
            public void onResponse(Call<Prestasi> call, Response<Prestasi> response) {
                progressBar.setVisibility(View.GONE);

//                Acara ambilData = new Acara();
                tvKeterangan.setText(response.body().getKeterangan());
                tvNamaPrestasi.setText(response.body().getNamaPrestasi());
                tvTanggal.setText(response.body().getTanggal());
                Glide.with(DetailPrestasiActivity.this)
                        .load("http://projectdsc.ahdirdiysarm.com/uploads/prestasi/".concat(response.body().getImage()))
                        .into(imgDetail);

                Log.e("Nama Prestasi ","OnRespone "+String.valueOf(response.body().getNamaPrestasi()));
            }

            @Override
            public void onFailure(Call<Prestasi> call, Throwable t) {

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home : {
                finish();
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

}
