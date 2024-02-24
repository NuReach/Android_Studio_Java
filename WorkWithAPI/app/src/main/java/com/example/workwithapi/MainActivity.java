package com.example.workwithapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView list1 = findViewById(R.id.list);
        DownloadApi downloadApi = new DownloadApi(MainActivity.this, result -> {
            ItemAdapter itemAdapter = new ItemAdapter(MainActivity.this, result);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            list1.setLayoutManager(linearLayoutManager);
            list1.setAdapter(itemAdapter);
        });

        downloadApi.execute();
    }
}