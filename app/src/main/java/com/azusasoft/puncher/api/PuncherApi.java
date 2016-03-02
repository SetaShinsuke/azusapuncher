package com.azusasoft.puncher.api;

import android.content.Context;

import com.azusasoft.puncher.utils.FakeServer;

/**
 * Created by SETA on 2016/2/24.
 */
public class PuncherApi {

    static PuncherApi api;
    private LeaveApi leaveApi;

    private boolean isAtWork = false;//是否打卡
    private boolean isOut    = false;//是否外勤中
    private long lastPunchTime = 0;//打卡时间-等效时间，根据服务器数据计算出来
    private long lastOutTime = 0;//外勤开始时间


    private PuncherApi(){
        this.leaveApi = new LeaveApi();
    }

    public static PuncherApi getApi(){
        if(api==null){
            api = new PuncherApi();
        }
        return api;
    }

    //登录
    public void Login(){

    }
    //退出
    public void logout(){

    }

    //打卡
    public void punchOn2Server(ResultHandlerInterface resultHandlerInterface){
        //TODO: if (success)
        this.lastPunchTime = System.currentTimeMillis() ; // 减去 从服务器拉取的本日签到时长;
        this.lastOutTime = 0;
        isAtWork = true;
        isOut = false;
        resultHandlerInterface.onResponse( null );
    }
    //签退
    public void punchOff2Server(ResultHandlerInterface resultHandlerInterface){
        //TODO: if (success)
        isAtWork = false;
        isOut = false;
        resultHandlerInterface.onResponse(null);
    }

    /**
     * 打开应用后检测是否已打卡
     * 若已打卡，则返回已打卡时间，并根据本地时间计算打卡时间
     * 若未打卡，则检测是否外勤中——外勤中则记录外出开始时间,跳转到外勤计时界面
     * TODO:需要知道 【本日已打卡时间】
     **/
    public void checkAtWork(ResultHandlerInterface resultHandlerInterface){
        String response = "";
        isAtWork  = false;
        isOut = false;
        resultHandlerInterface.onResponse( response );
        //记录从服务器拉取的时间


    }

    //开始外勤
    public void startOut2Server(ResultHandlerInterface resultHandlerInterface){
        this.isOut = true;
        this.isAtWork = false;
        this.lastPunchTime = 0;
        this.lastOutTime = System.currentTimeMillis();
        resultHandlerInterface.onResponse(null);
    }
    //结束外勤
    public void endOut2Server(ResultHandlerInterface resultHandlerInterface){
        this.isOut = false;
        this.isAtWork = false;
        resultHandlerInterface.onResponse(null);
    }

    public boolean isAtWork() {
        return isAtWork;
    }
    public boolean isOut() {
        return isOut;
    }
    public long getLastPunchTime() {
        return lastPunchTime;
    }
    public long getLastOutTime() {
        return lastOutTime;
    }

}
