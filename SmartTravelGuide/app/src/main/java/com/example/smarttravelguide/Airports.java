package com.example.smarttravelguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Airports extends AppCompatActivity {
    RecyclerView airportVaRv;
    AirportAdapter adapterairport;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),UserLayout.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airports);
        airportVaRv = findViewById(R.id.airport_va_rv);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        airportVaRv.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        //Popular Cities
        FirebaseRecyclerOptions<AirportDataHolder> optionsairport =
                new FirebaseRecyclerOptions.Builder<AirportDataHolder>()
                        .setQuery(FirebaseDatabase.getInstance()
                                .getReference()
                                .child("airports"), AirportDataHolder.class)
                        .build();

        adapterairport = new AirportAdapter(optionsairport);
        airportVaRv.setAdapter(adapterairport);

    }//oncreate
    @Override
    public void onStart() {
        super.onStart();
        adapterairport.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterairport.stopListening();
    }
}