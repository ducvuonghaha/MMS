package com.example.mms.model;

public class User {
    public String USER_NAME ;
    public String USER_PASSWORD ;
    public String USER_ADDRESS ;
    public String USER_PHONE;
    public String USER_FULL_NAME ;

    public User() {

    }

    public User(String USER_NAME, String USER_PASSWORD, String USER_ADDRESS, String USER_PHONE, String USER_FULL_NAME) {
        this.USER_NAME = USER_NAME;
        this.USER_PASSWORD = USER_PASSWORD;
        this.USER_ADDRESS = USER_ADDRESS;
        this.USER_PHONE = USER_PHONE;
        this.USER_FULL_NAME = USER_FULL_NAME;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getUSER_PASSWORD() {
        return USER_PASSWORD;
    }

    public void setUSER_PASSWORD(String USER_PASSWORD) {
        this.USER_PASSWORD = USER_PASSWORD;
    }

    public String getUSER_ADDRESS() {
        return USER_ADDRESS;
    }

    public void setUSER_ADDRESS(String USER_ADDRESS) {
        this.USER_ADDRESS = USER_ADDRESS;
    }

    public String getUSER_PHONE() {
        return USER_PHONE;
    }

    public void setUSER_PHONE(String USER_PHONE) {
        this.USER_PHONE = USER_PHONE;
    }

    public String getUSER_FULL_NAME() {
        return USER_FULL_NAME;
    }

    public void setUSER_FULL_NAME(String USER_FULL_NAME) {
        this.USER_FULL_NAME = USER_FULL_NAME;
    }
}
