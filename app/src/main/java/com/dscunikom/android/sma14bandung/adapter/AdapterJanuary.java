package com.dscunikom.android.sma14bandung.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dscunikom.android.sma14bandung.R;
import com.dscunikom.android.sma14bandung.model.Kalender;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class AdapterJanuary extends RecyclerView.Adapter<AdapterJanuary.Holder> {
    List<Kalender> mListKalender;
    Context context;

    public AdapterJanuary(Context context) {
        this.context = context;
    }

    public List<Kalender> getmListKalender() {
        return mListKalender;
    }

    public void setmListKalender(List<Kalender> mListKalender) {
        this.mListKalender = mListKalender;
    }

    @NonNull
    @Override
    public AdapterJanuary.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_kalender, parent, false);
        return new AdapterJanuary.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterJanuary.Holder holder, int position) {
        String getDate = getmListKalender().get(position).getTanggal();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(getDate);
            SimpleDateFormat newFormat = new SimpleDateFormat("dd");
            String dateFix = newFormat.format(date);
            holder.tvTanggal.setText(dateFix);
        } catch (ParseException e) {
            e.printStackTrace();
        }



        holder.tvKegiatan.setText(getmListKalender().get(position).getNamaKalender());

    }

    @Override
    public int getItemCount() {
        return mListKalender.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView tvKegiatan,tvTanggal;

        public Holder(View itemView) {
            super(itemView);
            tvTanggal = itemView.findViewById(R.id.text_tanggal);

            tvKegiatan = itemView.findViewById(R.id.text_ket);

        }
    }
}
