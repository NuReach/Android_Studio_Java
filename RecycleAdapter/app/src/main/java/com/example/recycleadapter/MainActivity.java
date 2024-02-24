package com.example.recycleadapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView list = findViewById(R.id.list);
        ItemAdapter itemAdapter = new ItemAdapter(MainActivity.this,locations());
        list.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(linearLayoutManager);
        list.setAdapter(itemAdapter);

    }

    List<Location> locations(){
        List<Location> list = new ArrayList<>();
        list.add(new Location("Phnom Penh","This is 123456", R.drawable.img));
        list.add(new Location("Phnom Penh1","This is 123456", R.drawable.img));
        list.add(new Location("Phnom Penh2","This is 123456", R.drawable.img));
        list.add(new Location("Phnom Penh3","This is 123456", R.drawable.img));
        list.add(new Location("Phnom Penh4","This is 123456", R.drawable.img));
        return list;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.get_detail){
            showDetail();
            return true;
        }
        if (item.getItemId() ==R.id.about_us)  {
            showAbout();
            return true;
        }
        return onOptionsItemSelected(item);
    }

    private void showAbout(){
        CharSequence text = "This is the toast shoe about";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
        toast.show();

    }
    private void showDetail(){
        CharSequence text = "This is the toast text show detail";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
        toast.show();
    }

}