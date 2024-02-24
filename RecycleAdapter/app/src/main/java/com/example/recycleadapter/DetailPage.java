package com.example.recycleadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);
        Location item = (Location) getIntent().getSerializableExtra("item");
        TextView textViewDetail = findViewById(R.id.titleDetail);
        ImageView img = findViewById(R.id.imgDetail);
        textViewDetail.setText(item.title);
        img.setImageResource(item.img);
        Toast.makeText(DetailPage.this,item.title,Toast.LENGTH_LONG).show();
    }
}