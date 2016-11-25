package com.azusasoft.puncher.api;

import java.util.ArrayList;

/**
 * Created by SETA on 2016/2/23.
 * 每一个 请假/外勤 是一个 {@link Leave} 对象
 * PS:此功能主要作备案用，为防止信息不通畅，具体信息交流请使用电话/即时通讯
 */
public class Leave {
    private int type = 0;   //类型: 0:请假;1.外勤
    private int status = 0; //状态: 0:审核中;1.同意;2.拒绝;3.撤回
    //TODO: 状态:需要显示历史状态&时间……
    //TODO: 管理员A审核操作之后，管理员B操作的处理 （弹窗提示?）

    private String applicant,time,statusDetail;
    private ArrayList<String> accessors = new ArrayList<>();

    public Leave() {
    }


    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getTitle() {
        if(type==0){
            return applicant + "的请假";
        }
        return applicant + "的外勤";
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatusDetail() {
        return statusDetail;
    }

    public void setStatusDetail(String statusDetail) {
        this.statusDetail = statusDetail;
    }

    public ArrayList<String> getAccessors() {
        return accessors;
    }

    public void setAccessors(ArrayList<String> accessors) {
        this.accessors = accessors;
    }
}
