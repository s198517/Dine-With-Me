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
 * Created by simenarvnes on 13/11/15.
 */
public class PostJSON_AddUserInvitation extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params){
        String addUser_url = "http://46.101.59.146/sql/addUserInvitation.php";

        String userId = params[0];
        String invitationId = params[1];
        String username = params[2];
        String phonenumber = params[3];

        String output = "";
        String s = "";

        try {

            URL url = new URL(addUser_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);

            OutputStream OS = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

            String data = URLEncoder.encode("UserId", "UTF-8") + "=" + URLEncoder.encode(userId, "UTF-8") + "&" +
                    URLEncoder.encode("InvitationId", "UTF-8") + "=" + URLEncoder.encode(invitationId, "UTF-8") + "&" +
                    URLEncoder.encode("Username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                    URLEncoder.encode("Phonenumber", "UTF-8") + "=" + URLEncoder.encode(phonenumber, "UTF-8");

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
