package com.fishingtrip.fishingtrip;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ScrollView;

public class MainMenu extends AppCompatActivity {
    private Drawable mActionBarBackgroundDrawable;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);

        /*안면도 이미지 Fragment*/
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        FragmentSample1 fs1 = new FragmentSample1();
        fragmentTransaction.add(R.id.fragment_menu, fs1,"test");
        fragmentTransaction.commit();

        //Add back button to ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        //make transparent to ActionBar
        actionBar.setDisplayShowTitleEnabled(false);

        //메뉴 선택
        TextView tv_reservation = (TextView)findViewById(R.id.menu_reserv);
        tv_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.isClickable()){
                    Intent intent = new Intent(MainMenu.this, MenuReservation.class);
                    startActivity(intent);
                }
            }
        });

        TextView tv_point = (TextView)findViewById(R.id.menu_point);
        tv_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.isClickable()){
                    Intent intent = new Intent(MainMenu.this, MenuPoint.class);
                    startActivity(intent);
                }
            }
        });

        TextView tv_save = (TextView)findViewById(R.id.menu_save);
        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.isClickable()){
                    Intent intent = new Intent(MainMenu.this, MenuSave.class);
                    startActivity(intent);
                }
            }
        });

        TextView tv_plan = (TextView)findViewById(R.id.menu_plan);
        tv_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.isClickable()){
                    Intent intent = new Intent(MainMenu.this, MenuDayPlan.class);
                    startActivity(intent);
                }
            }
        });

        TextView tv_info = (TextView)findViewById(R.id.menu_info);
        tv_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.isClickable()){
                    Intent intent = new Intent(MainMenu.this, MenuInfo.class);
                    startActivity(intent);
                }
            }
        });

        TextView tv_weather = (TextView)findViewById(R.id.menu_weather);
        tv_weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.isClickable()){
                    Intent intent = new Intent(MainMenu.this, MenuWeather.class);
                    startActivity(intent);
                }
            }
        });

        TextView tv_tips = (TextView)findViewById(R.id.menu_tips);
        tv_tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.isClickable()){
                    Intent intent = new Intent(MainMenu.this, MenuTips.class);
                    startActivity(intent);
                }
            }
        });

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
            Intent intent = new Intent(MainMenu.this, Feedback.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void swapFragment(){
        //To do
    }

}
