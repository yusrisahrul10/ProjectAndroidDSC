package com.dscunikom.android.sma14bandung.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dscunikom.android.sma14bandung.R;
import com.dscunikom.android.sma14bandung.getModel.GetBerita;
import com.dscunikom.android.sma14bandung.model.Berita;
import com.dscunikom.android.sma14bandung.rest.Api;
import com.dscunikom.android.sma14bandung.rest.ApiInterface;
import com.dscunikom.android.sma14bandung.rest.SessionManager;
import com.google.firebase.messaging.FirebaseMessaging;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailBeritaActivity extends AppCompatActivity {
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
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        id = getIntent().getStringExtra("id_berita");
        progressBar = findViewById(R.id.progressbarberita);
        ButterKnife.bind(this);
        FirebaseMessaging.getInstance().subscribeToTopic("helsan");
        getData();


        Toolbar ToolBarBerita = (Toolbar)findViewById(R.id.toolbar1);
        setSupportActionBar(ToolBarBerita);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void getData(){
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String,String> user = sessionManager.getUserDetils();
//        String id = user.get(SessionManager.ID_BERITA);
        retrofit2.Call<GetBerita> berhasil = apiInterface.getDetailBerita(id);

        berhasil.enqueue(new Callback<GetBerita>() {
            @Override
            public void onResponse(retrofit2.Call<GetBerita> call, Response<GetBerita> response) {
                progressBar.setVisibility(View.GONE);
                List<Berita> mList = response.body().getGetBerita();
                tvIsiBerita.setText(mList.get(0).getIsi_berita());
                tvJudul.setText(mList.get(0).getJudul_berita());
                String getDate = mList.get(0).getTanggal();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date date = dateFormat.parse(getDate);
                    SimpleDateFormat newFormat = new SimpleDateFormat("EEEE, MMM dd, yyyy");
                    String dateFix = newFormat.format(date);
                    tvTanggal.setText(dateFix);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Glide.with(DetailBeritaActivity.this)
                        .load("http://sman14bdg.dscunikom.com/uploads/berita/"+mList.get(0).getImage())
                        .into(imgDetail);
            }

            @Override
            public void onFailure(retrofit2.Call<GetBerita> call, Throwable t) {

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
