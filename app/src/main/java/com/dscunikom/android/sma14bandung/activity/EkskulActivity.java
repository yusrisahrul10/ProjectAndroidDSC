package com.dscunikom.android.sma14bandung.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dscunikom.android.sma14bandung.R;
import com.dscunikom.android.sma14bandung.adapter.AdapterGridFasilitasEkskul;
import com.dscunikom.android.sma14bandung.model.President;
import com.dscunikom.android.sma14bandung.model.PresidentData;

import java.util.ArrayList;

public class EkskulActivity extends AppCompatActivity {

    RecyclerView rvCategory;
    private ArrayList<President> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekskul);

        rvCategory = findViewById(R.id.rv_grid_ekskul);
        rvCategory.setHasFixedSize(true);

        list = new ArrayList<>();
        list.addAll(PresidentData.getListData());

        rvCategory.setLayoutManager(new GridLayoutManager(this, 2));
        AdapterGridFasilitasEkskul adapterGridFasilitasEkskul = new AdapterGridFasilitasEkskul(this);
        adapterGridFasilitasEkskul.setListPresident(list);
        rvCategory.setAdapter(adapterGridFasilitasEkskul);
    }
}
