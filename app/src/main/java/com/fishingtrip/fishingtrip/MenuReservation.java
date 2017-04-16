package com.fishingtrip.fishingtrip;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.Toast;

public class MenuReservation  extends AppCompatActivity{
    //[START] Fading action bar
    private Drawable mActionBarBackgroundDrawable;
    //[END] Fading action bar

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_reservation);

        //ActionBar setting
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("Reservation");

        //[START] Fading action bar
        mActionBarBackgroundDrawable = getResources().getDrawable(R.drawable.ab_background);
        mActionBarBackgroundDrawable.setAlpha(0);

        actionBar.setBackgroundDrawable(mActionBarBackgroundDrawable);

        ((NotifyingScrollView) findViewById(R.id.scroll_view_reserv)).setOnScrollChangedListener(mOnScrollChangedListener);
        //[END] Fading action bar

        //[START] Fading action bar
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
        //[END] Fading action bar
    }

    //[START] Fading action bar
    private NotifyingScrollView.OnScrollChangedListener mOnScrollChangedListener = new NotifyingScrollView.OnScrollChangedListener() {
        public void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt) {
            final int headerHeight = findViewById(R.id.header_image_reserv).getHeight() - getSupportActionBar().getHeight();
            final float ratio = (float) Math.min(Math.max(t, 0), headerHeight) / headerHeight;
            final int newAlpha = (int) (ratio * 255);
            mActionBarBackgroundDrawable.setAlpha(newAlpha);
        }
    };
    //[END] Fading action bar

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
            //Toast.makeText(MenuReservation.this, "Not implemented yet", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MenuReservation.this, Feedback.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
