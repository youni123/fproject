package com.fishingtrip.fishingtrip;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.Toast;

public class MenuPoint  extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_point);

        //Set up toolbar as actionbar - toolbar is defined in the layout file
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_point);
        setSupportActionBar(toolbar);
        //Get a support ActionBar corresponding to this toolbar
        ActionBar actionBar = getSupportActionBar();
        //// Enable the Up button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("Point");

        //Initializing the TabLayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout_point);
        tabLayout.addTab(tabLayout.newTab().setText("선상"));
        tabLayout.addTab(tabLayout.newTab().setText("좌대"));
        tabLayout.addTab(tabLayout.newTab().setText("갯바위"));
        tabLayout.addTab(tabLayout.newTab().setText("해루질"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing ViewPager
        viewPager = (ViewPager) findViewById(R.id.pager_point);

        //Creating TabPagerAdapter adapter
        String selectedMenu = "Point";
        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), selectedMenu);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //Set TabSelectedListener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

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
            //Toast.makeText(MenuPoint.this, "Not implemented yet", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MenuPoint.this, Feedback.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
