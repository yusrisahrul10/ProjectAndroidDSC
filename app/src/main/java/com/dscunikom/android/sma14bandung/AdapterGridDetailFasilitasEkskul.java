package com.dscunikom.android.sma14bandung;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dscunikom.android.sma14bandung.adapter.AdapterGridFasilitasEkskul;
import com.dscunikom.android.sma14bandung.model.President;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterGridDetailFasilitasEkskul extends RecyclerView.Adapter<AdapterGridDetailFasilitasEkskul.GridViewHolder> {

    private ArrayList<President> listPresident;
    private Context context;

    public AdapterGridDetailFasilitasEkskul(Context context) {
        this.context = context;
    }

    public ArrayList<President> getListPresident() {
        return listPresident;
    }

    public void setListPresident(ArrayList<President> listPresident) {
        this.listPresident = listPresident;
    }

    @NonNull
    @Override
    public AdapterGridDetailFasilitasEkskul.GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_detail_fasilitas_ekskul, parent, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterGridDetailFasilitasEkskul.GridViewHolder holder, int position) {

//        Glide.with(context)
//                .load(getListPresident().get(0).getPhoto())
//                .into(holder.imgFirst);
        Glide.with(context)
                .load(getListPresident().get(position).getPhoto())
                .into(holder.imgSecond);

    }

    @Override
    public int getItemCount() {
        return getListPresident().size();
    }

    class GridViewHolder extends RecyclerView.ViewHolder {

       // @BindView(R.id.img_detail_content_first)
//        ImageView imgFirst;
        @BindView(R.id.img_content_detail_sec) ImageView imgSecond;
        public GridViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
