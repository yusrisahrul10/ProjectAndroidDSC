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
import android.widget.ImageView;


import com.dscunikom.android.sma14bandung.activity.EkskulActivity;
import com.dscunikom.android.sma14bandung.activity.FasilitasActivity;
import com.dscunikom.android.sma14bandung.activity.ProfileActivity;
import com.dscunikom.android.sma14bandung.adapter.AdapterBerita;
import com.dscunikom.android.sma14bandung.adapter.AdapterPrestasiHome;
import com.dscunikom.android.sma14bandung.R;
import com.dscunikom.android.sma14bandung.adapter.CardViewNewsEventAdapter;
import com.dscunikom.android.sma14bandung.getModel.GetBerita;
import com.dscunikom.android.sma14bandung.model.Berita;
import com.dscunikom.android.sma14bandung.model.President;
import com.dscunikom.android.sma14bandung.model.PresidentData;
import com.dscunikom.android.sma14bandung.rest.Api;
import com.dscunikom.android.sma14bandung.rest.ApiInterface;
import com.dscunikom.android.sma14bandung.rest.SessionManager;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;


import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

//

    ArrayList<President> list;


    public RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    CarouselView carouselView;
    int[] sampleImages = {R.drawable.asd,R.drawable.asd,R.drawable.asd};

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, view);
        recyclerView = view.findViewById(R.id.rv_prestasi_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false));

        final AdapterPrestasiHome adapterBerita = new AdapterPrestasiHome(this.getActivity());

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

        carouselView = view.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);

        return view;
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
//            imageView.setImageResource(sampleImages[position]);
            imageView.setImageResource(sampleImages[position]);

        }
    };

    @OnClick(R.id.btn_visi_misi)
    public void visiMisiClick(){
        Intent intent = new Intent(getActivity(), ProfileActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_fasilitas)
    public void fasilitasClick(){
        Intent intent = new Intent(getActivity(), FasilitasActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_ekskul)
    public void ekskulClick(){
        Intent intent = new Intent(getActivity(), EkskulActivity.class);
        startActivity(intent);
    }
}
