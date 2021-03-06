package com.dscunikom.android.sma14bandung.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.dscunikom.android.sma14bandung.activity.DetailBeritaActivity;
import com.dscunikom.android.sma14bandung.adapter.AdapterBerita;
import com.dscunikom.android.sma14bandung.getModel.GetBerita;
import com.dscunikom.android.sma14bandung.model.Berita;
import com.dscunikom.android.sma14bandung.model.President;
import com.dscunikom.android.sma14bandung.R;
import com.dscunikom.android.sma14bandung.rest.Api;
import com.dscunikom.android.sma14bandung.rest.ApiInterface;
import com.dscunikom.android.sma14bandung.rest.ItemClickSupport;
import com.dscunikom.android.sma14bandung.rest.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

    public ArrayList<President> list;
    public RecyclerView recyclerView;
    public ArrayList<Berita> mListBerita;
    ApiInterface mApiInterface;
    AdapterBerita sadapterBerita;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    SessionManager sessionManager;
    SwipeRefreshLayout swipeRefreshLayout;

    ProgressBar progressBar;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_news, container, false);
        progressBar = rootView.findViewById(R.id.progressbarnews);
        recyclerView = rootView.findViewById(R.id.rv_news);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        sessionManager = new SessionManager(getActivity().getApplicationContext());
        swipeRefreshLayout = rootView.findViewById(R.id.swLayout);

        getData();

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


        return rootView;
    }
    private void clickItemDetail(Berita berita){
        Intent detailActivity = new Intent(getActivity(), DetailBeritaActivity.class);
        detailActivity.putExtra("id_berita",berita.getId_berita());
        startActivity(detailActivity);
        getActivity().overridePendingTransition(0,0);
    }

    private void getData(){
        final AdapterBerita adapterBerita = new AdapterBerita(this.getActivity());
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<GetBerita> call = apiInterface.getBerita();
        call.enqueue(new Callback<GetBerita>() {
            @Override
            public void onResponse(Call<GetBerita> call, Response<GetBerita> response) {
                progressBar.setVisibility(View.GONE);
                List<Berita> beritaList = response.body().getGetBerita();
                String id_berita = new Berita().getId_berita();
                sessionManager.createIdBerita(id_berita);
                adapterBerita.setmListBerita(beritaList);
//                recyclerView.setAdapter(adapterBerita);
                reloadView(adapterBerita,beritaList);

            }

            @Override
            public void onFailure(Call<GetBerita> call, Throwable t) {
            }
        });
    }

    public void reloadView(RecyclerView.Adapter adapter, final List<Berita> list ){
        recyclerView.setAdapter(adapter);
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, final int position, View v) {
                Berita listBerita = list.get(position);
                String id_berita = listBerita.getId_berita();
                sessionManager.createIdBerita(id_berita);
                clickItemDetail(list.get(position));
            }
        });
    }



}
