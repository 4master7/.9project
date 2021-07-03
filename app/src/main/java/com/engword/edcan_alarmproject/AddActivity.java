package com.engword.edcan_alarmproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Button back = findViewById(R.id.back);
        Button save = findViewById(R.id.save);
        EditText alarm = findViewById(R.id.alarmtext);
        EditText cont = findViewById(R.id.contenttext);
        save.setOnClickListener(v->{
            String alarmText = alarm.getText().toString();
            String contText = cont.getText().toString();
            if(!alarmText.isEmpty()&&!contText.isEmpty()){
                ((ListActivity)ListActivity.mCon).adapter.addItem(contText,alarmText);
                ((ListActivity)ListActivity.mCon).adapter.notifyDataSetChanged();
                finish();
            }
            else
                Toast.makeText(this, "모두 채워주세요", Toast.LENGTH_SHORT).show();
        });

        back.setOnClickListener(v->{
            finish();
        });
    }
}