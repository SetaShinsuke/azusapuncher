package com.azusasoft.puncher.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.azusasoft.puncher.R;

/**
 * Created by SETA on 2015/3/22.
 */
public class SquareImageView extends ImageView {
    private boolean byWidth=true;

    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SquareImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(byWidth){
            setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth()); //Snap to width
        }else {
            setMeasuredDimension(getMeasuredHeight(), getMeasuredHeight()); //Snap to height
        }
    }

    private void init(Context context,AttributeSet attrs){
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.SquareImageView,
                0, 0);
        try {
            byWidth = a.getBoolean(R.styleable.SquareImageView_square_by_width, true);
        } finally {
            a.recycle();
        }
    }
}
