package com.example.simenarvnes.s198517;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by simenarvnes on 09/11/15.
 */
//ADD USER
public class PostJSON_AddInvitation extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params){
        String addUser_url = "http://46.101.59.146/sql/addInvitation.php";

        String food = params[0];
        String budget = params[1];
        String date = params[2];
        String time = params[3];
        String userId = params[4];
        String zip = params[5];

        String output = "";
        String s = "";

        try {

            URL url = new URL(addUser_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);

            OutputStream OS = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

            String data = URLEncoder.encode("Food", "UTF-8") + "=" + URLEncoder.encode(food, "UTF-8") + "&" +
                    URLEncoder.encode("Budget", "UTF-8") + "=" + URLEncoder.encode(budget, "UTF-8") + "&" +
                    URLEncoder.encode("Date", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8") + "&" +
                    URLEncoder.encode("Time", "UTF-8") + "=" + URLEncoder.encode(time, "UTF-8") + "&" +
                    URLEncoder.encode("UserId", "UTF-8") + "=" + URLEncoder.encode(userId, "UTF-8") + "&" +
                    URLEncoder.encode("Zip", "UTF-8") + "=" + URLEncoder.encode(zip, "UTF-8");

            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            OS.close();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

            while((s = bufferedReader.readLine()) != null){
                output = output + s;
            }

            httpURLConnection.disconnect();
            bufferedReader.close();

            return output;

        }
        catch (Exception e){
            return "Something went wrong " + e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result){
    }

    protected void onProgressUpdate(){

    }
}

