package com.example.week4;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView textResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textResult = findViewById(R.id.result);
        textResult.setText("No Information");
    }
    public void getDetail(View view) {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(),com.example.week4.Table.class);
        getPersonResult.launch(intent);
    }


    ActivityResultLauncher<Intent> getPersonResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // Here, no request code
                    Intent data = result.getData();
                    ArrayList<Person> personData = data.getParcelableArrayListExtra("personData");
                    Person personObj = personData.get(0);
                    TextView detailTextView = (TextView) findViewById(R.id.result);
                    detailTextView.setText(personObj.toString());
                } });

    }

