package com.dscunikom.android.sma14bandung.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dscunikom.android.sma14bandung.R;
import com.dscunikom.android.sma14bandung.getModel.GetGuru;
import com.dscunikom.android.sma14bandung.model.Guru;
import com.dscunikom.android.sma14bandung.rest.Api;
import com.dscunikom.android.sma14bandung.rest.ApiInterface;
import com.dscunikom.android.sma14bandung.rest.SessionManager;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailGuruActivity extends AppCompatActivity {

    SessionManager sessionManager;

    public static String EXTRA_NAME = "extra_name";
    public static String EXTRA_MAPEL = "extra_mapel";
    public static String EXTRA_EMAIL = "extra_email";
    public static String EXTRA_KELAS = "extra_kelas";

    @BindView(R.id.tvName)
    TextView tvNama;
    @BindView(R.id.kelas)
    TextView tvKelas;
    @BindView(R.id.mapelajaran)
    TextView tvPelajaran;
    @BindView(R.id.emaill)
    TextView tvEmail;
    @BindView(R.id.gambar)
    ImageView ivGambar;
    @BindView(R.id.jabatan)
    TextView tvJabatan;


    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_guru);
        FirebaseMessaging.getInstance().subscribeToTopic("helsan");
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarguru);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textView1 = (TextView) findViewById(R.id.tvName);
        textView2 = (TextView) findViewById(R.id.kelas);
        textView3 = (TextView) findViewById(R.id.mapelajaran);
        textView4 = (TextView) findViewById(R.id.emaill);

        String name = getIntent().getStringExtra(EXTRA_NAME);
        String mapel = getIntent().getStringExtra(EXTRA_MAPEL);
        String email = getIntent().getStringExtra(EXTRA_EMAIL);
        String kelas = getIntent().getStringExtra(EXTRA_KELAS);

        String text = name;
        textView1.setText(text);
        String mapell = mapel;
        textView2.setText(mapell);
        String emaill = email;
        textView3.setText(emaill);
        String kelass = kelas;
        textView4.setText(kelass);

        getData();


    }

    public void getData() {
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String,String> user = sessionManager.getUserDetils();
        String id = user.get(SessionManager.ID_GURU);
        retrofit2.Call<Guru> berhasil = apiInterface.getDetailGuru(id);
        berhasil.enqueue(new Callback<Guru>() {
            @Override
            public void onResponse(retrofit2.Call<Guru> call, Response<Guru> response) {
                tvNama.setText(response.body().getNamaGuru());
                tvKelas.setText(response.body().getKelasAjar());
                tvPelajaran.setText(response.body().getMapel());
                tvEmail.setText(response.body().getEmail());
                tvJabatan.setText(response.body().getJabatan());
                Glide.with(DetailGuruActivity.this)
                        .load("http://projectdsc.ahdirdiysarm.com/uploads/guru/".concat(response.body().getImage()))
                        .into(ivGambar);

            }

            @Override
            public void onFailure(retrofit2.Call<Guru> call, Throwable t) {

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
