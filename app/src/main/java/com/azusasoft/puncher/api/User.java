package com.azusasoft.puncher.api;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by SETA on 2016/3/3.
 */
public class User {
    public String id,name,groupName,position ;
    public int hourAtWork = 0;
    public int dayAtWork  = 0;
    public int hourPer    = 0;
    public int dayPer     = 0;
    public int punishCash = 0;
    public int late       = 0;
    public int absent     = 0;
    public int noSignOff  = 0;
    public int out        = 0;
    public int sickLeave  = 0;
    public int otherLeave = 0;
    public boolean isLogin = false;

    //恢复出用户id
    //TODO:用户是否登录？服务器判断？本地记录？
    public void restore(){
        this.id = "23333";
        this.name = "张坚强";
        this.isLogin = true;
        this.groupName = "划水组";
        this.position = "划水大队长";
    }


}
