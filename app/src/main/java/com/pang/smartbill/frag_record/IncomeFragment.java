package com.pang.smartbill.frag_record;

import com.pang.smartbill.R;
import com.pang.smartbill.db.DBManager;
import com.pang.smartbill.db.TypeBean;

import java.util.List;
/**
 * Revenue record page
 */
public class IncomeFragment extends BaseRecordFragment {


    @Override
    public void loadDataToGV() {
        super.loadDataToGV();
        //get database
        List<TypeBean> inlist = DBManager.getTypeList(1);
        typeList.addAll(inlist);
        adapter.notifyDataSetChanged();
        typeTv.setText("others");
        typeIv.setImageResource(R.mipmap.in_qt_fs);
    }

    @Override
    public void saveAccountToDB() {
        accountBean.setKind(1);
        DBManager.insertItemToAccounttb(accountBean);
    }
}
