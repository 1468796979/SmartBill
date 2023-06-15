package com.pang.smartbill.group.frag_group_record;

import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pang.smartbill.R;
import com.pang.smartbill.db.AccountBean;
import com.pang.smartbill.db.DBManager;
import com.pang.smartbill.db.GroupBean;
import com.pang.smartbill.db.TypeBean;
import com.pang.smartbill.ui.KeyBoardUtils;
import com.pang.smartbill.ui.NoteDialog;
import com.pang.smartbill.ui.SelectTimeDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CreateNewGroupFragment extends Fragment{

     EditText titleEt,descriptionEt,categoryEt;
//    GridView typeGv;
    ImageView backIB;
    Button saveTv,addMemberBt;

    GroupBean groupBean; //save group info to a object

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        groupBean = new GroupBean(); //create a object
        groupBean.setCurrency("MYR");
        groupBean.setCategory("others");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.activity_create_new_group, container, false);
       initView(view);

        //GridView
//        loadDataToGV();
//        setGVListener(); //set GridView onclick


        return view;
    }

    private void initView(View view) {
        saveTv = view.findViewById(R.id.group_save_bt);
        titleEt = view.findViewById(R.id.group_title_et);
        descriptionEt = view.findViewById(R.id.group_description_et);
        backIB = view.findViewById(R.id.group_record_iv_back);
//        categoryEt =  view.findViewById(R.id.group_category_et);
//        typeGv = view.findViewById(R.id.group_frag_record_gv);
//        addMemberBt = view.findViewById(R.id.group_btn_member_add);


        backIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        saveTv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Click Save button
                // save data to database and go back
                String groupTitle1 = titleEt.getText().toString();
                if(TextUtils.isEmpty(groupTitle1)){

                 Toast.makeText(getContext(), "Group Title Can Not Be Empty!", Toast.LENGTH_SHORT).show();


                }else{

                    saveGroupToDB();

                    getActivity().finish();
                }

            }
        });

    }

    //save the group info to database
    private void saveGroupToDB() {

        String groupTitle = titleEt.getText().toString();
        String description = descriptionEt.getText().toString();

        groupBean.setGrouptitle(groupTitle);
        groupBean.setDescription(description);

        groupBean.setCurrency("MYR");
        groupBean.setCategory("others");


        DBManager.insertInfoToGrouptb(groupBean);
    }


}
