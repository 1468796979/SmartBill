package com.pang.smartbill;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pang.smartbill.db.DBManager;
import com.pang.smartbill.group.CreateNewGroupActivity;
import com.pang.smartbill.group.frag_group_record.CreateNewGroupFragment;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

public class GroupActivity extends AppCompatActivity  implements View.OnClickListener{

    ListView groupLv;
    ImageButton addGroupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_group);


//
//        groupLv.findViewById(R.id.group_lv);
//        addGroupBtn.findViewById(R.id.group_main_btn_add);
//        groupLv.setOnClickListener(this);
//        addGroupBtn.setOnClickListener(this);


        bottomNavigationView.setOnItemSelectedListener(item -> {

            int itemId =item.getItemId();

            if(itemId == R.id.navigation_main){

                startActivity(new Intent(getApplicationContext(), MainActivity.class));

                finish();
                return true;
            } else if (itemId == R.id.navigation_group) {


                return true;

            } else if (itemId == R.id.navigation_setting) {

                startActivity(new Intent(getApplicationContext(), SettingActivity.class));

                finish();
                return true;

            }


            return false;
        });

    }

//    private void initView() {
//        groupLv.findViewById(R.id.group_lv);
//        addGroupBtn.findViewById(R.id.group_main_btn_add);
//        groupLv.setOnClickListener(this);
//        addGroupBtn.setOnClickListener(this);
//    }


    public void onClick(View view) {


        int viewId =view.getId();
        if(viewId == R.id.group_main_btn_add){

            Intent it3 = new Intent(this, CreateNewGroupActivity.class);
            startActivity(it3);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}