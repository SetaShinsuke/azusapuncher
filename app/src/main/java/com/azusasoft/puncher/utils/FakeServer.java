package com.azusasoft.puncher.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by SETA on 2016/3/2.
 */
public class FakeServer {

    //通知服务器记录签退时间
//    public static void postTime2Server(Context context ){
//        SharedPreferences sharedPreferences = context.getSharedPreferences( Constants.FAKE_SERVER,Context.MODE_PRIVATE );
//        long lastTime = System.currentTimeMillis() - getTimeFromServer(context);
//        sharedPreferences.edit().putLong(Constants.SERVER_PUNCH_TIME , lastTime).apply();
//    }
//
//    //返回本日已签到时长
//    public static long getTimeFromServer(Context context){
//        SharedPreferences sharedPreferences = context.getSharedPreferences( Constants.FAKE_SERVER,Context.MODE_PRIVATE );
//        return sharedPreferences.getLong(Constants.SERVER_PUNCH_TIME , 0);
//    }
}
