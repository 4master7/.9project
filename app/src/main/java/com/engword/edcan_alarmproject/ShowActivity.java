package com.engword.edcan_alarmproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Intent i = getIntent();
        TextView alarm = findViewById(R.id.alarmview);
        TextView cont = findViewById(R.id.contentview);
        Button back = findViewById(R.id.back);

        alarm.setText(i.getStringExtra("time"));
        cont.setText(i.getStringExtra("content"));
        back.setOnClickListener(v->{
            finish();
        });
    }
}