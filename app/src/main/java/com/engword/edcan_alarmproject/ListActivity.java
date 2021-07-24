package com.engword.edcan_alarmproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;


public class ListActivity extends AppCompatActivity {
    public static Context mCon;
    ListAdapter adapter = new ListAdapter();
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mCon = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mListView = findViewById(R.id.listView);
        mListView.setAdapter(adapter);

        SharedPreferences pref = getSharedPreferences("ListValue" , Activity.MODE_PRIVATE);
        int cnt = pref.getInt("Cnt",0);
        if(cnt!=0){ //아이템이 있는경우만
            for(int i=0; i<cnt; i++){
                String contText = pref.getString("Con"+i,"ERROR"+i);
                String alarmText = pref.getString("Time"+i,"ERROR");
                adapter.addItem_notPut(contText,alarmText);
            }
            adapter.notifyDataSetChanged();
        }
        findViewById(R.id.feb_list_main).setOnClickListener(this::pressAddBtn);
    }

    public void pressAddBtn(View v) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }
}