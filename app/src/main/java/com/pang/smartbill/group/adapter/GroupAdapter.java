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

import java.util.Calendar;
import java.util.List;

public class GroupAdapter extends BaseAdapter {

    Context context;
    List<GroupBean>mDatas;
    LayoutInflater inflater;


    public GroupAdapter(Context context, List<GroupBean>mDatas) {
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
        GroupAdapter.ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.group_item_mainlv,parent,false);
            holder = new GroupAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (GroupAdapter.ViewHolder) convertView.getTag();
        }
        GroupBean bean = mDatas.get(position);
        holder.typeIv.setImageResource(R.drawable.group_title_sample);
        holder.groupTitleTv.setText(bean.getGrouptitle());
        holder.descriptionTv.setText(bean.getDescription());

        return convertView;
    }

    class ViewHolder{

        ImageView typeIv;
        TextView groupTitleTv,descriptionTv;

        public ViewHolder(View view){

            typeIv = view.findViewById(R.id.group_item_mainlv_iv);
            groupTitleTv = view.findViewById(R.id.group_item_mainlv_tv_title);
            descriptionTv = view.findViewById(R.id.group_item_mainlv_tv_description);

        }
    }

}
