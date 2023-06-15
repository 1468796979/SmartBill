package com.pang.smartbill;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pang.smartbill.db.DBManager;
import com.pang.smartbill.db.GroupBean;
import com.pang.smartbill.group.CreateNewGroupActivity;
import com.pang.smartbill.group.adapter.GroupAdapter;
import com.pang.smartbill.group.frag_group_record.CreateNewGroupFragment;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class GroupActivity extends AppCompatActivity  implements View.OnClickListener{
    ListView groupLv;
    List<GroupBean> mDatas;
    private GroupAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_group);

        groupLv = findViewById(R.id.group_lv);


        mDatas = new ArrayList<>();

        adapter = new GroupAdapter(this,mDatas);

        groupLv.setAdapter(adapter);
//        addGroupBtn.findViewById(R.id.group_main_btn_add);
//        groupLv.setOnClickListener(this);
//        addGroupBtn.setOnClickListener(this);

        List<String> groupLv = new ArrayList<>();


        ListView groupLv1 = findViewById(R.id.group_lv);

        groupLv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GroupBean groupBean = (GroupBean) parent.getItemAtPosition(position);
                Intent intent = new Intent(GroupActivity.this, CreateNewGroupActivity.class);
                intent.putExtra("id", groupBean.getId());
                intent.putExtra("grouptitle", groupBean.getGrouptitle());
                intent.putExtra("description", groupBean.getDescription());
                startActivityForResult(intent, 1);
            }
        });





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
        loadDBData();

    }

    private void loadDBData() {
        List<GroupBean> list = DBManager.getInfoFromGrouptb();
        mDatas.clear();
        mDatas.addAll(list);
        adapter.notifyDataSetChanged();
    }
}