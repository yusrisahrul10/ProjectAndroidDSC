package com.dscunikom.android.sma14bandung.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.dscunikom.android.sma14bandung.R;
import com.dscunikom.android.sma14bandung.adapter.AdapterFasilitas;
import com.dscunikom.android.sma14bandung.getModel.GetFasilitas;
import com.dscunikom.android.sma14bandung.model.Fasilitas;
import com.dscunikom.android.sma14bandung.rest.Api;
import com.dscunikom.android.sma14bandung.rest.ApiInterface;
import com.dscunikom.android.sma14bandung.rest.SessionManager;
import com.google.firebase.messaging.FirebaseMessaging;
import com.dscunikom.android.sma14bandung.rest.ItemClickSupport;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FasilitasActivity extends AppCompatActivity {

    RecyclerView rvCategory;
    SessionManager sessionManager;
    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fasilitas);
        swipeRefreshLayout = findViewById(R.id.swLayout);
        progressBar = findViewById(R.id.progressbarfasilitas);

        sessionManager = new SessionManager(getApplicationContext());
        FirebaseMessaging.getInstance().subscribeToTopic("helsan");
        rvCategory = findViewById(R.id.rv_grid_fasilitas);
        rvCategory.setHasFixedSize(true);
        rvCategory.setLayoutManager(new GridLayoutManager(this, 2));
        getData();

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorRedSwipe,R.color.colorGraySwipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },3000);

            }
        });

    }

    private void getData(){
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<GetFasilitas> call = apiInterface.getFasilitas();

        call.enqueue(new Callback<GetFasilitas>() {
            @Override
            public void onResponse(Call<GetFasilitas> call, Response<GetFasilitas> response) {
                progressBar.setVisibility(View.GONE);


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
        detailActivity.putExtra("id_fasilitas",fasilitas.getIdFasilitas());
        startActivity(detailActivity);
        this.overridePendingTransition(0, 0);

    }

    public void reloadView(RecyclerView.Adapter adapter, final List<Fasilitas> list ){
        rvCategory.setAdapter(adapter);
        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, final int position, View v) {
                Fasilitas listFasilitas = list.get(position);
                String id_fasilitas = listFasilitas.getIdFasilitas();
//                sessionManager.createdIdFasilitas(id_fasilitas);
                clickItemDetail(list.get(position));
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
