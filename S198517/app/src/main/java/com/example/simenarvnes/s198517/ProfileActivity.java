package com.example.simenarvnes.s198517;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.ActionBar;

import java.util.ArrayList;

/**
 * Created by simenarvnes on 05/11/15.
 */
public class ProfileActivity extends AppCompatActivity {

    public static ArrayList<String> invitations = new ArrayList<>();
    public static ArrayList<Integer> ids = new ArrayList<>();
    GetJSON_GetAllInvitationsForUser getJSON_getAllInvitationsForUser = new GetJSON_GetAllInvitationsForUser();
    ListView listView;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //DELETE LIST CONTENT
        invitations.clear();
        ids.clear();

        //SET FONT
        Utilities.setFont(getApplicationContext(), this.findViewById(android.R.id.content));

        //SET ICON
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0D9FBD")));
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //SET INFO
        TextView username = (TextView)findViewById(R.id.profileName);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String usernameToTextView = sharedPreferences.getString("username", "");
        String id = String.valueOf(sharedPreferences.getInt("id", 0));

        username.setText("Welcome " + usernameToTextView);

        context = this;
        listView = (ListView) findViewById(R.id.listView1);
        listView.setAdapter(new CustomAdapterUserInvitations(this, invitations, R.drawable.play, ids));

        //SET ADAPTER
        try {
            getJSON_getAllInvitationsForUser.execute(id);
        }
        catch (Exception e){

        }
    }

    //MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.listactivity) {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //INVITATION
    public void addInvitation(View view){

        Intent intent = new Intent(this, InvitationActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }
}
