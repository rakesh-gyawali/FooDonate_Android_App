package com.example.food_donation_dissertation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.github.ybq.android.spinkit.SpinKitView;

public class SplashScreenActivity extends AppCompatActivity {

    private ImageView ivSplashLogo;
    private SpinKitView spin_kit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ivSplashLogo = findViewById(R.id.ivSplashLogo);
        spin_kit = findViewById(R.id.spin_kit);

        Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
//        spin_kit.startAnimation(animFadeIn);
        ivSplashLogo.startAnimation(animFadeIn);
    }
}