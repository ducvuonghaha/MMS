package com.example.mms.database;

import java.text.DecimalFormat;

public class Constant {

    public static final DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

    //USER
    public static final String USER_TABLE = "NguoiDung";

    //khai bao ten cot trong bang nguoiDung
    public static final String USER_NAME = "Username";
    public static final String USER_PASSWORD = "Password";
    public static final String USER_PHONE = "Phone";
    public static final String USER_FULL_NAME = "HoTen";
    public static final String USER_ADDRESS = "Diachi";

    //cau lenh tao bang nguoi dung

    //CREATE TABLE nguoiDung (userName NVARCHAR(50) , Password NVARCHAR(50) , Phone NCHAR(10) , hoTen NVARCHAR(50));

    public static final String CREATE_USER_TABLE = "CREATE TABLE " + USER_TABLE + "(" +
            "" + USER_NAME + " NVARCHAR(50) PRIMARY KEY," +
            "" + USER_PASSWORD + " NVARCHAR(50)," +
            "" + USER_ADDRESS + " NVARCHAR(200)," +
            "" + USER_PHONE + " NCHAR(10)," +
            "" + USER_FULL_NAME + " NVARCHAR(50)" +
            ")";


    //SẢN PHẨM
    public static final String PRODUCT_TABLE = "SanPham";
    public static final String PRODUCT_ID = "MaSanPham";
    public static final String PRODUCT_NAME = "TenSanPham";
    public static final String PRODUCT_SPECIES = "Kieu";
    public static final String PRODUCT_TYPE = "TheLoai";
    public static final String PRODUCT_SOLDNUMBER = "SoLuongDaBan";
    public static final String PRODUCT_PRICE = "GiaThanh";
    public static final String PRODUCT_IMAGE = "HinhAnh";
    public static final String PRODUCT_DESCRIPTION = "MoTa";


    public static final String CREATE_PRODUCT_TABLE = "CREATE TABLE " + PRODUCT_TABLE + "(" +
            "" + PRODUCT_ID + " NCHAR(7) PRIMARY KEY," +
            "" + PRODUCT_NAME + " NVARCHAR(100)," +
            "" + PRODUCT_SPECIES + " NVARCHAR(50)," +
            "" + PRODUCT_TYPE + " NVARCHAR(30)," +
            "" + PRODUCT_SOLDNUMBER + " INT," +
            "" + PRODUCT_PRICE + " INT," +
            "" + PRODUCT_IMAGE + " BLOB," +
            "" + PRODUCT_DESCRIPTION + " NVARCHAR(1000)" +
            ")";


    //SẢN PHẨM TRONG GIỎ HÀNG
    public static final String PRODUCT_CART_TABLE = "GioHang";

    public static final String PRODUCT_CART_ID = "MaSanPham";
    public static final String PRODUCT_CART_USERNAME = "Username";
    public static final String PRODUCT_CART_NAME = "TenSanPham";
    public static final String PRODUCT_CART_NUMBER = "SoLuong";
    public static final String PRODUCT_CART_PRICE = "GiaThanh";
    public static final String PRODUCT_CART_IMAGE = "HinhAnh";


    public static final String CREATE_PRODUCT_CART_TABLE = "CREATE TABLE " + PRODUCT_CART_TABLE + "(" +
            "" + PRODUCT_CART_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            "" + PRODUCT_CART_NAME + " NVARCHAR(100)," +
            "" + PRODUCT_CART_USERNAME + " NVARCHAR(100)," +
            "" + PRODUCT_CART_PRICE + " INT," +
            "" + PRODUCT_CART_IMAGE + " BLOB," +
            "" + PRODUCT_CART_NUMBER + " INT" +
            ")";


    //ĐƠN HÀNG CỦA TÔI
    public static final String MY_ORDER_TABLE = "DonHangCuaToi";

    public static final String MY_ORDER_ID = "MaSanPham";
    public static final String MY_ORDER_USERNAME = "Username";
    public static final String MY_ORDER_NAME = "TenSanPham";
    public static final String MY_ORDER_NUMBER = "SoLuong";
    public static final String MY_ORDER_PRICE = "GiaThanh";
    public static final String MY_ORDER_IMAGE = "HinhAnh";


    public static final String CREATE_MY_ORDER_TABLE = "CREATE TABLE " + MY_ORDER_TABLE + "(" +
            "" + MY_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            "" + MY_ORDER_NAME + " NVARCHAR(100)," +
            "" + MY_ORDER_USERNAME + " NVARCHAR(100)," +
            "" + MY_ORDER_PRICE + " INT," +
            "" + MY_ORDER_IMAGE + " BLOB," +
            "" + MY_ORDER_NUMBER + " INT" +
            ")";

    //Voucher
    public static final String VOUCHER_TABLE = "Voucher";

    public static final String VOUCHER_NAME = "VoucherID";
    public static final String VOUCHER_USERNAME = "VoucherUsername";
    public static final String VOUCHER_DATE = "ThoiHan";
    public static final String VOUCHER_DESCRIPTION = "NoiDungVoucher";
    public static final String VOUCHER_DISCOUNT = "PhanTram";
    public static final String VOUCHER_IMAGE = "AnhKM";

    public static final String CREATE_VOUCHER_TABLE = "CREATE TABLE " + VOUCHER_TABLE + "(" +
            "" + VOUCHER_NAME + " NCHAR(50) PRIMARY KEY NOT NULL," +
            "" + VOUCHER_DESCRIPTION + " NCHAR(100)," +
            "" + VOUCHER_DATE + " NVARCHAR(100)," +
            "" + VOUCHER_USERNAME + " NVARCHAR(100)," +
            "" + VOUCHER_DISCOUNT + " DOUBLE," +
            "" + VOUCHER_IMAGE + " BLOB" +
            ")";
}
