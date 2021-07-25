package com.engword.edcan_alarmproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

import static com.engword.edcan_alarmproject.ListActivity.mCon;

public class ListAdapter extends BaseAdapter {

    private ArrayList<ListItem> mItems = new ArrayList<>();
    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public ListItem getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        Context context = parent.getContext();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_custom, parent, false);
        }
        ConstraintLayout bg = convertView.findViewById(R.id.listBG);
        TextView OzenOhu =  convertView.findViewById(R.id.dayornight);
        TextView timeText =  convertView.findViewById(R.id.timeTx);
        Button del = convertView.findViewById(R.id.del);

        final ListItem myItem = getItem(position);

        int h = Integer.parseInt(myItem.getTimes().split(":")[0]);
        int m = Integer.parseInt(myItem.getTimes().split(":")[1]);
        if(h>=0&&h<=11)
            OzenOhu.setText("오전");
        else {
            OzenOhu.setText("오후");
            h-=12;
        }
        if(h==0)
            h=12;
        String a = h+":";
        if(m<10)
            a+="0"+m;
        else
            a+=Integer.toString(m);
        timeText.setText(a);

        bg.setOnClickListener(v -> {
            Intent intent = new Intent(mCon, ShowActivity.class);
            intent.putExtra("content",myItem.getContents());
            intent.putExtra("time",myItem.getTimes());
            intent.putExtra("pos",position);
            mCon.startActivity(intent);
        });
        del.setOnClickListener(v -> {
            deleteItem(position);
            ((ListActivity)ListActivity.mCon).adapter.notifyDataSetChanged();
        });
        return convertView;
    }
    public void addItem_notPut(String contents, String times) {
        ListItem mItem = new ListItem();
        mItem.setContents(contents);
        mItem.setTimes(times);
        mItems.add(mItem);
    }
    public void addItem(String contents, String times) {
        SharedPreferences pref = mCon.getSharedPreferences("ListValue", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("Cnt",pref.getInt("Cnt",0)+1); //아이템 개수
        editor.putString("Con"+pref.getInt("Cnt",0),contents);
        editor.putString("Time"+pref.getInt("Cnt",0),times);
        editor.apply();

        ListItem mItem = new ListItem();
        mItem.setContents(contents);
        mItem.setTimes(times);
        mItems.add(mItem);
    }
    public void deleteItem(int pos) {
        SharedPreferences pref = mCon.getSharedPreferences("ListValue", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        mItems.remove(pos);
        for(int i=pos; i<pref.getInt("Cnt",0)-1; i++) {
            editor.putString("Con"+i,pref.getString("Con"+(i+1),"Error"));
            editor.putString("Time"+i,pref.getString("Time"+(i+1),"Error"));
        }
        editor.putInt("Cnt",pref.getInt("Cnt",0)-1);
        editor.apply();
        Toast.makeText(mCon,"삭제하였습니다!", Toast.LENGTH_SHORT).show();
    }
    public void setmItem(String time, String cont, int position){
        ListItem tmp = new ListItem();
        tmp.setTimes(time);
        tmp.setContents(cont);
        mItems.set(position, tmp);
        SharedPreferences pref = mCon.getSharedPreferences("ListValue", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Con"+position,cont);
        editor.putString("Time"+position,time);
        editor.apply();
    }
}
