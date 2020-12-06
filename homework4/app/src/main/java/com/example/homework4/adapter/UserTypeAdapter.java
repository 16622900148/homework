package com.example.homework4.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.homework4.R;
import com.example.homework4.bean.User;

import java.util.List;

public class UserTypeAdapter extends BaseAdapter {

    private Context mContext;
    private List<User> mDataList;

    public UserTypeAdapter(Context context, List<User> dataList) {
        mContext = context;
        mDataList = dataList;
    }

    @Override
    public int getCount() {
        return mDataList != null ? mDataList.size() : 0;
    }

    @Override
    public User getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_user_type_adapter, null);
            viewHolder = new ViewHolder(convertView);
        } else
            viewHolder = (ViewHolder) convertView.getTag();
        User item = getItem(position);
        viewHolder.tvName.setText(item.userTypeName);
        return convertView;
    }

    class ViewHolder {
        TextView tvName;

        public ViewHolder(View convertView) {
            tvName = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(this);
        }
    }

}
