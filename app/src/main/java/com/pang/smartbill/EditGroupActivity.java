package com.pang.smartbill;



import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pang.smartbill.db.DBManager;
import com.pang.smartbill.db.GroupBean;
import com.pang.smartbill.db.GroupDatabase;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_group);



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



        backIB = findViewById(R.id.group_edit_record_iv_back);
        updateGroupButton = findViewById(R.id.group_edit_save_bt);



        // Update group button click listener
        updateGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Retrieve the updated group details
                String updatedTitle = titleEditText.getText().toString();
                String updatedDescription = descriptionEditText.getText().toString();


                // Update the Group object with the updated details
                GroupBean groupBean1 = new GroupBean();
                groupBean1.setGrouptitle(updatedTitle);
                groupBean1.setDescription(updatedDescription);

                DBManager.insertInfoToGrouptb(groupBean1);

                finish();
            }
        });


        backIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });






        // Delete group button click listener
//        deleteGroupButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Delete the group from the database or perform any necessary operations
//
//                // Finish the activity and go back to the previous screen
//                finish();
//            }
//        });
    }

    @Override
    public void onClick(View v) {

    }


}

