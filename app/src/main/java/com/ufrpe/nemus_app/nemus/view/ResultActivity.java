package com.ufrpe.nemus_app.nemus.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ufrpe.nemus_app.nemus.R;
import com.ufrpe.nemus_app.nemus.controller.Controller;

public class ResultActivity extends AppCompatActivity {

    private static boolean fail;
    private static int hits;
    private static int misses;
    private TextView txtResult;
    private Button btnBack;
    private ImageView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        txtResult = findViewById(R.id.result_text);
        btnBack = findViewById(R.id.back_to_menu);
        header = findViewById(R.id.result_header);

        if (Controller.getInstance().isChallengeMode()){
            if (fail){
                header.setImageResource(R.drawable.result_header_challenge);
            } else {
                header.setImageResource(R.drawable.result_header_challenge_finished);
            }
        }

        if (Controller.getInstance().isChallengeMode()){
            txtResult.setText("Acertos: " + String.valueOf(hits));
        } else {
            txtResult.setText("Erros: " + String.valueOf(misses));
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent backToMenu = new Intent(ResultActivity.this, MainActivity.class);
        finish();
        startActivity(backToMenu);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public static void setHits(int hits) {
        ResultActivity.hits = hits;
    }

    public static void setMisses(int misses) {
        ResultActivity.misses = misses;
    }

    public static void setFail(boolean fail) {
        ResultActivity.fail = fail;
    }
}
