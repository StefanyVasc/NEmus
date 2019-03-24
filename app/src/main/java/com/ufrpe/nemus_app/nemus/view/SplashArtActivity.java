package com.ufrpe.nemus_app.nemus.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ufrpe.nemus_app.nemus.R;
import com.ufrpe.nemus_app.nemus.controller.Controller;

import java.util.Random;

public class SplashArtActivity extends AppCompatActivity {

    Handler handler = new Handler();
    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_art);

        controller = Controller.getInstance();
        controller.setContext(SplashArtActivity.this);
        controller.initializePlaylists();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent startApp = new Intent(SplashArtActivity.this, MainActivity.class);
                Controller.getInstance().playBackgroundMusic();
                finish();
                startActivity(startApp);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        }, 1500);
    }
}
