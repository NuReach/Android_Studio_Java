package com.example.week5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

      ListView listView;
      String[] cities;

    ArrayAdapter<String> adapter;

//    String tutorials[]
//            = { "Algorithms", "Data Structures",
//            "Languages", "Interview Corner",
//            "GATE", "ISRO CS",
//            "UGC NET CS", "CS Subjects",};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        list = findViewById(R.id.list);
//        ArrayAdapter<String> arr ;
//        arr = new ArrayAdapter<String>(
//                this,
//                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
//                tutorials
//        );
//        list.setAdapter(arr);
//        //hande list view event click
//        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String selectedItem=(String) list.getItemAtPosition(i);
//                Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_LONG).show();
//            }
//        });
        cities = getProvinceNames();
        listView = (ListView) findViewById(R.id.list);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cities);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedProvice= (String) listView.getItemAtPosition(position);
                Location location = loadLocationsData().get(selectedProvice);
                Toast.makeText(getApplicationContext(), location.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
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

    private String[] getProvinceNames () {
        String[] provinces = new String[loadLocationsData().size()];
        provinces = loadLocationsData().keySet().toArray(provinces);
        return provinces;
    }

    private void displaySelectedProvincesInfo (String provinceName) {
        Location loc = loadLocationsData().get(provinceName);
        Toast.makeText(getApplicationContext(), loc.toString(), Toast.LENGTH_LONG).show();
    }

    private HashMap<String, Location> loadLocationsData () {
        HashMap<String, Location> locations = new HashMap<>();
        locations.put("PhnomPenh", new Location(11.5564,104.9282));
        locations.put("Sihanouk", new Location(10.627543,103.522141));
        locations.put("Kampot", new Location(10.594242,104.164032));
        locations.put("SiemReap", new Location(13.364047,103.860313));
        locations.put("Battambang", new Location(13.028697,102.989616));
        locations.put("KampongCham", new Location(11.99339,105.4635));
        locations.put("KampongChhnang", new Location(12.25,104.66667));
        locations.put("KampongThom", new Location(12.71112,104.88873));
        locations.put("KohKong", new Location(11.61531,102.9838));
        locations.put("Kep", new Location(10.48291,104.31672));
        locations.put("PreyVeng", new Location(11.48682,105.32533));
        locations.put("Takeo", new Location(10.99081,104.78498));
        locations.put("Pursat", new Location(12.53878,103.9192));
        locations.put("Mondolkiri", new Location(12.45583,107.18811));
        locations.put("StungTreng", new Location(13.52586,105.9683));
        locations.put("SvayRieng", new Location(11.08785,105.79935));
        locations.put("PreahVihear", new Location(13.80731,104.98046));
        locations.put("Kandal", new Location(11.48333,104.95));
        locations.put("BanteayMeanchey", new Location(13.58588,102.97369));
        locations.put("Ratanakiri", new Location(13.73939,106.98727));
        locations.put("KampongSpeu", new Location(11.45332,104.52085));
        locations.put("Kratie", new Location(12.48811,106.01879));
        locations.put("Pailin", new Location(12.84895,102.60928));
        locations.put("OtârMéanchey", new Location(14.18175,103.51761));
        locations.put("TbongKhmum", new Location(11.8891,105.8760));
        return locations;
    }

}