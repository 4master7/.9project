package com.engword.edcan_alarmproject;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

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
        TextView tv_contents =  convertView.findViewById(R.id.tv_contents) ;
        ImageButton DelBtn =  convertView.findViewById(R.id.deletebtn) ;
        ImageButton ComBtn =  convertView.findViewById(R.id.completebtn) ;
        // LinearLayout BackGround =  convertView.findViewById(R.id.listBG);

        /* 각 리스트에 뿌려줄 아이템을 받아오는데 mMyItem 재활용 */
        final ListItem myItem = getItem(position);

        /* 각 위젯에 세팅된 아이템을 뿌려준다 */
        tv_contents.setText(myItem.getContents());

//        if(myItem.getIsDaily()) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                BackGround.setBackground(ContextCompat.getDrawable(context, R.drawable.listview_daily_layout));
//            } else {
//                BackGround.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.listview_daily_layout));
//            }
//        }
//        else {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                BackGround.setBackground(ContextCompat.getDrawable(context, R.drawable.listview_layout));
//            } else {
//                BackGround.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.listview_layout));
//            }
//        }


        /* (위젯에 대한 이벤트리스너)  */
//        ComBtn.setOnClickListener(v -> {
//            ListValues OnCom = new ListValues();
//            OnCom.onComplete(myItem.getIsDaily(),position);
//        });
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
