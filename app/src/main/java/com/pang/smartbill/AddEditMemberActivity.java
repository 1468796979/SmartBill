package com.pang.smartbill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.pang.smartbill.db.DBManager;
import com.pang.smartbill.db.GroupBean;
import com.pang.smartbill.db.MemberBean;

public class AddEditMemberActivity extends AppCompatActivity {

    Button addMemberBt;
    ImageView backBt;
    EditText memberNameEt;

    private GroupBean groupBean;
    private MemberBean memberBean;
    int  id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_member);

        addMemberBt = findViewById(R.id.member_add_bt);
        memberNameEt = findViewById(R.id.addMemberNameText);
        backBt = findViewById(R.id.member_record_iv_back);

//        groupBean = getIntent().getParcelableExtra("grouptb");
//        id = getIntent().getExtras().getInt("id");





        String addM = memberNameEt.getText().toString().trim();



            addMemberBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(TextUtils.isEmpty(addM)){

                        Toast.makeText(getApplicationContext(),"Name can not be empty!", Toast.LENGTH_LONG).show();
                    }{
//                    saveMemberToDB();

                    finish();
                    }

                }
            });

        backBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });






    }

    private void saveMemberToDB() {

        String memberName = memberNameEt.getText().toString();

        memberBean.setMemberName(memberName);
        memberBean.setMemberGroupId(1);

        DBManager.insertInfoToMembertb(memberBean);
    }


}