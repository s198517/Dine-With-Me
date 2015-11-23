package com.example.simenarvnes.s198517;

/**
 * Created by simenarvnes on 04/11/15.
 */

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


public class Database {

    //ADD USER
    public class PostJSON extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params){
            String addUser_url = "http://46.101.59.146/sql/addUser.php";

            String firstname = params[0];
            String lastname = params[1];
            String address = params[2];
            String zip = params[3];
            String username = params[4];
            String password = params[5];

            String output = "";
            String s = "";

            try {

                URL url = new URL(addUser_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

                String data = URLEncoder.encode("Firstname", "UTF-8") + "=" + URLEncoder.encode(firstname, "UTF-8") + "&" +
                        URLEncoder.encode("Lastname", "UTF-8") + "=" + URLEncoder.encode(lastname, "UTF-8") + "&" +
                        URLEncoder.encode("Address", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8") + "&" +
                        URLEncoder.encode("Zip", "UTF-8") + "=" + URLEncoder.encode(zip, "UTF-8") + "&" +
                        URLEncoder.encode("Username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("Password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
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

        protected void onPostExecute(String result){
            //textView.append(result);
        }

        protected void onProgressUpdate(){

        }
    }

    //GET USERS
    public class GetJSON extends AsyncTask<String, Void, String> {

        public TextView textView;

        public TextView getTextView() {
            return textView;
        }

        public void setTextView(TextView textView) {
            this.textView = textView;
        }

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

                    textView.append(id + ", " + sirname + ", " + lastname + ", " + address + ", " + zip + ", " + username + ", " + password + ", " + rating + "\n");
                }

            }
            catch (Exception e){
                textView.append(e.toString());
            }
        }
    }
}
