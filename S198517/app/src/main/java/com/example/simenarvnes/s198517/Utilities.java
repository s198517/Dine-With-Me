package com.example.simenarvnes.s198517;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by simenarvnes on 04/11/15.
 */
public class Utilities {

    private static String fontPath = "fonts/Lato-Light.ttf";

    public static void setFont(final Context context, final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    setFont(context, child);
                }
                } else if (v instanceof TextView ) {
                    ((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), fontPath));
                }
            } catch (Exception e) {
        }
    }
}
