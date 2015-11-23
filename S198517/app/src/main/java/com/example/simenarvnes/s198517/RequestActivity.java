package com.example.simenarvnes.s198517;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by simenarvnes on 18/11/15.
 */
public class RequestActivity extends AppCompatActivity {

    public static ArrayList<String> info = new ArrayList<>();
    public static ArrayList<Integer> ids = new ArrayList<>();
    public static ArrayList<String> phone = new ArrayList<>();

    GetJSON_AllRequests getJSON_allRequests = new GetJSON_AllRequests();

    String invitationId;

    ListView listView;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        //DELETE LIST CONTENT
        info.clear();
        ids.clear();

        //SET FONT
        Utilities.setFont(getApplicationContext(), this.findViewById(android.R.id.content));


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        invitationId = sharedPreferences.getString("invitationId", "");

        context = this;
        listView = (ListView) findViewById(R.id.listView2);
        listView.setAdapter(new CustomAdapterRequests(this, info, R.drawable.plus, ids, phone));

        //SET ADAPTER
        try {
            getJSON_allRequests.execute(invitationId);
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

        if (id == R.id.profileactivity) {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
