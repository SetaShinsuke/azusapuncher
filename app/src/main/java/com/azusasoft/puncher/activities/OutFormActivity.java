package com.azusasoft.puncher.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.azusasoft.puncher.R;
import com.azusasoft.puncher.api.PuncherApi;
import com.azusasoft.puncher.api.ResultHandlerInterface;
import com.azusasoft.puncher.dialogs.SelectAccessorsDialog;
import com.azusasoft.puncher.events.StopOutTimerEvent;
import com.azusasoft.puncher.framework.BaseActivity;
import com.azusasoft.puncher.utils.Constants;
import com.azusasoft.puncher.utils.StringUtils;
import com.azusasoft.puncher.utils.UtilMethod;
import com.azusasoft.puncher.utils.ViewUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import de.greenrobot.event.EventBus;

import static com.azusasoft.puncher.utils.Constants.isViewAnimating;
import static com.azusasoft.puncher.utils.UtilMethod.fastLog;

/**
 * Created by SETA on 2016/2/26.
 */
public class OutFormActivity extends BaseActivity {
    private Context context;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_out_form);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        initToolBar(toolbar);

        //TODO:设置开始、结束时间
        Date date = Calendar.getInstance().getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR,2);
        calendar.add(Calendar.MINUTE,30);
        Date date2 = calendar.getTime();
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm",Locale.CHINA);
        String dateString = dt1.format(date);
        String dateString2 = dt1.format(date2);
        String lastTime = StringUtils.getLastTime(date, date2);
        ((TextView) findViewById(R.id.start_time_text)).setText(dateString);
        ((TextView)findViewById(R.id.end_time_text)).setText(dateString2);
        ((TextView)findViewById(R.id.last_time)).setText(lastTime);
    }


    //选择审核者
    public void onShowSelectAccessors(View view){
        SelectAccessorsDialog dialog = new SelectAccessorsDialog();
        ArrayList<String> accessors = new ArrayList<>();
        accessors.add("卡罗特");
        accessors.add("达尔");
        accessors.add("比克");
        accessors.add("撒旦");
        dialog.setAccessors(accessors);
        dialog.show(getSupportFragmentManager(), "");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.send,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.send){
            if( isViewAnimating ){
                return true;
            }
            isViewAnimating = true;
            ViewUtils.showSnack(toolbar, "提交中…");
            //TODO:提交申请
            PuncherApi.getApi().endOut2Server(new ResultHandlerInterface() {
                @Override
                public void onResponse(Object response) {
                    Snackbar snackbar = ViewUtils.snack( toolbar , "提交成功！" );
                    snackbar.setCallback(new Snackbar.Callback() {
                        @Override
                        public void onDismissed(Snackbar snackbar, int event) {
                            super.onDismissed(snackbar, event);
                            StopOutTimerEvent stopOutTimerEvent = new StopOutTimerEvent();
                            EventBus.getDefault().post( stopOutTimerEvent );
                            finish();
                            isViewAnimating = false;
                        }
                    });
                    snackbar.show();
                }

                @Override
                public void onError(Exception e) {
                    Snackbar snackbar = ViewUtils.snack( toolbar , "提交失败，请重试！" );
                    snackbar.setCallback(new Snackbar.Callback() {
                        @Override
                        public void onDismissed(Snackbar snackbar, int event) {
                            super.onDismissed(snackbar, event);
                            isViewAnimating = false;
                        }
                    });
                    snackbar.show();
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //TODO:确认是否有未填写的信息
        fastLog("点击返回");
        super.onBackPressed();
    }
}
