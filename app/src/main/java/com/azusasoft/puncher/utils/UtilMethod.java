package com.azusasoft.puncher.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.TextView;

import com.azusasoft.puncher.R;
import com.azusasoft.puncher.api.Leave;

import java.util.ArrayList;
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

    public static int getColorByStatus( Context context , int status ){
        int color = ContextCompat.getColor(context , R.color.colorPrimary300);
        switch ( status ){
            case 0:
                color = ContextCompat.getColor(context , R.color.azusa_color);
                break;
            case 1:
                color = ContextCompat.getColor(context , R.color.colorPrimary300);
                break;
            case 2:
                color = ContextCompat.getColor(context , R.color.facehub_alpha);
                break;
            case 3:
                color = ContextCompat.getColor(context , R.color.azusa_grey);
                break;
            default:
                break;
        }
        return color;
    }

    public static ArrayList<Leave> initData(){
        ArrayList<Leave> leaves = new ArrayList<>();
        for(int i=0;i<30;i++){
            Leave leave = new Leave();
            int a = (int)(Math.random()*10);
            if(a>3){
                leave.setStatus(1);
            }else {
                leave.setStatus(a);
            }
            leaves.add(leave);
        }
        return leaves;
    }
}
