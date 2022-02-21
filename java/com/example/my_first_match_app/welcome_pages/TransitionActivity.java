package com.example.my_first_match_app.welcome_pages;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.my_first_match_app.MainActivity;
import com.example.my_first_match_app.R;

public class TransitionActivity extends AppCompatActivity {
    //用于标识第一次进入的Flag
    private boolean isFirst = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        SharedPreferences preferences = getSharedPreferences("isFirstInData", MODE_PRIVATE);
        isFirst = preferences.getBoolean("isFirstIn", true);
        new Handler().postDelayed(() -> {
            if (isFirst) {
                startActivity(new Intent(TransitionActivity.this, com.example.my_first_match_app.welcome_pages.WelcomeActivity.class));
            } else {
                startActivity(new Intent(TransitionActivity.this, MainActivity.class));
            }
            finish();
        }, 2000);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isFirstIn", false);
        editor.apply();
    }
}