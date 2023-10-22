package com.example.smarttravelguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Malls extends AppCompatActivity {
    RecyclerView mallVaRv;
    MallAdapter adaptermall;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),UserLayout.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_malls);

        mallVaRv = findViewById(R.id.mall_va_rv);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        mallVaRv.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        //Popular Cities
        FirebaseRecyclerOptions<MallsDataHolder> optionsmall =
                new FirebaseRecyclerOptions.Builder<MallsDataHolder>()
                        .setQuery(FirebaseDatabase.getInstance()
                                .getReference()
                                .child("malls"), MallsDataHolder.class)
                        .build();

        adaptermall = new MallAdapter(optionsmall);
        mallVaRv.setAdapter(adaptermall);

    }//oncreate
    @Override
    public void onStart() {
        super.onStart();
        adaptermall.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adaptermall.stopListening();
    }
}