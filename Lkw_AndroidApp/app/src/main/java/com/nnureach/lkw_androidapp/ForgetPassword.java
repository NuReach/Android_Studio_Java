package com.nnureach.lkw_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        EditText etRecoverEmail = findViewById(R.id.etRecoverEmail);
        Button btnRecoverEmail = findViewById(R.id.btnRecover);
        firebaseAuth = FirebaseAuth.getInstance();
        btnRecoverEmail.setOnClickListener(v -> {
            String email = etRecoverEmail.getText().toString();
            if (email.isEmpty()){
                Toast.makeText(getApplicationContext(),"Enter you email",Toast.LENGTH_LONG).show();
            }else {
                firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        Toast.makeText(this, "Email sent , please check your inbox to reset password", Toast.LENGTH_SHORT).show();
                        finish();;
                        startActivity(new Intent(this,MainActivity.class));
                    }else {
                        Toast.makeText(this, "Your email is wrong", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });

    }
}