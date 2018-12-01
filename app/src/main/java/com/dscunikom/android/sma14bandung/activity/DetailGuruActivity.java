package com.dscunikom.android.sma14bandung.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.dscunikom.android.sma14bandung.R;
import com.google.firebase.messaging.FirebaseMessaging;

public class DetailGuruActivity extends AppCompatActivity {
    public static String EXTRA_NAME = "extra_name";
    public static String EXTRA_MAPEL = "extra_mapel";
    public static String EXTRA_EMAIL = "extra_email";
    public static String EXTRA_KELAS = "extra_kelas";


    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_guru);
        FirebaseMessaging.getInstance().subscribeToTopic("helsan");

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarguru);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textView1 = (TextView) findViewById(R.id.tvName);
        textView2 = (TextView) findViewById(R.id.kelas);
        textView3 = (TextView) findViewById(R.id.mapelajaran);
        textView4 = (TextView) findViewById(R.id.emaill);

        String name = getIntent().getStringExtra(EXTRA_NAME);
        String mapel = getIntent().getStringExtra(EXTRA_MAPEL);
        String email = getIntent().getStringExtra(EXTRA_EMAIL);
        String kelas = getIntent().getStringExtra(EXTRA_KELAS);

        String text = name;
        textView1.setText(text);
        String mapell = mapel;
        textView2.setText(mapell);
        String emaill = email;
        textView3.setText(emaill);
        String kelass = kelas;
        textView4.setText(kelass);



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home : {
                finish();
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
