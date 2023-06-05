package com.pang.smartbill;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.pang.smartbill.adapter.RecordAdapter;
import com.pang.smartbill.frag_record.IncomeFragment;
import com.pang.smartbill.frag_record.OutcomeFragment;


import java.util.ArrayList;
import java.util.List;

public class RecordActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        tabLayout = findViewById(R.id.record_tabs);
        viewPager = findViewById(R.id.record_vp);

        initPager();
    }

    private void initPager() {

        List<Fragment>fragmentList = new ArrayList<>();

        OutcomeFragment outFrag = new OutcomeFragment();
        IncomeFragment inFrag = new IncomeFragment();
        fragmentList.add(outFrag);
        fragmentList.add(inFrag);


        RecordAdapter pagerAdapter = new RecordAdapter(getSupportFragmentManager(), fragmentList);

        viewPager.setAdapter(pagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
    }


    public void onClick(View view) {
        int viewId = view.getId();
        if(viewId == R.id.record_iv_back){
            finish();
        }
    }
}
