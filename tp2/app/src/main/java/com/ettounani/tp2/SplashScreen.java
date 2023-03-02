package com.ettounani.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {
    // Variable
    private static int SPLASH_TIME_OUT = 10000;
    Animation anim;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            // This method will be executed once the timer is over
            // Start your app main activity
                Intent i = new Intent(SplashScreen.this,
                        LoginActivity.class);
                startActivity(i);
            // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
        img=findViewById(R.id.imageView);
        anim= AnimationUtils.loadAnimation(this, R.anim.anim);
        img.setAnimation(anim);
    }
}