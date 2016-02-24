package com.azusasoft.puncher.api;

/**
 * Created by SETA on 2016/2/24.
 */
public class PuncherApi {

    static PuncherApi api;
    private LeaveApi leaveApi;

    private PuncherApi(){
        this.leaveApi = new LeaveApi();
    }

    public static PuncherApi getApi(){
        if(api==null){
            api = new PuncherApi();
        }
        return api;
    }
}
