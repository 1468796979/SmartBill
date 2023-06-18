package com.pang.smartbill;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pang.smartbill.db.DBManager;
import com.pang.smartbill.db.GroupBean;
import com.pang.smartbill.db.MemberBean;
import com.pang.smartbill.group.adapter.MemberAdapter;

import java.util.ArrayList;
import java.util.List;

public class AddEditGroupActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText titleEditText;
    private EditText descriptionEditText;
    private EditText categoryEditText;
    private EditText currencyEditText;
    private Button updateGroupButton;
    private Button deleteGroupButton;
    private GroupBean groupBean;
    private Button saveTv, addMemberBt;
    private MemberBean memberBean;
    private ImageView backIB;
    long group_id;
    ListView memberLv;
    List<MemberBean> mDatas;
    private MemberAdapter adapter;
    private int memberId;
    private boolean isNewGroup; // Flag to indicate if it's a new group or existing group

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_group);

        memberLv = findViewById(R.id.group_edit_add_member_lv);
        backIB = findViewById(R.id.group_edit_record_iv_back);
        updateGroupButton = findViewById(R.id.group_edit_save_bt);
        addMemberBt = findViewById(R.id.group_edit_btn_member_add);
        titleEditText = findViewById(R.id.group_edit_title_et);
        descriptionEditText = findViewById(R.id.group_edit_description_et);

        DBManager.initDB(this);

        mDatas = new ArrayList<>();

        adapter = new MemberAdapter(this, mDatas);
        memberLv.setAdapter(adapter);

        // Check if it's a new group or existing group
        isNewGroup = getIntent().getBooleanExtra("new_group", false);
        if (isNewGroup) {
            // New group, initialize a new GroupBean object
            groupBean = new GroupBean();
        } else {
            // Existing group, retrieve the Group object from the intent
            groupBean = getIntent().getParcelableExtra("grouptb");

            group_id = getIntent().getExtras().getLong("id");
            String grouptitle = getIntent().getExtras().getString("grouptitle");
            String description = getIntent().getExtras().getString("description");




            groupBean = new GroupBean();
            groupBean.setId(group_id);
            groupBean.setGrouptitle(grouptitle);
            groupBean.setDescription(description);

            // Display the existing group details
            titleEditText.setText(grouptitle);
            descriptionEditText.setText(description);
        }

        addMemberBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String titleEt = titleEditText.getText().toString();

                if(TextUtils.isEmpty(titleEt)){

                    Toast.makeText(getApplicationContext(),"Title can not be empty!", Toast.LENGTH_LONG).show();
                }else {

                    if (isNewGroup) {
                        // Add new group to the database
                        long lastGroupId = DBManager.getLastGroupId(); // Retrieve the last group ID from the database
                        long newGroupId = lastGroupId + 1; // Increment the last group ID by 1
                        Intent intent1 = new Intent(AddEditGroupActivity.this, AddMemberActivity.class);
                        intent1.putExtra("id", newGroupId);

                        startActivity(intent1);
                    } else {
                        // Update existing group in the database
                        Intent intent1 = new Intent(AddEditGroupActivity.this, AddMemberActivity.class);
                        long group_id = groupBean.getId();
                        intent1.putExtra("id", group_id);

                        startActivity(intent1);
                    }
                }
            }
        });

        setLVLongClickListener();

        // Update or create group button click listener
        updateGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String titleEt = titleEditText.getText().toString();

                if (TextUtils.isEmpty(titleEt)) {
                    Toast.makeText(getApplicationContext(), "Title can not be empty!", Toast.LENGTH_LONG).show();
                } else {

                // Retrieve the updated group details
                String updatedTitle = titleEditText.getText().toString();
                String updatedDescription = descriptionEditText.getText().toString();

                // Update the Group object with the updated details
                groupBean.setGrouptitle(updatedTitle);
                groupBean.setDescription(updatedDescription);
                groupBean.setCurrency("MYR");
                groupBean.setCategory("others");

                if (isNewGroup) {
                    // Add new group to the database
                    DBManager.insertInfoToGrouptb(groupBean);
                } else {
                    // Update existing group in the database
                    DBManager.updateInfoToGrouptb(groupBean);
                }

                finish();

            }
            }
        });

        backIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setLVLongClickListener() {
        memberLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                int pos = position;
                MemberBean clickBean = mDatas.get(pos);
                showDeleteItemDialog(clickBean);
                return false;
            }
        });

    }

    private void showDeleteItemDialog(final MemberBean clickBean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Prompt").setMessage("Are you sure you want to delete this record?")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Ensure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int click_id = clickBean.getMemberId();
                        DBManager.deleteItemFromMembertbById(click_id);
                        mDatas.remove(clickBean);
                        adapter.notifyDataSetChanged();
                    }
                });
        builder.create().show();
    }

    @Override
    public void onClick(View v) {
        // Handle button clicks if any
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDBData();
    }

    private void loadDBData() {

        List<MemberBean> list = DBManager.getInfoFromMembertb();
        if (isNewGroup) {
            mDatas.clear();
            long lastGroupId = DBManager.getLastGroupId(); // Retrieve the last group ID from the database
            long newGroupId = lastGroupId +1;
            for (MemberBean member : list) {
                if (member.getMemberGroupId() == newGroupId) {
                    mDatas.add(member);
                }
            }
            adapter.notifyDataSetChanged();
        } else {
            mDatas.clear();
            for (MemberBean member : list) {
                if (member.getMemberGroupId() == group_id) {
                    mDatas.add(member);
                }
            }
            adapter.notifyDataSetChanged();
        }


    }


}