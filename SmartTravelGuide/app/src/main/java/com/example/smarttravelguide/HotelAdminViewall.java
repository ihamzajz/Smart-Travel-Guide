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

public class HotelAdminViewall extends AppCompatActivity {
    RecyclerView hotelVaAdminRv;

    HotelAdminAdapter adapterHotelAdmin;
    Button bringHotels;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_admin_viewall);

        bringHotels = findViewById(R.id.bringaddhotel);
        bringHotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),HotelAdd.class);
                startActivity(intent);
            }
        });


        hotelVaAdminRv = findViewById(R.id.hotel_va_rv_admin);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        hotelVaAdminRv.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        //Popular Cities
        FirebaseRecyclerOptions<HotelDataHolder> optionshoteladmin =
                new FirebaseRecyclerOptions.Builder<HotelDataHolder>()
                        .setQuery(FirebaseDatabase.getInstance()
                                .getReference()
                                .child("hotels"), HotelDataHolder.class)
                        .build();

        adapterHotelAdmin = new HotelAdminAdapter(optionshoteladmin);
        hotelVaAdminRv.setAdapter(adapterHotelAdmin);
    }//oncreate
    @Override
    public void onStart() {
        super.onStart();
        adapterHotelAdmin.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterHotelAdmin.stopListening();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),AdminLayout.class);
        startActivity(intent);
    }
}