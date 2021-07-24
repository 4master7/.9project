package com.engword.edcan_alarmproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Date;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Button back = findViewById(R.id.back);
        Button save = findViewById(R.id.save);
        EditText cont = findViewById(R.id.contenttext);
        TimePicker picker = findViewById(R.id.timepick);
        String time = new SimpleDateFormat("HH:mm").format(new Date(System.currentTimeMillis()));
        picker.setHour(Integer.parseInt(time.split(":")[0]));
        picker.setMinute(Integer.parseInt(time.split(":")[1]));

        save.setOnClickListener(v->{
            String alarm = picker.getHour()+":"+picker.getMinute();
            String contText = cont.getText().toString();
            ((ListActivity)ListActivity.mCon).adapter.addItem(contText,alarm);
            ((ListActivity)ListActivity.mCon).adapter.notifyDataSetChanged();
            finish();
        });

        back.setOnClickListener(v->{
            finish();
        });
    }
}