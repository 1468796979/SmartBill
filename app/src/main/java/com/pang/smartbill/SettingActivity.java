package com.pang.smartbill;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pang.smartbill.db.DBManager;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    public void onClick(View view) {

        int viewId = view.getId();
        if(viewId == R.id.setting_iv_back ){

        } else if (viewId == R.id.setting_tv_clear ) {

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
