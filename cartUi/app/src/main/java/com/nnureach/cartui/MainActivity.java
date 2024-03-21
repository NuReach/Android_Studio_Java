package com.nnureach.cartui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.nnureach.cartui.adapter.CardAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private List<String> dataList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataList.add("Item 1");
        dataList.add("Item 2");
        dataList.add("Item 3");
        Log.d("data size", String.valueOf(dataList.size()));
        adapter = new CardAdapter(this, dataList);
        recyclerView.setAdapter(adapter);

    }
}