package com.ufrpe.nemus_app.nemus.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ufrpe.nemus_app.nemus.R;
import com.ufrpe.nemus_app.nemus.controller.Controller;

public class MainActivity extends AppCompatActivity {

    ImageView header;
    Button play;
    Button about;
    Button configuration;
    AnimationDrawable animation;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Controller.getInstance().setContext(MainActivity.this);

        header = findViewById(R.id.header);
        if (Controller.getInstance().isAnimationsEnabled()) {
            header.setBackgroundResource(R.drawable.bird_animation);

            animation = (AnimationDrawable) header.getBackground();
            animation.start();
        } else {
            header.setImageResource(R.drawable.header_filled);
        }

        play = findViewById(R.id.btn_play);
        about = findViewById(R.id.btn_about);
        configuration = findViewById(R.id.btn_configuration);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selectMode = new Intent(MainActivity.this, ModeSelectionActivity.class);
                finish();
                startActivity(selectMode);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aboutView = new Intent(MainActivity.this, AboutActivity.class);
                finish();
                startActivity(aboutView);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left );
            }
        });

        configuration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aboutView = new Intent(MainActivity.this, ConfigurationActivity.class);
                finish();
                startActivity(aboutView);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left );
            }
        });

    }

}
