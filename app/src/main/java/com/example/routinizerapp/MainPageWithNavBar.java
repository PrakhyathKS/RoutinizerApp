package com.example.routinizerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainPageWithNavBar extends AppCompatActivity {

    BottomNavigationView bnv;
    FragmentManager fragmentManager;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_with_nav_bar);
        bnv = findViewById(R.id.bottomNavBar);
        fragmentManager = getSupportFragmentManager();

        showFragment(new ForyouFragment());

        bnv.setOnItemSelectedListener(item -> {
            if(item.getItemId()==R.id.forYou) {
                showFragment(new ForyouFragment());
                return true;
            } else if (item.getItemId()==R.id.task) {
                showFragment(new MainPageFragment());
                return true;
            } else if (item.getItemId()==R.id.timerMenu) {
                showFragment(new First4Fragment());
                return true;
            } else if (item.getItemId()==R.id.logoutMenu) {
                showFragment(new Second4Fragment());
                return true;
            }
            return false;
        });
    }
    private void showFragment(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, fragment)
                .commit();
    }
}