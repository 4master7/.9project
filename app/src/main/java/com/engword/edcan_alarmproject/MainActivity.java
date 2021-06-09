package com.engword.edcan_alarmproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fm=getSupportFragmentManager();
    private ListFragment List_Fragment=new ListFragment();
    private ex ex=new ex();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FragmentTransaction transaction=fm.beginTransaction();
        transaction.replace(R.id.framlayout,List_Fragment).commitAllowingStateLoss();
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation_view);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.first:{
                    FragmentTransaction transaction1=fm.beginTransaction();
                    transaction1.replace(R.id.framlayout,List_Fragment).commitAllowingStateLoss();
                    break;
                }
                case R.id.second:{
                    FragmentTransaction transaction1=fm.beginTransaction();
                    transaction1.replace(R.id.framlayout,ex).commitAllowingStateLoss();
                    break;
                }
            }
            return true;
        });
    }
}