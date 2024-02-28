package com.example.updatetoserver;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class SaveData extends AsyncTask<String,Void,String> {
    private Context context;
    private HashMap<String,String> postData;

    private ProgressDialog progressDialog;

    public SaveData (Context context, HashMap<String,String> postData){
        this.context = context;
        this.postData = postData;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Saving...");
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... urls) {
        return invokePost(urls[0],postData);
    }

    private String invokePost( String requestUrl , HashMap<String,String> postDataParams) {
        String response = "";
        URL url;
        String LOG = "SavaData";
        try {
             url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));
            writer.flush();
            writer.close();
            os.close();
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response+=line;
                }
            } else {
                response = "";
                Log.d(LOG, responseCode + " ");
            }

        } catch (MalformedURLException e) {
            Log.d(LOG, "MalformedURLException Error: " + e);
        } catch (ProtocolException e) {
            Log.d(LOG, "ProtocolException Error: " + e);
        } catch (UnsupportedEncodingException e) {
            Log.d(LOG, "UnsupportedEncodingException Error: " + e);
        } catch (IOException e) {
            Log.d(LOG, "IOException Error: " + e);
        }
        return response;
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first) {
                first = false;
            } else {
                result.append("&");
            }
                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));

        }

        return result.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        if (result == null){
            Log.d("SavaData","Something wrong while save data");
        }else {
            Log.d("SaveData",result);
        }
    }
}
