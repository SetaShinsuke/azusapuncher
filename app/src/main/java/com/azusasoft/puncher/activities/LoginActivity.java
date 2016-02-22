package com.azusasoft.puncher.activities;

import android.os.Bundle;

import com.azusasoft.puncher.framework.BaseActivity;
import com.azusasoft.puncher.utils.UtilMethod;

/**
 * Created by SETA on 2016/2/22.
 * 登陆页
 */
public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UtilMethod.initEmptyActivity(this);
    }
}
