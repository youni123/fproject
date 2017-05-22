package com.fishingtrip.fishingtrip;

import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.ScrollView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class MenuSave extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_save);

        //Set up toolbar as actionbar - toolbar is defined in the layout file
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_save);
        setSupportActionBar(toolbar);
        //Get a support ActionBar corresponding to this toolbar
        ActionBar actionBar = getSupportActionBar();
        //Enable the Up button
        actionBar.setDisplayHomeAsUpEnabled(true);
        //Set the title in appbar
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("Saved Place");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_save, menu);
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
            //Toast.makeText(MenuSave.this, "Not implemented yet", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MenuSave.this, Feedback.class);

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
}
