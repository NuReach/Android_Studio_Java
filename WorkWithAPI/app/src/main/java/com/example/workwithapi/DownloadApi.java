package com.example.workwithapi;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DownloadApi extends AsyncTask<Void,Void, String> {
    private  ProgressDialog progressDialog;
    private Context context;

    private final String urlString = "https://jsonplaceholder.typicode.com/comments?postId=1";
    private OnRecieveData onRecieveData;

    DownloadApi (Context context, OnRecieveData onRecieveData){
        this.context = context;
        this.onRecieveData = onRecieveData;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Downloading...");
        progressDialog.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(con.getInputStream())
                );
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null){
                    sb.append(line).append("\n");
                }
                reader.close();
                return sb.toString();
            }finally {
                con.disconnect();
            }
        }catch (Exception e){
            Log.e("Error",e.getMessage(),e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s == null){
            Log.d("AsyncTask","There is an error");
        }else {
            progressDialog.hide();
            try {
                List<Post> posts = Arrays.asList(Converter.fromJsonString(s));
                onRecieveData.recceived(posts);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    };
}
