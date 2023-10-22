package com.example.smarttravelguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PopularCitiesUserViewall extends AppCompatActivity {

    RecyclerView popularCityVaRv;
    PopularCitiesViewallAdapter adapterva;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),UserLayout.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_cities_user_viewall);

        popularCityVaRv = findViewById(R.id.pcity_va_rv);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        popularCityVaRv.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        //Popular Cities
        FirebaseRecyclerOptions<PopularCitiesDataHolder> options =
                new FirebaseRecyclerOptions.Builder<PopularCitiesDataHolder>()
                        .setQuery(FirebaseDatabase.getInstance()
                                .getReference()
                                .child("popular_cities"), PopularCitiesDataHolder.class)
                        .build();

        adapterva = new PopularCitiesViewallAdapter(options);
        popularCityVaRv.setAdapter(adapterva);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapterva.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterva.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pcity_search,menu);

        MenuItem item = menu.findItem(R.id.pcity_searchid);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processSearch(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void processSearch(String s){
        FirebaseRecyclerOptions<PopularCitiesDataHolder> options =
                new FirebaseRecyclerOptions.Builder<PopularCitiesDataHolder>()
                        .setQuery(FirebaseDatabase.getInstance()
                                .getReference()
                                .child("popular_cities")
                                .orderByChild("name")
                                .startAt(s)
                                .endAt(s+"\uf8ff"), PopularCitiesDataHolder.class)
                                 .build();
        adapterva = new PopularCitiesViewallAdapter(options);
        adapterva.startListening();
        popularCityVaRv.setAdapter(adapterva);
    }





}

