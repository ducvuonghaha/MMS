package com.example.mms.model;

public class User {
    public String USER_NAME ;
    public String USER_PASSWORD ;
    public String USER_ADDRESS ;
    public String USER_PHONE;
    public String USER_FULL_NAME ;

    public User(String USER_NAME, String USER_PASSWORD, String USER_ADDRESS, String USER_PHONE, String USER_FULL_NAME) {
        this.USER_NAME = USER_NAME;
        this.USER_PASSWORD = USER_PASSWORD;
        this.USER_ADDRESS = USER_ADDRESS;
        this.USER_PHONE = USER_PHONE;
        this.USER_FULL_NAME = USER_FULL_NAME;
    }
}
