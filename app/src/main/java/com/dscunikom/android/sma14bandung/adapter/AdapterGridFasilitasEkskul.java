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
import com.dscunikom.android.sma14bandung.model.President;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterGridFasilitasEkskul extends RecyclerView.Adapter<AdapterGridFasilitasEkskul.GridViewHolder> {

    private Context context;
    private ArrayList<President> listPresident;

    public AdapterGridFasilitasEkskul(Context context) {
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
    public AdapterGridFasilitasEkskul.GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_fasilitas_ekskul, parent, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterGridFasilitasEkskul.GridViewHolder holder, int position) {
        Glide.with(context)
                .load(getListPresident().get(position).getPhoto())
                .into(holder.imgPhoto);

        holder.tvTitle.setText(getListPresident().get(position).getName());
    }

    @Override
    public int getItemCount() {
        return getListPresident().size();
    }

    class GridViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.image_item_photo) ImageView imgPhoto;
        @BindView(R.id.text_item_title)
        TextView tvTitle;
        public GridViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
