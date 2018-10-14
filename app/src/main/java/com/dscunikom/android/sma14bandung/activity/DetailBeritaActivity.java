package com.dscunikom.android.sma14bandung.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dscunikom.android.sma14bandung.R;
import com.dscunikom.android.sma14bandung.getModel.GetBerita;
import com.dscunikom.android.sma14bandung.model.Berita;
import com.dscunikom.android.sma14bandung.rest.Api;
import com.dscunikom.android.sma14bandung.rest.ApiInterface;
import com.dscunikom.android.sma14bandung.rest.SessionManager;

import java.util.HashMap;
import java.util.List;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        getData();
    }

    public void getData(){
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String,String> user = sessionManager.getUserDetils();
        String id = user.get(SessionManager.ID_BERITA);
        retrofit2.Call<GetBerita> berhasil = apiInterface.getDetailBerita(id);

        berhasil.enqueue(new Callback<GetBerita>() {
            @Override
            public void onResponse(retrofit2.Call<GetBerita> call, Response<GetBerita> response) {
               List<Berita> mList = response.body().getGetBerita();
//                Berita berita = new Berita();
                tvIsiBerita.setText(mList.get(0).getIsi_berita());
                tvJudul.setText(mList.get(0).getJudul_berita());
                tvTanggal.setText(mList.get(0).getTanggal());

                Glide.with(DetailBeritaActivity.this)
                        .load("http://projectdsc.ahdirdiysarm.com/uploads/berita/"+mList.get(0).getImage())
                        .into(imgDetail);
            }

            @Override
            public void onFailure(retrofit2.Call<GetBerita> call, Throwable t) {

            }
        });
    }
}
