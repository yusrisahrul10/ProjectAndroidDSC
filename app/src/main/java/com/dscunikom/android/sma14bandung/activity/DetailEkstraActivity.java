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
import com.dscunikom.android.sma14bandung.model.Ekstrakulikuler;
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

public class DetailEkstraActivity extends AppCompatActivity {

        SessionManager sessionManager;
        @BindView(R.id.txtNamaEkstra)
        TextView tvNamaEkstra;
        @BindView(R.id.txtKeterangan)
        TextView tvIsiEkstra;
        @BindView(R.id.imageDetailEksra)
        ImageView imgDetail;

        ProgressBar progressBar;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_detail_ekstra);

            progressBar = findViewById(R.id.progressbarekstra);

            ButterKnife.bind(this);
            FirebaseMessaging.getInstance().subscribeToTopic("helsan");
            getData();

            Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar8);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        private void getData(){
            ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
            sessionManager = new SessionManager(getApplicationContext());
            HashMap<String,String> user = sessionManager.getUserDetils();
            String id = user.get(SessionManager.ID_EKSTRA);
            Call<Ekstrakulikuler> call = apiInterface.getDetailEkstra(id);

            call.enqueue(new Callback<Ekstrakulikuler>() {
                @Override
                public void onResponse(Call<Ekstrakulikuler> call, Response<Ekstrakulikuler> response) {
                    progressBar.setVisibility(View.GONE);
//                Acara ambilData = new Acara();
                    tvIsiEkstra.setText(response.body().getKeterangan());
                    tvNamaEkstra.setText(response.body().getNamaEkstra());

                    Glide.with(DetailEkstraActivity.this)
                            .load("http://projectdsc.ahdirdiysarm.com/uploads/ekstrakulikuler/".concat(response.body().getImage()))
                            .into(imgDetail);

                    Log.e("Nama Ekstrakulikuler ","OnRespone "+String.valueOf(response.body().getNamaEkstra()));
                }

                @Override
                public void onFailure(Call<Ekstrakulikuler> call, Throwable t) {

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

