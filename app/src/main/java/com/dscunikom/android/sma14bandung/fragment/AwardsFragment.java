package com.dscunikom.android.sma14bandung.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.dscunikom.android.sma14bandung.activity.DetailPrestasiActivity;
import com.dscunikom.android.sma14bandung.adapter.AdapterPrestasi;
import com.dscunikom.android.sma14bandung.R;
import com.dscunikom.android.sma14bandung.getModel.GetPrestasi;
import com.dscunikom.android.sma14bandung.model.President;
import com.dscunikom.android.sma14bandung.model.PresidentData;
import com.dscunikom.android.sma14bandung.model.Prestasi;
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
public class AwardsFragment extends Fragment {

    RecyclerView rvCategory;
    private ArrayList<President> list;
    ApiInterface apiInterface;
    SessionManager sessionManager;

    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefreshLayout;

    public AwardsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_awards, container, false);
        progressBar = rootView.findViewById(R.id.progressbaraward);
        rvCategory = rootView.findViewById(R.id.rv_awards);
        rvCategory.setHasFixedSize(true);
        swipeRefreshLayout = rootView.findViewById(R.id.swLayout);

        list = new ArrayList<>();
        list.addAll(PresidentData.getListData());

        rvCategory.setLayoutManager(new LinearLayoutManager(this.getActivity()));
//        AdapterPrestasi adapterPrestasi = new AdapterPrestasi(this.getActivity());
//        adapterPrestasi.setListPresident(list);
//        rvCategory.setAdapter(adapterPrestasi);
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

    private void getData(){
        final AdapterPrestasi adapterPrestasi = new AdapterPrestasi(this.getActivity());
        sessionManager = new SessionManager(getActivity().getApplicationContext());
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<GetPrestasi> call = apiInterface.getPrestasi();

        call.enqueue(new Callback<GetPrestasi>() {
            @Override
            public void onResponse(Call<GetPrestasi> call, Response<GetPrestasi> response) {
                progressBar.setVisibility(View.GONE);
                List<Prestasi> listPrestasi = response.body().getResult();
                adapterPrestasi.setmListPrestasi(listPrestasi);
//                rvCategory.setAdapter(adapterPrestasi);
                reloadView(adapterPrestasi,listPrestasi);

            }

            @Override
            public void onFailure(Call<GetPrestasi> call, Throwable t) {

            }
        });
    }

    private void clickItemDetail(Prestasi prestasi){
        Intent detailActivity = new Intent(getActivity(), DetailPrestasiActivity.class);
        startActivity(detailActivity);
        getActivity().overridePendingTransition(0,0);
    }


    public void reloadView(RecyclerView.Adapter adapter, final List<Prestasi> list) {
        rvCategory.setAdapter(adapter);
        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, final int position, View v) {
                Prestasi listPrestasi = list.get(position);
                String id_prestasi = listPrestasi.getIdPrestasi();
                sessionManager.createdIdPrestasi(id_prestasi);
                clickItemDetail(list.get(position));
            }
        });

    }
}
