package com.azusasoft.puncher.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;

import com.azusasoft.puncher.utils.UtilMethod;

import java.util.ArrayList;

/**
 * Created by SETA on 2016/2/25.
 */
public class SelectAccessorsDialog extends DialogFragment {
    private ArrayList<String> accessors = new ArrayList<>();
    private ArrayList<String> selectedAccessors = new ArrayList<>();
    //TODO:接口回调，返回已选择的审核者(或可抛出事件)

    public void setAccessors( ArrayList<String> accessors ){
        this.accessors = new ArrayList<>(accessors);
    }

    private CharSequence[] getAccessors(){
        return accessors.toArray(new CharSequence[accessors.size()]);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
        builder.setTitle("选择审核者")
                .setMultiChoiceItems(getAccessors(), null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if(isChecked){
                            selectedAccessors.add( accessors.get(which) );
                        }else {
                            selectedAccessors.remove( accessors.get(which) );
                        }
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UtilMethod.fastLog( "选择审核者 : " + selectedAccessors );
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }
}
