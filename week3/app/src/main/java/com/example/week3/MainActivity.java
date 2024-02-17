package com.example.week3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public  void foodBtnClick ( View v) {
        showView( "Food", R.drawable.food);
    }
    public  void drinkBtnClick ( View v) {
        showView( "Drink", R.drawable.drink);
    }
    public  void VegtableBtnClick ( View v) {showView( "Vegtable", R.drawable.vegtable);}

    public void showView(String foodName, int drawableImage){
        Bundle dataBundle = new Bundle();
        dataBundle.putString("name",foodName);
        dataBundle.putInt("image",drawableImage);
        //use intent
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(),com.example.week3.FoodDetail.class);
        intent.putExtras(dataBundle);
        startActivity(intent);
    }

    public  void phoneBtn ( View v ) {
        Uri uri = Uri.parse("tel:078441752");
        Intent intent = new Intent(Intent.ACTION_DIAL,uri);
        startActivity(intent);
    }

    public void searchBtn ( View v ) {
        Uri uri = Uri.parse("https://www.limkokwing.net/");
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }
}