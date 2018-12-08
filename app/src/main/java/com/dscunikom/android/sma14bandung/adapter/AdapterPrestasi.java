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
import com.bumptech.glide.request.RequestOptions;
import com.dscunikom.android.sma14bandung.R;
import com.dscunikom.android.sma14bandung.model.President;
import com.dscunikom.android.sma14bandung.model.Prestasi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterPrestasi extends RecyclerView.Adapter<AdapterPrestasi.ListViewHolder> {

    private ArrayList<President> listPresident;
    List<Prestasi> mListPrestasi;

    public List<Prestasi> getmListPrestasi() {
        return mListPrestasi;
    }

    public void setmListPrestasi(List<Prestasi> mListPrestasi) {
        this.mListPrestasi = mListPrestasi;
    }

    private Context context;

    public AdapterPrestasi(Context context) {
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
    public AdapterPrestasi.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_prestasi, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPrestasi.ListViewHolder holder, int position) {
        final Prestasi p = getmListPrestasi().get(position);


        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.placeholder);
        requestOptions.error(R.drawable.placeholder);
        Glide.with(context)
                .asBitmap()
                .load("http://sman14bdg.dscunikom.com/uploads/prestasi/"+getmListPrestasi().get(position).getImage())

                .into(holder.imgPrestasi);
        holder.tvPrestasi.setText(p.getNamaPrestasi());
    }

    @Override
    public int getItemCount() {
        return getmListPrestasi().size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_fragment_prestasi)
        ImageView imgPrestasi;
        @BindView(R.id.text_fragment_prestasi)
        TextView tvPrestasi;

        public ListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
