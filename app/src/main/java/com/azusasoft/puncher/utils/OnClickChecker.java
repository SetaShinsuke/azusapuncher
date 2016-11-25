package com.azusasoft.puncher.utils;

import java.util.ArrayList;

/**
 * Created by SETA on 2016/2/23.
 * 用来帮助 Activity 判断当前是否可点击某个View
 * [注意!]
 *      此方法比较繁琐，适合对点击判断比较严谨的情况
 *      一般情况下请直接 判断/修改 {@link com.azusasoft.puncher.utils.Constants#isViewAnimating}
 * [用法:]
 *      1.需要阻止点击的地方添加规则 {@link #prevent(String)} ;
 *      2.需要判断可否点击的地方调用 {@link #isClickPrevent(String...)} ;
 *      3.关闭阻止点击 {@link #undoPrevent(String)} ;
 */
public class OnClickChecker {

    private ArrayList<String> unClickableRules = new ArrayList<>();

    /**
     * 添加规则:阻止点击
     * */
    public void prevent(String key){
        if( !this.unClickableRules.contains(key) ){ //已有该条目,则不添加
            this.unClickableRules.add(key);
        }
    }

    /**
     * 添加规则:允许点击
     * */
    public void undoPrevent(String key){
        if( this.unClickableRules.contains(key) ){ //已有该条目，则移除
            this.unClickableRules.remove(key);
        }
    }

    /**
     * @param keys 要判断的规则;
     * 如果判断其中包含不可点击的条目，则返回false
     * */
    public boolean isClickPrevent(String... keys){
        for(String key:keys){
            if(this.unClickableRules.contains(key)){
                return true;
            }
        }
        return false;
    }
}
