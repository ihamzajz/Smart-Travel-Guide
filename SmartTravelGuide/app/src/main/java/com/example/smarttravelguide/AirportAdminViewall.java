package com.example.smarttravelguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class AirportAdminViewall extends AppCompatActivity {

    RecyclerView airportVaAdminRv;

    AirportAdminAdapter adapterAirportAdmin;
    Button bringMalls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport_admin_viewall);

        bringMalls = findViewById(R.id.bringaddairport);
        bringMalls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AirportAdd.class);
                startActivity(intent);
            }
        });


        airportVaAdminRv = findViewById(R.id.airport_va_rv_admin);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        airportVaAdminRv.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        //Popular Cities
        FirebaseRecyclerOptions<AirportDataHolder> optionsairportadmin =
                new FirebaseRecyclerOptions.Builder<AirportDataHolder>()
                        .setQuery(FirebaseDatabase.getInstance()
                                .getReference()
                                .child("airports"), AirportDataHolder.class)
                        .build();

        adapterAirportAdmin = new AirportAdminAdapter(optionsairportadmin);
        airportVaAdminRv.setAdapter(adapterAirportAdmin);

    }//oncreate
    @Override
    public void onStart() {
        super.onStart();
        adapterAirportAdmin.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterAirportAdmin.stopListening();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),AdminLayout.class);
        startActivity(intent);
    }
}