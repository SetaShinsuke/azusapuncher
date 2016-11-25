package com.azusasoft.puncher.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by SETA on 2016/2/24.
 */
public class ViewUtils {

    public static Snackbar snack(View view,String text){
        return Snackbar.make(view,text,Snackbar.LENGTH_SHORT);
    }
    public static Snackbar snack(View view,String text,int duration){
        return Snackbar.make(view,text,duration);
    }

    public static void showSnack(View view,String text){
        showSnack(view,text,Snackbar.LENGTH_SHORT);
    }
    public static void showSnack(View view,String text,int duration){
        Snackbar.make(view,text,duration).show();
    }
}
