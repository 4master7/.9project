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
import android.widget.CheckBox;
import android.widget.Toast;

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
        Button tv_contents =  convertView.findViewById(R.id.tv_contents);
        Button del = convertView.findViewById(R.id.del);

        final ListItem myItem = getItem(position);

        tv_contents.setText("   "+myItem.getContents());

        tv_contents.setOnClickListener(v -> {
            Intent intent = new Intent(mCon, ShowActivity.class);
            intent.putExtra("content",myItem.getContents());
            intent.putExtra("time",myItem.getTimes());
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
}
