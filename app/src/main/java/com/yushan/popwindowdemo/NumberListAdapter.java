package com.yushan.popwindowdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by beiyong on 2017-4-28.
 */

public class NumberListAdapter extends BaseAdapter {

    private ArrayList<String> mLists;
    private Context mContext;

    public NumberListAdapter(Context context, ArrayList<String> list) {
        this.mContext = context;
        this.mLists = list;
    }

    @Override
    public int getCount() {
        return mLists.size();
    }

    @Override
    public Object getItem(int position) {
        return mLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_number, null);

            holder = new ViewHolder();
            holder.tv_number = (TextView) convertView.findViewById(R.id.tv_number);
            holder.ib_del = (ImageView) convertView.findViewById(R.id.ib_del);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String number = mLists.get(position);

        holder.tv_number.setText(number);
        holder.ib_del.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mLists.remove(position);
                notifyDataSetChanged(); // 通知ListView刷新界面
            }
        });

        return convertView;
    }

    static class ViewHolder {
        TextView tv_number;
        ImageView ib_del;
    }
}

