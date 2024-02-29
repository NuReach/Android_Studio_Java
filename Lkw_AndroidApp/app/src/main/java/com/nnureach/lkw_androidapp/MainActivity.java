package com.nnureach.lkw_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etPassword = findViewById(R.id.etPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegister);
        TextView tvForgetPassword = findViewById(R.id.tvFpw);


        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(getApplicationContext(),"All Fields are required",Toast.LENGTH_LONG).show();
            }else if ( password.length() <7 ){
                Toast.makeText(getApplicationContext(),"Password should be greater than 7 digits",Toast.LENGTH_LONG).show();
            }else {
                Log.d("login",email);
            }
        });

        btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Signup.class);
            startActivity(intent);
        });

        tvForgetPassword.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ForgetPassword.class);
            startActivity(intent);
        });
    }
}