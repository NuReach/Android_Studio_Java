package com.example.week3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        Bundle dataBundle = getIntent().getExtras();
        String foodName = dataBundle.getString("name");
        Drawable image = getResources().getDrawable(dataBundle.getInt("image"));

        TextView textFoodView = (TextView) findViewById(R.id.textId2);
        ImageView imageFoodView = (ImageView) findViewById(R.id.imageId2);


        textFoodView.setText(foodName);
        imageFoodView.setImageDrawable(image);
    }



}