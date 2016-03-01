package com.azusasoft.puncher.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.azusasoft.puncher.utils.UtilMethod.fastLog;

/**
 * Created by SETA on 2016/2/29.
 */
public class StringUtils {

    public static String getLastTime(Date start,Date end){
        String result="";
        long lastTime = end.getTime() - start.getTime();
        if( lastTime/(1000*60*60*24) >0){
            result += ( lastTime/(1000*60*60*24) + "天");
        }
        lastTime = lastTime%(1000*60*60*24);
        if(lastTime/(1000*60*60)>0){
            result += ( lastTime/(1000*60*60) + "小时");
        }
        lastTime = lastTime%(1000*60*60);
        if(lastTime/(1000*60)>0){
            result += ( lastTime/(1000*60) + "分钟");
        }
        return result;
    }
    public static String getLastTime(long start,long end){
        String result = "";
        long period = end - start;
        if(period/1000/60/60>0){
            result = formatS(String.format("%02d:",period/1000/60/60));
        }else {
            result = "00:";
        }
        period = period%(1000*60*60);
        if(period/1000/60>0){
            result += formatS(String.format("%02d:", period / 1000 / 60));
        }else {
            result += "00:";
        }
        period = period%(1000*60);
        if(period/1000>0){
            result += formatS(String.format("%02d",period/1000));
        }else {
            result += "00";
        }
        return result;
    }
    private static String formatS( String s ){
        if(s.trim().length()<2){
            return "0"+s.trim();
        }
        return s.trim();
    }

    public static String formDate(Date date){
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
        return dt1.format(date);
    }
    public static String formDate(Date date , String format){
        String result = "";
        SimpleDateFormat dt1 = new SimpleDateFormat( format , Locale.CHINA);
        return dt1.format(date);
    }
    public static String getDayOfWeek(Calendar c){
        String result = "星期一";
        int i = c.get(Calendar.DAY_OF_WEEK);
        switch (i){
            case 1:
                result = "星期日";
                break;
            case 2:
                result = "星期一";
                break;
            case 3:
                result = "星期二";
                break;
            case 4:
                result = "星期三";
                break;
            case 5:
                result = "星期四";
                break;
            case 6:
                result = "星期五";
                break;
            case 7:
                result = "星期六";
                break;
        }
        return result;
    }
}
