package com.pang.smartbill;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pang.smartbill.adapter.AccountAdapter;
import com.pang.smartbill.db.AccountBean;
import com.pang.smartbill.db.DBManager;
import com.pang.smartbill.ui.CalendarDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    ListView historyLv;
    TextView timeTv;

    List<AccountBean>mDatas;
    AccountAdapter adapter;
    int year,month;
    int dialogSelPos = -1;
    int dialogSelMonth = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        historyLv = findViewById(R.id.history_lv);
        timeTv = findViewById(R.id.history_tv_time);
        mDatas = new ArrayList<>();

        adapter = new AccountAdapter(this,mDatas);
        historyLv.setAdapter(adapter);
        initTime();
        timeTv.setText(year+"-"+month+" ");
        loadData(year,month);
        setLVClickListener();
    }

    private void setLVClickListener() {
        historyLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AccountBean accountBean = mDatas.get(position);
                deleteItem(accountBean);
                return false;
            }
        });
    }

    private void deleteItem(final AccountBean accountBean) {
        final int delId = accountBean.getId();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("prompt").setMessage("Are you sure you want to delete this record?？")
                .setNegativeButton("Cancel",null)
                .setPositiveButton("Ensure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBManager.deleteItemFromAccounttbById(delId);
                        mDatas.remove(accountBean);
                        adapter.notifyDataSetChanged();
                    }
                });
        builder.create().show();
    }

    /* Get a list of income and expenditure in the specified year and month*/
    private void loadData(int year,int month) {
        List<AccountBean> list = DBManager.getAccountListOneMonthFromAccounttb(year, month);
        mDatas.clear();
        mDatas.addAll(list);
        adapter.notifyDataSetChanged();
    }

    private void initTime() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH)+1;
    }

    public void onClick(View view) {

        int viewId = view.getId();
        if(viewId == R.id.history_iv_back ){

            finish();
        }else if(viewId == R.id.history_iv_rili){
            CalendarDialog dialog = new CalendarDialog(this,dialogSelPos,dialogSelMonth);
            dialog.show();
            dialog.setDialogSize();
            dialog.setOnRefreshListener(new CalendarDialog.OnRefreshListener() {
                @Override
                public void onRefresh(int selPos, int year, int month) {
                    timeTv.setText(year+"-"+month+" ");
                    loadData(year,month);
                    dialogSelPos = selPos;
                    dialogSelMonth = month;
                }
            });
        }

    }
}
