package com.ufrpe.nemus_app.nemus.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ufrpe.nemus_app.nemus.R;
import com.ufrpe.nemus_app.nemus.controller.Controller;

public class CampaignSelectActivity extends AppCompatActivity {

    private ViewPager pager;
    private CustomPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campain_select);

        pager = findViewById(R.id.view_pager);
        adapter = new CustomPagerAdapter();

        pager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        Intent back = new Intent(CampaignSelectActivity.this, ModeSelectionActivity.class);
        finish();
        startActivity(back);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    private class CustomPagerAdapter extends PagerAdapter{

        Context context;
        LayoutInflater layoutInflater;

        CustomPagerAdapter(){
            context = CampaignSelectActivity.this;
        }

        public int[] drawablesHeader = {
                R.drawable.campaign_viver,
                R.drawable.campaign_trabalhar,
                R.drawable.campaign_ocupar,
                R.drawable.campaign_cantar,
                R.drawable.campaign_crer,
                R.drawable.campaign_criar,
                R.drawable.campaign_migrar
        };

        public int[] drawablesImg = {
                R.drawable.campaign_img,
                R.drawable.campaign_img,
                R.drawable.campaign_img,
                R.drawable.campaign_img,
                R.drawable.campaign_img,
                R.drawable.campaign_img,
                R.drawable.campaign_img
        };

        @Override
        public int getCount() {
            return drawablesHeader.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, final int position) {
            layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            assert layoutInflater != null;
            View view = layoutInflater.inflate(R.layout.campaign_slide, container, false);

            ImageView header = view.findViewById(R.id.campaign_header);
            header.setImageResource(drawablesHeader[position]);

            ImageView img = view.findViewById(R.id.campaign_img);
            img.setImageResource(drawablesImg[position]);

            Button button = view.findViewById(R.id.btn_play_campaign);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //TODO do the selection logic with the controller

                    Controller.getInstance().pauseBackgroundMusic();
                    if (position <= 1) {
                        Log.d("POSITION", String.valueOf(position));
                        Controller.getInstance().setCurrentPlayList(Controller.getInstance().playLists[position + 1].copy());
                    } else {
                        Controller.getInstance().setCurrentPlayList(Controller.getInstance().playLists[2]);
                    }
                    Intent goPlay = new Intent(CampaignSelectActivity.this, PlayActivity.class);
                    finish();
                    startActivity(goPlay);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            });

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((RelativeLayout) object);
        }
    }
}


