package com.example.smarttravelguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class CategoriesAdminViewall extends AppCompatActivity {

    RecyclerView catAdminVaRv;
    CategoriesAdminViewallAdapter adapterCvaAdmin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_admin_viewall);

        catAdminVaRv = findViewById(R.id.cat_va_rv_admin);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        catAdminVaRv.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        //Categories
        FirebaseRecyclerOptions<CategoriesDataHolder> optionsc =
                new FirebaseRecyclerOptions.Builder<CategoriesDataHolder>()
                        .setQuery(FirebaseDatabase.getInstance()
                                .getReference()
                                .child("categories"), CategoriesDataHolder.class)
                        .build();

        adapterCvaAdmin = new CategoriesAdminViewallAdapter(optionsc);
        catAdminVaRv.setAdapter(adapterCvaAdmin);
    }

    public void onStart() {
        super.onStart();
        adapterCvaAdmin.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterCvaAdmin.stopListening();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),AdminLayout.class);
        startActivity(intent);
    }
}