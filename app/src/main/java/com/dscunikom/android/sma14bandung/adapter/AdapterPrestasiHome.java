package com.dscunikom.android.sma14bandung.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dscunikom.android.sma14bandung.R;
import com.dscunikom.android.sma14bandung.model.Berita;
import com.dscunikom.android.sma14bandung.model.Prestasi;

import java.util.List;

public class AdapterPrestasiHome extends RecyclerView.Adapter<AdapterPrestasiHome.MyViewHolder> {

    public List<Prestasi> mlistPrestasi;
    Context context;

    public AdapterPrestasiHome(Context context) {
        this.context = context;
    }

    public AdapterPrestasiHome(List<Prestasi> mlistPrestasi) {
        this.mlistPrestasi = mlistPrestasi;
    }

    public List<Prestasi> getmListPrestasi() {
        return mlistPrestasi;
    }

    public void setMlistPrestasi(List<Prestasi> mlistPrestasi) {
        this.mlistPrestasi = mlistPrestasi;
    }

    @NonNull
    @Override
    public AdapterPrestasiHome.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prestasi, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPrestasiHome.MyViewHolder holder, int position) {

        final Prestasi p = getmListPrestasi().get(position);

        holder.txtJudul.setText(p.getNamaPrestasi().substring(0,8)+"...");

        Glide.with(context.getApplicationContext())

                .load("http://sman14bdg.dscunikom.com/uploads/prestasi/"+p.getImage())
                .into(holder.imgContent);

    }

    @Override
    public int getItemCount() {
        return mlistPrestasi.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imgContent;
        TextView txtJudul;
        public MyViewHolder(View itemView) {
            super(itemView);
            imgContent = itemView.findViewById(R.id.image_prestasi);
            txtJudul = itemView.findViewById(R.id.txtJudulPrestasi);


        }
    }
}
