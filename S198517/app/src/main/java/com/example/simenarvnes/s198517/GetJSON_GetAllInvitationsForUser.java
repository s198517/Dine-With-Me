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
 * Created by simenarvnes on 17/11/15.
 */

//GET INVITATIONS FOR ONE USER
public class GetJSON_GetAllInvitationsForUser extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params){
        String addUser_url = "http://46.101.59.146/sql/getUserInvitations.php";

        String userid = params[0];

        String output = "";
        String s = "";

        try {

            URL url = new URL(addUser_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);

            OutputStream OS = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

            String data = URLEncoder.encode("UserId", "UTF-8") + "=" + URLEncoder.encode(userid, "UTF-8");

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
                JSONArray jsonArray = jsonObject.getJSONArray("UserInvitations");


                for(int i = 0; i < jsonArray.length(); i++){

                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    int id = jsonObject1.getInt("Id");
                    String food = String.valueOf(jsonObject1.getString("Food"));
                    String budget = String.valueOf(jsonObject1.getString("Budget"));
                    String date = String.valueOf(jsonObject1.getString("Date"));
                    String time = String.valueOf(jsonObject1.getString("Time"));
                    String userId = String.valueOf(jsonObject1.getInt("UserId"));
                    String zip = jsonObject1.getString("Zip");

                    ProfileActivity.invitations.add(" \n Food: " + food + "\n Date: " + date + "\n Time: " + time + "\n");
                    ProfileActivity.ids.add(id);

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
