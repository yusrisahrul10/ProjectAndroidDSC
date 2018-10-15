package com.dscunikom.android.sma14bandung;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dscunikom.android.sma14bandung.model.President;
import com.dscunikom.android.sma14bandung.model.PresidentData;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFasilitasActivity extends AppCompatActivity {

    RecyclerView rvCategory;
    private ArrayList<President> list;
//    @BindView(R.id.img_detail_content_first)
//    ImageView imgFirst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_fasilitas);

        ButterKnife.bind(this);

//        Glide.with(this)
//                .load(list.get(0).getPhoto())
//                .into(imgFirst);

        rvCategory = findViewById(R.id.rv_detail_content);

        list = new ArrayList<>();
        list.addAll(PresidentData.getListData());

        rvCategory.setLayoutManager(new GridLayoutManager(this, 3));
        AdapterGridDetailFasilitasEkskul adapterGridDetailFasilitasEkskul = new AdapterGridDetailFasilitasEkskul(this);
        adapterGridDetailFasilitasEkskul.setListPresident(list);
        rvCategory.setAdapter(adapterGridDetailFasilitasEkskul);
    }
}
