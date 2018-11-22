package com.example.mirliam.friends_management;

import java.util.Date;
import java.util.UUID;

public class Friend {
    private UUID mId;
    private String mName;
    private String mGender;
    private Date mBirthday;
    private StringBuilder mHobby;


    public Friend() {
        mId = UUID.randomUUID();
        mBirthday = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public boolean getGender() {
        if(mGender == null){
            ;
        }else {
            if (mGender.equals("男")) {
                return true;
            } else if (mGender.equals("女"))
                return false;
        }
        return true;
    }

    public void setGender(String gender) {
        mGender = gender;
    }

    public Date getBirthday() {
        return mBirthday;
    }

    public void setBirthday(Date birthday) {
        mBirthday = birthday;
    }

    public StringBuilder getHobby() {
        return mHobby;
    }

    public void setHobby(StringBuilder hobby) {
        mHobby = hobby;
    }
}
