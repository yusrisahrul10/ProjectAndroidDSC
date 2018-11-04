package com.dscunikom.android.sma14bandung.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dscunikom.android.sma14bandung.DetailEkskulActivity;
import com.dscunikom.android.sma14bandung.DetailFasilitasActivity;
import com.dscunikom.android.sma14bandung.R;
import com.dscunikom.android.sma14bandung.adapter.AdapterEkskul;
import com.dscunikom.android.sma14bandung.adapter.AdapterGridFasilitasEkskul;
import com.dscunikom.android.sma14bandung.getModel.GetEkstra;
import com.dscunikom.android.sma14bandung.model.Ekstrakulikuler;
import com.dscunikom.android.sma14bandung.model.Fasilitas;
import com.dscunikom.android.sma14bandung.model.President;
import com.dscunikom.android.sma14bandung.model.PresidentData;
import com.dscunikom.android.sma14bandung.rest.Api;
import com.dscunikom.android.sma14bandung.rest.ApiInterface;
import com.dscunikom.android.sma14bandung.rest.SessionManager;
import com.google.firebase.messaging.FirebaseMessaging;
import com.dscunikom.android.sma14bandung.rest.ItemClickSupport;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EkskulActivity extends AppCompatActivity {

    RecyclerView rvCategory;
    private ArrayList<President> list;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekskul);
        sessionManager = new SessionManager(getApplicationContext());
        FirebaseMessaging.getInstance().subscribeToTopic("helsan");
        rvCategory = findViewById(R.id.rv_grid_ekskul);
        rvCategory.setHasFixedSize(true);

        list = new ArrayList<>();
        list.addAll(PresidentData.getListData());

        rvCategory.setLayoutManager(new GridLayoutManager(this, 2));
        getData();

    }

    private void getData(){
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<GetEkstra> call = apiInterface.getEkstra();
        call.enqueue(new Callback<GetEkstra>() {
            @Override
            public void onResponse(Call<GetEkstra> call, Response<GetEkstra> response) {
                List<Ekstrakulikuler> ekstrakulikulers = response.body().getResult();
                AdapterEkskul adapterEkskul = new AdapterEkskul(EkskulActivity.this);
                adapterEkskul.setmListEkstra(ekstrakulikulers);
                rvCategory.setAdapter(adapterEkskul);
                reloadView(adapterEkskul, ekstrakulikulers);
            }

            @Override
            public void onFailure(Call<GetEkstra> call, Throwable t) {

            }
        });
    }

    private void clickItemDetail(Ekstrakulikuler ekstrakulikuler){
        Intent detailActivity = new Intent(this, DetailEkstraActivity.class);
        startActivity(detailActivity);
    }

    public void reloadView(RecyclerView.Adapter adapter, final List<Ekstrakulikuler> list ){
        rvCategory.setAdapter(adapter);
        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, final int position, View v) {
                Ekstrakulikuler listEkstra = list.get(position);
                String id_ekstra = listEkstra.getIdEkstra();
                sessionManager.createIdEkstra(id_ekstra);
                clickItemDetail(list.get(position));
            }
        });
    }
}
