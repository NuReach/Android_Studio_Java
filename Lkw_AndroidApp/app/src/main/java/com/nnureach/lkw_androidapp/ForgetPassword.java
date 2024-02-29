package com.nnureach.lkw_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        EditText etRecoverEmail = findViewById(R.id.etRecoverEmail);
        Button btnRecoverEmail = findViewById(R.id.btnRecover);

        btnRecoverEmail.setOnClickListener(v -> {
            String email = etRecoverEmail.getText().toString();
            if (email.isEmpty()){
                Toast.makeText(getApplicationContext(),"Enter you email",Toast.LENGTH_LONG).show();
            }
            Log.d("Recover email",email);
        });

    }
}