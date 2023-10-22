package com.example.smarttravelguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class PopularCitiesAdminViewall extends AppCompatActivity {


    RecyclerView popularCityVaAdminRv;
    PopularCitiesAdminViewaalAdapter adapterava;
    Button bringAddPCity;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_cities_admin_viewall);


        bringAddPCity = findViewById(R.id.bringaddpcity);
        bringAddPCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PopularCitiesAdd.class);
                startActivity(intent);
            }
        });

        popularCityVaAdminRv = findViewById(R.id.pcity_va_rv_admin);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        popularCityVaAdminRv.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        //Popular Cities
        FirebaseRecyclerOptions<PopularCitiesDataHolder> options =
                new FirebaseRecyclerOptions.Builder<PopularCitiesDataHolder>()
                        .setQuery(FirebaseDatabase.getInstance()
                                .getReference()
                                .child("popular_cities"), PopularCitiesDataHolder.class)
                        .build();

        adapterava = new PopularCitiesAdminViewaalAdapter(options);
        popularCityVaAdminRv.setAdapter(adapterava);
    }//oncreate

    @Override
    public void onStart() {
        super.onStart();
        adapterava.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterava.stopListening();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),AdminLayout.class);
        startActivity(intent);
    }

}
