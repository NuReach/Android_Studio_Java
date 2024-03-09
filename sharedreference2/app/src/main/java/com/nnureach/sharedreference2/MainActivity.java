package com.nnureach.sharedreference2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "LoginPrefs";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         * Check if we successfully logged in before.
         * If we did, redirect to home page
         */
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        if (settings.getString("logged", "").equals("logged")) {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        }

        Button b = (Button) findViewById(R.id.loginbutton);
        b.setOnClickListener(v -> {
            EditText username = (EditText) findViewById(R.id.login);
            EditText password = (EditText) findViewById(R.id.password);


            if(username.getText().toString().equals("harshid") && password.getText().toString().equals("harshid")) {
                //make SharedPreferences object
                SharedPreferences settings1 = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings1.edit();
                editor.putString("username_key", username.getText().toString());
                editor.putString("password_key", password.getText().toString());
                editor.putString("logged", "logged");
                editor.apply();
                Toast.makeText(getApplicationContext(), "Successfull Login", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }

        });
    }
}