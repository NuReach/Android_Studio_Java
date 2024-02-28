package com.example.updatetoserver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    public final String url = "http://198.169.12.130/androidApi/savaData.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText etTitle = findViewById(R.id.etTitle);
        EditText etBody = findViewById(R.id.etBody);
        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String body = etBody.getText().toString();

            HashMap<String,String> postData = new HashMap<>();
            postData.put("title",title);
            postData.put("body",body);
            SaveData saveData = new SaveData(MainActivity.this,postData);
            saveData.execute(url);
        });
    }
}