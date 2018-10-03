package com.dscunikom.android.sma14bandung.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dscunikom.android.sma14bandung.R;
import com.dscunikom.android.sma14bandung.activity.MainActivity;

public class PreloadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preload);
    }

    public void move(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
