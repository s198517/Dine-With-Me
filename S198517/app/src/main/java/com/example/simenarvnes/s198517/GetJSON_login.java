package com.example.simenarvnes.s198517;

import android.os.AsyncTask;
import android.util.Log;

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

/**
 * Created by simenarvnes on 09/11/15.
 */
//GET USERS
public class GetJSON_login extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params){
        String addUser_url = "http://46.101.59.146/sql/login.php";

        String username = params[0];
        String password = params[1];

        String output = "";
        String s = "";

        try {

            URL url = new URL(addUser_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);

            OutputStream OS = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

            String data =
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

            JSONObject jsonObject = null;


            try{
                jsonObject = new JSONObject(output.substring(output.indexOf("{"), output.lastIndexOf("}") + 1));
                JSONArray jsonArray = jsonObject.getJSONArray("User");


                for(int i = 0; i < jsonArray.length(); i++){

                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String id = String.valueOf(jsonObject1.getInt("Id"));
                    String zip = jsonObject1.getString("Zip");
                    String userName = jsonObject1.getString("Username");
                    String phonenumber = jsonObject1.getString("Phonenumber");
                    String address = jsonObject1.getString("Address");
                    SignInActivity.userInfo.add(id);
                    SignInActivity.userInfo.add(zip);
                    SignInActivity.userInfo.add(userName);
                    SignInActivity.userInfo.add(phonenumber);
                    SignInActivity.userInfo.add(address);
                }

            }
            catch (Exception e){

            }

            if(output == ""){
                output = "0";
            }

            return output;

            }
            catch (Exception e){
                return "Something went wrong " + e.getMessage();
            }
        }
    }
