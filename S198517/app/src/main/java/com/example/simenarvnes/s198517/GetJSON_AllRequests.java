package com.example.simenarvnes.s198517;

import android.os.AsyncTask;
import android.telephony.SmsManager;

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
 * Created by simenarvnes on 18/11/15.
 */
//GET INVITATIONS FOR ONE USER
public class GetJSON_AllRequests extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params){
        String addUser_url = "http://46.101.59.146/sql/getRequests.php";

        String invitationId = params[0];

        String output = "";
        String s = "";

        try {

            URL url = new URL(addUser_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);

            OutputStream OS = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

            String data = URLEncoder.encode("InvitationId", "UTF-8") + "=" + URLEncoder.encode(invitationId, "UTF-8");

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
                JSONArray jsonArray = jsonObject.getJSONArray("Requests");


                for(int i = 0; i < jsonArray.length(); i++){

                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    int userid = jsonObject1.getInt("UserId");
                    int invitationid = jsonObject1.getInt("InvitationId");
                    //boolean joined = jsonObject1.getBoolean("Invited");
                    String username = jsonObject1.getString("Username");
                    String phonenumber = jsonObject1.getString("Phonenumber");


                    RequestActivity.info.add(username + " wants to dine with you!");
                    RequestActivity.ids.add(userid);
                    RequestActivity.phone.add(phonenumber);
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

