package com.fishingtrip.fishingtrip;

import java.io.Serializable;

public class UserProfileInfo implements Serializable {
    private String personDisplayName;
    private String personEmail;

    public UserProfileInfo(){

    }

    public UserProfileInfo(String displayname, String email){
        this.personDisplayName = displayname;
        this.personEmail = email;
    }

    public String getPersonDisplayName(){
        return personDisplayName;
    }

    public void setPersonDisplayName(String displayName) {
        this.personDisplayName = displayName;
    }

    public String getPersonEmail(){
        return personEmail;
    }

    public void setPersonEmail(String email){
        this.personEmail = email;
    }
}
