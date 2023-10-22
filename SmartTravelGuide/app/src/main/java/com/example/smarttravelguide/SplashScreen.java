package com.example.smarttravelguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class SplashScreen extends AppCompatActivity {
    //Variables
    ImageView backgroundImage;
    TextView slogan;
    SharedPreferences onBoardingScreen;

    //Animations
    Animation sideAnim,botAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        //Hooks
        backgroundImage = findViewById(R.id.backgound_image);
        slogan = findViewById(R.id.slogan);

        //Animations
        sideAnim = AnimationUtils.loadAnimation(this,R.anim.side_anim);
        botAnim = AnimationUtils.loadAnimation(this,R.anim.bot_anim);

        //Set animations on element
        backgroundImage.setAnimation(sideAnim);
        slogan.setAnimation(botAnim);

        int SPLASH_TIMER = 4500;
        new Handler().postDelayed(() -> {

//            onBoardingScreen = getSharedPreferences("OnBoardingScreen",MODE_PRIVATE);
//            boolean isFirstTime = onBoardingScreen.getBoolean("firstTime",true);
//            SharedPreferences.Editor editor = onBoardingScreen.edit();
//            editor.putBoolean("firstTime",false);
//            editor.apply();

            Intent intent = new Intent(getApplicationContext(), OnBoarding.class);
            startActivity(intent);
            finish();

//            if(!isFirstTime){
//
//                SharedPreferences.Editor editor = onBoardingScreen.edit();
//                editor.putBoolean("firstTime",false);
//                editor.apply();
//
//                Intent intent = new Intent(getApplicationContext(), OnBoarding.class);
//                startActivity(intent);
//                finish();
//            }
//            else{
//                Intent intent = new Intent(getApplicationContext(), UserDashboard.class);
//                startActivity(intent);
//                finish();
//            }


        }, SPLASH_TIMER);
    }
}