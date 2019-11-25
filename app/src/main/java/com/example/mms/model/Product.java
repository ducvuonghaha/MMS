package com.example.mms.model;

public class Product {
    public String PRODUCT_ID;
    public String PRODUCT_NAME;
    public String PRODUCT_SPECIES;
    public String PRODUCT_TYPE;
    public int PRODUCT_SOLDNUMBER;
    public int PRODUCT_PRICE;
    public byte[] PRODUCT_IMAGE;
    public String PRODUCT_VIDEO;
    public String PRODUCT_DESCRIPTION;

    public Product(String PRODUCT_ID, String PRODUCT_NAME, String PRODUCT_SPECIES, String PRODUCT_TYPE, int PRODUCT_SOLDNUMBER, int PRODUCT_PRICE, byte[] PRODUCT_IMAGE, String PRODUCT_VIDEO, String PRODUCT_DESCRIPTION) {
        this.PRODUCT_ID = PRODUCT_ID;
        this.PRODUCT_NAME = PRODUCT_NAME;
        this.PRODUCT_SPECIES = PRODUCT_SPECIES;
        this.PRODUCT_TYPE = PRODUCT_TYPE;
        this.PRODUCT_SOLDNUMBER = PRODUCT_SOLDNUMBER;
        this.PRODUCT_PRICE = PRODUCT_PRICE;
        this.PRODUCT_IMAGE = PRODUCT_IMAGE;
        this.PRODUCT_VIDEO = PRODUCT_VIDEO;
        this.PRODUCT_DESCRIPTION = PRODUCT_DESCRIPTION;
    }
}
