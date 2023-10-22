package com.example.smarttravelguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

import java.util.Calendar;

public class SignUp extends AppCompatActivity {


    //Variables For Form validation
    TextInputLayout fullName, userName, eMail, passWord, phoneNumber;
//    TextInputLayout eMail, passWord;
    RadioGroup radioGroup;
    RadioButton radioGenderMale,radioGenderFemale;
    DatePicker datePicker;
    CountryCodePicker countryCodePicker;
    Button signUpButton;

    String gender = "";


    DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    //FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retailer_sign_up);

        fullName = findViewById(R.id.signup_fullname);
        userName = findViewById(R.id.signup_username);
        eMail = findViewById(R.id.signup_email);
        passWord = findViewById(R.id.signup_password);
        datePicker = findViewById(R.id.age_picker);

//        radioGroup = findViewById(R.id.radiogroup);
        radioGenderMale = findViewById(R.id.male);
        radioGenderFemale = findViewById(R.id.female);



        phoneNumber = findViewById(R.id.signup_phone);
        countryCodePicker = findViewById(R.id.signup_ccp);
        signUpButton = findViewById(R.id.signup_btn);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        mAuth = FirebaseAuth.getInstance();




        //mUser = mAuth.getCurrentUser();

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                perfromAuth();
            }
        });
    }

    private void perfromAuth() {

        if (!validateFullName() | !validateUn()| !validateEmail() | !validatePassword()  | !validateAge() | !validatePhone()) {
            return;
        }


        String fullname = fullName.getEditText().getText().toString();
        String uername = userName.getEditText().getText().toString();
        String email = eMail.getEditText().getText().toString();
        String password = passWord.getEditText().getText().toString();

//        mAuth = FirebaseAuth.getInstance();

        String phoneno = phoneNumber.getEditText().getText().toString();
        String countrycodepicker = countryCodePicker.getTextView_selectedCountry().toString();

//        selectedGender = findViewById(radioGroup.getCheckedRadioButtonId());
//        String gender = selectedGender.getText().toString();

        if(radioGenderMale.isChecked()){
            gender = "Male";
        }
        if(radioGenderFemale.isChecked()){
            gender = "Female";
        }

        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();
        String date = day + "/" + month + "/" + year;


        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignUp.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                            UserDataHolder userInfo = new UserDataHolder(fullname, uername, email, gender,phoneno,date);
                             FirebaseDatabase.getInstance().getReference("Users")
                                     .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                     .setValue(userInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                                         @Override
                                         public void onComplete(@NonNull Task<Void> task) {
                                             Toast.makeText(SignUp.this, "Registration Completed!", Toast.LENGTH_LONG).show();
                                             startActivity(new Intent(getApplicationContext(),Login.class));
                                         }
                                     });
                } else {
                    Toast.makeText(SignUp.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }






    //validation functions

    public boolean validateUn() {
        String val = userName.getEditText().getText().toString().trim();
        //String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            userName.setError("Field cannot be empty");
            return false;
        }
        else {
            userName.setError(null);
            userName.setErrorEnabled(false);
            return true;
        }

    }
    public boolean validateFullName() {
        String val = fullName.getEditText().getText().toString().trim();
        if (val.isEmpty()) {
            fullName.setError("Field cannot be empty");
            return false;
        }else {
            fullName.setError(null);
            fullName.setErrorEnabled(false);
            return true;
        }

    }
    public boolean validateEmail() {
        String val = eMail.getEditText().getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            eMail.setError("Field cannot be empty");
            return false;
        }
        else if (!val.matches(emailPattern)) {
            eMail.setError("Invalid email address");
            return false;
        }
        else {
            eMail.setError(null);
            eMail.setErrorEnabled(false);
            return true;
        }

    }
    public boolean validatePassword() {
        String val = passWord.getEditText().getText().toString().trim();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                //".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            passWord.setError("Field cannot be empty");
            return false;
        }
        else if (val.length() < 4) {
            passWord.setError("Password should contain 4 characters!");
            return false;
        }
//        else if (!val.matches(passwordVal)) {
//            passWord.setError("must contain alphabet & number");
//            return false;
//        }
        else {
            passWord.setError(null);
            passWord.setErrorEnabled(false);
            return true;
        }

    }
    private boolean validateGender() {
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please Select Gender", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }

    }
    private boolean validateAge() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int userAge = datePicker.getYear();
        int isAgeValid = currentYear - userAge;

        if (isAgeValid < 14) {
            Toast.makeText(this, "You are not eligible to apply", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
    private boolean validatePhone() {

        String val = phoneNumber.getEditText().getText().toString().trim();
        if (val.isEmpty()) {
            phoneNumber.setError("Field cannot be empty");
            return false;
        } else {
            phoneNumber.setError(null);
            phoneNumber.setErrorEnabled(false);
            return true;
        }


    }
}