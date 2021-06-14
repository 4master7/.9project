package com.engword.edcan_alarmproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    public static Context mCon;
    ListAdapter adapter = new ListAdapter();
    private ListView mListView;
    int a=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mCon = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mListView = findViewById(R.id.listView);
        mListView.setAdapter(adapter);
        findViewById(R.id.feb_list_main).setOnClickListener(this::pressAddBtn);
    }

    public void pressAddBtn(View v) {
        adapter.addItem(String.valueOf(a),String.valueOf(a++));
        adapter.notifyDataSetChanged();
    }
}