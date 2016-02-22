package com.azusasoft.puncher.activities;

import android.os.Bundle;

import com.azusasoft.puncher.framework.BaseActivity;
import com.azusasoft.puncher.utils.UtilMethod;

/**
 * Created by SETA on 2016/2/22.
 * 1.审核【请假】的列表
 * 2.审核【外勤】的列表
 */
public class VerifyHistoryActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UtilMethod.initEmptyActivity(this);
    }

}
