package com.pang.smartbill;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pang.smartbill.adapter.AccountAdapter;
import com.pang.smartbill.db.AccountBean;
import com.pang.smartbill.db.DBManager;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    ListView searchLv;
    EditText searchEt;
    TextView emptyTv;
    List<AccountBean>mDatas;
    AccountAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        mDatas = new ArrayList<>();
        adapter = new AccountAdapter(this,mDatas);
        searchLv.setAdapter(adapter);
        searchLv.setEmptyView(emptyTv);
    }

    private void initView() {
        searchEt = findViewById(R.id.search_et);
        searchLv = findViewById(R.id.search_lv);
        emptyTv = findViewById(R.id.search_tv_empty);
    }

    public void onClick(View view) {
        int viewId = view.getId();
        if(viewId == R.id.search_iv_back ){
            finish();
        } else if (viewId == R.id.search_iv_sh ) {
            String msg = searchEt.getText().toString().trim();

            if (TextUtils.isEmpty(msg)) {
                Toast.makeText(this,"The input cannot be empty!",Toast.LENGTH_SHORT).show();
                return;
            }

            List<AccountBean> list = DBManager.getAccountListByRemarkFromAccounttb(msg);
            mDatas.clear();
            mDatas.addAll(list);
            adapter.notifyDataSetChanged();
        }
    }
}
