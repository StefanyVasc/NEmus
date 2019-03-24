package com.ufrpe.nemus_app.nemus.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ufrpe.nemus_app.nemus.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    @Override
    public void onBackPressed() {
        Intent back = new Intent(AboutActivity.this, MainActivity.class);
        finish();
        startActivity(back);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
