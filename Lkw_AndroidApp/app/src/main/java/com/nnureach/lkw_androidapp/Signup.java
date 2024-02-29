package com.nnureach.lkw_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText etRegisterEmail = findViewById(R.id.etRegisterEmail);
        EditText etRegisterPassword = findViewById(R.id.etRegisterPassword);
        Button btnSignup = findViewById(R.id.btnSignup);
        Button btnGoLogin = findViewById(R.id.btnGoLogin);

        btnSignup.setOnClickListener(v -> {
            String email = etRegisterEmail.getText().toString().trim();
            String password = etRegisterPassword.getText().toString().trim();
            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(getApplicationContext(),"All Fields are required",Toast.LENGTH_LONG).show();
            }else if ( password.length() <7 ){
                Toast.makeText(getApplicationContext(),"Password should be greater than 7 digits",Toast.LENGTH_LONG).show();
            }else {
                Log.d("register",email);
            }
        });

        btnGoLogin.setOnClickListener(v -> {
            Intent intent = new Intent(Signup.this,MainActivity.class);
            startActivity(intent);
        });


    }
}