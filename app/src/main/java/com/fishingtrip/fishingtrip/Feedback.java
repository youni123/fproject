package com.fishingtrip.fishingtrip;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Feedback extends AppCompatActivity{

    private ImageView mCapturedview;
    private String mLog;
    private Bitmap btm;
    private TextView mSenderTextView;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);

        //Appbar
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_feedback);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("Feedback");

        //replace close button, not up button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);

        //SharedPreference 계정 정보로부터 sender 설정
        SharedPreferences uEmail_pref = getSharedPreferences("USER_EMAIL", MODE_PRIVATE);
        String user_email = uEmail_pref.getString("USER_EMAIL", "");
        mSenderTextView = (TextView)findViewById(R.id.feedback_sender);
        mSenderTextView.setText(getString(R.string.feedback_sender, user_email));

        //screen capture
        if(getIntent().hasExtra("screenshot")){
            //bitmap 압축 해제
            btm = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("screenshot"),0,getIntent().getByteArrayExtra("screenshot").length);
            mCapturedview = (ImageView)findViewById(R.id.feedback_capture);
            mCapturedview.setImageBitmap(btm);
        }
        if(getIntent().hasExtra("adblogs")){
            mLog = (String)getIntent().getSerializableExtra("adblogs");
        }

        //checkbox
        CheckBox checkbox = (CheckBox)findViewById(R.id.feedback_check);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.getId() == R.id.feedback_check) {
                    if (isChecked) {

                    } else {

                    }
                }
            }
        });
    }

    //close this activity as oppose to navigating up
    @Override
    public boolean onSupportNavigateUp(){
        this.finish();
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.feedback_send, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_send) {
            Toast.makeText(Feedback.this, "Not implemented yet", Toast.LENGTH_SHORT).show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
