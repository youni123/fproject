package com.fishingtrip.fishingtrip;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    // Count number of tabs
    private int tabCount;
    private String menu;

    public TabPagerAdapter(FragmentManager fm, int tabCount, String selectedMenu) {
        super(fm);
        this.tabCount = tabCount;
        menu = selectedMenu;
    }

    @Override
    public Fragment getItem(int position) {
        // Returning the current tabs
        switch(menu){
            case "Reservation":
                switch (position) {
                    case 0:
                        Fragment_MenuReserv_TabShip tab1 = new Fragment_MenuReserv_TabShip();
                        return tab1;
                    case 1:
                        Fragment_MenuReserv_TabFloatel tab2 = new Fragment_MenuReserv_TabFloatel();
                        return tab2;
                    case 2:
                        Fragment_MenuReserv_TabRiver tab3 = new Fragment_MenuReserv_TabRiver();
                        return tab3;
                    case 3:
                        Fragment_MenuReserv_TabReservoir tab4 = new Fragment_MenuReserv_TabReservoir();
                        return tab4;
                    default:
                        return null;
                }
            case "Point":
                switch (position) {
                    case 0:
                        Fragment_MenuPoint_TabShip tab1 = new Fragment_MenuPoint_TabShip();
                        return tab1;
                    case 1:
                        Fragment_MenuPoint_TabFloatel tab2 = new Fragment_MenuPoint_TabFloatel();
                        return tab2;
                    case 2:
                        Fragment_MenuPoint_TabRock tab3 = new Fragment_MenuPoint_TabRock();
                        return tab3;
                    case 3:
                        Fragment_MenuPoint_TabMudflat tab4 = new Fragment_MenuPoint_TabMudflat();
                        return tab4;
                    default:
                        return null;
                }
            case "Weather":
                switch (position) {
                    case 0:
                        Fragment_MenuWeather_TabToday tab1 = new Fragment_MenuWeather_TabToday();
                        return tab1;
                    case 1:
                        Fragment_MenuWeather_TabTomorrow tab2 = new Fragment_MenuWeather_TabTomorrow();
                        return tab2;
                    case 2:
                        Fragment_MenuWeather_TabTen tab3 = new Fragment_MenuWeather_TabTen();
                        return tab3;
                    default:
                        return null;
                }
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
