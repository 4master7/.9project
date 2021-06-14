package com.engword.edcan_alarmproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

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
        /* 'listview_custom' Layout을 inflate하여 convertView 참조 획득 */
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_custom, parent, false);
        }

        /* 'listview_custom'에 정의된 위젯에 대한 참조 획득 */
        Button tv_contents =  convertView.findViewById(R.id.tv_contents);

        /* 각 리스트에 뿌려줄 아이템을 받아오는데 mMyItem 재활용 */
        final ListItem myItem = getItem(position);

        /* 각 위젯에 세팅된 아이템을 뿌려준다 */
        tv_contents.setText("   "+myItem.getContents());

        tv_contents.setOnClickListener(v -> {
            Intent intent = new Intent(ListActivity.mCon, ShowActivity.class);
            intent.putExtra("content",myItem.getContents());
            intent.putExtra("time",myItem.getTimes());
            ListActivity.mCon.startActivity(intent);
        });
//
//        DelBtn.setOnClickListener(v -> {
//            ListValues OnDel = new ListValues();
//            OnDel.onDelete(position);
//        });

        return convertView;
    }

//    public interface onItemDelete{
//        void onDelete(int Position);
//    }
//    public interface onItemComplete{
//        void onComplete(boolean isD, int Position);
//    }

    /* 아이템 데이터 추가를 위한 함수.*/
    public void addItem(String contents, String times) {

        ListItem mItem = new ListItem();

        /* MyItem에 아이템을 setting한다. */
        mItem.setContents(contents);
        mItem.setTimes(times);

        /* mItems에 MyItem을 추가한다. */
        mItems.add(mItem);

    }

    public void deleteItem(int pos) {
        mItems.remove(pos);
    }
}
