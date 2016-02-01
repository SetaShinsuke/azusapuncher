package com.azusasoft.puncher;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private NavigationView navigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.navigationView = (NavigationView) findViewById(R.id.left_drawer_container);
//        initDrawerMenu();

//        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
//        drawerLayout.closeDrawer(GravityCompat.START);
    }

    private ArrayList<String> weeklyDetail = new ArrayList<>();
    private final int SUMMARIZE = 0;
    private final int WEEKLY_DETAIL = 1;
    private final int EXIT_ITEM = 2;

    private void initDrawerMenu(){
        Menu localMenu;
        if(this.navigationView!=null && this.navigationView.getMenu()!=null){
            localMenu = this.navigationView.getMenu();
            localMenu.clear();
            localMenu.add(SUMMARIZE,0,0,"周到勤天数").setIcon(R.drawable.ic_date_present_black);
            localMenu.add(SUMMARIZE,1,1,"本周累计罚款").setIcon(R.drawable.ic_punish_black);

            localMenu.add(WEEKLY_DETAIL, 2, 2, "迟到");
            localMenu.add(WEEKLY_DETAIL, 3, 3, "没打卡");
            localMenu.add(WEEKLY_DETAIL, 4, 4, "没签退");
            localMenu.add(WEEKLY_DETAIL, 5, 5, "外勤");
            localMenu.add(WEEKLY_DETAIL, 6, 6, "病假");
            localMenu.add(WEEKLY_DETAIL, 7, 7, "事假");

            localMenu.add(EXIT_ITEM,8,8,"切换账号").setIcon(R.drawable.ic_run);
            localMenu.add(EXIT_ITEM,9,9,"退出应用").setIcon(R.drawable.exit);


        }
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
