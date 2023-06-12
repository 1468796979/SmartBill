package com.pang.smartbill;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.os.Bundle;
import android.content.Intent;

public class GroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_group);



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

//            switch (item.getItemId()) {
//                case R.id.navigation_main:
//
//                    return true;
//                case R.id.navigation_group:
//                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                    finish();
//                    return true;
//                case R.id.navigation_setting:
//                    startActivity(new Intent(getApplicationContext(), SettingActivity.class));
//
//                    finish();
//                    return true;
//            }

            return false;
        });

    }
}