package com.pang.smartbill;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.pang.smartbill.db.DBManager;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {


    Button about,clear,history,setClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_setting);



        initView();


        bottomNavigationView.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();

            if (itemId == R.id.navigation_main) {

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.navigation_group) {


                startActivity(new Intent(getApplicationContext(), GroupActivity.class));
                finish();
                return true;

            } else if (itemId == R.id.navigation_setting) {

                return true;

            }
            return false;
        });
    }

    private void initView() {
        about = findViewById(R.id.dialog_more_btn_about);
        clear = findViewById(R.id.dialog_more_btn_setting);
        history = findViewById(R.id.dialog_more_btn_record);
        setClass = findViewById(R.id.dialog_more_btn_info);
        about.setOnClickListener(this);
        clear.setOnClickListener(this);
        history.setOnClickListener(this);
        setClass.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {


        int viewId = view.getId();


        if (viewId == R.id.dialog_more_btn_about) {

            Intent about = new Intent(this, AboutActivity.class);
            startActivity(about);

        } else if (viewId == R.id.dialog_more_btn_setting) {

            showDeleteDialog();

        } else if (viewId == R.id.dialog_more_btn_record) {


            Intent history = new Intent(this, HistoryActivity.class);
            startActivity(history);

        } else if (viewId == R.id.dialog_more_btn_info) {

            Intent setClass = new Intent(this, MonthChartActivity.class);
            startActivity(setClass);

        }


    }




    private void showDeleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete prompt")
                .setMessage("Are you sure you want to delete all records？\nNote: After deletion cannot be restored, please choose carefully!\n" +
                        "\n")
                .setPositiveButton("Cancel",null)
                .setNegativeButton("Ensure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBManager.deleteAllAccount();
                        Toast.makeText(SettingActivity.this,"successfully delete\n！",Toast.LENGTH_SHORT).show();
                    }
                });
        builder.create().show();
    }
}
