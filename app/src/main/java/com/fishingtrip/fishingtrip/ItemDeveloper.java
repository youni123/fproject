package com.fishingtrip.fishingtrip;

public class ItemDeveloper {
    public int mImg;
    public String mName;
    public String mRole;
    public String mEmail;

    public ItemDeveloper(String name, String role, String email, int img){
        this.mName = name;
        this.mRole = role;
        this.mEmail = email;
        this.mImg = img;
    }

    public void setName(String name){
        this.mName = name;
    }

    public void setRole(String role){
        this.mRole = role;
    }

    public void setEmail(String email){
        this.mEmail = email;
    }

    public String getName(){
        return this.mName;
    }

    public String getRole(){
        return this.mRole;
    }

    public String getEmail(){
        return this.mEmail;
    }


}
