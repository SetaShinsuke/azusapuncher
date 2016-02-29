package com.azusasoft.puncher.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.azusasoft.puncher.R;
import com.azusasoft.puncher.api.Leave;
import com.azusasoft.puncher.framework.BaseActivity;
import com.azusasoft.puncher.utils.UtilMethod;

import java.util.ArrayList;

/**
 * Created by SETA on 2016/2/22.
 * 1.审核【请假】的列表
 * 2.审核【外勤】的列表
 */
public class VerifyHistoryActivity extends BaseActivity{
    private Context context;
    private RecyclerView verifyListView;
    private Toolbar toolbar;
    private ArrayList<Leave> leaves = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_history);
        context = this;

        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolBar(toolbar);

        verifyListView = (RecyclerView)findViewById(R.id.leave_list);
        VerifyHistoryAdapter listAdapter = new VerifyHistoryAdapter(context);
        verifyListView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        verifyListView.setLayoutManager(layoutManager);
        listAdapter.setOnClickListener(new OnListItemClick());
        this.leaves = UtilMethod.initData();
        listAdapter.setLeaves( leaves );
    }

    class OnListItemClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            int position = verifyListView.indexOfChild(v);
            Intent intent = new Intent(context,LeaveDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putBoolean("isAdmin",true);
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.verify_filter_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ArrayList<Leave> filteredLeaves = new ArrayList<>();
        switch (item.getItemId()){
            case R.id.verified:
                //TODO:筛选已审核条目
                for(int i=0;i<this.leaves.size();i++){
                    if(leaves.get(i).getStatus() == 1){
                        filteredLeaves.add(leaves.get(i));
                    }
                }
                break;
            case R.id.not_verified:
                //TODO:筛选未审核条目
                for(int i=0;i<this.leaves.size();i++){
                    if(leaves.get(i).getStatus() == 0){
                        filteredLeaves.add(leaves.get(i));
                    }
                }
                break;
            case R.id.show_all:
                //TODO:显示全部
                filteredLeaves = new ArrayList<>(leaves);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        ((VerifyHistoryAdapter) verifyListView.getAdapter()).setLeaves(filteredLeaves);
        return super.onOptionsItemSelected(item); //上层有返回键处理的代码
    }
}

/**
 * 发起的请假/外勤的Adapter
 * */
class VerifyHistoryAdapter extends RecyclerView.Adapter{
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

    public VerifyHistoryAdapter(Context context){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setLeaves(ArrayList<Leave> leaves){
        this.leaves = new ArrayList<>(leaves);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = layoutInflater.inflate(R.layout.leave_list_item,parent,false);
        VerifyListHolder holder = new VerifyListHolder(convertView);
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
        VerifyListHolder holder = (VerifyListHolder) viewHolder;
        Leave leave = leaves.get(position);
        int color = UtilMethod.getColorByStatus( context , leave.getStatus() );
        holder.statusBkg.setBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return this.leaves.size();
    }

    class VerifyListHolder extends RecyclerView.ViewHolder{
        TextView status,title,time,statusDetail;
        View statusBkg;

        public VerifyListHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener( onClickListener );
        }
    }

}
