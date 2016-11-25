package com.azusasoft.puncher.api;

import java.util.ArrayList;

/**
 * Created by SETA on 2016/2/24.
 */
public class LeaveApi {


    //TODO: 各项记录    1.服务器统一给、本地筛选? 2.分类给?
    private ArrayList<Leave> verifyLeaves = new ArrayList<>();  //管理员审核记录
    private ArrayList<Leave> leaves = new ArrayList<>();    //普通用户个人记录

    public LeaveApi(){

    }

    public ArrayList<Leave> getLeaves(){
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
