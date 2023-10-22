package com.example.smarttravelguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class TrendingCitiesAdminViewall extends AppCompatActivity {

    RecyclerView trenindCityVaAdminRv;
    TrendingCitiesAdminAdapter adaptertcity;
    Button bringAddTCity;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending_cities_admin_viewall);

        bringAddTCity = findViewById(R.id.bringaddtcity);
        bringAddTCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),TrendingCitiesAdd.class);
                startActivity(intent);
            }
        });

        trenindCityVaAdminRv = findViewById(R.id.tcity_va_rv_admin);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        trenindCityVaAdminRv.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        FirebaseRecyclerOptions<TrendingCitiesDataHolder> optionstcityadmin =
                new FirebaseRecyclerOptions.Builder<TrendingCitiesDataHolder>()
                        .setQuery(FirebaseDatabase.getInstance()
                                .getReference()
                                .child("trending_cities"), TrendingCitiesDataHolder.class)
                        .build();

        adaptertcity = new TrendingCitiesAdminAdapter(optionstcityadmin);
        trenindCityVaAdminRv.setAdapter(adaptertcity);
    }//oncreate

    public void onStart() {
        super.onStart();
        adaptertcity.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adaptertcity.stopListening();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),AdminLayout.class);
        startActivity(intent);
    }
}