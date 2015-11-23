package com.example.simenarvnes.s198517;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by simenarvnes on 18/11/15.
 */
public class CustomAdapterRequests extends BaseAdapter {

    ArrayList<String> result;
    ArrayList<Integer> ids;
    ArrayList<String> phonenumbers;
    Context context;
    int imageId;

    private static LayoutInflater inflater=null;

    public CustomAdapterRequests(RequestActivity requestActivity, ArrayList<String> invitation, int image, ArrayList<Integer> id, ArrayList<String> phone) {
        ids = id;
        result=invitation;
        phonenumbers = phone;
        context=requestActivity;
        imageId=image;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        rowView = inflater.inflate(R.layout.request_list, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textView3);
        holder.img=(ImageView) rowView.findViewById(R.id.imageView3);
        holder.tv.setText(result.get(position));
        holder.tv.setId(ids.get(position));
        holder.img.setImageResource(imageId);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                AlertDialog.Builder id = builder.setPositiveButton("Invite!",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                                String username = sharedPreferences.getString("username", "");
                                String address = sharedPreferences.getString("address", "");
                                String info = sharedPreferences.getString("info", "");

                                //SEND SMS TO INVITED USER
                                SmsManager smsManager = SmsManager.getDefault();
                                smsManager.sendTextMessage(phonenumbers.get(position), null, "You have been invited by " + username + "!\nAddress: " + address + "\n" + info, null, null);

                                String userName = result.get(position);
                                String[] array = userName.split(" ", 2);

                                Toast.makeText(context, "You invited " + array[0] + " to dine with you!", Toast.LENGTH_SHORT).show();
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
