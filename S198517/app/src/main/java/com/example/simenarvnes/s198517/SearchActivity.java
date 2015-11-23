package com.example.simenarvnes.s198517;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by simenarvnes on 05/11/15.
 */
public class SearchActivity extends AppCompatActivity {

    ListView listView;
    Context context;

    public static ArrayList<Invitation> list = new ArrayList<>();
    public GetJSON_AllInvitations getJSON_allInvitations = new GetJSON_AllInvitations();
    public static ArrayList<String> info = new ArrayList<>();
    public static ArrayList<Integer> id = new ArrayList<>();
    public static ArrayList<String> zip = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //DELETE LIST CONTENT
        info.clear();
        id.clear();

        //SET FONT
        Utilities.setFont(getApplicationContext(), this.findViewById(android.R.id.content));

        //SET ICON
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0D9FBD")));
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        context = this;
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new CustomAdapter(this, info, R.drawable.request, id));

        //SET ADAPTER
        try {
            getJSON_allInvitations.execute(new String[]{"http://46.101.59.146/sql/getInvitations.php"});
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

    //SEARCH
    public void search(View v){

        ArrayList<String> info2 = new ArrayList<>();
        ArrayList<Integer> id2 = new ArrayList<>();
        TextView textView = (TextView)findViewById(R.id.result);
        EditText editText = (EditText)findViewById(R.id.searchZip);
        String zipString = editText.getText().toString();

        textView.setText("");

        if(zipString.equals("")){
            listView.setAdapter(new CustomAdapter(this, info, R.drawable.request, id));
        }
        else {


            for (int i = 0; i < zip.size(); i++) {
                if (zipString.equals(zip.get(i))) {
                    info2.add(info.get(i));
                    id2.add((id.get(i)));
                }
            }

            if (info2.size() == 0) {
                textView.setText(getResources().getString(R.string.sorry));
            }
            listView.setAdapter(new CustomAdapter(this, info2, R.drawable.request, id2));
        }
    }
}
