package com.example.smarttravelguide;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class UserHomeFragment extends Fragment  implements NavigationView.OnNavigationItemSelectedListener{


    static final float END_SCALE = 0.7f;

    TextView sendtofeedback;

    //View All Variables
    TextView pcityViewAll,tcityViewAll,catViewAll;

    RelativeLayout malls,airport,hotel;


    //RecylerView Variables
    RecyclerView popularCityRv,trendingCityRv,categoryRv,feedbackRv;

    //RecyclerViewAdapters
    PopularCitiesAdapter adapter;
    TrendingCitiesAdapter adapterr;
    CategoriesAdapter adapterc;
    FeedbackAdapter adapterfb;

    //NavigationDrawer
    ImageView toggleBar;
    LinearLayout contentView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user_home, container, false);


        //RECYCLER VIEWS

        //Popular Cities RecylerView
        popularCityRv = v.findViewById(R.id.pcity_rv);
        popularCityRv.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));

        //Trending Cities RecylerView
        trendingCityRv = v.findViewById(R.id.tcity_rv);
        trendingCityRv.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));

        //Categories Cities RecylerView
        categoryRv = v.findViewById(R.id.cat_rv);
        categoryRv.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));


        //Feedback RecylerView
        feedbackRv = v.findViewById(R.id.fb_rv);
        feedbackRv.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));




        //Mall Airport Restaurants Caffe
        malls = v.findViewById(R.id.malls);
        airport = v.findViewById(R.id.airport);
        hotel = v.findViewById(R.id.hotel);

        malls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),Malls.class);
                startActivity(intent);
            }
        });

        airport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),Airports.class);
                startActivity(intent);
            }
        });

        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),Hotels.class);
                startActivity(intent);
            }
        });




        //View all Buttons
        pcityViewAll =  v.findViewById(R.id.pcity_viewall);
        tcityViewAll =  v.findViewById(R.id.tcity_viewall);
        catViewAll =  v.findViewById(R.id.cat_viewall);
        sendtofeedback =  v.findViewById(R.id.sendtofeedback);


        //Navigation Drawer
        toggleBar = v.findViewById(R.id.toggle_bar);
        contentView = v.findViewById(R.id.content);
        drawerLayout = v.findViewById(R.id.drawer_layout);
        navigationView = v.findViewById(R.id.navigation_view);



        //RECYCLER VIEW DATA GET

        //Popular Cities
        FirebaseRecyclerOptions<PopularCitiesDataHolder> options =
                new FirebaseRecyclerOptions.Builder<PopularCitiesDataHolder>()
                        .setQuery(FirebaseDatabase.getInstance()
                                .getReference()
                                .child("popular_cities"), PopularCitiesDataHolder.class)
                        .build();

        adapter = new PopularCitiesAdapter(options);
        popularCityRv.setAdapter(adapter);

        //Trending Cities
        FirebaseRecyclerOptions<TrendingCitiesDataHolder> options2 =
                new FirebaseRecyclerOptions.Builder<TrendingCitiesDataHolder>()
                        .setQuery(FirebaseDatabase.getInstance()
                                .getReference()
                                .child("trending_cities"), TrendingCitiesDataHolder.class)
                        .build();

        adapterr = new TrendingCitiesAdapter(options2);
        trendingCityRv.setAdapter(adapterr);

        //Categories
        FirebaseRecyclerOptions<CategoriesDataHolder> optionsc =
                new FirebaseRecyclerOptions.Builder<CategoriesDataHolder>()
                        .setQuery(FirebaseDatabase.getInstance()
                                .getReference()
                                .child("categories"), CategoriesDataHolder.class)
                        .build();

        adapterc = new CategoriesAdapter(optionsc);
        categoryRv.setAdapter(adapterc);

        //Feedback
        FirebaseRecyclerOptions<FeedbackHelperClass> optionsfb =
                new FirebaseRecyclerOptions.Builder<FeedbackHelperClass>()
                        .setQuery(FirebaseDatabase.getInstance()
                                .getReference()
                                .child("feedback"), FeedbackHelperClass.class)
                        .build();

        adapterfb = new FeedbackAdapter(optionsfb);
        feedbackRv.setAdapter(adapterfb);





        toggleBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerVisible(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else{
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        navigationDrawer();




        // SEND TO VIEW ALL PAGES

        //Popular Cities
        pcityViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),PopularCitiesUserViewall.class);
                startActivity(intent);
            }
        });
        //Trending Cities
        tcityViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),TrendingCitiesUserViewall.class);
                startActivity(intent);
            }
        });

        //Categories
        catViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),CategoriesUserViewall.class);
                startActivity(intent);
            }
        });

        sendtofeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),FeedbackAdd.class);
                startActivity(intent);
            }
        });

        return v;


    }


    //GET DATA FROM DATABASE
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
        adapterr.startListening();
        adapterc.startListening();
        adapterfb.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
        adapterr.stopListening();
        adapterc.stopListening();
        adapterfb.stopListening();
    }




    private void navigationDrawer() {

        //Navigation Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.usernav_pcities);

        toggleBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerVisible(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else{
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {
        drawerLayout.setScrimColor(getResources().getColor(R.color.colorPrimary));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener(){
            public void onDrawerSlide(View drawerView,float slideOffset){

                //Scale the view based on the current offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                //Translate the view, accounting for the scaled  width

                final float xoffset = drawerView.getWidth() * slideOffset;
                final float xoffsetdiff = contentView.getWidth() * diffScaledOffset /2;
                final float xTransiton = xoffset - xoffsetdiff;
                contentView.setTranslationX(xTransiton);

            };
        });
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId()){
            case R.id.usernav_pcities:
                startActivity(new Intent(getActivity().getApplicationContext(),PopularCitiesUserViewall.class));
                break;
            case R.id.usernav_tcities:
                startActivity(new Intent(getActivity().getApplicationContext(),TrendingCitiesUserViewall.class));
                break;

            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity().getApplicationContext(),Login.class));
                Toast.makeText(getActivity().getApplicationContext(), "Logout Successful", Toast.LENGTH_LONG).show();
                break;
            case R.id.usernav_cat:
                startActivity(new Intent(getActivity().getApplicationContext(),CategoriesUserViewall.class));
                break;
            case R.id.usernav_gethelp:
                Toast.makeText(getActivity().getApplicationContext(), "Police Helpline: 090078601", Toast.LENGTH_LONG).show();
                break;
            case R.id.usernav_trash:
                Toast.makeText(getActivity().getApplicationContext(), "Trash is empty", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }



}