package com.ufrpe.nemus_app.nemus.controller;

import com.ufrpe.nemus_app.nemus.R;

/**
 * Created by Ricardo Silva on 07/05/2018.
 */
public class LifeController {

    private static LifeController instance = new LifeController();
    private int lives;
    private int[] livesImgs;

    /** Singleton builder **/
    private LifeController(){
        livesImgs = new int[]{
                R.drawable.lives_empty,
                R.drawable.lives_1,
                R.drawable.lives_2,
                R.drawable.lives_3,
                R.drawable.lives_4,
                R.drawable.lives_5,
                R.drawable.lives_filled
        };
    }

    public static LifeController getInstance() {
        return instance;
    }

    public int getCurrentLifeDrawable(){
        return livesImgs[lives];
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}
