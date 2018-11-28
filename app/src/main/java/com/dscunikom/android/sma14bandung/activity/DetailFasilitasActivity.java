package com.dscunikom.android.sma14bandung.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.dscunikom.android.sma14bandung.R;
import com.dscunikom.android.sma14bandung.adapter.AdapterGambarFasilitas;
import com.dscunikom.android.sma14bandung.getModel.GetGambarFasilitas;
import com.dscunikom.android.sma14bandung.model.GambarFasilitas;
import com.dscunikom.android.sma14bandung.rest.Api;
import com.dscunikom.android.sma14bandung.rest.ApiInterface;
import com.dscunikom.android.sma14bandung.rest.SessionManager;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailFasilitasActivity extends AppCompatActivity {
    RecyclerView myRecycler;

    private RecyclerView.Adapter mAdapter;
//    private RecyclerView.LayoutManager mLayoutManager;
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
           myRecycler = (RecyclerView) findViewById(R.id.rv_detail_fasilitas);
            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            myRecycler.setLayoutManager(llm);
//            list.setAdapter( adapter );

//            rvCategory = findViewById(R.id.rv_detail_prestasi);
//            rvCategory.setHasFixedSize(true);
//            rvCategory.setLayoutManager(new LinearLayoutManager(this));
            getData();


            Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar6);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        private void getData(){
            ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
            sessionManager = new SessionManager(getApplicationContext());
            HashMap<String,String> user = sessionManager.getUserDetils();
            String id = user.get(SessionManager.ID_FASILITAS);

            Call<GetGambarFasilitas> call = apiInterface.getDetailFasilitas(id);
            call.enqueue(new Callback<GetGambarFasilitas>() {
                @Override
                public void onResponse(Call<GetGambarFasilitas> call, Response<GetGambarFasilitas> response) {
                    List<GambarFasilitas> gambarFasilitas = response.body().getGambarFasilitas();
                    Log.e("onRespone : ", "Gambar Fasilitas : "+String.valueOf(gambarFasilitas.size()));
                    AdapterGambarFasilitas adapterGambarFasilitas = new AdapterGambarFasilitas(DetailFasilitasActivity.this);
                    adapterGambarFasilitas.setmGambarFasilitas(gambarFasilitas);
                    myRecycler.setAdapter(adapterGambarFasilitas);

                }

                @Override
                public void onFailure(Call<GetGambarFasilitas> call, Throwable t) {
                    Log.e("onFailure : ", "Gambar Fasilitas : "+String.valueOf(t.getMessage()));

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
