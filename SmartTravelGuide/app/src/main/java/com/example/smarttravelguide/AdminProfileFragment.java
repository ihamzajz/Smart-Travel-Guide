package com.example.smarttravelguide;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class AdminProfileFragment extends Fragment {

//    FirebaseUser user;
//    DatabaseReference reference;
//    String adminId;
//
//    TextInputLayout fullname_tv,username_tv,email_tv,password_tv,phonono_tv,age_tv,gender_tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_admin_profile, container, false);

//        user = FirebaseAuth.getInstance().getCurrentUser();
//        reference = FirebaseDatabase.getInstance().getReference("Users");
//        adminId = user.getUid();
//
//        fullname_tv = v.findViewById(R.id.aprofile_fullname);
//        username_tv = v.findViewById(R.id.aprofile_username);
//        email_tv = v.findViewById(R.id.aprofile_email);
//        //password_tv = v.findViewById(R.id.uprofile_password);
//        phonono_tv = v.findViewById(R.id.aprofile_phoneno);
//        age_tv = v.findViewById(R.id.aprofile_age);
//        gender_tv = v.findViewById(R.id.aprofile_gender);

//        reference.child(adminId).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                UserDataHolder AdninProfile = snapshot.getValue(UserDataHolder.class);
//
//                if(AdninProfile != null){
//                    String fullname = AdninProfile.fullName;
//                    String username = AdninProfile.userName;
//                    String email = AdninProfile.Email;
//                    //String password = userProfile.;
//                    String phoneno = AdninProfile.Phone;
//                    String age = AdninProfile.Age;
//                    String gender = AdninProfile.Gender;
//
//                    fullname_tv.getEditText().setText(fullname);
//                    username_tv.getEditText().setText(username);
//                    email_tv.getEditText().setText(email);
//                    phonono_tv.getEditText().setText("0" +phoneno);
//                    age_tv.getEditText().setText(age);
//                    gender_tv.getEditText().setText(gender);
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getActivity().getApplicationContext(), "Something Went Wrong", Toast.LENGTH_LONG).show();
//            }
//        });
        return v;
    }
}