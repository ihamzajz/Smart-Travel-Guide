package com.example.smarttravelguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class CategoriesUserViewall extends AppCompatActivity {
    RecyclerView catVaRv;
    CategoriesUserViewallAdapter adapterCva;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_user_viewall);

        catVaRv = findViewById(R.id.cat_va_rv);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        catVaRv.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        //Categories
        FirebaseRecyclerOptions<CategoriesDataHolder> optionsc =
                new FirebaseRecyclerOptions.Builder<CategoriesDataHolder>()
                        .setQuery(FirebaseDatabase.getInstance()
                                .getReference()
                                .child("categories"), CategoriesDataHolder.class)
                        .build();

        adapterCva = new CategoriesUserViewallAdapter(optionsc);
        catVaRv.setAdapter(adapterCva);
    }
    public void onStart() {
        super.onStart();
        adapterCva.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterCva.stopListening();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),UserLayout.class);
        startActivity(intent);
    }
}