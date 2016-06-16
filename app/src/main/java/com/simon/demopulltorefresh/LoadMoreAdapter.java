package com.simon.demopulltorefresh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by xw on 2016/6/16.
 */
public class LoadMoreAdapter extends BaseAdapter {

    private Context context;
    private List<String> list;

    public LoadMoreAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addItems(List<String> items) {
        list.addAll(items);
        notifyDataSetChanged();
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.text_view);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        holder.title.setText(list.get(position));
        return convertView;
    }

    public class ViewHolder {
        TextView title;
    }
}
