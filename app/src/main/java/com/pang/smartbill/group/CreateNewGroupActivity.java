package com.pang.smartbill.group;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.pang.smartbill.R;
import com.pang.smartbill.SearchActivity;
import com.pang.smartbill.adapter.RecordAdapter;
import com.pang.smartbill.frag_record.IncomeFragment;
import com.pang.smartbill.frag_record.OutcomeFragment;
import com.pang.smartbill.group.frag_group_record.CreateNewGroupFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CreateNewGroupActivity extends AppCompatActivity {


    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_record);

        viewPager = findViewById(R.id.group_record_vp);

        initPager();
    }



    private void initPager() {

        List<Fragment>fragmentList = new ArrayList<>();

        CreateNewGroupFragment createNewGroupFragment = new CreateNewGroupFragment();

        fragmentList.add(createNewGroupFragment);


    //set adapter
        RecordAdapter pagerAdapter = new RecordAdapter(getSupportFragmentManager(), fragmentList);

        viewPager.setAdapter(pagerAdapter);


    }


    public void onClick(View view) {
        int viewId = view.getId();

    }




}