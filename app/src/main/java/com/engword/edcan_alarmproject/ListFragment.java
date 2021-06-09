package com.engword.edcan_alarmproject;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class ListFragment extends Fragment {

    private ListView mListView;
    //private FragmentListMainBinding binding;
    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_list, null);


       // binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list,container, false);
        mListView = v.findViewById(R.id.listView);
        //v.findViewById(R.id.feb_list_main).setOnClickListener(this::pressAddBtn);

        return v;
    }
}