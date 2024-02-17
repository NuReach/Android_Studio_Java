package com.example.week2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText inputTempText;
    Button convertButton;
    TextView convertedText;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputTempText= (EditText) findViewById(R.id.input);
        convertButton= (Button) findViewById(R.id.btn);
        convertedText=(TextView) findViewById(R.id.result);
        //register and handle event click listener
        convertButton.setOnClickListener(v -> {
            String strFh=convertToFh(inputTempText.getText().toString());
            convertedText.setText(strFh+"F");
        });

        if (savedInstanceState != null) {
            String state = savedInstanceState.getString("strFh");
            String strFh=convertToFh(state);
            convertedText.setText(strFh+"F");
        }
    }

    @Override
    public void onSaveInstanceState( Bundle outState ) {
        inputTempText= (EditText) findViewById(R.id.input);
        String nResult = inputTempText.getText().toString();
        outState.putString("strFh",nResult);
        super.onSaveInstanceState(outState);
    }

    @SuppressLint("DefaultLocale")
    private String convertToFh(String p){
        try {
            double c= Double.parseDouble(p);
            double f=c * (9.0 * 5.0) + 32.0;
            return String.format("%3.2f", f);
        }catch (NumberFormatException nfe){
            return "Err";
        }
    }
}