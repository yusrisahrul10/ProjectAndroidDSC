package com.dscunikom.android.sma14bandung.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_acara);
        ButterKnife.bind(this);
        FirebaseMessaging.getInstance().subscribeToTopic("helsan");
        getData();
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


}
