package com.dscunikom.android.sma14bandung.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dscunikom.android.sma14bandung.activity.DetailAcaraActivity;
import com.dscunikom.android.sma14bandung.activity.DetailBeritaActivity;
import com.dscunikom.android.sma14bandung.adapter.AdapterAcara;
import com.dscunikom.android.sma14bandung.getModel.GetAcara;
import com.dscunikom.android.sma14bandung.model.Acara;
import com.dscunikom.android.sma14bandung.model.Berita;
import com.dscunikom.android.sma14bandung.model.President;
import com.dscunikom.android.sma14bandung.model.PresidentData;
import com.dscunikom.android.sma14bandung.R;
import com.dscunikom.android.sma14bandung.adapter.CardViewNewsEventAdapter;
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
public class EventsFragment extends Fragment {

    public ArrayList<President> list;
    public RecyclerView recyclerView;
    ApiInterface apiInterface;
    SessionManager sessionManager;



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
        final AdapterAcara adapterAcara = new AdapterAcara(this.getActivity());
        sessionManager = new SessionManager(getActivity().getApplicationContext());
//        CardViewNewsEventAdapter cardViewNewsEventAdapter = new CardViewNewsEventAdapter(this.getActivity());
//        cardViewNewsEventAdapter.setListPresident(list);
//        recyclerView.setAdapter(cardViewNewsEventAdapter);

        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<GetAcara> call = apiInterface.getAcara();
        call.enqueue(new Callback<GetAcara>() {
            @Override
            public void onResponse(Call<GetAcara> call, Response<GetAcara> response) {
                List<Acara> listAcara = response.body().getResult();
                Log.e("Acara ","OnRespone Acara : "+String.valueOf(listAcara.size()));
            adapterAcara.setmListAcara(listAcara);
//            recyclerView.setAdapter(adapterAcara);
            reloadView(adapterAcara,listAcara);
            }

            @Override
            public void onFailure(Call<GetAcara> call, Throwable t) {

            }
        });
        return rootView;
    }

    private void clickItemDetail(Acara acara){
        Intent detailActivity = new Intent(getActivity(), DetailAcaraActivity.class);
        startActivity(detailActivity);
        getActivity().overridePendingTransition(0,0);
    }

    public void reloadView(RecyclerView.Adapter adapter , final List<Acara> acara){
        recyclerView.setAdapter(adapter);
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Acara mList = acara.get(position);
                String id_acara = mList.getIdAcara();
                sessionManager.createIdAcara(id_acara);
                clickItemDetail(acara.get(position));
            }
        });
    }





}
