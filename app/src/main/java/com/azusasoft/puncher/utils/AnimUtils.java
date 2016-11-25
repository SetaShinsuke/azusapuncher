package com.azusasoft.puncher.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.azusasoft.puncher.R;
import com.azusasoft.puncher.interfaces.AnimListener;

/**
 * Created by SETA on 2016/3/4.
 */
public class AnimUtils {

    public static void revealIn(View view , final AnimListener listener){
        if( Build.VERSION.SDK_INT >= 21 && view.isAttachedToWindow() ) {
            int cx = (view.getLeft() + view.getRight()) / 2;
            int cy = (view.getTop() + view.getBottom()) / 2;
            int finalRadius = Math.max(view.getWidth(), view.getHeight());
            UtilMethod.fastLog("finalRadius : " + finalRadius);
            UtilMethod.fastLog("cx : " + cx);
            UtilMethod.fastLog("cy : " + cy);
            Animator anim =
                    ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);
            view.setVisibility(View.VISIBLE);
            if(listener!=null) {
                anim.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        listener.onAnimEnd();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
            }
            anim.start();
        }else if(listener!=null){
            listener.onAnimEnd();
        }
    }

    public static void revealOut(final View view ,final AnimListener listener) {
        if ( Build.VERSION.SDK_INT >= 21 && view.isAttachedToWindow() ) {
            int cx = view.getWidth() / 2;
            int cy = view.getHeight() / 2;
            float initialRadius = (float) Math.hypot(cx, cy);
            Animator anim =
                    ViewAnimationUtils.createCircularReveal(view, cx, cy, initialRadius, 0);
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    view.setVisibility(View.INVISIBLE);
                }
            });
            if(listener!=null) {
                anim.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        listener.onAnimEnd();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
            }
            anim.start();
        }else if(listener!=null){
            listener.onAnimEnd();
        }
    }


}
