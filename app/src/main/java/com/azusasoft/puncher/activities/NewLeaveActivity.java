package com.azusasoft.puncher.activities;

import android.content.Context;
import android.os.Bundle;

import com.azusasoft.puncher.R;
import com.azusasoft.puncher.framework.BaseActivity;
import com.azusasoft.puncher.utils.UtilMethod;

/**
 * Created by SETA on 2016/2/22.
 */
public class NewLeaveActivity extends BaseActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_leave);
        context = this;
    }
}
