package com.dscunikom.android.sma14bandung.activity;

import android.content.SharedPreferences;
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
import com.dscunikom.android.sma14bandung.rest.Api;
import com.dscunikom.android.sma14bandung.rest.ApiInterface;
import com.dscunikom.android.sma14bandung.rest.SessionManager;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailAcaraActivity extends AppCompatActivity {
    SessionManager sessionManager;
    @BindView(R.id.txtJudul)
    TextView tvJudul;
    @BindView(R.id.txtIsiBerita)
    TextView tvIsiBerita;
    @BindView(R.id.txtTanggal)
    TextView tvTanggal;
    @BindView(R.id.imageDetail)
    ImageView imgDetail;

    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_acara);

        progressBar = findViewById(R.id.progressbaracara);
        ButterKnife.bind(this);
        FirebaseMessaging.getInstance().subscribeToTopic("helsan");
        getData();

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void getData(){
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String,String> user = sessionManager.getUserDetils();
        String id = user.get(SessionManager.ID_ACARA);
        Call<Acara> call = apiInterface.getDetailAcara(id);

        call.enqueue(new Callback<Acara>() {
            @Override
            public void onResponse(Call<Acara> call, Response<Acara> response) {
//                Acara ambilData = new Acara();
                progressBar.setVisibility(View.GONE);
                tvIsiBerita.setText(response.body().getKeterangan());
                tvJudul.setText(response.body().getNamaAcara());
                tvTanggal.setText(response.body().getTanggal());
                Glide.with(DetailAcaraActivity.this)
                        .load("http://projectdsc.ahdirdiysarm.com/uploads/acara/".concat(response.body().getImage()))
                        .into(imgDetail);

                Log.e("Nama Acara ","OnRespone "+String.valueOf(response.body().getNamaAcara()));
            }

            @Override
            public void onFailure(Call<Acara> call, Throwable t) {

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
