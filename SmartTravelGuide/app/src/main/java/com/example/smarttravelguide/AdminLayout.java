package com.example.smarttravelguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.AdapterView;

import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class AdminLayout extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    AdminHomeFragment adminHomeFragment = new AdminHomeFragment();
    AdminProfileFragment adminProfileFragment = new AdminProfileFragment();
    AdminInboxFragment adminInboxFragment = new AdminInboxFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_layout);

        bottomNavigationView = findViewById(R.id.admin_bot_nav);

        getSupportFragmentManager().beginTransaction().replace(R.id.adminframeLayout,adminHomeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.adminhome:
                        getSupportFragmentManager().beginTransaction().replace(R.id.adminframeLayout,adminHomeFragment).commit();
                        return true;
                    case R.id.adminprofile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.adminframeLayout,adminProfileFragment).commit();
                        return true;
                    case R.id.admininbox:
                        getSupportFragmentManager().beginTransaction().replace(R.id.adminframeLayout,adminInboxFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }
}