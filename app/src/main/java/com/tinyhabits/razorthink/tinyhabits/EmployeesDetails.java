package com.tinyhabits.razorthink.tinyhabits;

/**
 * Created by Deepak Detni on 02-02-2017.
 */

public class EmployeesDetails {

    public String name;
    public String photoUrl;
    public String emailAddress;
    public String mobileNumber;
//    public String Uid;

    public EmployeesDetails(){

    }

    EmployeesDetails(String name, String photoUrl, String emailAddress, String mobileNumber){
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

//    public String getUid() {
//        return Uid;
//    }
//
//    public void setUid(String uid) {
//        Uid = uid;
//    }
}
