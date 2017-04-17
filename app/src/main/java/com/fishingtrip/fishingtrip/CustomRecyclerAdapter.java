package com.fishingtrip.fishingtrip;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder> {
    private ArrayList<MyData> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private CardView cardView;
        public ImageView mImageView;
        public TextView mDestinationTextView;
        public TextView mDetailTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.main_dest_cv);
            mImageView = (ImageView)itemView.findViewById(R.id.cv_dest_image);
            mDestinationTextView = (TextView)itemView.findViewById(R.id.cv_dest_text);
            mDetailTextView = (TextView)itemView.findViewById(R.id.cv_dest_text_detail);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CustomRecyclerAdapter(ArrayList<MyData> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CustomRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_destination, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mDestinationTextView.setText(mDataset.get(position).destination);
        holder.mDetailTextView.setText(mDataset.get(position).detail);
        holder.mImageView.setImageResource(mDataset.get(position).img);

        //onClickListener
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(),MainMenu.class));
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

class MyData{
    public String destination;
    public String detail;
    public int img;
    public MyData(String text_dest, String text_detail, int img){
        this.destination = text_dest;
        this.detail = text_detail;
        this.img = img;
    }
}





