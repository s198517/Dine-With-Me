package com.example.simenarvnes.s198517;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by simenarvnes on 09/11/15.
 */
public class ListFragment_Invitation extends Fragment {

    public GetJSON_AllInvitations getJSON_allInvitations = new GetJSON_AllInvitations();
    public static ArrayList<Invitation> list = new ArrayList<>();
    public static ArrayList<String> info = new ArrayList<>();
    public static ArrayList<Integer> zip = new ArrayList<>();

    public ListFragment_Invitation(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View v = inflater.inflate(R.layout.listview, container, false);
        final ListView lv = (ListView) v.findViewById(R.id.listView);


        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_1, info);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        return v;
    }
}

