package com.engword.edcan_alarmproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        Handler mHandler = new Handler();

        mHandler.postDelayed(() -> {
            Intent intent = new Intent(this, ListActivity.class);
            startActivity(intent);
            finish();
        },2500);
    }
}