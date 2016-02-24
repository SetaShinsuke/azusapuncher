package com.azusasoft.puncher.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.azusasoft.puncher.R;
import com.azusasoft.puncher.api.Leave;
import com.azusasoft.puncher.framework.BaseActivity;
import com.azusasoft.puncher.utils.Constants;
import com.azusasoft.puncher.utils.OnClickChecker;
import com.azusasoft.puncher.utils.UtilMethod;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by SETA on 2016/2/22.
 * 1.【请假】申请记录;
 * 2.【外勤】申请记录;
 */
public class LeaveHistoryActivity extends BaseActivity {

    private Context context;
    private RecyclerView leaveListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_history);
        context = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolBar(toolbar);

        leaveListView = (RecyclerView)findViewById(R.id.leave_list);
        LeaveHistoryAdapter listAdapter = new LeaveHistoryAdapter(context);
        leaveListView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        leaveListView.setLayoutManager(layoutManager);
        listAdapter.setOnClickListener(new OnListItemClick());
        listAdapter.setLeaves( UtilMethod.initData() );
    }

    class OnListItemClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            int position = leaveListView.indexOfChild(v);
            Snackbar.make(v,"点击条目"+position,Snackbar.LENGTH_SHORT).show();
        }
    }

}

/**
 * 发起的请假/外勤的Adapter
 * */
class LeaveHistoryAdapter extends RecyclerView.Adapter{
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Leave> leaves = new ArrayList<>();
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    };
    public void setOnClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    public LeaveHistoryAdapter(Context context){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setLeaves(ArrayList<Leave> leaves){
        this.leaves = leaves;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = layoutInflater.inflate(R.layout.leave_list_item,parent,false);
        LeaveListHolder holder = new LeaveListHolder(convertView);
        holder.status = (TextView) convertView.findViewById(R.id.status_text);
        holder.statusBkg = convertView.findViewById(R.id.status_bkg);
        holder.title = (TextView) convertView.findViewById(R.id.leave_title);
        holder.time = (TextView) convertView.findViewById(R.id.time_text);
        holder.statusDetail = (TextView) convertView.findViewById(R.id.status_detail);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        //TODO:设置数据
        LeaveListHolder holder = (LeaveListHolder) viewHolder;
        Leave leave = leaves.get(position);
        int color = UtilMethod.getColorByStatus( context , leave.getStatus() );
        holder.statusBkg.setBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return this.leaves.size();
    }

    class LeaveListHolder extends RecyclerView.ViewHolder{
        TextView status,title,time,statusDetail;
        View statusBkg;

        public LeaveListHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener( onClickListener );
        }
    }
}
