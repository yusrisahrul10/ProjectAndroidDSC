package com.dscunikom.android.sma14bandung;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dscunikom.android.sma14bandung.model.President;
import com.dscunikom.android.sma14bandung.model.PresidentData;

import java.util.ArrayList;

public class DetailEkskulActivity extends AppCompatActivity {

    RecyclerView rvCategory;
    private ArrayList<President> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ekskul);

        rvCategory = findViewById(R.id.rv_detail_content);

        list = new ArrayList<>();
        list.addAll(PresidentData.getListData());

        rvCategory.setLayoutManager(new GridLayoutManager(this, 3));
        AdapterGridDetailFasilitasEkskul adapterGridDetailFasilitasEkskul = new AdapterGridDetailFasilitasEkskul(this);
        adapterGridDetailFasilitasEkskul.setListPresident(list);
        rvCategory.setAdapter(adapterGridDetailFasilitasEkskul);
    }



}
