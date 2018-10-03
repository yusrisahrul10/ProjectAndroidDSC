package com.dscunikom.android.sma14bandung.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dscunikom.android.sma14bandung.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuruActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guru);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.img1)
    public void detailGuru(){
        startActivity(new Intent(this, DetailGuruActivity.class));
    }
}
