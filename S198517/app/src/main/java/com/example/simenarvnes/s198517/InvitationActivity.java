package com.example.simenarvnes.s198517;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by simenarvnes on 05/11/15.
 */
public class InvitationActivity extends AppCompatActivity {

    DatePickerFragment datePickerFragment = new DatePickerFragment();
    TimePickerFragment timePickerFragment = new TimePickerFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation);

        //SET FONT
        Utilities.setFont(getApplicationContext(), this.findViewById(android.R.id.content));
    }

    //DATE- AND TIMEPICKER FRAGMENT

    //Show date-picker fragment
    public void showDatePickerDialog(View v) {

        datePickerFragment.show(getFragmentManager(), "");
    }

    //Show time-picker fragment
    public void showTimePickerDialog(View v) {

        timePickerFragment.show(getFragmentManager(), "");
    }

    //ADD INVITATION

    //Add invitation
    public void addInvitation(View view){

        EditText food = (EditText)findViewById(R.id.food);
        EditText budget = (EditText)findViewById(R.id.budget);


        final String foodString = food.getText().toString();
        final String budgetString = budget.getText().toString();

        if(!validateFood(foodString)){
            food.setError("Fill in food");
        }

        if(!validateBudget(budgetString)){
            budget.setError("Fill in a budget");
        }

        if(validateFood(foodString) && validateBudget(budgetString)){

            int year = datePickerFragment.getYear();
            int month = datePickerFragment.getMonth();
            int day = datePickerFragment.getDay();

            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            int hour = timePickerFragment.getHour();
            int minute = timePickerFragment.getMinute();

            String time = hour + ":" + minute;

            try{
                PostJSON_AddInvitation addInvitation = new PostJSON_AddInvitation();

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String usernameToTextView = sharedPreferences.getString("username", "");
                String zip = sharedPreferences.getString("zip", "");
                int userId = sharedPreferences.getInt("id", 0);

                addInvitation.execute(foodString, budgetString, simpleDateFormat.format(calendar.getTime()), time, String.valueOf(userId), zip);

                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();

            }
            catch (Exception e){

            }

            food.setText("");
            budget.setText("");


        }
    }

    private boolean validateFood(String food) {
        String FOOD_PATTERN = "^[a-åA-Å]{3,20}$";

        Pattern pattern = Pattern.compile(FOOD_PATTERN);
        Matcher matcher = pattern.matcher(food);
        return matcher.matches();
    }

    private boolean validateBudget(String budget) {
        String BUDGET_PATTERN = "^[0-9]{1,20}$";

        Pattern pattern = Pattern.compile(BUDGET_PATTERN);
        Matcher matcher = pattern.matcher(budget);
        return matcher.matches();
    }
}
