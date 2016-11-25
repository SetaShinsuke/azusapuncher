package com.azusasoft.puncher.activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.azusasoft.puncher.R;
import com.azusasoft.puncher.dialogs.SelectAccessorsDialog;
import com.azusasoft.puncher.framework.BaseActivity;
import com.azusasoft.puncher.utils.UtilMethod;
import com.azusasoft.puncher.utils.ViewUtils;

import org.w3c.dom.Text;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by SETA on 2016/2/22.
 */
public class NewLeaveActivity extends BaseActivity {
    private Context context;
    private Toolbar toolbar;
    private TextView accessorsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_leave);
        context = this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolBar(toolbar);
        accessorsText = (TextView) findViewById(R.id.accessors_text);
    }

    //选择日期
    public void chooseStartDate(View view){
        Calendar c = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                ((TextView)findViewById(R.id.start_time_text)).setText( year + "年" + monthOfYear + "月" + dayOfMonth + "日");
            }
        }, c.get(Calendar.YEAR) , c.get(Calendar.MONTH) , c.get(Calendar.DAY_OF_MONTH) );
        datePickerDialog.show();
    }
    //选择时间
    public void chooseEndTime(View view){
        Calendar c = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                ((TextView)findViewById(R.id.end_time_text)).setText( year + "年" + monthOfYear + "月" + dayOfMonth + "日");
            }
        }, c.get(Calendar.YEAR) , c.get(Calendar.MONTH) , c.get(Calendar.DAY_OF_MONTH) );
        datePickerDialog.show();
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
        dialog.show( getSupportFragmentManager() , "");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.send,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.send){
            ViewUtils.showSnack(toolbar, "提交中…");
            //TODO:提交申请
        }
        return super.onOptionsItemSelected(item);
    }
}
