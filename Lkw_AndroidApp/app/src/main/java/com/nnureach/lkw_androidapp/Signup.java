package com.nnureach.lkw_androidapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signup extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText etRegisterEmail = findViewById(R.id.etRegisterEmail);
        EditText etRegisterPassword = findViewById(R.id.etRegisterPassword);
        Button btnSignup = findViewById(R.id.btnSignup);
        Button btnGoLogin = findViewById(R.id.btnGoLogin);
         firebaseAuth = FirebaseAuth.getInstance();


        btnSignup.setOnClickListener(v -> {
            String email = etRegisterEmail.getText().toString().trim();
            String password = etRegisterPassword.getText().toString().trim();
            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(getApplicationContext(),"All Fields are required",Toast.LENGTH_LONG).show();
            }else if ( password.length() <7 ){
                Toast.makeText(getApplicationContext(),"Password should be greater than 7 digits",Toast.LENGTH_LONG).show();
            }else {
                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                    if ( task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Registration Successfully",Toast.LENGTH_LONG).show();
                        sendEmailVerification();
                    }else {
                        Toast.makeText(this, "Failed to Register", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        btnGoLogin.setOnClickListener(v -> {
            Intent intent = new Intent(Signup.this,MainActivity.class);
            startActivity(intent);
        });


    }

    private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(task -> {
                Toast.makeText(getApplicationContext(), "Verification email is sent, Verify and Log in again", Toast.LENGTH_LONG).show();
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(Signup.this, MainActivity.class));
            });
        }else {
            Toast.makeText(this, "Failed to send verification email", Toast.LENGTH_SHORT).show();
        }
    }
}

