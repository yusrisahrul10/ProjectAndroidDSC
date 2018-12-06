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
import com.dscunikom.android.sma14bandung.model.Guru;

import java.util.List;

public class AdapterGuru extends RecyclerView.Adapter<AdapterGuru.GridViewHolder> {
    Context context;
    private List<Guru> mListGuru;

    public AdapterGuru(Context context) {
        this.context = context;
    }

    public List<Guru> getmListGuru() {
        return mListGuru;
    }

    public void setmListGuru(List<Guru> mListGuru) {
        this.mListGuru = mListGuru;
    }

    @NonNull
    @Override
    public AdapterGuru.GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_guru, parent, false);
        return new AdapterGuru.GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterGuru.GridViewHolder holder, int position) {
        holder.txtNamaGuru.setText(getmListGuru().get(position).getNamaGuru());
        Glide.with(context)
                .load("http://projectdsc.ahdirdiysarm.com/uploads/guru/"+getmListGuru().get(position).getImage())
                .into(holder.imgGuru);
    }

    @Override
    public int getItemCount() {
        return mListGuru.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        ImageView imgGuru;
        TextView txtNamaGuru;
        public GridViewHolder(View itemView) {
            super(itemView);
            imgGuru = itemView.findViewById(R.id.ImageGuru);
            txtNamaGuru = itemView.findViewById(R.id.txtNamaGuru);
        }
    }
}
