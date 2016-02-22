package com.azusasoft.puncher.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.azusasoft.puncher.R;
import com.azusasoft.puncher.events.ExitEvent;
import com.azusasoft.puncher.framework.BaseActivity;
import com.azusasoft.puncher.utils.UtilMethod;

import de.greenrobot.event.EventBus;

/**
 * Created by SETA on 2016/2/22.
 * 登陆页
 */
public class LoginActivity extends BaseActivity {

    private static long DOUBLE_CLICK_TIME = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        UtilMethod.initEmptyActivity(this);
        setContentView(R.layout.activity_login);
        setSwipeBackEnable(false);
    }


    public void onLoginClick(View view){
        Snackbar snackbar = Snackbar.make(findViewById(R.id.azusa_logo), "登录中...", Snackbar.LENGTH_SHORT);
        final Snackbar snackbar2 = Snackbar.make(findViewById(R.id.azusa_logo), "登录成功!", Snackbar.LENGTH_SHORT);
        snackbar2.setCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                super.onDismissed(snackbar, event);
                exitThis();
            }
        });
        snackbar.setCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                super.onDismissed(snackbar, event);
                snackbar2.show();
            }
        });
        snackbar.show();
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - DOUBLE_CLICK_TIME > 2000) {
            DOUBLE_CLICK_TIME = System.currentTimeMillis();
            Snackbar.make(findViewById(R.id.azusa_logo), "再按一次退出~", Snackbar.LENGTH_SHORT).show();
        }else {
            ExitEvent exitEvent = new ExitEvent();
            EventBus.getDefault().post(exitEvent);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    System.exit(0);
                }
            },500);
        }
    }
}
