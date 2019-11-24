package com.example.mms.model;

public class MyOrders {
    public String MY_ORDER_ID ;
    public String MY_ORDER_NAME ;
    public String MY_ORDER_USERNAME ;
    public int MY_ORDER_NUMBER ;
    public int MY_ORDER_PRICE ;
    public byte[] MY_ORDER_IMAGE ;

    public MyOrders(String MY_ORDER_ID, String MY_ORDER_NAME, String MY_ORDER_USERNAME, int MY_ORDER_NUMBER, int MY_ORDER_PRICE, byte[] MY_ORDER_IMAGE) {
        this.MY_ORDER_ID = MY_ORDER_ID;
        this.MY_ORDER_NAME = MY_ORDER_NAME;
        this.MY_ORDER_USERNAME = MY_ORDER_USERNAME;
        this.MY_ORDER_NUMBER = MY_ORDER_NUMBER;
        this.MY_ORDER_PRICE = MY_ORDER_PRICE;
        this.MY_ORDER_IMAGE = MY_ORDER_IMAGE;
    }
}
