package com.azusasoft.puncher.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.azusasoft.puncher.R;
import com.azusasoft.puncher.api.ResultHandlerInterface;
import com.azusasoft.puncher.framework.BaseActivity;
import com.azusasoft.puncher.utils.StringUtils;
import com.azusasoft.puncher.utils.ViewUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static com.azusasoft.puncher.api.PuncherApi.*;
import static com.azusasoft.puncher.utils.UtilMethod.fastLog;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Context context;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar toolbar;
    private Timer timer;
//    private boolean isAtWork = false;
//    private long punchStartTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;

        setContentView(R.layout.activity_main);
        this.navigationView = (NavigationView) findViewById(R.id.left_drawer_container);
        this.drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView.setNavigationItemSelectedListener(this);

        setSwipeBackEnable(false);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolBar(toolbar);
        initDrawerToggle();

        setDateCard();
        getApi().checkAtWork(new ResultHandlerInterface() {
            @Override
            public void onResponse(Object response) {
                setTime();
                if(getApi().isAtWork()){
                    startCount();
                }
                //外勤中则直接打开外勤计时界面
                if( getApi().isOut() ){
                    Intent intent = new Intent( context,OutDutyActivity.class);
                    context.startActivity( intent );
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void setDateCard(){
        //修改字体
        TextView timeText = (TextView) findViewById(R.id.time_text);
        Typeface robotoThin = Typeface.createFromAsset(context.getAssets(),
                "fonts/Roboto-Thin.ttf"); //use this.getAssets if you are calling from an Activity
        Typeface robotoRegular = Typeface.createFromAsset(context.getAssets(),
                "fonts/Roboto-Regular.ttf");
        timeText.setTypeface(robotoThin);

        TextView yearMonthText = (TextView)findViewById(R.id.year_month);
        TextView dayText = (TextView)findViewById(R.id.day);
        TextView weekdayText = (TextView)findViewById(R.id.weekday);
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        yearMonthText.setText(StringUtils.formDate(date, "yyyy年MM月"));
        dayText.setText(StringUtils.formDate(date, "dd"));
        weekdayText.setText(StringUtils.getDayOfWeek(calendar));
    }

    private void setTime(){
        TextView timeText = (TextView)findViewById(R.id.time_text);
        if(!getApi().isAtWork()){
            String text = "00:00:00";
            timeText.setText(text);
            disableViews();
        }else { //已打卡
            enableViews();
            String text = StringUtils.getLastTime( getApi().getLastPunchTime() , System.currentTimeMillis());
            timeText.setText(text);
        }
    }

    public void onFabClick(View view){
        //TODO:不能连接内网，阻止签到

        if( getApi().isAtWork() ){
            punchOff();
        }else {
            //外勤中！
            if( getApi().isOut() ){
                new AlertDialog.Builder(context)
                        .setTitle("请先结束外勤！")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(context,OutDutyActivity.class);
                                context.startActivity( intent );
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create()
                        .show();
                return;
            }

            punchOn();

        }
    }
    //打开
    public void punchOn(){
        getApi().punchOn2Server(new ResultHandlerInterface() {
            @Override
            public void onResponse(Object response) {
                enableViews();
                //打卡成功，开始计时
                startCount();
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
    //回家
    public void punchOff(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("确定要签退吗？")
                //确定签退
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        getApi().punchOff2Server(new ResultHandlerInterface() {
                            @Override
                            public void onResponse(Object response) {
                                //签退成功
                                timer.cancel();
                                disableViews();
                            }

                            @Override
                            public void onError(Exception e) {
                                //TODO:签退失败
                            }
                        });
                    }
                })
                //取消签退
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.create().show();
    }

    private void startCount(){
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        timer.scheduleAtFixedRate(new CountTask(), 100, 1000);
    }

    class CountTask extends TimerTask{
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
        //字体变彩色
        TextView timeText = (TextView)findViewById(R.id.time_text);
        if(Build.VERSION.SDK_INT >= 23) {
            timeText.setTextColor(getResources().getColor(R.color.colorPrimary, getTheme()));
        }else {
            timeText.setTextColor(getResources().getColor(R.color.colorPrimary));
        }
        //修改fab图标
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_stop_white, context.getTheme()));
        } else {
            fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_stop_white));
        }
    }
    private void disableViews(){
        //字体变灰
        TextView timeText = (TextView)findViewById(R.id.time_text);
        if(Build.VERSION.SDK_INT >= 23) {
            timeText.setTextColor(getResources().getColor(R.color.text_black_min, getTheme()));
        }else {
            timeText.setTextColor(getResources().getColor(R.color.text_black_min));
        }
        //修改fab图标
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_arrow, context.getTheme()));
        } else {
            fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_arrow));
        }
    }

    private void initDrawerToggle(){
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0){
            @Override public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
            @Override public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public void onCardClick(View view){
        fastLog("click");
        ViewUtils.snack(toolbar,"呵呵哒\n哟。").show();
    }

    /**
     * 点击Drawer Menu
     * */
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        if(item.getGroupId()==R.id.detail_group){
            Intent intent = new Intent(this,AllRecordActivity.class);
            context.startActivity(intent);
            return true;
        }
        switch (item.getItemId()){
            case R.id.logout_btn:
                Intent intent = new Intent(this,LoginActivity.class);
                context.startActivity(intent);
                break;
        }
        return true;
    }

    /**
     * 添加申请——右上角加号
     **/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        //TODO:如果不是管理员，隐藏“我审核的”按钮
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.new_leave:
                intent = new Intent(this,NewLeaveActivity.class);
                context.startActivity(intent);
                return true;
            case R.id.new_out:
                intent = new Intent(this,OutDutyActivity.class);
                context.startActivity(intent);
                return true;
            case R.id.i_started:
                intent = new Intent(this,LeaveHistoryActivity.class);
                context.startActivity(intent);
                return true;
            case R.id.i_verified:
                intent = new Intent(this,VerifyHistoryActivity.class);
                context.startActivity(intent);
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
