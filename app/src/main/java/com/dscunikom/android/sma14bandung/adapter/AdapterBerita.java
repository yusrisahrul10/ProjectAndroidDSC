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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdapterBerita extends RecyclerView.Adapter<AdapterBerita.CardViewViewHolder> {
    public List<Berita> mListBerita;
    Context context;

    public AdapterBerita(Context context) {
        this.context = context;
    }

    public AdapterBerita(List<Berita> mListBerita) {
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
    public AdapterBerita.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_news_event, parent, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBerita.CardViewViewHolder holder, int position) {

        final Berita p = getmListBerita().get(position);

        Glide.with(context)
                .load("http://sman14bdg.dscunikom.com/uploads/berita/"+p.getImage())
                .into(holder.imgContent);


//        holder.tvDate.setText(p.getTanggal());
        holder.tvTitle.setText(p.getJudul_berita());
        holder.tvIsiBerita.setText(p.getIsi_berita());

        String getDate = getmListBerita().get(position).getTanggal();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(getDate);
            SimpleDateFormat newFormat = new SimpleDateFormat("EEEE, MMM dd, yyyy");
            String dateFix = newFormat.format(date);
            holder.tvDate.setText(dateFix);
        } catch (ParseException e) {
            e.printStackTrace();
        }

//            holder.tvTitle.setText(mListBerita.get(position).getJudul_berita());
//            holder.tvDate.setText(mListBerita.get(position).getTanggal());
//        Glide.with(context)
//                .load("http://192.168.0.106/projectdsc/uploads/berita/"+mListBerita.get(position).getImage())
//                .into(holder.imgContent);
    }

    @Override
    public int getItemCount() {
        return mListBerita.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView imgContent;
        TextView tvTitle, tvDate,tvIsiBerita;
        public CardViewViewHolder(View itemView) {
            super(itemView);
            imgContent = itemView.findViewById(R.id.img_content);
            tvTitle = itemView.findViewById(R.id.tv_title_content);
            tvDate = itemView.findViewById(R.id.tv_date_content);
            tvIsiBerita = itemView.findViewById(R.id.tv_deskripsi);

        }
    }
}
