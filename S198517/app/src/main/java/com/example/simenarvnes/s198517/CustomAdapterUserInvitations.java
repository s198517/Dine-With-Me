package com.example.simenarvnes.s198517;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by simenarvnes on 17/11/15.
 */
public class CustomAdapterUserInvitations extends BaseAdapter{

    ArrayList<String> result;
    ArrayList<Integer> ids;
    Activity context;
    int imageId;

    private static LayoutInflater inflater=null;

    public CustomAdapterUserInvitations(Activity profileActivity, ArrayList<String> invitation, int image, ArrayList<Integer> id) {
        ids = id;
        result=invitation;
        context=profileActivity;
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
    public View getView(final int position, final View convertView, ViewGroup parent) {

        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.invitation_list, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textView2);
        holder.img=(ImageView) rowView.findViewById(R.id.imageView2);
        holder.tv.setText(result.get(position));
        holder.tv.setId(ids.get(position));
        holder.img.setImageResource(imageId);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                String invitationId = String.valueOf(ids.get(position));

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("invitationId", invitationId);
                editor.putString("info", result.get(position));
                editor.commit();

                Intent intent = new Intent(context, RequestActivity.class);
                context.startActivity(intent);
                ((ProfileActivity)context).overridePendingTransition(0, 0);
                ((ProfileActivity)context).finish();
            }
        });
        return rowView;
    }
}
