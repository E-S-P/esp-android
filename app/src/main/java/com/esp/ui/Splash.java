package com.esp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class Splash extends AppCompatActivity implements Animation.AnimationListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_splash);

        LinearLayout splash = (LinearLayout) findViewById(R.id.splash);
        Animation in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_fade_in);
        in.setAnimationListener(this);
        splash.setAnimation(in);

    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Intent intent = new Intent(Splash.this, Login.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }


}

