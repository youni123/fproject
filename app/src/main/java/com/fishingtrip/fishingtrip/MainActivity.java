package com.fishingtrip.fishingtrip;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //[START]CardView
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ItemDestination> destinationSet;
    //[END]CardView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //[START]CardView
        mRecyclerView = (RecyclerView) findViewById(R.id.main_dest_amd);
        //use this setting to improve performance if you know that changes
        //in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        //use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //specify an adapter
        destinationSet = new ArrayList<>();
        mAdapter = new CustomRecyclerAdapter(destinationSet);
        mRecyclerView.setAdapter(mAdapter);

        //여기에서 추가
        destinationSet.add(new ItemDestination("안면도", "충남 태안군 안면읍", R.drawable.ic_test_transparent_24dp));
        //[END]CardView

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //user information
        String user_name;
        String user_email;
        TextView mUserName;
        TextView mUserEmail;

        //로그인 확인되면 저장된 사용자 정보를 표시, 그렇지 않으면 default 값 표시
        SharedPreferences isSignedIn_pref = getSharedPreferences("SIGNED_IN", MODE_PRIVATE);
        if(isSignedIn_pref.getBoolean("SIGNED_IN", true)){
            //Get user information via SharedPreference
            SharedPreferences uName_pref = getSharedPreferences("USER_NAME", MODE_PRIVATE);
            SharedPreferences uEmail_pref = getSharedPreferences("USER_EMAIL", MODE_PRIVATE);
            user_name = uName_pref.getString("USER_NAME", "");
            user_email = uEmail_pref.getString("USER_EMAIL", "");
        }else{
            user_name = "Guest";
            user_email = "Guest@fishingtrip.com";
        }

        //Set user information in navigation header
        View nav_header_main = navigationView.getHeaderView(0);
        mUserName = (TextView)nav_header_main.findViewById(R.id.nav_header_name);
        mUserName.setText(user_name);
        mUserEmail = (TextView)nav_header_main.findViewById(R.id.nav_header_email);
        mUserEmail.setText(user_email);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
            Intent intent = new Intent(MainActivity.this, Feedback.class);

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
            //[END]

            //adb log 추출하여 저장하는 코드 추가
            String logs = "Test: ADB log would be included";
            //intend 로 전달
            intent.putExtra("adblogs", logs);

            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_developer) {
            // Handle developer activity
            Intent intent_dev = new Intent(MainActivity.this, DrawerDeveloper.class);
            startActivity(intent_dev);

        } else if (id == R.id.nav_settings) {
            //Handle setting activity
            Intent intent_set = new Intent(MainActivity.this, DrawerSettings.class);
            startActivity(intent_set);
        } else if (id == R.id.nav_share) {
            Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show();
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
