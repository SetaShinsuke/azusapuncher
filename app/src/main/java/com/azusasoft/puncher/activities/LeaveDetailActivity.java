package com.azusasoft.puncher.activities;

import android.os.Bundle;

import com.azusasoft.puncher.framework.BaseActivity;
import com.azusasoft.puncher.utils.UtilMethod;

/**
 * Created by SETA on 2016/2/22.
 * 1.xxx的请假进度页面;
 * 2.xxx的外勤进度页面;
 * 3.审批xxx的请假的页面;
 * 4.审批xxx的外勤的页面;
 */
public class LeaveDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UtilMethod.initEmptyActivity(this);
    }
}
