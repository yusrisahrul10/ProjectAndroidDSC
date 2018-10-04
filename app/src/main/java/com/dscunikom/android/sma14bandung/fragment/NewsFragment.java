package com.dscunikom.android.sma14bandung.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dscunikom.android.sma14bandung.adapter.AdapterBerita;
import com.dscunikom.android.sma14bandung.getModel.GetBerita;
import com.dscunikom.android.sma14bandung.model.Berita;
import com.dscunikom.android.sma14bandung.model.President;
import com.dscunikom.android.sma14bandung.model.PresidentData;
import com.dscunikom.android.sma14bandung.R;
import com.dscunikom.android.sma14bandung.adapter.CardViewNewsEventAdapter;
import com.dscunikom.android.sma14bandung.rest.Api;
import com.dscunikom.android.sma14bandung.rest.ApiInterface;

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

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_news, container, false);
        list = new ArrayList<>();
        list.addAll(PresidentData.getListData());
        recyclerView = rootView.findViewById(R.id.rv_news);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        final AdapterBerita adapterBerita = new AdapterBerita(this.getActivity());
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<GetBerita> call = apiInterface.getBerita();
        call.enqueue(new Callback<GetBerita>() {
            @Override
            public void onResponse(Call<GetBerita> call, Response<GetBerita> response) {
                List<Berita> beritaList = response.body().getGetBerita();
                Log.e("Testing ","GO : "+String.valueOf(beritaList.get(0).getImage()));
                adapterBerita.setmListBerita(beritaList);
                recyclerView.setAdapter(adapterBerita);
            }

            @Override
            public void onFailure(Call<GetBerita> call, Throwable t) {

            }
        });

        return rootView;
    }



}
