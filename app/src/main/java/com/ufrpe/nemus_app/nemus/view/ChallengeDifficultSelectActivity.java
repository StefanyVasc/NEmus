package com.ufrpe.nemus_app.nemus.view;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ufrpe.nemus_app.nemus.R;
import com.ufrpe.nemus_app.nemus.controller.Controller;
import com.ufrpe.nemus_app.nemus.controller.LifeController;

public class ChallengeDifficultSelectActivity extends AppCompatActivity {

    ImageView header;
    AnimationDrawable animation;
    Button btnEasy;
    Button btnNormal;
    Button btnHard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_difficult_select);

        header = findViewById(R.id.header);

        if (Controller.getInstance().isAnimationsEnabled()) {
            header.setBackgroundResource(R.drawable.bird_animation);
            animation = (AnimationDrawable) header.getBackground();
            animation.start();
        } else {
            header.setImageResource(R.drawable.header_filled);
        }

        btnEasy = findViewById(R.id.btn_easy);
        btnNormal = findViewById(R.id.btn_medium);
        btnHard = findViewById(R.id.btn_hard);

        btnEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LifeController.getInstance().setLives(6);
                Controller.getInstance().setCurrentPlayList(Controller.getInstance().playLists[0]);
                Intent play = new Intent(ChallengeDifficultSelectActivity.this, PlayActivity.class);
                finish();
                startActivity(play);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LifeController.getInstance().setLives(3);
                Controller.getInstance().setCurrentPlayList(Controller.getInstance().playLists[0]);
                Intent play = new Intent(ChallengeDifficultSelectActivity.this, PlayActivity.class);
                finish();
                startActivity(play);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        btnHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LifeController.getInstance().setLives(1);
                Controller.getInstance().setCurrentPlayList(Controller.getInstance().playLists[0]);
                Intent play = new Intent(ChallengeDifficultSelectActivity.this, PlayActivity.class);
                finish();
                startActivity(play);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent back = new Intent(ChallengeDifficultSelectActivity.this, ModeSelectionActivity.class);
        finish();
        startActivity(back);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
