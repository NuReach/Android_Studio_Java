package com.example.updateimgandvdtosserver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private String fileName = "myfilename.txt";
    private String filePath = "mypath";
    private File myExternalFile;
    private String myData = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText etText = findViewById(R.id.etText);
        Button btSave = findViewById(R.id.btnSave);
        Button btRead = findViewById(R.id.btnRead);
        TextView tvResponse = findViewById(R.id.tvRespone);
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()){
            btSave.setEnabled(false);
        }else {
            myExternalFile = new File(getExternalFilesDir(filePath),fileName);
        }
        btSave.setOnClickListener(v -> {
            String text = etText.getText().toString();
            try {
                FileOutputStream fos = new FileOutputStream(myExternalFile);
                fos.write(text.getBytes());
                fos.close();
            } catch (IOException e){
                e.printStackTrace();
            }
            etText.setText("");
            tvResponse.setText("Saved to external storage...");
        });
        btRead.setOnClickListener(v -> {
            try{
                FileInputStream fis = new FileInputStream(myExternalFile);
                DataInputStream in = new DataInputStream(fis);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String strLine;
                while ((strLine = br.readLine()) != null){
                    myData = myData + strLine;
                }
                in.close();
            }catch (IOException e){
                e.printStackTrace();
            }
            tvResponse.setText(myData);
        });

    }



    private static boolean isExternalStorageReadOnly () {
        String extStorage = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorage)){
            return true;
        }
        return false;
    }
    private static boolean isExternalStorageAvailable (){
        String extStorage = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorage)){
            return true;
        }
        return false;
    }
}