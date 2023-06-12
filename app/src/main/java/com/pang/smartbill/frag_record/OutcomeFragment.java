package com.pang.smartbill.frag_record;

import androidx.fragment.app.Fragment;

import com.pang.smartbill.R;
import com.pang.smartbill.db.DBManager;
import com.pang.smartbill.db.TypeBean;

import java.util.List;
/**
 * A simple {@link Fragment} subclass.
 */
public class OutcomeFragment extends BaseRecordFragment {


    //give GridView data
    @Override
    public void loadDataToGV() {
        super.loadDataToGV();
        //Gets the data source in database
        List<TypeBean> outlist = DBManager.getTypeList(0);
        typeList.addAll(outlist);
        adapter.notifyDataSetChanged();
        typeTv.setText("others");
        typeIv.setImageResource(R.mipmap.ic_qita_fs);
    }

    @Override
    public void saveAccountToDB() {
        accountBean.setKind(0);
        DBManager.insertItemToAccounttb(accountBean);
    }
}
