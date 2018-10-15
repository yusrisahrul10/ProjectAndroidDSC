package com.dscunikom.android.sma14bandung.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dscunikom.android.sma14bandung.AdapterPrestasi;
import com.dscunikom.android.sma14bandung.R;
import com.dscunikom.android.sma14bandung.model.President;
import com.dscunikom.android.sma14bandung.model.PresidentData;

import java.util.ArrayList;


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
        AdapterPrestasi adapterPrestasi = new AdapterPrestasi(this.getActivity());
        adapterPrestasi.setListPresident(list);
        rvCategory.setAdapter(adapterPrestasi);
        return rootView;
    }

}
