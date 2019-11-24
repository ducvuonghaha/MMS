package com.example.mms.model;

public class MyVoucher {
    public String VOUCHER_NAME;
    public String VOUCHER_USERNAME;
    public String VOUCHER_DATE;
    public String VOUCHER_DESCRIPTION;
    public double VOUCHER_DISCOUNT;
    public byte[] VOUCHER_IMAGE;

    public MyVoucher(String VOUCHER_NAME, String VOUCHER_USERNAME, String VOUCHER_DATE, String VOUCHER_DESCRIPTION, double VOUCHER_DISCOUNT, byte[] VOUCHER_IMAGE) {
        this.VOUCHER_NAME = VOUCHER_NAME;
        this.VOUCHER_USERNAME = VOUCHER_USERNAME;
        this.VOUCHER_DATE = VOUCHER_DATE;
        this.VOUCHER_DESCRIPTION = VOUCHER_DESCRIPTION;
        this.VOUCHER_DISCOUNT = VOUCHER_DISCOUNT;
        this.VOUCHER_IMAGE = VOUCHER_IMAGE;
    }
}
