package com.example.simenarvnes.s198517;

import android.os.AsyncTask;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by simenarvnes on 09/11/15.
 */
//GET USERS
public class GetJSON_AllUsers extends AsyncTask<String, Void, String> {

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
            JSONArray jsonArray = jsonObject.getJSONArray("User");


            for(int i = 0; i < jsonArray.length(); i++){

                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                int id = jsonObject1.getInt("Id");
                String sirname = jsonObject1.getString("Firstname");
                String lastname = jsonObject1.getString("Lastname");
                String address = jsonObject1.getString("Address");
                String zip = jsonObject1.getString("Zip");
                String username = jsonObject1.getString("Username");
                String password = jsonObject1.getString("Password");
                int rating = jsonObject1.getInt("Rating");

                User user = new User(id, sirname, lastname, address, zip, username, password, rating);
                ListFragment.list.add(user);
            }

        }
        catch (Exception e){

        }
    }
}