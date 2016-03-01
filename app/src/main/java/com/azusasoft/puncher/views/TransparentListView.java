package com.azusasoft.puncher.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by SETA on 2016/3/1.
 */
public class TransparentListView extends ListView {
    public TransparentListView(Context context) {
        super(context);
    }

    public TransparentListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TransparentListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TransparentListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return false;
//        return super.dispatchTouchEvent(ev);
    }
}
