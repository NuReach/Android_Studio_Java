package com.nnureach.sharedreference2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "LoginPrefs";
    SharedPreferences settings;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        settings = getSharedPreferences(PREFS_NAME, 0);
        String username = settings.getString("username_key", null);
        TextView welcome = (TextView) findViewById(R.id.welcomeTextView);
        welcome.setText(username);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater Inflater = getMenuInflater();
        Inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            SharedPreferences.Editor editor = settings.edit();
            editor.remove("username_key");
            editor.remove("password_key");
            editor.remove("logged");
            //editor.clear();
            editor.apply();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}