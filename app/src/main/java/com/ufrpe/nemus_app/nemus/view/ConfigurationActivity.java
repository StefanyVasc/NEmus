package com.ufrpe.nemus_app.nemus.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.ufrpe.nemus_app.nemus.R;
import com.ufrpe.nemus_app.nemus.controller.Controller;

public class ConfigurationActivity extends AppCompatActivity {

    private Switch soundSwitch;
    private Switch animationSwitch;
    private Button btnConfirm;
    private Button btnCancel;
    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        controller = Controller.getInstance();

        soundSwitch = findViewById(R.id.switch_sound);
        animationSwitch = findViewById(R.id.switch_animation);
        btnConfirm = findViewById(R.id.btn_config_confirm);
        btnCancel = findViewById(R.id.btn_config_cancel);

        soundSwitch.setChecked(controller.isMenuMusicEnabled());
        animationSwitch.setChecked(controller.isAnimationsEnabled());

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.setAnimationsEnabled(animationSwitch.isChecked());
                controller.setMenuMusicEnabled(soundSwitch.isChecked());
                if (!soundSwitch.isChecked()){
                    controller.pauseBackgroundMusic();
                } else {
                    controller.playBackgroundMusic();
                }
                Intent back = new Intent(ConfigurationActivity.this, MainActivity.class);
                finish();
                startActivity(back);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(ConfigurationActivity.this, MainActivity.class);
                finish();
                startActivity(back);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent back = new Intent(ConfigurationActivity.this, MainActivity.class);
        finish();
        startActivity(back);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
