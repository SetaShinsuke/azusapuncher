package com.azusasoft.puncher.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.azusasoft.puncher.R;
import com.azusasoft.puncher.framework.BaseActivity;
import com.azusasoft.puncher.utils.UtilMethod;
import com.azusasoft.puncher.utils.ViewUtils;

/**
 * Created by SETA on 2016/2/26.
 */
public class OutFormActivity extends BaseActivity {
    private Context context;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UtilMethod.initEmptyActivity(this);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        initToolBar(toolbar);
    }
}
