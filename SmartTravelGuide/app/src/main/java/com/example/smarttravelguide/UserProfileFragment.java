package com.example.smarttravelguide;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;


public class UserProfileFragment extends Fragment {

    FirebaseUser user;
    DatabaseReference reference;
    String userId;

    TextInputLayout fullname_tv,username_tv,email_tv,password_tv,phonono_tv,age_tv,gender_tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user_profile, container, false);



        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userId = user.getUid();

        fullname_tv = v.findViewById(R.id.uprofile_fullname);
        username_tv = v.findViewById(R.id.uprofile_username);
        email_tv = v.findViewById(R.id.uprofile_email);
        //password_tv = v.findViewById(R.id.uprofile_password);
        phonono_tv = v.findViewById(R.id.uprofile_phoneno);
        age_tv = v.findViewById(R.id.uprofile_age);
        gender_tv = v.findViewById(R.id.uprofile_gender);

        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserDataHolder userProfile = snapshot.getValue(UserDataHolder.class);

                if(userProfile != null){
                    String fullname = userProfile.fullName;
                    String username = userProfile.userName;
                    String email = userProfile.Email;
                    //String password = userProfile.;
                    String phoneno = userProfile.Phone;
                    String age = userProfile.Age;
                    String gender = userProfile.Gender;

                    fullname_tv.getEditText().setText(fullname);
                    username_tv.getEditText().setText(username);
                    email_tv.getEditText().setText(email);
                    phonono_tv.getEditText().setText("0" +phoneno);
                    age_tv.getEditText().setText(age);
                    gender_tv.getEditText().setText(gender);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity().getApplicationContext(), "Something Went Wrong", Toast.LENGTH_LONG).show();
            }
        });
        return v;
    }


}