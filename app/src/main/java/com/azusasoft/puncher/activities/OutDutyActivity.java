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
import android.view.View;
import android.widget.TextView;

import com.azusasoft.puncher.R;
import com.azusasoft.puncher.dialogs.SelectAccessorsDialog;
import com.azusasoft.puncher.framework.BaseActivity;

import java.util.ArrayList;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_duty);
        this.context = this;
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolBar( toolbar );

        styleText((TextView) findViewById(R.id.time_text));
        styleText((TextView) findViewById(R.id.time_text_second));
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

    private boolean isOut = false;
    public void onFabClick(View view){
        if(isOut){
            pauseOut();
        }else {
            startOut();
        }
    }

    private void startOut(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_stop_white, context.getTheme()));
        } else {
            fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_stop_white));
        }
        isOut = true;
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
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_arrow, context.getTheme()));
        } else {
            fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_arrow));
        }
        isOut = false;
        Intent intent = new Intent(this,OutFormActivity.class);
        startActivity(intent);
    }

}
