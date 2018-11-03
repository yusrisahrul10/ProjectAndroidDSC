package com.dscunikom.android.sma14bandung.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dscunikom.android.sma14bandung.adapter.AdapterPrestasi;
import com.dscunikom.android.sma14bandung.R;
import com.dscunikom.android.sma14bandung.getModel.GetPrestasi;
import com.dscunikom.android.sma14bandung.model.President;
import com.dscunikom.android.sma14bandung.model.PresidentData;
import com.dscunikom.android.sma14bandung.model.Prestasi;
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
public class AwardsFragment extends Fragment {

    RecyclerView rvCategory;
    private ArrayList<President> list;

    public AwardsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_awards, container, false);

        rvCategory = rootView.findViewById(R.id.rv_awards);
        rvCategory.setHasFixedSize(true);

        list = new ArrayList<>();
        list.addAll(PresidentData.getListData());

        rvCategory.setLayoutManager(new LinearLayoutManager(this.getActivity()));
//        AdapterPrestasi adapterPrestasi = new AdapterPrestasi(this.getActivity());
//        adapterPrestasi.setListPresident(list);
//        rvCategory.setAdapter(adapterPrestasi);

        final AdapterPrestasi adapterPrestasi = new AdapterPrestasi(this.getActivity());
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<GetPrestasi> call = apiInterface.getPrestasi();

        call.enqueue(new Callback<GetPrestasi>() {
            @Override
            public void onResponse(Call<GetPrestasi> call, Response<GetPrestasi> response) {
                List<Prestasi> listPrestasi = response.body().getResult();
                adapterPrestasi.setmListPrestasi(listPrestasi);
                rvCategory.setAdapter(adapterPrestasi);

            }
            @Override
            public void onFailure(Call<GetPrestasi> call, Throwable t) {

            }
        });

        return rootView;


    }

}
