package com.ufrpe.nemus_app.nemus.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.ufrpe.nemus_app.nemus.R;
import com.ufrpe.nemus_app.nemus.controller.Controller;
import com.ufrpe.nemus_app.nemus.controller.LifeController;
import com.ufrpe.nemus_app.nemus.model.Music;

import java.util.Random;

public class PlayActivity extends AppCompatActivity {

    private ImageButton btnPlayMusic;
    private Button btnChoice1;
    private Button btnChoice2;
    private Button btnChoice3;
    private Button btnChoice4;
    private ImageView imgLives;
    private LifeController lifeController;

    private String[] musicChoices = new String[4];
    private int rightChoiceIndex;
    private Controller controller;
    private Music music;
    private Handler handler;
    private int hits;
    private int misses;
    private boolean isPlaying = false;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        btnPlayMusic = findViewById(R.id.btn_play_music);
        btnChoice1 = findViewById(R.id.btn_choice_1);
        btnChoice2 = findViewById(R.id.btn_choice_2);
        btnChoice3 = findViewById(R.id.btn_choice_3);
        btnChoice4 = findViewById(R.id.btn_choice_4);
        imgLives = findViewById(R.id.img_lives);

        handler = new Handler();
        controller = Controller.getInstance();
        lifeController = LifeController.getInstance();
        controller.pauseBackgroundMusic();

        hits = 0;

        if (controller.isChallengeMode()){
            imgLives.setImageResource(lifeController.getCurrentLifeDrawable());
        } else {
            imgLives.setImageResource(R.drawable.lives_empty);
        }

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (isPlaying) {
                    isPlaying = false;
                    controller.stopMusic();
                    btnPlayMusic.setImageResource(R.drawable.btn_play_music);
                    setEnableColors();
                    btnPlayMusic.setEnabled(true);
                }
            }
        };

        setCurrentMusic(false);

        //TODO add show right choice if wrong

        btnPlayMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDisableColors();
                btnPlayMusic.setImageResource(R.drawable.btn_play_music_clicked);
                if (isPlaying){
                    isPlaying = false;
                    handler.removeCallbacks(runnable);
                    controller.stopMusic();
                    btnPlayMusic.setImageResource(R.drawable.btn_play_music);
                    setEnableColors();
                } else {
                    isPlaying = true;
                    controller.playMusic(music);
                    handler.postDelayed(runnable, 8000);
                }

            }
        });



        btnChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choiceVerify(0);
            }
        });
        btnChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choiceVerify(1);
            }
        });
        btnChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choiceVerify(2);
            }
        });
        btnChoice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choiceVerify(3);
            }
        });
    }

    private void setCurrentMusic(boolean excluding) {
        music = controller.getCurrentPlayList().getRandomMusic(excluding);
        Random r = new Random();
        musicChoices = new String[4];
        rightChoiceIndex = r.nextInt(4);
        musicChoices[rightChoiceIndex] = music.getName();
        String[] wrongChoices = controller.getRandomNames();

        int step = 0;
        for (int i = 0; i < 4; i++) {
            if (musicChoices[i] == null) {
                musicChoices[i] = wrongChoices[step];
                step++;
            }
        }
        btnChoice1.setText(musicChoices[0]);
        btnChoice2.setText(musicChoices[1]);
        btnChoice3.setText(musicChoices[2]);
        btnChoice4.setText(musicChoices[3]);
    }

    private void finishCampaign(boolean fail) {
        if (controller.isChallengeMode()){
            ResultActivity.setHits(hits);
            ResultActivity.setFail(fail);
        } else {
            ResultActivity.setMisses(misses);
        }
        Intent showResult = new Intent(PlayActivity.this, ResultActivity.class);
        finish();
        startActivity(showResult);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private void buildDialog(int drawable){
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.feedback_dialog, null);
        dialogBuilder.setView(dialogView);

        ImageView img = dialogView.findViewById(R.id.img_dialog);
        img.setImageResource(drawable);
        Button btnContinue = dialogView.findViewById(R.id.btn_continue);
        final AlertDialog alertDialog = dialogBuilder.create();
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
    }

    private void choiceVerify(int choiceIndex){
        isPlaying = false;
        if (rightChoiceIndex == choiceIndex){
            buildDialog(R.drawable.congrats_dialog);
            hits++;
            if (controller.getCurrentPlayList().getMusicSet().size() == 1){
                finishCampaign(false);
            } else {
                setCurrentMusic(true);
                setDisableColors();
            }
        } else {
            buildDialog(R.drawable.sorry_dialog);
            misses++;
            if (controller.isChallengeMode()){
                lifeController.setLives(lifeController.getLives() - 1);
                if (lifeController.getLives() > 0) {
                    imgLives.setImageResource(lifeController.getCurrentLifeDrawable());
                    setCurrentMusic(false);
                    setDisableColors();
                } else {
                    finishCampaign(true);
                }
            } else {
                setCurrentMusic(false);
                setDisableColors();
            }
        }
    }

    public void setEnableColors(){
        btnChoice1.setEnabled(true);
        btnChoice2.setEnabled(true);
        btnChoice3.setEnabled(true);
        btnChoice4.setEnabled(true);
        btnChoice1.setTextColor(getResources().getColor(R.color.white));
        btnChoice2.setTextColor(getResources().getColor(R.color.white));
        btnChoice3.setTextColor(getResources().getColor(R.color.white));
        btnChoice4.setTextColor(getResources().getColor(R.color.white));
        btnChoice1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        btnChoice2.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        btnChoice3.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        btnChoice4.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    public void setDisableColors(){
        btnChoice1.setEnabled(false);
        btnChoice2.setEnabled(false);
        btnChoice3.setEnabled(false);
        btnChoice4.setEnabled(false);
        btnChoice1.setTextColor(getResources().getColor(R.color.grey));
        btnChoice2.setTextColor(getResources().getColor(R.color.grey));
        btnChoice3.setTextColor(getResources().getColor(R.color.grey));
        btnChoice4.setTextColor(getResources().getColor(R.color.grey));
        btnChoice1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDisabled));
        btnChoice2.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDisabled));
        btnChoice3.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDisabled));
        btnChoice4.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDisabled));
    }

    @Override
    public void onBackPressed() {
        Controller.getInstance().pauseBackgroundMusic();
        Intent back = new Intent(PlayActivity.this, ModeSelectionActivity.class);
        Controller.getInstance().playBackgroundMusic();
        finish();
        startActivity(back);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
