package com.example.myapplication;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import static android.content.Intent.getIntent;

public class SecAct extends AppCompatActivity {
    //SecAct which passes items data using Parcelable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sec_act);

        // receiving data from explicit intent
        Intent i = getIntent();
        Item s = i.getExtras().getParcelable("name");


        TextView tv = findViewById(R.id.tvName);
        TextView tv7 = findViewById(R.id.tvLoc);
        ImageView im = findViewById(R.id.imageView2);
        tv.setText(s.display_name);
        tv7.setText(s.location);
        Glide.with(this)
                .load(s.profile_image)
                .into(im);
    }
}



