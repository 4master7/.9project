package com.engword.edcan_alarmproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

public class ShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Intent i = getIntent();
        EditText cont = findViewById(R.id.contenttext);
        Button back = findViewById(R.id.back);
        TimePicker picker = findViewById(R.id.timepick);
        Button save = findViewById(R.id.save);
        String t = i.getStringExtra("time");
        picker.setHour(Integer.parseInt(t.split(":")[0]));
        picker.setMinute(Integer.parseInt(t.split(":")[1]));
        cont.setText(i.getStringExtra("content"));
        int pos = i.getIntExtra("pos",-1);
        save.setOnClickListener(v->{
            String alarm = picker.getHour()+":"+picker.getMinute();
            String contText = cont.getText().toString();
            ((ListActivity)ListActivity.mCon).adapter.setmItem(alarm,contText,pos);
            ((ListActivity)ListActivity.mCon).adapter.notifyDataSetChanged();
            finish();
        });
        back.setOnClickListener(v->{
            finish();
        });
    }
}