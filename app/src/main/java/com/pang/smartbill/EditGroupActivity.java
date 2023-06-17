package com.pang.smartbill;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.pang.smartbill.db.DBManager;
import com.pang.smartbill.db.GroupBean;
import com.pang.smartbill.db.MemberBean;
import com.pang.smartbill.group.adapter.GroupAdapter;
import com.pang.smartbill.group.adapter.MemberAdapter;

import java.util.ArrayList;
import java.util.List;

public class EditGroupActivity extends AppCompatActivity  implements View.OnClickListener{
    private EditText titleEditText;
    private EditText descriptionEditText;
    private EditText categoryEditText;
    private EditText currencyEditText;
    private Button updateGroupButton;
    private Button deleteGroupButton;
    private GroupBean groupBean;
    private Button saveTv,addMemberBt;

    private ImageView backIB;
    Long id;
    ListView memberLv;
    List<MemberBean> mDatas;
    private MemberAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_group);

        mDatas = new ArrayList<>();

        adapter = new MemberAdapter(this,mDatas);


        memberLv = findViewById(R.id.group_edit_add_member_lv);
        backIB = findViewById(R.id.group_edit_record_iv_back);
        updateGroupButton = findViewById(R.id.group_edit_save_bt);
        addMemberBt = findViewById(R.id.group_edit_btn_member_add);
        memberLv.setAdapter(adapter);



        // Retrieve the Group object from the intent
        groupBean = getIntent().getParcelableExtra("grouptb");


        id = getIntent().getExtras().getLong("id");
        String grouptitle = getIntent().getExtras().getString("grouptitle");
        String description = getIntent().getExtras().getString("description");

        // Initialize views
        titleEditText = findViewById(R.id.group_edit_title_et);
        descriptionEditText = findViewById(R.id.group_edit_description_et);

        // Initialize the GroupBean object
        groupBean = new GroupBean();
        groupBean.setId(id);
        groupBean.setGrouptitle(grouptitle);
        groupBean.setDescription(description);

        // Display the existing group details
        titleEditText.setText(grouptitle);
        descriptionEditText.setText(description);

        addMemberBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent1 = new Intent(EditGroupActivity.this, AddEditMemberActivity.class);
//                intent1.putExtra("id",id);
                startActivity(intent1);


            }
        });







        // Update group button click listener
        updateGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Retrieve the updated group details
                String updatedTitle = titleEditText.getText().toString();
                String updatedDescription = descriptionEditText.getText().toString();


                // Update the Group object with the updated details

                groupBean.setGrouptitle(updatedTitle);
                groupBean.setDescription(updatedDescription);
                groupBean.setCurrency("MYR");
                groupBean.setCategory("others");

                DBManager.updateInfoToGrouptb(groupBean);

                finish();
            }
        });


        backIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onClick(View v) {

    }




}

