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

public class ModeSelectionActivity extends AppCompatActivity {

    private ImageView header;
    private Button btnSinglePLayer;
    private Button btnChallengeMode;
    private AnimationDrawable animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_selection);

        header = findViewById(R.id.header);
        if (Controller.getInstance().isAnimationsEnabled()) {
            header.setBackgroundResource(R.drawable.bird_animation);
            animation = (AnimationDrawable) header.getBackground();
            animation.start();
        } else {
            header.setImageResource(R.drawable.header_filled);
        }

        btnSinglePLayer = findViewById(R.id.btn_single_player);
        btnChallengeMode = findViewById(R.id.btn_challenge_mode);

        btnSinglePLayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controller.getInstance().setChallengeMode(false);
                Intent selectCampaign = new Intent(ModeSelectionActivity.this, CampaignSelectActivity.class);
                finish();
                startActivity(selectCampaign);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        btnChallengeMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controller.getInstance().setChallengeMode(true);
                Intent selectDifficult = new Intent(ModeSelectionActivity.this, ChallengeDifficultSelectActivity.class);
                finish();
                startActivity(selectDifficult);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent back = new Intent(ModeSelectionActivity.this, MainActivity.class);
        finish();
        startActivity(back);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
