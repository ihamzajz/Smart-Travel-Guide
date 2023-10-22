package com.example.smarttravelguide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class UserLayout extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    UserHomeFragment userHomeFragment = new UserHomeFragment();
    UserProfileFragment userProfileFragment = new UserProfileFragment();
    UserMapsFragment userMapsFragment = new UserMapsFragment();
    UserInboxFragment userInboxFragment = new UserInboxFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_layout);

        bottomNavigationView = findViewById(R.id.user_bot_nav);

        getSupportFragmentManager().beginTransaction().replace(R.id.userframeLayout,userHomeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.userhome:home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.userframeLayout,userHomeFragment).commit();
                        return true;
                    case R.id.userprofile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.userframeLayout,userProfileFragment).commit();
                        return true;
                    case R.id.usermap:
                        getSupportFragmentManager().beginTransaction().replace(R.id.userframeLayout,userMapsFragment).commit();
                        return true;
                    case R.id.userinbox:
                        getSupportFragmentManager().beginTransaction().replace(R.id.userframeLayout,userInboxFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }
}