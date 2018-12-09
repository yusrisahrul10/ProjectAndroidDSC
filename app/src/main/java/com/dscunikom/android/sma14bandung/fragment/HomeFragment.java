package com.dscunikom.android.sma14bandung.fragment;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


import com.dscunikom.android.sma14bandung.activity.DetailPrestasiActivity;
import com.dscunikom.android.sma14bandung.activity.EkskulActivity;
import com.dscunikom.android.sma14bandung.activity.FasilitasActivity;
import com.dscunikom.android.sma14bandung.activity.ProfileActivity;
import com.dscunikom.android.sma14bandung.adapter.AdapterBerita;
import com.dscunikom.android.sma14bandung.adapter.AdapterPrestasi;
import com.dscunikom.android.sma14bandung.adapter.AdapterPrestasiHome;
import com.dscunikom.android.sma14bandung.R;
import com.dscunikom.android.sma14bandung.adapter.CardViewNewsEventAdapter;
import com.dscunikom.android.sma14bandung.getModel.GetBerita;
import com.dscunikom.android.sma14bandung.getModel.GetPrestasi;
import com.dscunikom.android.sma14bandung.model.Berita;
import com.dscunikom.android.sma14bandung.model.President;
import com.dscunikom.android.sma14bandung.model.PresidentData;
import com.dscunikom.android.sma14bandung.model.Prestasi;
import com.dscunikom.android.sma14bandung.rest.Api;
import com.dscunikom.android.sma14bandung.rest.ApiInterface;
import com.dscunikom.android.sma14bandung.rest.ItemClickSupport;
import com.dscunikom.android.sma14bandung.rest.SessionManager;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
    Context context;
    SessionManager sessionManager;


    static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    CarouselView carouselView;
    int[] sampleImages = {R.drawable.courusel_1,R.drawable.courosel_2,R.drawable.courosel_3};

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, view);
        locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);

        sessionManager = new SessionManager(getActivity().getApplicationContext());


        recyclerView = view.findViewById(R.id.rv_prestasi_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false));



        carouselView = view.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
        getPrestasi();
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

    private void getPrestasi(){
        final AdapterPrestasiHome adapterPrestasiHome = new AdapterPrestasiHome(this.getActivity());
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<GetPrestasi> call = apiInterface.getPrestasiLimit();
        call.enqueue(new Callback<GetPrestasi>() {
            @Override
            public void onResponse(Call<GetPrestasi> call, Response<GetPrestasi> response) {
                List<Prestasi> prestasiList = response.body().getResult();
                adapterPrestasiHome.setMlistPrestasi(prestasiList);
//                recyclerView.setAdapter(adapterPrestasiHome);
                reloadView(adapterPrestasiHome,prestasiList);
            }

            @Override
            public void onFailure(Call<GetPrestasi> call, Throwable t) {

            }
        });
    }



    @OnClick(R.id.btn_fasilitas)
    public void fasilitasClick(){
        Intent intent = new Intent(getActivity(), FasilitasActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.map)
    public void mapClick(){

        if(ActivityCompat.checkSelfPermission(getActivity(),Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        }else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null){

                double latti = location.getLatitude();
                double longi = location.getLongitude();
                double destinationLatitude = -6.9029577;
                double destinationLongitude = 107.6359022;

                String uri = "http://maps.google.com/maps?saddr=" + latti + "," + longi + "&daddr=" + destinationLatitude + "," + destinationLongitude;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent);
//                Toast.makeText(getActivity(), "Your Location is - \nLat: " + latti + "\nLong: " + longi, Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(getActivity(), "Silahkan Aktifkan GPS Anda Terlebih Dahulu", Toast.LENGTH_LONG).show();

            }
        }

    }

    @OnClick(R.id.btn_ekskul)
    public void ekskulClick(){
        Intent intent = new Intent(getActivity(), EkskulActivity.class);
        startActivity(intent);
    }

    private void clickItemDetail(Prestasi prestasi){
        Intent detailActivity = new Intent(getActivity(), DetailPrestasiActivity.class);
        detailActivity.putExtra("id_prestasi",prestasi.getIdPrestasi());
        startActivity(detailActivity);
        getActivity().overridePendingTransition(0,0);
    }

    public void reloadView(RecyclerView.Adapter adapter , final List<Prestasi> prestasis){
        recyclerView.setAdapter(adapter);
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Prestasi mList = prestasis.get(position);
                String id_acara = mList.getIdPrestasi();
//                sessionManager.createdIdPrestasi(id_acara);
                clickItemDetail(prestasis.get(position));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getPrestasi();
    }
}
