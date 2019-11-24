package com.example.mms.model;

public class ProductCart {
    public String PRODUCT_CART_ID ;
    public String PRODUCT_CART_NAME ;
    public String PRODUCT_CART_USERNAME ;
    public int PRODUCT_CART_NUMBER ;
    public int PRODUCT_CART_PRICE ;
    public byte[] PRODUCT_CART_IMAGE ;

    public ProductCart(String PRODUCT_CART_ID, String PRODUCT_CART_NAME, String PRODUCT_CART_USERNAME, int PRODUCT_CART_NUMBER, int PRODUCT_CART_PRICE, byte[] PRODUCT_CART_IMAGE) {
        this.PRODUCT_CART_ID = PRODUCT_CART_ID;
        this.PRODUCT_CART_NAME = PRODUCT_CART_NAME;
        this.PRODUCT_CART_USERNAME = PRODUCT_CART_USERNAME;
        this.PRODUCT_CART_NUMBER = PRODUCT_CART_NUMBER;
        this.PRODUCT_CART_PRICE = PRODUCT_CART_PRICE;
        this.PRODUCT_CART_IMAGE = PRODUCT_CART_IMAGE;
    }
}
