package com.dscunikom.android.sma14bandung.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dscunikom.android.sma14bandung.R;
import com.dscunikom.android.sma14bandung.adapter.AdapterFasilitas;
import com.dscunikom.android.sma14bandung.adapter.AdapterGambarFasilitas;
import com.dscunikom.android.sma14bandung.adapter.AdapterPrestasi;
import com.dscunikom.android.sma14bandung.getModel.GetFasilitas;
import com.dscunikom.android.sma14bandung.getModel.GetGambarFasilitas;
import com.dscunikom.android.sma14bandung.model.Fasilitas;
import com.dscunikom.android.sma14bandung.model.GambarFasilitas;
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

public class DetailFasilitassActivity extends AppCompatActivity {
    RecyclerView rvCategory;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
        SessionManager sessionManager;
//        @BindView(R.id.txtNamaFasilitas)
//        TextView tvNamaFasilitas;
//        @BindView(R.id.imageDetailFasilitas)
//        ImageView imgDetail;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_fasilitas);
            ButterKnife.bind(this);
            FirebaseMessaging.getInstance().subscribeToTopic("helsan");

//            rvCategory = findViewById(R.id.rv_grid_fasilitas);
//            rvCategory.setHasFixedSize(true);
//            rvCategory.setLayoutManager(new GridLayoutManager(this, 2));

            rvCategory = findViewById(R.id.rv_detail_prestasi);
            mLayoutManager = new LinearLayoutManager(this);
            rvCategory.setLayoutManager(mLayoutManager);
            getData();
        }

        private void getData(){
            ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
            sessionManager = new SessionManager(getApplicationContext());
            HashMap<String,String> user = sessionManager.getUserDetils();
            String id = user.get(SessionManager.ID_FASILITAS);
//            Call<GetFasilitas> call = apiInterface.getDetailFasilitas(id);
            Call<GetGambarFasilitas> call = apiInterface.getDetailFasilitas(id);
            call.enqueue(new Callback<GetGambarFasilitas>() {
                @Override
                public void onResponse(Call<GetGambarFasilitas> call, Response<GetGambarFasilitas> response) {
                    List<GambarFasilitas> gambarFasilitas = response.body().getGambarFasilitas();
                    //jir gamau di panggil napa itu
                    Log.e("onRespone : ", "Gambar Fasilitas : "+String.valueOf(gambarFasilitas.size()));
                    AdapterGambarFasilitas adapterGambarFasilitas = new AdapterGambarFasilitas(DetailFasilitassActivity.this);
                    adapterGambarFasilitas.setmGambarFasilitas(gambarFasilitas);
                    rvCategory.setAdapter(adapterGambarFasilitas);
                }

                @Override
                public void onFailure(Call<GetGambarFasilitas> call, Throwable t) {
                    Log.e("onFailure : ", "Gambar Fasilitas : "+String.valueOf(t.getMessage()));

                }
            });



        }


}
