package com.dscunikom.android.sma14bandung.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.dscunikom.android.sma14bandung.DetailFasilitasActivity;
import com.dscunikom.android.sma14bandung.R;
import com.dscunikom.android.sma14bandung.adapter.AdapterFasilitas;
import com.dscunikom.android.sma14bandung.adapter.AdapterGridFasilitasEkskul;
import com.dscunikom.android.sma14bandung.getModel.GetFasilitas;
import com.dscunikom.android.sma14bandung.model.Berita;
import com.dscunikom.android.sma14bandung.model.Fasilitas;
import com.dscunikom.android.sma14bandung.model.President;
import com.dscunikom.android.sma14bandung.model.PresidentData;
import com.dscunikom.android.sma14bandung.rest.Api;
import com.dscunikom.android.sma14bandung.rest.ApiInterface;
import com.google.firebase.messaging.FirebaseMessaging;
import com.dscunikom.android.sma14bandung.rest.ItemClickSupport;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FasilitasActivity extends AppCompatActivity {

    RecyclerView rvCategory;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fasilitas);
        FirebaseMessaging.getInstance().subscribeToTopic("helsan");
        rvCategory = findViewById(R.id.rv_grid_fasilitas);
        rvCategory.setHasFixedSize(true);
        rvCategory.setLayoutManager(new GridLayoutManager(this, 2));
        getData();

    }

    private void getData(){
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<GetFasilitas> call = apiInterface.getFasilitas();

        call.enqueue(new Callback<GetFasilitas>() {
            @Override
            public void onResponse(Call<GetFasilitas> call, Response<GetFasilitas> response) {
                List<Fasilitas> listFasilitas = response.body().getResult();
                AdapterFasilitas adapterFasilitas = new AdapterFasilitas(FasilitasActivity.this);
                adapterFasilitas.setmListFasilitas(listFasilitas);
                rvCategory.setAdapter(adapterFasilitas);

                reloadView(adapterFasilitas,listFasilitas);


            }

            @Override
            public void onFailure(Call<GetFasilitas> call, Throwable t) {

            }
        });
    }

    private void clickItemDetail(Fasilitas fasilitas){
        Intent detailActivity = new Intent(this, DetailFasilitasActivity.class);
        startActivity(detailActivity);
    }

    public void reloadView(RecyclerView.Adapter adapter, final List<Fasilitas> list ){
        rvCategory.setAdapter(adapter);
        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, final int position, View v) {
                Fasilitas listBerita = list.get(position);
                clickItemDetail(list.get(position));
            }
        });
    }
}
