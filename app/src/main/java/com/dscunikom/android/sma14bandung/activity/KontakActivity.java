package com.dscunikom.android.sma14bandung.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
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

import com.dscunikom.android.sma14bandung.R;
import com.google.firebase.messaging.FirebaseMessaging;

public class KontakActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontak);
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

        ImageView telp = findViewById(R.id.telp);
        telp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.telp:
                        String phonenumber = "082295103327";
                        Intent dialnumber = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: " + phonenumber));
                        startActivity(dialnumber);
                        break;
                }
            }
        });
        ImageView website = findViewById(R.id.web);
        website.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.web:
                        Uri webpage = Uri.parse("http://www.android.com");
                        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                        startActivity(webIntent);
                        break;
                }
            }
        });
        ImageView facebook = findViewById(R.id.fb);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.fb:
                        Uri fbpage = Uri.parse("https://www.facebook.com/SMAN-14-Bandung-339617946099977/");
                        Intent fbIntent = new Intent(Intent.ACTION_VIEW, fbpage);
                        startActivity(fbIntent);
                        break;
                }

            }
        });
        ImageView whatsapp = findViewById(R.id.wa);
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.wa:
                        String number = "+6282295103327";
                        String url = "https://api.whatsapp.com/send?phone="+number;
                        Intent whatsapp = new Intent(Intent.ACTION_VIEW);
                        whatsapp.setData(Uri.parse(url));
                        startActivity(whatsapp);
                        break;
                }
            }
        });
        ImageView instagram = findViewById(R.id.ig);
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.ig:
                        Uri ig = Uri.parse("http://instagram.cm/_u/fryantabif");
                        Intent likeIng = new Intent(Intent.ACTION_VIEW, ig);
                        likeIng.setPackage("com.instagram.android");
                        try {
                            startActivity(likeIng);
                        }
                        catch (ActivityNotFoundException e){
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("http://instagram.com/fryantabif")));
                        }
                        break;
                }

            }
        });

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
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        item.setChecked(true);

        if (id == R.id.nav_home) {
            // Handle the camera action
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            ActivityCompat.finishAffinity(KontakActivity.this);
        } else if (id == R.id.nav_teacher) {
            Intent intent = new Intent(this, GuruActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            ActivityCompat.finishAffinity(KontakActivity.this);
//
        } else if (id == R.id.nav_calendar) {
            Intent intent = new Intent(this, KalendarActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            ActivityCompat.finishAffinity(KontakActivity.this);


        } else if (id == R.id.nav_contact) {


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
