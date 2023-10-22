package com.example.smarttravelguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Hotels extends AppCompatActivity {
    RecyclerView hotelVaRv;
    HotelAdapter adapterHotel;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),UserLayout.class);
        startActivity(intent);
    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);

        hotelVaRv = findViewById(R.id.hotel_va_rv);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        hotelVaRv.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        //Popular Cities
        FirebaseRecyclerOptions<HotelDataHolder> optionshotel =
                new FirebaseRecyclerOptions.Builder<HotelDataHolder>()
                        .setQuery(FirebaseDatabase.getInstance()
                                .getReference()
                                .child("hotels"), HotelDataHolder.class)
                        .build();

        adapterHotel = new HotelAdapter(optionshotel);
        hotelVaRv.setAdapter(adapterHotel);
    }//oncreate
    @Override
    public void onStart() {
        super.onStart();
        adapterHotel.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterHotel.stopListening();
    }
}