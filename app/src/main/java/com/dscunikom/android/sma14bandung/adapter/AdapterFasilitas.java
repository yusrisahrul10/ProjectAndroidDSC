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
import com.dscunikom.android.sma14bandung.model.Fasilitas;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterFasilitas extends RecyclerView.Adapter<AdapterFasilitas.GridViewHolder> {
    private Context context;

    private List<Fasilitas> mListFasilitas;
    public AdapterFasilitas(Context context) {
        this.context = context;
    }

    public List<Fasilitas> getmListFasilitas() {
        return mListFasilitas;
    }

    public void setmListFasilitas(List<Fasilitas> mListFasilitas) {
        this.mListFasilitas = mListFasilitas;
    }

    @NonNull
    @Override
    public AdapterFasilitas.GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_fasilitas_ekskul, parent, false);
        return new AdapterFasilitas.GridViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFasilitas.GridViewHolder holder, int position) {
        holder.tvTitle.setText(getmListFasilitas().get(position).getNamaFasilitas());
        Glide.with(context)
                .load("http://sman14bdg.dscunikom.com/uploads/files/"+getmListFasilitas().get(position).getImage())
                .into(holder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return mListFasilitas.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_item_photo)
        ImageView imgPhoto;
        @BindView(R.id.text_item_title)
        TextView tvTitle;
        public GridViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
