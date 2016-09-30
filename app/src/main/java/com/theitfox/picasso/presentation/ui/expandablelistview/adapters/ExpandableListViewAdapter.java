package com.theitfox.picasso.presentation.ui.expandablelistview.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.theitfox.picasso.data.gson.ChildItem;
import com.theitfox.picasso.data.gson.ExpandableItem;

import java.util.List;

/**
 * Created by btquanto on 30/09/2016.
 */

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    private List<ExpandableItem> list;
    private LayoutInflater inflater;

    public ExpandableListViewAdapter(Context context, List<ExpandableItem> list) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getChildren().size();
    }

    @Override
    public ExpandableItem getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public ChildItem getChild(int groupPosition, int childPosition) {
        return getGroup(groupPosition).getChildren().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        // View Holder patterm
        SimpleViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            holder = new SimpleViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (SimpleViewHolder) convertView.getTag();
        }
        // Set data
        ExpandableItem item = getGroup(groupPosition);
        holder.tvTitle.setText(item.getTitle());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        // View Holder patterm
        SimpleViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            holder = new SimpleViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (SimpleViewHolder) convertView.getTag();
        }
        // Set data
        ChildItem item = getChild(groupPosition, childPosition);
        holder.tvTitle.setText(item.getTitle());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private class SimpleViewHolder {
        private TextView tvTitle;

        public SimpleViewHolder(View itemView) {
            this.tvTitle = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
}
