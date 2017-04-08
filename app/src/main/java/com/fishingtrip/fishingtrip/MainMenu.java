package com.fishingtrip.fishingtrip;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
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
        actionBar.setDisplayShowTitleEnabled(false);

        //fading action bar
        mActionBarBackgroundDrawable = getResources().getDrawable(R.drawable.ab_background);
        mActionBarBackgroundDrawable.setAlpha(0);

        actionBar.setBackgroundDrawable(mActionBarBackgroundDrawable);

        ((NotifyingScrollView) findViewById(R.id.scroll_view)).setOnScrollChangedListener(mOnScrollChangedListener);

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

        /* https://cyrilmottier.com/2013/05/24/pushing-the-actionbar-to-the-next-level/
         This fading action bar code above doesn’t work for pre-JELLY_BEAN_MR1 devices.
         Indeed, the ActionBar isn’t invalidating itself when required because it isn’t registering itself as the Drawable’s callback.
         You can workaround this issue simply be attaching the following Callback in the onCreate(Bundle) method
         */
        Drawable.Callback mDrawableCallback = new Drawable.Callback() {
            @Override
            public void invalidateDrawable(Drawable who) {
                getSupportActionBar().setBackgroundDrawable(who);
            }

            @Override
            public void scheduleDrawable(Drawable who, Runnable what, long when) {
            }

            @Override
            public void unscheduleDrawable(Drawable who, Runnable what) {
            }
        };

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            mActionBarBackgroundDrawable.setCallback(mDrawableCallback);
        }

    }

    //fading action bar
    private NotifyingScrollView.OnScrollChangedListener mOnScrollChangedListener = new NotifyingScrollView.OnScrollChangedListener() {
        public void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt) {
            //final int headerHeight = findViewById(R.id.fragment_menu).getHeight() - getActionBar().getHeight();
            final int headerHeight = 800;
            final float ratio = (float) Math.min(Math.max(t, 0), headerHeight) / headerHeight;
            final int newAlpha = (int) (ratio * 255);
            mActionBarBackgroundDrawable.setAlpha(newAlpha);
        }
    };

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
