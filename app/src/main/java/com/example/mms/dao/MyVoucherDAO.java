package com.example.mms.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mms.database.Database;
import com.example.mms.model.MyVoucher;

import java.util.ArrayList;
import java.util.List;

import static com.example.mms.database.Constant.VOUCHER_DATE;
import static com.example.mms.database.Constant.VOUCHER_DESCRIPTION;
import static com.example.mms.database.Constant.VOUCHER_DISCOUNT;
import static com.example.mms.database.Constant.VOUCHER_IMAGE;
import static com.example.mms.database.Constant.VOUCHER_NAME;
import static com.example.mms.database.Constant.VOUCHER_TABLE;
import static com.example.mms.database.Constant.VOUCHER_USERNAME;

public class MyVoucherDAO {
    private Database database;

    public MyVoucherDAO(Context context) {
        this.database = new Database(context);
    }

    public long insertVouchers(MyVoucher myVoucher) {
        long result = -1;
        ContentValues contentValues = new ContentValues();
        contentValues.put(VOUCHER_NAME, myVoucher.VOUCHER_NAME);
        contentValues.put(VOUCHER_USERNAME, myVoucher.VOUCHER_USERNAME);
        contentValues.put(VOUCHER_DATE, myVoucher.VOUCHER_DATE);
        contentValues.put(VOUCHER_DESCRIPTION, myVoucher.VOUCHER_DESCRIPTION);
        contentValues.put(VOUCHER_DISCOUNT, myVoucher.VOUCHER_DISCOUNT);
        contentValues.put(VOUCHER_IMAGE, myVoucher.VOUCHER_IMAGE);

        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        result = sqLiteDatabase.insert(VOUCHER_TABLE, null, contentValues);
        sqLiteDatabase.close();
        return result;

    }

    public boolean checkVoucher(String voucherCode) {
        boolean check = false;
        String query = "SELECT * FROM Voucher WHERE VoucherID  = '" + voucherCode + "'  ";
        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            check = true;
        }
        cursor.close();
        return check;
    }

    public double getPercentDiscount(String voucherCode) {
        double percent = 0;
        String QUERY = "SELECT PhanTram FROM Voucher WHERE VoucherID = '" + voucherCode + "' ";
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(QUERY, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    percent = cursor.getDouble(0);
                    cursor.moveToNext();
                }
                cursor.close();
                sqLiteDatabase.close();
            }
        }
        return percent;
    }

    public List<MyVoucher> getAllMyVouchers(String username) {
        List<MyVoucher> myVoucherList = new ArrayList<>();
        String sSQL = "SELECT * FROM Voucher WHERE VoucherUsername like '" + username + "'";
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sSQL, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                //di chuyen toi vi tri dau tien cua con tro
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    String VOUCHER_NAME_ = cursor.getString(cursor.getColumnIndex(VOUCHER_NAME));
                    String VOUCHER_USERNAME_ = cursor.getString(cursor.getColumnIndex(VOUCHER_USERNAME));
                    String VOUCHER_DATE_ = cursor.getString(cursor.getColumnIndex(VOUCHER_DATE));
                    String VOUCHER_DESCRIPTION_ = cursor.getString(cursor.getColumnIndex(VOUCHER_DESCRIPTION));
                    double VOUCHER_DISCOUNT_ = cursor.getDouble(cursor.getColumnIndex(VOUCHER_DISCOUNT));
                    byte[] VOUCHER_IMAGE_ = cursor.getBlob(cursor.getColumnIndex(VOUCHER_IMAGE));

                    MyVoucher myVoucher = new MyVoucher(VOUCHER_NAME_, VOUCHER_USERNAME_, VOUCHER_DATE_, VOUCHER_DESCRIPTION_, VOUCHER_DISCOUNT_, VOUCHER_IMAGE_);
                    myVoucherList.add(myVoucher);
                    cursor.moveToNext();
                }
                cursor.close();
                sqLiteDatabase.close();
            }
        }
        return myVoucherList;
    }
}
