package com.example.simenarvnes.s198517;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by simenarvnes on 09/11/15.
 */
public class ListFragment extends Fragment {

    public static ArrayList<User> list = new ArrayList<>();
    public GetJSON_AllUsers allUsers = new GetJSON_AllUsers();

    public ListFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View v = inflater.inflate(R.layout.listview, container, false);
        final ListView lv = (ListView) v.findViewById(R.id.listView);

        allUsers.execute(new String[]{"http://46.101.59.146/sql/getUsers.php"});

        ArrayList<String> lsd = new ArrayList<>();

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_1, lsd);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        return v;
    }
}
