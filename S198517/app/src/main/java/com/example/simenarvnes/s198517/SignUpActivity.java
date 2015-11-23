package com.example.simenarvnes.s198517;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by simenarvnes on 03/11/15.
 */
public class SignUpActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //SET FONT
        Utilities.setFont(getApplicationContext(), this.findViewById(android.R.id.content));
    }

    //ADD USER

    //Add user
    public void addUser(View view){

        EditText address = (EditText)findViewById(R.id.address);
        EditText zip = (EditText)findViewById(R.id.zip);
        EditText username = (EditText)findViewById(R.id.username);
        EditText password = (EditText)findViewById(R.id.password);
        EditText phonenumber = (EditText)findViewById(R.id.phonenumber);

        final String addressString = address.getText().toString();
        final String zipString = zip.getText().toString();
        final String usernameString = username.getText().toString();
        final String passwordString = password.getText().toString();
        final String phonenumberString = phonenumber.getText().toString();

        if(!validateAddress(addressString)){
            address.setError("Fill in address");
        }

        if(!validateZip(zipString)){
            zip.setError("Fill in a zip-code");
        }

        if(!validateName(usernameString)){
            zip.setError("Fill in a username");
        }

        if(!validateName(passwordString)){
            zip.setError("Fill in a password");
        }

        if(!validatePhonenumber(phonenumberString)){
            zip.setError("Fill in a phone number");
        }

        if(validateAddress(addressString) && validateZip(zipString) && validateName(usernameString) && validateName(passwordString) && validatePhonenumber(phonenumberString)){

            PostJSON postJSON = new PostJSON();
            int id = 0;

            try{
               id = Integer.parseInt(postJSON.execute(addressString, zipString, usernameString, passwordString, phonenumberString).get());
            }
            catch (Exception e){

            }

            //Write information about user to shared pref.
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putInt("id", id);
            editor.putString("address", addressString);
            editor.putString("zip", zipString);
            editor.putString("username", usernameString);
            editor.putString("phonenumber", phonenumberString);
            editor.commit();

            address.setText("");
            zip.setText("");
            phonenumber.setText("");

            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
            finish();
        }
    }

    //VALIDATION

    // validate sirname/lastname
    private boolean validateName(String name) {
        String NAME_PATTERN = "^[a-åA-Å]{3,20}$";

        Pattern pattern = Pattern.compile(NAME_PATTERN);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    // validate address
    private boolean validateAddress(String address) {
        String ADDRESS_PATTERN = "^[a-åA-Å0-9 ]{3,40}$";

        Pattern pattern = Pattern.compile(ADDRESS_PATTERN);
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
    }

    // validate zip
    private boolean validateZip(String zip) {
        String ZIP_PATTERN = "^[0-9]{4}$";

        Pattern pattern = Pattern.compile(ZIP_PATTERN);
        Matcher matcher = pattern.matcher(zip);
        return matcher.matches();
    }

    // validate phonenumber
    private boolean validatePhonenumber(String phonenumber) {
        String PHONE_PATTERN = "^[0-9]{8}$";

        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phonenumber);
        return matcher.matches();
    }

}
