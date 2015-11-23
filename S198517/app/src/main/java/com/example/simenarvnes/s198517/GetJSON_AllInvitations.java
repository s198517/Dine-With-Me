package com.example.simenarvnes.s198517;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by simenarvnes on 09/11/15.
 */
//GET USERS
public class GetJSON_AllInvitations extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... urls){
        String s = "";
        String output = "";
        for(String url : urls){
            try{
                URL urlen = new URL(urls[0]);
                HttpURLConnection conn = (HttpURLConnection)urlen.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");

                if(conn.getResponseCode() != 200){
                    throw new RuntimeException("Failed: HTTP error code: " + conn.getResponseCode());
                }

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                while((s = bufferedReader.readLine()) != null){
                    output = output + s;
                }

                conn.disconnect();

                return output;
            }
            catch (Exception e){
                return "Something went wrong " + e.getMessage();
            }
        }
        return output;
    }

    @Override
    protected void onPostExecute(String ss){
        JSONObject jsonObject = null;

        try{
            jsonObject = new JSONObject(ss.substring(ss.indexOf("{"), ss.lastIndexOf("}") + 1));
            JSONArray jsonArray = jsonObject.getJSONArray("Invitation");

            ListFragment_Invitation.list.clear();

            for(int i = 0; i < jsonArray.length(); i++){

                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                int id = jsonObject1.getInt("Id");
                String food = jsonObject1.getString("Food");
                String budget = jsonObject1.getString("Budget");
                String date = jsonObject1.getString("Date");
                String time = jsonObject1.getString("Time");
                String userId = jsonObject1.getString("UserId");
                String zip = jsonObject1.getString("Zip");
                SearchActivity.info.add(" \n Food: " + food + "\n Date: " + date + "\n Time: " + time + "\n");
                SearchActivity.id.add(id);
                SearchActivity.zip.add(zip);
                Invitation invitation = new Invitation(id, food, Double.parseDouble(budget), date, time, Integer.parseInt(userId));
            }

        }
        catch (Exception e){

        }
    }
}