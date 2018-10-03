package com.dscunikom.android.sma14bandung.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dscunikom.android.sma14bandung.President;
import com.dscunikom.android.sma14bandung.R;

import java.util.ArrayList;

public class CardViewNewsEventAdapter extends RecyclerView.Adapter<CardViewNewsEventAdapter.CardViewViewHolder> {

    private ArrayList<President> listPresident;
    private Context context;

    public CardViewNewsEventAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<President> getListPresident() {
        return listPresident;
    }

    public void setListPresident(ArrayList<President> listPresident) {
        this.listPresident = listPresident;
    }

    @Override
    public CardViewNewsEventAdapter.CardViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_news_event, parent, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardViewNewsEventAdapter.CardViewViewHolder holder, int position) {
        final President p = getListPresident().get(position);

        Glide.with(context)
                .load(p.getPhoto())
                .into(holder.imgContent);

        holder.tvDate.setText(p.getRemarks());
        holder.tvTitle.setText(p.getName());

    }

    @Override
    public int getItemCount() {
        return getListPresident().size();
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
