package com.engword.edcan_alarmproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class ShowActivity extends AppCompatActivity {
    private AlarmManager alarmMgr;
    private PendingIntent pendingIntent;
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
            createNotificationChannel();
            alarmBroadcastReceiver(picker.getHour(),picker.getMinute());
            finish();
        });
        back.setOnClickListener(v->{
            finish();
        });
    }
    public void alarmBroadcastReceiver(int h,int m) {
        Intent alarmBroadcastReceiverintent = new Intent(this, AlarmBroadcastReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmBroadcastReceiverintent, 0);

        alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, h);
        calendar.set(Calendar.MINUTE, m);

        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 86400000, pendingIntent);
    }

    public void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "알림";
            String description = "Oreo Version 이상을 위한 알림";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel("channel_id", name, importance);
            notificationChannel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}