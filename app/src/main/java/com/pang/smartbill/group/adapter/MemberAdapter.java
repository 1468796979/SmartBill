package com.pang.smartbill.group.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pang.smartbill.R;
import com.pang.smartbill.adapter.AccountAdapter;
import com.pang.smartbill.db.AccountBean;
import com.pang.smartbill.db.GroupBean;
import com.pang.smartbill.db.MemberBean;

import java.util.Calendar;
import java.util.List;


public class MemberAdapter extends  BaseAdapter{


    Context context;
    List<MemberBean>mDatas;
    LayoutInflater inflater;


    public MemberAdapter(Context context, List<MemberBean>mDatas) {
        this.context = context;
        this.mDatas = mDatas;
        inflater = LayoutInflater.from(context);


    }


    public int getCount() {
        return mDatas.size();
    }


    public Object getItem(int position) {
        return mDatas.get(position);
    }


    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        MemberAdapter.ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.member_item_mainlv,parent,false);
            holder = new MemberAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (MemberAdapter.ViewHolder) convertView.getTag();
        }
        MemberBean bean = mDatas.get(position);
        holder.typeIv.setImageResource(R.drawable.baseline_person_24);
        holder.memberNameTv.setText(bean.getMemberName());


        return convertView;
    }

    class ViewHolder{

        ImageView typeIv;
        TextView memberNameTv;

        public ViewHolder(View view){

            typeIv = view.findViewById(R.id.member_item_mainlv_iv);
            memberNameTv = view.findViewById(R.id.member_item_mainlv_tv_title);

        }
    }

}
