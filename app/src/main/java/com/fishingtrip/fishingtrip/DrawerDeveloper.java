package com.fishingtrip.fishingtrip;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DrawerDeveloper extends AppCompatActivity {
    Context mContext;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    ArrayList<ItemDeveloper> developerSet;
    public String tab = "developer";

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_developer);

        //Set up toolbar as actionbar - toolbar is defined in the layout file
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_developer);
        setSupportActionBar(toolbar);
        //Get a support ActionBar corresponding to this toolbar
        ActionBar actionBar = getSupportActionBar();
        //Enable the Up button
        actionBar.setDisplayHomeAsUpEnabled(true);
        //Set the title in appbar
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("Developer");

        mContext = getApplicationContext();

        //[START]CardView
        mRecyclerView = (RecyclerView) findViewById(R.id.developer_list);
        //use this setting to improve performance if you know that changes
        //in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        //Everything in the R class is a reference, hence it's just defined as an int
        //We have to use getString() to actual string value
        developerSet = new ArrayList<>();
        developerSet.add(new ItemDeveloper(mContext.getString(R.string.name_hs), mContext.getString(R.string.role_hs), mContext.getString(R.string.email_hs), R.drawable.ic_mail_outline_grey_24dp));
        developerSet.add(new ItemDeveloper(mContext.getString(R.string.name_sj), mContext.getString(R.string.role_sj), mContext.getString(R.string.email_sj), R.drawable.ic_mail_outline_grey_24dp));
        developerSet.add(new ItemDeveloper(mContext.getString(R.string.name_sy), mContext.getString(R.string.role_sy), mContext.getString(R.string.email_sy), R.drawable.ic_mail_outline_grey_24dp));
        developerSet.add(new ItemDeveloper(mContext.getString(R.string.name_dh), mContext.getString(R.string.role_dh), mContext.getString(R.string.email_dh), R.drawable.ic_mail_outline_grey_24dp));

        //use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        //set LayoutManager to RecyclerView
        mRecyclerView.setLayoutManager(mLayoutManager);

        //specify an adapter
        mAdapter = new CustomAdapter(developerSet, mContext);
        mRecyclerView.setAdapter(mAdapter);
        //[END]CardView
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_feedback, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //Toast.makeText(MainMenu.this, "Not implemented yet", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(DrawerDeveloper.this, Feedback.class);

            //[START] screen capture for feedback
            View vScreen = getWindow().getDecorView().getRootView();
            vScreen.setDrawingCacheEnabled(true);
            Bitmap btm_screen = Bitmap.createBitmap(vScreen.getDrawingCache());
            //작업 후 다시 false 로 세팅
            //그렇지 않으면 view 를 생성할 때마다 Cache 에 저장하여 성능 문제가 있을 수 있음
            vScreen.setDrawingCacheEnabled(false);

            Toast.makeText(getApplicationContext(), "Screen is captured!", Toast.LENGTH_LONG).show();
            //intent 간의 데이터 전송 시 100kb 이하로 제한되어 있어 bitmap 자체를 전달하려 하면
            //binder transaction 실패 에러가 발생함. string 형태로 변환해서 전달해야 함
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            btm_screen.compress(Bitmap.CompressFormat.PNG, 50, bs);
            //intend 로 전달
            intent.putExtra("screenshot", bs.toByteArray());

            //adb log 추출하여 저장하는 코드 추가
            String logs = "Test: ADB log would be included";
            //intend 로 전달
            intent.putExtra("adblogs", logs);
            //[END]

            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //[START]CardView
    class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
        private ArrayList<ItemDeveloper> mItems;
        private Context context;

        // Provide a suitable constructor (depends on the kind of mDataset)
        public CustomAdapter(ArrayList<ItemDeveloper> myDataset, Context mContext) {
            context = mContext;
            this.mItems = myDataset;
        }

        //Provide a reference to the views for each data item
        //Complex data items may need more than one view per item, and
        //you provide access to all the views for a data item in a view holder
        public class ViewHolder extends RecyclerView.ViewHolder {
            private CardView cardView;
            public TextView mNameTextView;
            public TextView mRoleTextView;
            public ImageView mImageView;

            public ViewHolder(View itemView) {
                super(itemView);
                cardView = (CardView)itemView.findViewById(R.id.drawer_developer_cv);
                mNameTextView = (TextView)itemView.findViewById(R.id.dev_name_text);
                mRoleTextView = (TextView)itemView.findViewById(R.id.dev_role_text);
                mImageView = (ImageView)itemView.findViewById(R.id.dev_email_icon);
            }
        }

        // Create new views (invoked by the layout manager)
        @Override
        public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cardview_developer, parent, false);
            // set the view's size, margins, paddings and layout parameters

            CustomAdapter.ViewHolder vh = new CustomAdapter.ViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        public void onBindViewHolder(CustomAdapter.ViewHolder holder, int position) {
            //get element from your mDataset at this position
            //replace the contents of the view with that element
            holder.mNameTextView.setText(mItems.get(position).mName);
            holder.mRoleTextView.setText(mItems.get(position).mRole);
            holder.mImageView.setImageResource(mItems.get(position).mImg);

            //onClickListener
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //v.getContext().startActivity(new Intent(v.getContext(),MainMenu.class));
                }
            });
        }

        // Return the size of your mDataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mItems.size();
        }
    }
    //[END]CardView
}