package com.example.tubes03_g.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.tubes03_g.R;
import com.github.appintro.AppIntro;
import com.github.appintro.AppIntroFragment;

public class IntroActivity extends AppIntro {

    //https://www.geeksforgeeks.org/how-to-make-an-activity-appear-only-once-in-android/

    String check = "false";

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        if (sharedpreferences.getBoolean(check, true)) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean(check, Boolean.FALSE);
            editor.apply();
        } else {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntroFragment.newInstance("home", "welcome to bikeWaze!", R.drawable.ic_appintro_next, Color.BLUE));
        addSlide(AppIntroFragment.newInstance("menu","next"));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
    }

    @Override
    protected void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}