package com.azusasoft.puncher.utils;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

import com.azusasoft.puncher.R;

import java.util.Objects;

/**
 * Created by SETA on 2016/2/18.
 */
public class UtilMethod {

    public static void fastLog(String text){
        Log.v("hehe",text);
    }

    public static void initEmptyActivity(Activity activity){
        activity.setContentView(R.layout.activity_empty);
        ((TextView)activity.findViewById(R.id.text_view)).setText(activity.getClass().getName());
    }
}
