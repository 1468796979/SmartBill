package com.pang.smartbill.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.pang.smartbill.AboutActivity;
import com.pang.smartbill.HistoryActivity;
import com.pang.smartbill.MonthChartActivity;
import com.pang.smartbill.R;
import com.pang.smartbill.SettingActivity;

public class MoreDialog extends Dialog implements View.OnClickListener {
    Button aboutBtn,settingBtn,historyBtn,infoBtn;
    ImageView errorIv;
    public MoreDialog(@NonNull Context context) {
        super(context);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_more);
        aboutBtn = findViewById(R.id.dialog_more_btn_about);
        settingBtn = findViewById(R.id.dialog_more_btn_setting);
        historyBtn = findViewById(R.id.dialog_more_btn_record);
        infoBtn = findViewById(R.id.dialog_more_btn_info);
        errorIv = findViewById(R.id.dialog_more_iv);

        aboutBtn.setOnClickListener(this);
        settingBtn.setOnClickListener(this);
        historyBtn.setOnClickListener(this);
        infoBtn.setOnClickListener(this);
        errorIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        int viewId = v.getId();


        if (viewId == R.id.dialog_more_btn_about) {

            intent.setClass(getContext(), AboutActivity.class);
            getContext().startActivity(intent);

        } else if (viewId == R.id.dialog_more_btn_setting) {

            intent.setClass(getContext(), SettingActivity.class);
            getContext().startActivity(intent);

        } else if (viewId == R.id.dialog_more_btn_record) {

            intent.setClass(getContext(), HistoryActivity.class);
            getContext().startActivity(intent);

        } else if (viewId == R.id.dialog_more_btn_info) {

            intent.setClass(getContext(), MonthChartActivity.class);
            getContext().startActivity(intent);

        } else if (viewId == R.id.dialog_more_iv) {
            // Handle the click event for dialog_more_iv
        }

        cancel();
    }


    public void setDialogSize(){

        Window window = getWindow();

        WindowManager.LayoutParams wlp = window.getAttributes();

        Display d = window.getWindowManager().getDefaultDisplay();
        wlp.width = (int)(d.getWidth());
        wlp.gravity = Gravity.BOTTOM;
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes(wlp);
    }
}
