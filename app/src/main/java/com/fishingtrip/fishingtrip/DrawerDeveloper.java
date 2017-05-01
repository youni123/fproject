package com.fishingtrip.fishingtrip;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class DrawerDeveloper extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_developer);

        //Add back button to ActionBar
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_developer);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("Developer");

        ListView listview ;
        ListViewAdapter_developer adapter;

        //Adapter 생성
        adapter = new ListViewAdapter_developer() ;

        //리스트뷰 참조 및 Adapter 달기
        listview = (ListView) findViewById(R.id.list_developer);
        listview.setAdapter(adapter);

        //개발자 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_email_black_24dp),getString(R.string.name_hs), "Director&Developer") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_email_black_24dp),getString(R.string.name_sj), "Producer&Tester") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_email_black_24dp),getString(R.string.name_sy), "Developer") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_email_black_24dp),getString(R.string.name_dh), "Developer") ;

        //개발자 선택시 메일 발송
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                //get item
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;
                String titleStr = item.getTitle() ;
                //To do here!!
                if(titleStr == getString(R.string.name_hs)){

                }else if(titleStr == getString(R.string.name_sj)){

                }else if(titleStr == getString(R.string.name_sy)){

                }else if(titleStr == getString(R.string.name_dh)){

                }
            }
        }) ;

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
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}