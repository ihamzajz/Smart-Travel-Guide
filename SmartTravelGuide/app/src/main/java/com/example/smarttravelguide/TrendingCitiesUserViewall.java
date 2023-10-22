package com.example.smarttravelguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class TrendingCitiesUserViewall extends AppCompatActivity {



    RecyclerView trendingCityVaRv;
    TrendingCitiesViewallAdapter adaptertva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending_cities_user_viewall);

        trendingCityVaRv = findViewById(R.id.tcity_va_rv);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        trendingCityVaRv.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        FirebaseRecyclerOptions<TrendingCitiesDataHolder> options2 =
                new FirebaseRecyclerOptions.Builder<TrendingCitiesDataHolder>()
                        .setQuery(FirebaseDatabase.getInstance()
                                .getReference()
                                .child("trending_cities"), TrendingCitiesDataHolder.class)
                        .build();

        adaptertva = new TrendingCitiesViewallAdapter(options2);
        trendingCityVaRv.setAdapter(adaptertva);


    }

    @Override
    public void onStart() {
        super.onStart();
        adaptertva.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adaptertva.stopListening();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),UserLayout.class);
        startActivity(intent);
    }
}