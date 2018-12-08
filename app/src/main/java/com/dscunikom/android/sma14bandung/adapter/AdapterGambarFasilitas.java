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
import com.dscunikom.android.sma14bandung.getModel.GetPrestasi;
import com.dscunikom.android.sma14bandung.model.Fasilitas;
import com.dscunikom.android.sma14bandung.model.GambarFasilitas;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterGambarFasilitas  extends RecyclerView.Adapter<AdapterGambarFasilitas.GridViewHolder> {
    private Context context;

    private List<GambarFasilitas> mGambarFasilitas;

    public AdapterGambarFasilitas(Context context) {
        this.context = context;
    }


    public List<GambarFasilitas> getmGambarFasilitas() {
        return mGambarFasilitas;
    }

    public void setmGambarFasilitas(List<GambarFasilitas> mGambarFasilitas) {
        this.mGambarFasilitas = mGambarFasilitas;
    }

    @NonNull
    @Override
    public AdapterGambarFasilitas.GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_detail_fasilitas_ekskul, parent, false);
        return new AdapterGambarFasilitas.GridViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterGambarFasilitas.GridViewHolder holder, int position) {
//       holder.tvTitle.setVisibility(View.GONE);
        Glide.with(context)
                .load("http://sman14bdg.dscunikom.com/uploads/files/"+getmGambarFasilitas().get(position).getImage())
                .into(holder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return mGambarFasilitas.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_content_detail_sec) ImageView imgPhoto;
//        @BindView(R.id.text_item_title)
//        TextView tvTitle;
        public GridViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
