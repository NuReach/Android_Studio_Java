package com.nnureach.lkw_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etPassword = findViewById(R.id.etPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegister);
        TextView tvForgetPassword = findViewById(R.id.tvFpw);
        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null){
            finish();
            startActivity(new Intent(this, Home.class));
        }

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(getApplicationContext(),"All Fields are required",Toast.LENGTH_LONG).show();
            }else if ( password.length() <7 ){
                Toast.makeText(getApplicationContext(),"Password should be greater than 7 digits",Toast.LENGTH_LONG).show();
            }else {
                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        checkEmailVerification();
                    }else {
                        Toast.makeText(getApplicationContext(),"Account does not exist",Toast.LENGTH_LONG).show();
                    }
                });
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


    private void checkEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        assert firebaseUser != null;
        if (firebaseUser.isEmailVerified()){
            Toast.makeText(getApplicationContext(),"Logged In",Toast.LENGTH_LONG).show();
            finish();
            startActivity(new Intent(this, Home.class));
        }else{
            Toast.makeText(getApplicationContext(),"Please verify your email",Toast.LENGTH_LONG).show();
            firebaseAuth.signOut();
        }
    }

}