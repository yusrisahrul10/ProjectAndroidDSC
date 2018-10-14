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
import com.dscunikom.android.sma14bandung.model.Ekstrakulikuler;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterEkskul extends RecyclerView.Adapter<AdapterEkskul.GridViewHolder> {
    private List<Ekstrakulikuler> mListEkstra;
    private Context context;

    public List<Ekstrakulikuler> getmListEkstra() {
        return mListEkstra;
    }

    public void setmListEkstra(List<Ekstrakulikuler> mListEkstra) {
        this.mListEkstra = mListEkstra;
    }

    public AdapterEkskul(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterEkskul.GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_fasilitas_ekskul, parent, false);
        return new AdapterEkskul.GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterEkskul.GridViewHolder holder, int position) {
        holder.tvTitle.setText(getmListEkstra().get(position).getNamaEkstra());

        Glide.with(context)
                .load("http://projectdsc.ahdirdiysarm.com/uploads/ekstrakulikuler/"+getmListEkstra().get(position).getImage())
                .into(holder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return mListEkstra.size();
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
