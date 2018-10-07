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

import java.util.List;

public class AdapterPrestasiHome extends RecyclerView.Adapter<AdapterPrestasiHome.MyViewHolder> {

    public List<Berita> mListBerita;
    Context context;

    public AdapterPrestasiHome(Context context) {
        this.context = context;
    }

    public AdapterPrestasiHome(List<Berita> mListBerita) {
        this.mListBerita = mListBerita;
    }

    public List<Berita> getmListBerita() {
        return mListBerita;
    }

    public void setmListBerita(List<Berita> mListBerita) {
        this.mListBerita = mListBerita;
    }

    @NonNull
    @Override
    public AdapterPrestasiHome.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_news_event, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPrestasiHome.MyViewHolder holder, int position) {
//        final Berita p = getmListBerita().get(position);
//
//        Glide.with(context)
//                .load("http://192.168.0.106/projectdsc/uploads/berita/"+p.getImage())
//                .into(holder.imgPrestasi);

        final Berita p = getmListBerita().get(position);

        Glide.with(context)
                .load("http://192.168.0.106/projectdsc/uploads/berita/"+p.getImage())
                .into(holder.imgContent);


        holder.tvDate.setText(p.getTanggal());
        holder.tvTitle.setText(p.getJudul_berita());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imgContent;
        TextView tvTitle, tvDate;
        public MyViewHolder(View itemView) {
            super(itemView);
            imgContent = itemView.findViewById(R.id.img_content);
            tvTitle = itemView.findViewById(R.id.tv_title_content);
            tvDate = itemView.findViewById(R.id.tv_date_content);
        }
    }
}
