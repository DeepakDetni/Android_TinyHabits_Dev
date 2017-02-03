package com.tinyhabits.razorthink.tinyhabits;

/**
 * Created by Deepak Detni on 30-01-2017.
 */

public class UserLogin {

    public String name;
    public String photoUrl;
    public String emailAddress;
    public String mobileNumber;

    public UserLogin(){

    }

    UserLogin(String name, String photoUrl, String emailAddress, String mobileNumber){
        this.name = name;
        this.photoUrl = photoUrl;
        this.emailAddress = emailAddress;
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
