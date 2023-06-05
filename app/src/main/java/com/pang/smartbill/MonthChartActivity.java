package com.pang.smartbill;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.pang.smartbill.adapter.ChartVPAdapter;
import com.pang.smartbill.db.DBManager;
import com.pang.smartbill.frag_chart.IncomChartFragment;
import com.pang.smartbill.frag_chart.OutcomChartFragment;
import com.pang.smartbill.ui.CalendarDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MonthChartActivity extends AppCompatActivity {
    Button inBtn,outBtn;
    TextView dateTv,inTv,outTv;
    ViewPager chartVp;
     int year;
     int month;
    int selectPos = -1,selectMonth =-1;
    List<Fragment>chartFragList;
    private IncomChartFragment incomChartFragment;
    private OutcomChartFragment outcomChartFragment;
    private ChartVPAdapter chartVPAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_chart);
        initView();
        initTime();
        initStatistics(year,month);
        initFrag();
        setVPSelectListener();
    }

    private void setVPSelectListener() {
        chartVp.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                setButtonStyle(position);
            }
        });
    }

    private void initFrag() {
        chartFragList = new ArrayList<>();
        //add Fragment object
        incomChartFragment = new IncomChartFragment();
        outcomChartFragment = new OutcomChartFragment();
        //add data to Fragment
        Bundle bundle = new Bundle();
        bundle.putInt("year",year);
        bundle.putInt("month",month);
        incomChartFragment.setArguments(bundle);
        outcomChartFragment.setArguments(bundle);

        chartFragList.add(outcomChartFragment);
        chartFragList.add(incomChartFragment);

        chartVPAdapter = new ChartVPAdapter(getSupportFragmentManager(), chartFragList);
        chartVp.setAdapter(chartVPAdapter);
//        Fragment to Acitivy
    }

    /* Statistics */
    private void initStatistics(int year, int month) {
        float inMoneyOneMonth = DBManager.getSumMoneyOneMonth(year, month, 1);
        float outMoneyOneMonth = DBManager.getSumMoneyOneMonth(year, month, 0);
        int incountItemOneMonth = DBManager.getCountItemOneMonth(year, month, 1);
        int outcountItemOneMonth = DBManager.getCountItemOneMonth(year, month, 0);
        dateTv.setText(year+"-"+month+" bills");
        inTv.setText("total "+incountItemOneMonth+" income: $ "+inMoneyOneMonth);
        outTv.setText("total "+outcountItemOneMonth+" expense: $ "+outMoneyOneMonth);

    }

    /**initializing time*/
    private void initTime() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH)+1;
    }


    private void initView() {
        inBtn = findViewById(R.id.chart_btn_in);
        outBtn = findViewById(R.id.chart_btn_out);
        dateTv = findViewById(R.id.chart_tv_date);
        inTv = findViewById(R.id.chart_tv_in);
        outTv = findViewById(R.id.chart_tv_out);
        chartVp = findViewById(R.id.chart_vp);
    }

    public void onClick(View view) {
        int viewId = view.getId();
        if(viewId == R.id.chart_iv_back){
            finish();
        } else if (viewId == R.id.chart_iv_rili) {
            showCalendarDialog();
        } else if (viewId == R.id.chart_btn_in) {
            setButtonStyle(1);
            chartVp.setCurrentItem(1);
        } else if (viewId == R.id.chart_btn_out) {
            setButtonStyle(0);
            chartVp.setCurrentItem(0);
        }
    }
    /* Displays the calendar*/
    private void showCalendarDialog() {
        CalendarDialog dialog = new CalendarDialog(this, selectPos, selectMonth);
        dialog.show();
        dialog.setDialogSize();
        dialog.setOnRefreshListener(new CalendarDialog.OnRefreshListener() {
            @Override
            public void onRefresh(int selPos, int year, int month) {
                MonthChartActivity.this.selectPos = selPos;
                MonthChartActivity.this.selectMonth = month;
                initStatistics(year,month);
                incomChartFragment.setDate(year,month);
                outcomChartFragment.setDate(year,month);
            }
        });
    }

    /* expense-0  income-1*/
    private void setButtonStyle(int kind){
        if (kind == 0) {
            outBtn.setBackgroundResource(R.drawable.main_recordbtn_bg);
            outBtn.setTextColor(Color.WHITE);
            inBtn.setBackgroundResource(R.drawable.dialog_btn_bg);
            inBtn.setTextColor(Color.BLACK);
        }else if (kind == 1){
            inBtn.setBackgroundResource(R.drawable.main_recordbtn_bg);
            inBtn.setTextColor(Color.WHITE);
            outBtn.setBackgroundResource(R.drawable.dialog_btn_bg);
            outBtn.setTextColor(Color.BLACK);
        }
    }
}
