package com.fishingtrip.fishingtrip;

import android.graphics.drawable.Drawable;

public class ListViewDestination {
    private Drawable destDrawable;
    private String destStr;
    private String locStr;

    public void setDestDrawable(Drawable image){
        destDrawable = image;
    }
    public void setDest(String destination){
        destStr = destination;
    }
    public void setLoc(String location){
        locStr = location;
    }
    public Drawable getDestDrawable(){
        return this.destDrawable;
    }
    public String getDest(){
        return this.destStr;
    }
    public String getLoc(){
        return this.locStr;
    }
}
