package com.dscunikom.android.sma14bandung.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.dscunikom.android.sma14bandung.R;
import com.google.firebase.messaging.FirebaseMessaging;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuruActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guru);
        ButterKnife.bind(this);
        FirebaseMessaging.getInstance().subscribeToTopic("helsan");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        img = findViewById(R.id.imgKlik);
        img.setOnClickListener(this);
        img = findViewById(R.id.img2);
        img.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            Intent goToMainActivity = new Intent(getApplicationContext(), MainActivity.class);
            goToMainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Will clear out your activity history stack till now
            startActivity(goToMainActivity);
        }
    }

//    @OnClick(R.id.img1)
//    public void detailGuru(){
//        startActivity(new Intent(this, DetailGuruActivity.class));
//    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.



        int id = item.getItemId();
        item.setChecked(true);

        if (id == R.id.nav_home) {
            // Handle the camera action
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            ActivityCompat.finishAffinity(GuruActivity.this);

        } else if (id == R.id.nav_teacher) {


        } else if (id == R.id.nav_calendar) {
            Intent intent = new Intent(this, KalendarActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            ActivityCompat.finishAffinity(GuruActivity.this);

        } else if (id == R.id.nav_contact) {
            Intent intent = new Intent(this, KontakActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            ActivityCompat.finishAffinity(GuruActivity.this);

        }  else if (id == R.id.nav_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            ActivityCompat.finishAffinity(GuruActivity.this);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgKlik:

                Intent moveWithDataIntent = new Intent(GuruActivity.this, DetailGuruActivity.class);
                moveWithDataIntent.putExtra(DetailGuruActivity.EXTRA_NAME, "DicodingAcademy Boy");
                moveWithDataIntent.putExtra(DetailGuruActivity.EXTRA_MAPEL, "Biologi");
                moveWithDataIntent.putExtra(DetailGuruActivity.EXTRA_EMAIL, "ary@surabaya");
                moveWithDataIntent.putExtra(DetailGuruActivity.EXTRA_KELAS, "12 IPA 8");

//                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 5);
                startActivity(moveWithDataIntent);
                break;
            case R.id.img2:

                Intent moveImage2 = new Intent(GuruActivity.this, DetailGuruActivity.class);
                moveImage2.putExtra(DetailGuruActivity.EXTRA_NAME, "CUUUUU");
                moveImage2.putExtra(DetailGuruActivity.EXTRA_MAPEL, "Fisika");
                moveImage2.putExtra(DetailGuruActivity.EXTRA_EMAIL, "ary@jampang");
                moveImage2.putExtra(DetailGuruActivity.EXTRA_KELAS, "12 IPA 2");
//                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 5);
                startActivity(moveImage2);
                break;
        }
    }
}
