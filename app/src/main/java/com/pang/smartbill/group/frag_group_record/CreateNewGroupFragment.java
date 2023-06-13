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

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pang.smartbill.R;
import com.pang.smartbill.db.AccountBean;
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


    EditText titleEt,descriptionEt;
    GridView typeGv;
    ImageView backIB;
    Button saveTv,addMemberBt;

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
        typeGv = view.findViewById(R.id.group_frag_record_gv);
        addMemberBt = view.findViewById(R.id.group_btn_member_add);

        backIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        saveTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Save data to database and go back


            }
        });

    }


}
