package com.example.simenarvnes.s198517;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by simenarvnes on 03/11/15.
 */
public class SignInActivity extends AppCompatActivity{

    public static ArrayList<String> userInfo = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        //SET FONT
        Utilities.setFont(getApplicationContext(), this.findViewById(android.R.id.content));
    }

    public void login(View view) {

        EditText username = (EditText) findViewById(R.id.lusername);
        EditText password = (EditText) findViewById(R.id.lpassword);

        final String usernameString = username.getText().toString();
        final String passwordString = password.getText().toString();

        if (!validateName(usernameString)) {
            username.setError("Fill in a username");
        }

        if (!validateName(passwordString)) {
            password.setError("Fill in a password");
        }

        if (validateName(usernameString) && validateName(passwordString)) {

            GetJSON_login login = new GetJSON_login();

            try{
                login.execute(usernameString, passwordString).get();

                //Write information about user to shared pref.
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putInt("id", Integer.parseInt(userInfo.get(0)));
                editor.putString("zip", userInfo.get(1));
                editor.putString("username", userInfo.get(2));
                editor.putString("phonenumber", userInfo.get(3));
                editor.putString("address", userInfo.get(4));

                editor.commit();

                if(Integer.parseInt(userInfo.get(0)) > 0 ){
                    Intent intent = new Intent(this, ProfileActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    finish();
                }
                else{
                    username.setError("Wrong username or password");
                }
            }
            catch (Exception e){

            }

        }
    }

    // validate sirname/lastname
    private boolean validateName(String name) {
        String NAME_PATTERN = "^[a-åA-Å]{3,20}$";

        Pattern pattern = Pattern.compile(NAME_PATTERN);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
}
