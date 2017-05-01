package com.fishingtrip.fishingtrip;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    // Count number of tabs
    private int tabCount;

    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        // Returning the current tabs
        switch (position) {
            case 0:
                Fragment_MenuTapShip tab1 = new Fragment_MenuTapShip();
                return tab1;
            case 1:
                Fragment_MenuTabFloatel tab2 = new Fragment_MenuTabFloatel();
                return tab2;
            case 2:
                Fragment_MenuTabRiver tab3 = new Fragment_MenuTabRiver();
                return tab3;
            case 3:
                Fragment_MenuTabReservoir tab4 = new Fragment_MenuTabReservoir();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
