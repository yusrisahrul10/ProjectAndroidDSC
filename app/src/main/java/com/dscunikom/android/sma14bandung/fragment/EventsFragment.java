package com.dscunikom.android.sma14bandung.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dscunikom.android.sma14bandung.President;
import com.dscunikom.android.sma14bandung.PresidentData;
import com.dscunikom.android.sma14bandung.R;
import com.dscunikom.android.sma14bandung.adapter.CardViewNewsEventAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment {

    public ArrayList<President> list;
    public RecyclerView recyclerView;

    public EventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_events, container, false);

        list = new ArrayList<>();
        list.addAll(PresidentData.getListData());
        recyclerView = rootView.findViewById(R.id.rv_event);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        CardViewNewsEventAdapter cardViewNewsEventAdapter = new CardViewNewsEventAdapter(this.getActivity());
        cardViewNewsEventAdapter.setListPresident(list);
        recyclerView.setAdapter(cardViewNewsEventAdapter);
        return rootView;
    }

}
