package com.example.smarttravelguide;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;


public class AdminHomeFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener{

    static final float END_SCALE = 0.7f;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView toggleBar;
    LinearLayout contentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =   inflater.inflate(R.layout.fragment_admin_home, container, false);


        drawerLayout = v.findViewById(R.id.drawer_layout_admin);
        navigationView = v.findViewById(R.id.navigation_view_admin);
        toggleBar = v.findViewById(R.id.toggle_bar_admin);
        contentView = v.findViewById(R.id.content);


        navigationDrawer();
        return v;
    }
    private void navigationDrawer() {
        //Navigation Drawer
        navigationView.bringToFront();
       navigationView.setNavigationItemSelectedListener(this);
       navigationView.setCheckedItem(R.id.adminnav_pcities);

        toggleBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerVisible(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else{
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
        animateNavigationDrawer1();
    }

    private void animateNavigationDrawer1() {
        drawerLayout.setScrimColor(getResources().getColor(R.color.colorPrimary));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener(){
            public void onDrawerSlide(View drawerView,float slideOffset){

                //Scale the view based on the current offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                //Translate the view, accounting for the scaled  width

                final float xoffset = drawerView.getWidth() * slideOffset;
                final float xoffsetdiff = contentView.getWidth() * diffScaledOffset /2;
                final float xTransiton = xoffset - xoffsetdiff;
                contentView.setTranslationX(xTransiton);

            };
        });
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId()){
            case R.id.adminnav_pcities:
//                Intent intent = new Intent(getApplicationContext(),AllCategoies.class);
//                startActivity(intent;
                startActivity(new Intent(getActivity().getApplicationContext(),PopularCitiesAdminViewall.class));
                break;
            case R.id.adminnav_tcities:
                startActivity(new Intent(getActivity().getApplicationContext(),TrendingCitiesAdminViewall.class));
                break;
            case R.id.adminnav_cat:
                startActivity(new Intent(getActivity().getApplicationContext(),CategoriesAdminViewall.class));
                break;
            case R.id.adminnav_malls:
                startActivity(new Intent(getActivity().getApplicationContext(),MallAdminViewall.class));
                break;
            case R.id.adminnav_airports:
                startActivity(new Intent(getActivity().getApplicationContext(),AirportAdminViewall.class));
                break;
            case R.id.adminnav_hotels:
                startActivity(new Intent(getActivity().getApplicationContext(),HotelAdminViewall.class));
                break;
            case R.id.adminnav_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity().getApplicationContext(),Login.class));
                Toast.makeText(getActivity().getApplicationContext(), "Logout Successful", Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }
}