package com.nnureach.sharereference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    SharedPreferences prefs;
    TextView highScoreView;
    TextView randomScoreView;
    final String mypreference = "mypref";
    final String HIGH_SCORE_KEY = "highScoreKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Connecting to the views
        highScoreView = (TextView) findViewById(R.id.highScoreView);
        randomScoreView = (TextView) findViewById(R.id.randomView);

        //Initializing sharedpreference
        prefs = getSharedPreferences(mypreference, Context.MODE_PRIVATE);


        //Play button
        Button playButton = findViewById(R.id.play_button);
        playButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //random number from 1 to 100
                int min = 1;
                int max = 100;
                int random = new Random().nextInt((max - min) + 1) + min;
                randomScoreView.setText(String.valueOf(random));

                // Get Stored High Score
                if (random > prefs.getInt(HIGH_SCORE_KEY, -1)) {
                    // Get and edit high score
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt(HIGH_SCORE_KEY, random);
                    editor.apply();
                }
                prefs = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
                //get preference
                if (prefs.contains(HIGH_SCORE_KEY)) {
                    highScoreView.setText(String.valueOf(prefs.getInt(HIGH_SCORE_KEY,-1)));
                }
            }
        });


        Button resetButton = findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Set high score to 0
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt(HIGH_SCORE_KEY, 0);
                editor.apply();

                //get preference
                prefs = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
                if (prefs.contains(HIGH_SCORE_KEY)) {
                    highScoreView.setText(String.valueOf(prefs.getInt(HIGH_SCORE_KEY,-1)));
                }
            }
        });
    }
}