package com.example.simenarvnes.s198517;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by simenarvnes on 10/11/15.
 */
public class CustomAdapter extends BaseAdapter {

    ArrayList<String> result;
    ArrayList<Integer> ids;
    Context context;
    int imageId;
    public PostJSON_AddUserInvitation postJSON_addUserInvitation = new PostJSON_AddUserInvitation();

    private static LayoutInflater inflater=null;

    public CustomAdapter(SearchActivity searchActivity, ArrayList<String> invitation, int image, ArrayList<Integer> id) {
        ids = id;
        result=invitation;
        context=searchActivity;
        imageId=image;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return result.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder
    {
        TextView tv;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.search_list, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textView1);
        holder.img=(ImageView) rowView.findViewById(R.id.imageView1);
        holder.tv.setText(result.get(position));
        holder.tv.setId(ids.get(position));
        holder.img.setImageResource(imageId);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                AlertDialog.Builder id = builder.setPositiveButton("Send request",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(v.getContext());
                                int userId = sharedPreferences.getInt("id", 0);
                                String username = sharedPreferences.getString("username", "");
                                String phonenumber = sharedPreferences.getString("phonenumber", "");

                                try {
                                    postJSON_addUserInvitation.execute(String.valueOf(userId), String.valueOf(ids.get(position)), username, phonenumber);
                                } catch (Exception e) {

                                }
                                Toast.makeText(v.getContext().getApplicationContext(), "Invitation requested", Toast.LENGTH_SHORT).show();
                            }
                        });

                builder.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


                            }
                        });

                builder.show();
            }
        });
        return rowView;
    }
}
