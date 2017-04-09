package com.fishingtrip.fishingtrip;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter{
    private ArrayList<ListViewItem> listViewList = new ArrayList<ListViewItem>();

    public ListViewAdapter(){

    }

    //override
    public int getCount(){
        return listViewList.size();
    }

    //override
    public View getView(int position, View convertView, ViewGroup parent){
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_destination, parent, false);
        }

        ImageView iconImageView = (ImageView)convertView.findViewById(R.id.cm_img_dest);
        TextView titleTextView = (TextView)convertView.findViewById(R.id.cm_dest);
        TextView descTextView = (TextView)convertView.findViewById(R.id.cm_loc);

        ListViewItem listViewDest = listViewList.get(position);
        iconImageView.setImageDrawable(listViewDest.getIcon());
        titleTextView.setText(listViewDest.getTitle());
        descTextView.setText(listViewDest.getDesc());

        return convertView;
    }

    //override
    public long getItemId(int position){
        return position;
    }

    //override
    public Object getItem(int position){
        return listViewList.get(position);
    }

    public void addItem(Drawable icon, String title, String description){
        ListViewItem item = new ListViewItem();

        item.setIcon(icon);
        item.setTitle(title);
        item.setDesc(description);

        listViewList.add(item);
    }

}
