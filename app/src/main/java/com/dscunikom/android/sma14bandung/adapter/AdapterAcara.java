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
import com.dscunikom.android.sma14bandung.model.Acara;

import java.util.List;

public class AdapterAcara extends RecyclerView.Adapter<AdapterAcara.CardViewViewHolder> {
    List<Acara> mListAcara;
    Context context;

    public AdapterAcara(Context context) {
        this.context = context;
    }

    public List<Acara> getmListAcara() {
        return mListAcara;
    }

    public void setmListAcara(List<Acara> mListAcara) {
        this.mListAcara = mListAcara;
    }

    @NonNull
    @Override
    public AdapterAcara.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_news_event, parent, false);
        return new AdapterAcara.CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAcara.CardViewViewHolder holder, int position) {
            holder.tvTitle.setText(getmListAcara().get(position).getNamaAcara());
        holder.tvDate.setText(getmListAcara().get(position).getTanggal());

        Glide.with(context)
                .load("http://projectdsc.ahdirdiysarm.com/uploads/acara/"+getmListAcara().get(position).getImage())
                .into(holder.imgContent);
    }

    @Override
    public int getItemCount() {
        return mListAcara.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView imgContent;
        TextView tvTitle, tvDate;
        public CardViewViewHolder(View itemView) {
            super(itemView);
            imgContent = itemView.findViewById(R.id.img_content);
            tvTitle = itemView.findViewById(R.id.tv_title_content);
            tvDate = itemView.findViewById(R.id.tv_date_content);
        }
    }
}
