package com.pang.smartbill;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pang.smartbill.db.AccountBean;
import com.pang.smartbill.db.DBManager;
import com.pang.smartbill.db.GroupBean;
import com.pang.smartbill.group.CreateNewGroupActivity;
import com.pang.smartbill.group.adapter.GroupAdapter;
import com.pang.smartbill.group.frag_group_record.CreateNewGroupFragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
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
                Intent intent1 = new Intent(GroupActivity.this, EditGroupActivity.class);
                long group_id = groupBean.getId();
                String groupTitile = groupBean.getGrouptitle();
                String description = groupBean.getDescription();
                intent1.putExtra("id",group_id);
                intent1.putExtra("grouptitle", groupTitile);
                intent1.putExtra("description", description);
                startActivity(intent1);

            }
        });

        setLVLongClickListener();




        //Bottom Navigation
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


    private void setLVLongClickListener() {
        groupLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    return false;
                }
                int pos = position-1;
                GroupBean clickBean = mDatas.get(pos+1);


                showDeleteItemDialog(clickBean);
                return false;
            }
        });
    }

    private void showDeleteItemDialog(final GroupBean clickBean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("prompt ").setMessage("Choose the options:")

                .setNegativeButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent1 = new Intent(GroupActivity.this, EditGroupActivity.class);
                        long group_id = clickBean.getId();
                        String groupTitile = clickBean.getGrouptitle();
                        String description = clickBean.getDescription();
                        intent1.putExtra("id",group_id);
                        intent1.putExtra("grouptitle", groupTitile);
                        intent1.putExtra("description", description);
                        startActivity(intent1);

                        adapter.notifyDataSetChanged();
                    }
                })

                .setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        long click_id = clickBean.getId();

                        DBManager.deleteItemFromGrouptbById(click_id);
                        mDatas.remove(clickBean);
                        adapter.notifyDataSetChanged();

                    }
                })
                .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        Intent intent1 = new Intent(GroupActivity.this, EditGroupActivity.class);
                        long group_id = clickBean.getId();
                        String groupTitile = clickBean.getGrouptitle();
                        String description = clickBean.getDescription();
                        intent1.putExtra("id",group_id);
                        intent1.putExtra("grouptitle", groupTitile);
                        intent1.putExtra("description", description);
                        startActivity(intent1);

                        adapter.notifyDataSetChanged();
                    }
                });
        builder.create().show();
    }

    private void loadDBData() {
        List<GroupBean> list = DBManager.getInfoFromGrouptb();
        mDatas.clear();
        mDatas.addAll(list);
        adapter.notifyDataSetChanged();
    }


}