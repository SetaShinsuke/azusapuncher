package com.azusasoft.puncher.utils;

import java.util.Date;

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
}
