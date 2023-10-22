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

public class MallAdminViewall extends AppCompatActivity {

    RecyclerView mallsVaAdminRv;
    MallAdminAdapter adapterMallAdmin;
    Button bringMalls;
    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_admin_viewall);

        bringMalls = findViewById(R.id.bringaddmall);
        bringMalls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MallAdd.class);
                startActivity(intent);
            }
        });


        mallsVaAdminRv = findViewById(R.id.mall_va_rv_admin);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        mallsVaAdminRv.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        //Popular Cities
        FirebaseRecyclerOptions<MallsDataHolder> optionsmalladmin =
                new FirebaseRecyclerOptions.Builder<MallsDataHolder>()
                        .setQuery(FirebaseDatabase.getInstance()
                                .getReference()
                                .child("malls"), MallsDataHolder.class)
                        .build();

        adapterMallAdmin = new MallAdminAdapter(optionsmalladmin);
        mallsVaAdminRv.setAdapter(adapterMallAdmin);

    }//oncreate

    @Override
    public void onStart() {
        super.onStart();
        adapterMallAdmin.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterMallAdmin.stopListening();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),AdminLayout.class);
        startActivity(intent);
    }
}