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
    private ArrayList<ListViewDestination> listViewDestList = new ArrayList<ListViewDestination>();

    public ListViewAdapter(){

    }

    //override
    public int getCount(){
        return listViewDestList.size();
    }

    //override
    public View getView(int position, View convertView, ViewGroup parent){
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_destination, parent, false);
        }

        ImageView destImageView = (ImageView)convertView.findViewById(R.id.cm_img_dest);
        TextView destTextView = (TextView)convertView.findViewById(R.id.cm_dest);
        TextView locTextView = (TextView)convertView.findViewById(R.id.cm_loc);

        ListViewDestination listViewDest = listViewDestList.get(position);
        destImageView.setImageDrawable(listViewDest.getDestDrawable());
        destTextView.setText(listViewDest.getDest());
        locTextView.setText(listViewDest.getLoc());

        return convertView;
    }

    //override
    public long getItemId(int position){
        return position;
    }

    //override
    public Object getItem(int position){
        return listViewDestList.get(position);
    }

    public void addItem(Drawable image, String dest, String loc){
        ListViewDestination item = new ListViewDestination();

        item.setDestDrawable(image);
        item.setDest(dest);
        item.setLoc(loc);

        listViewDestList.add(item);
    }

}
