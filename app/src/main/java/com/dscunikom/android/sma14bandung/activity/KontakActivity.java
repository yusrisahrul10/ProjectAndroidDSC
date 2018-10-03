package com.dscunikom.android.sma14bandung.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.dscunikom.android.sma14bandung.R;

public class KontakActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontak);

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
}
