package com.dscunikom.android.sma14bandung.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dscunikom.android.sma14bandung.R;
import com.google.firebase.messaging.FirebaseMessaging;

public class DetailGuruActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_guru);
        FirebaseMessaging.getInstance().subscribeToTopic("helsan");
    }
}
