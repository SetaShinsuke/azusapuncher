package com.azusasoft.puncher.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
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
import com.azusasoft.puncher.framework.BaseActivity;

import static com.azusasoft.puncher.utils.UtilMethod.fastLog;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{
    private Context context;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar toolbar;
    private NavigationView.OnNavigationItemSelectedListener onDrawerMenuClick;

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


        //修改字体
//        TextView dayWeekText = (TextView) findViewById(R.id.day_of_week);
//        TextView dateText    = (TextView) findViewById(R.id.main_date);
        TextView timeText = (TextView) findViewById(R.id.time_text);
        Typeface robotoThin = Typeface.createFromAsset(context.getAssets(),
                "fonts/Roboto-Thin.ttf"); //use this.getAssets if you are calling from an Activity
        Typeface robotoRegular = Typeface.createFromAsset(context.getAssets(),
                "fonts/Roboto-Regular.ttf");
        timeText.setTypeface(robotoThin);
//        dayWeekText.setTypeface(robotoThin);
//        dateText.setTypeface(robotoRegular);

//        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
//        drawerLayout.closeDrawer(GravityCompat.START);
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
    }

    /**
     * 点击Drawer Menu
     * */
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout_btn:
                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
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
        return true;
    }
}
