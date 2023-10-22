package com.example.smarttravelguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hbb20.CountryCodePicker;

public class Login extends AppCompatActivity {

    TextInputLayout loginEmail, loginPassword;

    Button createAccount;
    MaterialButton loginBtn;
    FirebaseAuth mAuth;
    //FirebaseUser mUser;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_login);

        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        loginBtn = findViewById(R.id.login_btn);
        createAccount = findViewById(R.id.sendtosignup);




        mAuth = FirebaseAuth.getInstance();
        //mUser = mAuth.getCurrentUser();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();

            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
            }
        });

    }

    private void performLogin() {

        if (!validateEmail() | !validatePassword()) {
            return;
        }
        String email = loginEmail.getEditText().getText().toString();
        String password = loginPassword.getEditText().getText().toString();

        mAuth = FirebaseAuth.getInstance();
        if (email.equals("admin@gmail.com")&&password.equals("admin12345")){
            Intent intent = new Intent(getApplicationContext(),AdminLayout.class);
            startActivity(intent);
            finish();
        }else{


            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(Login.this,new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(getApplicationContext(), UserLayout.class);
                        Toast.makeText(Login.this, "Login Successfull", Toast.LENGTH_SHORT).show();


//                    intent.putExtra("email",mAuth.getCurrentUser().getEmail());
//                    intent.putExtra("uid",mAuth.getCurrentUser().getUid());

                        startActivity(intent);
                        finish();

                    } else {
                        loginEmail.getEditText().setText("");
                        loginPassword.getEditText().setText("");
                        Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }}


    public boolean validateEmail() {
        String val = loginEmail.getEditText().getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            loginEmail.setError("Field cannot be empty");
            return false;
        }
//        else if (!val.matches(emailPattern)) {
//            loginEmail.setError("Invalid email address");
//            return false;
//        }
        else {
            loginEmail.setError(null);
            loginEmail.setErrorEnabled(false);
            return true;
        }

    }
    public boolean validatePassword() {
        String val = loginPassword.getEditText().getText().toString().trim();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            loginPassword.setError("Field cannot be empty");
            return false;
        }
//        else if (val.length() < 4) {
//            loginPassword.setError("Password should contain 4 characters!");
//            return false;
//        }
//        else if (!val.matches(passwordVal)) {
//            loginPassword.setError("Password is too weak");
//            return false;
//        }
        else {
            loginPassword.setError(null);
            loginPassword.setErrorEnabled(false);
            return true;
        }

    }




}