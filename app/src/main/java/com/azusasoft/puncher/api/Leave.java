package com.azusasoft.puncher.api;

import java.util.ArrayList;

/**
 * Created by SETA on 2016/2/23.
 * 每一个请假是一个 {@link Leave} 对象
 */
public class Leave {
    //0:审核中;1.同意;2.拒绝;3.撤回
    private int status;
    private String applicant,title,time,statusDetail;
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
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
