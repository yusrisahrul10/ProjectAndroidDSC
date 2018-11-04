package com.dscunikom.android.sma14bandung.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dscunikom.android.sma14bandung.R;
import com.dscunikom.android.sma14bandung.model.Fasilitas;
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

public class DetailFasilitassActivity extends AppCompatActivity {


        SessionManager sessionManager;
        @BindView(R.id.txtNamaFasilitas)
        TextView tvNamaFasilitas;
        @BindView(R.id.imageDetailFasilitas)
        ImageView imgDetail;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_fasilitas2);
            ButterKnife.bind(this);
            FirebaseMessaging.getInstance().subscribeToTopic("helsan");
            getData();
        }

        private void getData(){
            ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
            sessionManager = new SessionManager(getApplicationContext());
            HashMap<String,String> user = sessionManager.getUserDetils();
            String id = user.get(SessionManager.ID_FASILITAS);
            Call<Fasilitas> call = apiInterface.getDetailFasilitas(id);

            call.enqueue(new Callback<Fasilitas>() {
                @Override
                public void onResponse(Call<Fasilitas> call, Response<Fasilitas> response) {
//                Acara ambilData = new Acara();
//                    tvIsiEkstra.setText(response.body().getKeterangan());
                    tvNamaFasilitas.setText(response.body().getNamaFasilitas());

                    Glide.with(DetailFasilitassActivity.this)
                            .load("http://projectdsc.ahdirdiysarm.com/uploads/files/".concat(response.body().getImage()))
                            .into(imgDetail);

                    Log.e("Nama Fasilitas ","OnRespone "+String.valueOf(response.body().getNamaFasilitas()));
                }

                @Override
                public void onFailure(Call<Fasilitas> call, Throwable t) {

                }
            });
        }


}
