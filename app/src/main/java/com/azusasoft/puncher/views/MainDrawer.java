package com.azusasoft.puncher.views;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.azusasoft.puncher.R;
import com.azusasoft.puncher.api.User;

/**
 * Created by SETA on 2016/3/3.
 */
public class MainDrawer extends NavigationView {
    private Context context;

    public MainDrawer(Context context) {
        super(context);
        initView(context);
    }

    public MainDrawer(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MainDrawer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        this.context = context;
    }

    public void setUser(User user){
        //TODO:显示用户资料
        View v = getHeaderView(0);
        if(v==null){
            return;
        }
        ((TextView) v.findViewById(R.id.user_name)).setText( user.name );
        String userDetail = user.groupName + " " + user.position + " @ID:" + user.id;
        ((TextView) v.findViewById(R.id.user_detail)).setText( userDetail );
    }
}
