package com.pang.smartbill.frag_record;

import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
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

/**
 * Record the spending module in the page
 */
public abstract class BaseRecordFragment extends Fragment implements View.OnClickListener {

    KeyboardView keyboardView;
    EditText moneyEt;
    ImageView typeIv;
    TextView typeTv,noteTv,timeTv;
    GridView typeGv;
    List<TypeBean>typeList;
    TypeBaseAdapter adapter;
    AccountBean accountBean;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountBean = new AccountBean();   //create object
        accountBean.setTypename("others");
        accountBean.setsImageId(R.mipmap.ic_qita_fs);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_outcome, container, false);
        initView(view);
        setInitTime();
        //GridView
        loadDataToGV();
        setGVListener(); //set GridView onclick
        return view;
    }
    /* Get the current time，show to timeTv*/
    private void setInitTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = sdf.format(date);
        timeTv.setText(time);
        accountBean.setTime(time);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        accountBean.setYear(year);
        accountBean.setMonth(month);
        accountBean.setDay(day);
    }

    /* set GridView onclick*/
    private void setGVListener() {
        typeGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.selectPos = position;
                adapter.notifyDataSetInvalidated();
                TypeBean typeBean = typeList.get(position);
                String typename = typeBean.getTypename();
                typeTv.setText(typename);
                accountBean.setTypename(typename);
                int simageId = typeBean.getSimageId();
                typeIv.setImageResource(simageId);
                accountBean.setsImageId(simageId);
            }
        });
    }


    public void loadDataToGV() {
        typeList = new ArrayList<>();
        adapter = new TypeBaseAdapter(getContext(), typeList);
        typeGv.setAdapter(adapter);
    }

    private void initView(View view) {
        keyboardView = view.findViewById(R.id.frag_record_keyboard);
        moneyEt = view.findViewById(R.id.frag_record_et_money);
        typeIv = view.findViewById(R.id.frag_record_iv);
        typeGv = view.findViewById(R.id.frag_record_gv);
        typeTv = view.findViewById(R.id.frag_record_tv_type);
        noteTv = view.findViewById(R.id.frag_record_tv_note);
        timeTv = view.findViewById(R.id.frag_record_tv_time);
        noteTv.setOnClickListener(this);
        timeTv.setOnClickListener(this);
        //display the soft keyboard
        KeyBoardUtils boardUtils = new KeyBoardUtils(keyboardView, moneyEt);
        boardUtils.showKeyboard();
        //Set the interface, listen to confirm that the button was clicked
        boardUtils.setOnEnsureListener(new KeyBoardUtils.OnEnsureListener() {
            @Override
            public void onEnsure() {

                String moneyStr = moneyEt.getText().toString();
                if (TextUtils.isEmpty(moneyStr)||moneyStr.equals("0")) {
                    getActivity().finish();
                    return;
                }
                float money = Float.parseFloat(moneyStr);
                accountBean.setMoney(money);

                saveAccountToDB();

                getActivity().finish();
            }
        });
    }

    public abstract void saveAccountToDB();

    @Override
    public void onClick(View v) {
        int viewId = v.getId();

        if(viewId == R.id.frag_record_tv_time){
            showTimeDialog();
        }else if(viewId == R.id.frag_record_tv_note){
            showBZDialog();
        }
    }

    private void showTimeDialog() {
        SelectTimeDialog dialog = new SelectTimeDialog(getContext());
        dialog.show();

        dialog.setOnEnsureListener(new SelectTimeDialog.OnEnsureListener() {
            @Override
            public void onEnsure(String time, int year, int month, int day) {
                timeTv.setText(time);
                accountBean.setTime(time);
                accountBean.setYear(year);
                accountBean.setMonth(month);
                accountBean.setDay(day);
            }
        });
    }


    public  void showBZDialog(){
        final NoteDialog dialog = new NoteDialog(getContext());
        dialog.show();
        dialog.setDialogSize();
        dialog.setOnEnsureListener(new NoteDialog.OnEnsureListener() {
            @Override
            public void onEnsure() {
                String msg = dialog.getEditText();
                if (!TextUtils.isEmpty(msg)) {
                    noteTv.setText(msg);
                    accountBean.setNote(msg);
                }
                dialog.cancel();
            }
        });
    }
}
