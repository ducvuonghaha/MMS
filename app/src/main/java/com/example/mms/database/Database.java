package com.example.mms.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.mms.database.Constant.CREATE_MY_ORDER_TABLE;
import static com.example.mms.database.Constant.CREATE_PRODUCT_CART_TABLE;
import static com.example.mms.database.Constant.CREATE_PRODUCT_TABLE;
import static com.example.mms.database.Constant.CREATE_USER_TABLE;
import static com.example.mms.database.Constant.CREATE_VOUCHER_TABLE;
import static com.example.mms.database.Constant.PRODUCT_CART_TABLE;
import static com.example.mms.database.Constant.PRODUCT_TABLE;
import static com.example.mms.database.Constant.USER_TABLE;
import static com.example.mms.database.Constant.VOUCHER_TABLE;

public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context, "mms4.sql", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_PRODUCT_TABLE);
        db.execSQL(CREATE_PRODUCT_CART_TABLE);
        db.execSQL(CREATE_MY_ORDER_TABLE);
        db.execSQL(CREATE_VOUCHER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + PRODUCT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + PRODUCT_CART_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + VOUCHER_TABLE);
    }
}
