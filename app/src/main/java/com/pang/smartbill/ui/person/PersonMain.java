package com.pang.smartbill.ui.person;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.pang.smartbill.databinding.ActivityMainBinding;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.pang.smartbill.adapter.AccountAdapter;
import com.pang.smartbill.db.AccountBean;
import com.pang.smartbill.db.DBManager;
import com.pang.smartbill.ui.BudgetDialog;
import com.pang.smartbill.ui.group.GroupFragment;
import com.pang.smartbill.ui.person.PersonFragment;
import com.pang.smartbill.ui.setting.SettingFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.pang.smartbill.R;
import com.pang.smartbill.SearchActivity;
import com.pang.smartbill.RecordActivity;
import com.pang.smartbill.MonthChartActivity;

public class PersonMain extends AppCompatActivity  implements View.OnClickListener{

    private ActivityMainBinding binding;

    ListView todayLv;
    ImageView searchIv;
    ImageButton editBtn;
    ImageButton moreBtn;

    List<AccountBean>mDatas;
    AccountAdapter adapter;
    int year,month,day;

    View headerView;
    TextView topOutTv,topInTv,topbudgetTv,topConTv;
    // ImageView topShowIv;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initialize the database
        DBManager.initDB(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initTime();
        initView();
        preferences = getSharedPreferences("budget", Context.MODE_PRIVATE);

        addLVHeaderView();
        mDatas = new ArrayList<>();

        adapter = new AccountAdapter(this, mDatas);
        todayLv.setAdapter(adapter);


    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment_activity_main,fragment);
    }

    public void initView() {
        todayLv = findViewById(R.id.person_lv);
        editBtn = findViewById(R.id.person_btn_add);
        // moreBtn = findViewById(R.id.main_btn_more);
        searchIv = findViewById(R.id.person_iv_search);
        editBtn.setOnClickListener(this);
        // moreBtn.setOnClickListener(this);
        searchIv.setOnClickListener(this);
        setLVLongClickListener();
    }

    public void setLVLongClickListener() {
        todayLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    return false;
                }
                int pos = position-1;
                AccountBean clickBean = mDatas.get(pos);


                showDeleteItemDialog(clickBean);
                return false;
            }
        });
    }

    public void showDeleteItemDialog(final  AccountBean clickBean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("prompt ").setMessage("Are you sure you want to delete this record？")
                .setNegativeButton("Cancel",null)
                .setPositiveButton("Ensure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int click_id = clickBean.getId();

                        DBManager.deleteItemFromAccounttbById(click_id);
                        mDatas.remove(clickBean);
                        adapter.notifyDataSetChanged();
                        setTopTvShow();
                    }
                });
        builder.create().show();
    }


    public void addLVHeaderView() {

        headerView = getLayoutInflater().inflate(R.layout.item_mainlv_top, null);
        todayLv.addHeaderView(headerView);

        topOutTv = headerView.findViewById(R.id.item_mainlv_top_tv_out);
        topInTv = headerView.findViewById(R.id.item_mainlv_top_tv_in);
        topbudgetTv = headerView.findViewById(R.id.item_mainlv_top_tv_budget);
        topConTv = headerView.findViewById(R.id.item_mainlv_top_tv_day);
        //topShowIv = headerView.findViewById(R.id.item_mainlv_top_iv_hide);

        topbudgetTv.setOnClickListener(this);
        headerView.setOnClickListener(this);
        // topShowIv.setOnClickListener(this);

    }

    public void initTime() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH)+1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }


    @Override
    protected void onResume() {
        super.onResume();
        loadDBData();
        setTopTvShow();
    }

    public void setTopTvShow() {

        float incomeOneDay = DBManager.getSumMoneyOneDay(year, month, day, 1);
        float outcomeOneDay = DBManager.getSumMoneyOneDay(year, month, day, 0);
        String infoOneDay = "Today's Expenditure $"+outcomeOneDay+"  Income $"+incomeOneDay;
        topConTv.setText(infoOneDay);

        float incomeOneMonth = DBManager.getSumMoneyOneMonth(year, month, 1);
        float outcomeOneMonth = DBManager.getSumMoneyOneMonth(year, month, 0);
        topInTv.setText("$"+incomeOneMonth);
        topOutTv.setText("$"+outcomeOneMonth);


        float bmoney = preferences.getFloat("bmoney", 0);
        if (bmoney == 0) {
            topbudgetTv.setText("$ 0");
        }else{
            float syMoney = bmoney-outcomeOneMonth;
            topbudgetTv.setText("$"+syMoney);
        }
    }


    public void loadDBData() {
        List<AccountBean> list = DBManager.getAccountListOneDayFromAccounttb(year, month, day);
        mDatas.clear();
        mDatas.addAll(list);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View v) {

        int viewId =v.getId();
        if(viewId == R.id.person_iv_search){
            Intent it = new Intent(this, SearchActivity.class);
            startActivity(it);
        } else if (viewId == R.id.person_btn_add) {
            Intent it1 = new Intent(this, RecordActivity.class);
            startActivity(it1);
        } else if (viewId == R.id.item_mainlv_top_tv_budget) {
            showBudgetDialog();
        }

//        switch (v.getId()) {
//            case R.id.main_iv_search:
//                Intent it = new Intent(this, SearchActivity.class);
//                startActivity(it);
//                break;
//            case R.id.main_btn_edit:
//                Intent it1 = new Intent(this, RecordActivity.class);
//                startActivity(it1);
//                break;
//            case R.id.main_btn_more:
//                MoreDialog moreDialog = new MoreDialog(this);
//                moreDialog.show();
//                moreDialog.setDialogSize();
//                break;
//            case R.id.item_mainlv_top_tv_budget:
//                showBudgetDialog();
//                break;
//            case R.id.item_mainlv_top_iv_hide:
//
//                toggleShow();
//                break;
//        }

        if (v == headerView) {

            Intent intent = new Intent();
            intent.setClass(this, MonthChartActivity.class);
            startActivity(intent);
        }
    }

    private void showBudgetDialog() {
        BudgetDialog dialog = new BudgetDialog(this);
        dialog.show();
        dialog.setDialogSize();
        dialog.setOnEnsureListener(new BudgetDialog.OnEnsureListener() {
            @Override
            public void onEnsure(float money) {

                SharedPreferences.Editor editor = preferences.edit();
                editor.putFloat("bmoney",money);
                editor.commit();

                float outcomeOneMonth = DBManager.getSumMoneyOneMonth(year, month, 0);
                float syMoney = money-outcomeOneMonth;
                topbudgetTv.setText("$"+syMoney);
            }
        });
    }

//    boolean isShow = true;
//
//    private void toggleShow() {
//        if (isShow) {
//            PasswordTransformationMethod passwordMethod = PasswordTransformationMethod.getInstance();
//            topInTv.setTransformationMethod(passwordMethod);
//            topOutTv.setTransformationMethod(passwordMethod);
//            topbudgetTv.setTransformationMethod(passwordMethod);
//            topShowIv.setImageResource(R.mipmap.ih_hide);
//            isShow = false;
//        }else{
//            HideReturnsTransformationMethod hideMethod = HideReturnsTransformationMethod.getInstance();
//            topInTv.setTransformationMethod(hideMethod);
//            topOutTv.setTransformationMethod(hideMethod);
//            topbudgetTv.setTransformationMethod(hideMethod);
//            topShowIv.setImageResource(R.mipmap.ih_show);
//            isShow = true;
//        }
//    }
}

