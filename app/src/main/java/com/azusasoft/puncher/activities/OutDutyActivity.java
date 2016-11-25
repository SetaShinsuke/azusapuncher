package com.azusasoft.puncher.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.azusasoft.puncher.R;
import com.azusasoft.puncher.api.PuncherApi;
import com.azusasoft.puncher.api.ResultHandlerInterface;
import com.azusasoft.puncher.dialogs.SelectAccessorsDialog;
import com.azusasoft.puncher.events.StopOutTimerEvent;
import com.azusasoft.puncher.framework.BaseActivity;
import com.azusasoft.puncher.api.PuncherApi.*;
import com.azusasoft.puncher.utils.StringUtils;
import com.azusasoft.puncher.utils.UtilMethod;
import com.azusasoft.puncher.utils.ViewUtils;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import de.greenrobot.event.EventBus;

import static com.azusasoft.puncher.api.PuncherApi.getApi;

/**
 * Created by SETA on 2016/2/22.
 * 新外勤申请
 * 注：
 * 外勤日期：默认为当前日期，外勤开始后为 开始日期
 * 外勤详情：外勤开始前后都可进行编辑，点击结束后若缺失信息(审核人/理由)，则提示填写
 * 申请提交：确认信息完整后，自动提交，提示【审核完成后，结算工时】
 */
public class OutDutyActivity extends BaseActivity {
    private Context context;
    private Toolbar toolbar;

    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_duty);
        this.context = this;
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolBar(toolbar);

        styleText((TextView) findViewById(R.id.time_text));
        styleText((TextView) findViewById(R.id.time_text_second));

        setTime();
        disableViews();
        if(getApi().isOut()){
            startCount();
            enableViews();
        }

//        EventBus.getDefault().register(this);
    }

    private void styleText(TextView textView){
        Typeface robotoThin = Typeface.createFromAsset(context.getAssets(),
                "fonts/Roboto-Thin.ttf"); //use this.getAssets if you are calling from an Activity
        Typeface robotoRegular = Typeface.createFromAsset(context.getAssets(),
                "fonts/Roboto-Regular.ttf");
        Typeface robotCondensedLight = Typeface.createFromAsset(context.getAssets(),
                "fonts/"+"RobotoCondensed-Light.ttf");
        Typeface robotCondensedRegular = Typeface.createFromAsset(context.getAssets(),
                "fonts/"+"RobotoCondensed-Regular.ttf");
        Typeface robotLight = Typeface.createFromAsset(context.getAssets(),
                "fonts/"+"Roboto-Light.ttf");
        textView.setTypeface(robotoThin);
    }

    private void setTime(){
        String s1 = "00 00";
        String s2 = " 00";
        TextView textView1 = (TextView) findViewById(R.id.time_text);
        TextView textView2 = (TextView) findViewById(R.id.time_text_second);
        if( !getApi().isOut() ){
            textView1.setText(s1);
            textView2.setText(s2);
        }else {
            long period = System.currentTimeMillis() - getApi().getLastOutTime();
            s1 = StringUtils.getHour(period) + " " + StringUtils.getMinute(period);
            s2 = " " + StringUtils.getSecond(period);
            textView1.setText(s1);
            textView2.setText(s2);
        }
    }

    public void onFabClick(View view){
        if( getApi().isOut() ){
            pauseOut();
        }else {
            if( getApi().isAtWork() ){
                View view1 = LayoutInflater.from(context).inflate(R.layout.alert_text,null,false);
                AlertDialog dialog = new AlertDialog.Builder(context)
                        .setTitle("提示")
                        .setView( view1 )
                        .setPositiveButton("开始外勤", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startOut();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create();
//                        dialog.setContentView(R.layout.alert_text);
                        dialog.show();
                return;
            }
            startOut();
        }
    }

    private void startOut(){
        getApi().startOut2Server(new ResultHandlerInterface() {
            @Override
            public void onResponse(Object response) {
                startCount();
                enableViews();
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
    //确认提交
    private void pauseOut() {
            AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("外勤结束吗?")
                    .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            stopOut();
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .create();
            dialog.show();
    }
    private void stopOut(){
        //TODO:停下计时器，显示提交详情
        Intent intent = new Intent(this,OutFormActivity.class);
        startActivity(intent);
    }

    private void startCount(){
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        timer.scheduleAtFixedRate(new CountTask(), 100, 1000);
    }

    private class CountTask extends TimerTask{

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setTime();
                }
            });
        }
    }

    private void enableViews(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_stop_white, context.getTheme()));
        } else {
            fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_stop_white));
        }
    }
    private void disableViews(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_arrow, context.getTheme()));
        } else {
            fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_arrow));
        }
    }

    public void onEvent(StopOutTimerEvent event){
        UtilMethod.fastLog("receive back. ");
        timer.cancel();
        finish();
    }

}
