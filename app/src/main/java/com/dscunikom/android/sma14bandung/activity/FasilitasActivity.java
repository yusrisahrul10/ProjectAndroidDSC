package com.dscunikom.android.sma14bandung.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.dscunikom.android.sma14bandung.R;
import com.dscunikom.android.sma14bandung.adapter.AdapterFasilitas;
import com.dscunikom.android.sma14bandung.adapter.AdapterGridFasilitasEkskul;
import com.dscunikom.android.sma14bandung.getModel.GetFasilitas;
import com.dscunikom.android.sma14bandung.model.Fasilitas;
import com.dscunikom.android.sma14bandung.model.President;
import com.dscunikom.android.sma14bandung.model.PresidentData;
import com.dscunikom.android.sma14bandung.rest.Api;
import com.dscunikom.android.sma14bandung.rest.ApiInterface;

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
//                ArrayList<Fasilitas> listFasilitas = response.body().getResult();
                AdapterFasilitas adapterFasilitas = new AdapterFasilitas(FasilitasActivity.this);
                adapterFasilitas.setmListFasilitas(listFasilitas);
                rvCategory.setAdapter(adapterFasilitas);

//                Log.e("Testing ","COBA COBA "+String.valueOf(go));

            }

            @Override
            public void onFailure(Call<GetFasilitas> call, Throwable t) {

            }
        });
    }
}
