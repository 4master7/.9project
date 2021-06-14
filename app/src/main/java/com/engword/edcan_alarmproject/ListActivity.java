package com.engword.edcan_alarmproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ListAdapter adapter = new ListAdapter();
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mListView = findViewById(R.id.listView);
        mListView.setAdapter(adapter);
        findViewById(R.id.feb_list_main).setOnClickListener(this::pressAddBtn);
        // adapter.addItem("1","1");
        // adapter.addItem("2","2");
    }

    public void pressAddBtn(View v) {
        adapter.addItem("1","1");
        adapter.notifyDataSetChanged();
    }
}