package com.azusasoft.puncher.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.azusasoft.puncher.R;
import com.azusasoft.puncher.api.Status;
import com.azusasoft.puncher.framework.BaseActivity;
import com.azusasoft.puncher.utils.UtilMethod;

import java.util.ArrayList;

import static com.azusasoft.puncher.utils.UtilMethod.fastLog;

/**
 * Created by SETA on 2016/2/22.
 * 1.xxx的请假进度页面;
 * 2.xxx的外勤进度页面;
 * 3.审批xxx的请假的页面;
 * 4.审批xxx的外勤的页面;
 */
public class LeaveDetailActivity extends BaseActivity {
    private Context context;
    private Toolbar toolbar;
    private Menu menu;
    private boolean isAdmin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_leave_detail);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        initToolBar(toolbar);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setTitle("给给给的请假");
        }

        Bundle bundle = getIntent().getExtras();
        Boolean isAdmin = bundle.getBoolean("isAdmin");
        this.isAdmin = isAdmin;
        if(menu!=null) {
            if (isAdmin) {
                menu.findItem(R.id.undo).setVisible(false);
                menu.findItem(R.id.agree).setVisible(true);
                menu.findItem(R.id.refuse).setVisible(true);
            } else {
                menu.findItem(R.id.undo).setVisible(true);
                menu.findItem(R.id.agree).setVisible(false);
                menu.findItem(R.id.refuse).setVisible(false);
            }
        }
        fastLog("onCreate.");

        ListView timeline = (ListView)findViewById(R.id.status_timeline);
        View headerView = View.inflate(context , R.layout.leave_detail_header,null);
        timeline.addHeaderView( headerView );
        StatusTimelineAdapter adapter = new StatusTimelineAdapter(context);
        ArrayList<Status> statuses = new ArrayList<>();
        for(int i=0;i<15;i++){
            statuses.add(new Status());
        }
        adapter.setStatuses(statuses);
        timeline.setAdapter( adapter );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        fastLog("onCreateMenu.");
        getMenuInflater().inflate(R.menu.menu_detail,menu);
        this.menu = menu;
        if (isAdmin) {
            menu.findItem(R.id.undo).setVisible(false);
            menu.findItem(R.id.agree).setVisible(true);
            menu.findItem(R.id.refuse).setVisible(true);
        } else {
            menu.findItem(R.id.undo).setVisible(true);
            menu.findItem(R.id.agree).setVisible(false);
            menu.findItem(R.id.refuse).setVisible(false);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.undo){
//            ViewUtils.showSnack(toolbar, "撤回中…");
            //TODO:撤回申请
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle("确定要撤回吗？")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .create();
            alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}


class StatusTimelineAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<Status> statuses = new ArrayList<>();

    public StatusTimelineAdapter(Context context){
        this.context = context;
    }

    public void setStatuses(ArrayList<Status> statuses){
        this.statuses = new ArrayList<>(statuses);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return statuses.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.status_timeline_item,parent,false);
            holder = new Holder();
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.date = (TextView) convertView.findViewById(R.id.time);
            holder.statusIcon = (TextView) convertView.findViewById(R.id.status_icon);
            holder.statusDetail = (TextView) convertView.findViewById(R.id.status_detail);
            holder.lineBtm = convertView.findViewById(R.id.line_vertical_btm);
            convertView.setTag(holder);
            convertView.setOnClickListener(null);
        }
        convertView.setVisibility(View.VISIBLE);
        if(position == statuses.size()-1 ){
            convertView.setVisibility(View.INVISIBLE);
            return convertView;
        }
        holder = (Holder)convertView.getTag();
        holder.lineBtm.setVisibility(View.VISIBLE);
        if(position == statuses.size()-2){
            holder.lineBtm.setVisibility(View.GONE);
        }
        return convertView;
    }

    class Holder{
        TextView name,date,statusIcon,statusDetail;
        View lineBtm;
    }
}