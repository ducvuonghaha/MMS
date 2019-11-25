package com.example.mms.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mms.database.Database;
import com.example.mms.model.User;

import java.util.ArrayList;
import java.util.List;

import static com.example.mms.database.Constant.USER_ADDRESS;
import static com.example.mms.database.Constant.USER_FULL_NAME;
import static com.example.mms.database.Constant.USER_NAME;
import static com.example.mms.database.Constant.USER_PASSWORD;
import static com.example.mms.database.Constant.USER_PHONE;
import static com.example.mms.database.Constant.USER_TABLE;

public class UserDAO {
    private Database database;

    public UserDAO(Context context) {
        this.database = new Database(context);
    }

    //USER
    public long insertUser(User user) {
        long result = -1;
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME, user.USER_NAME);
        contentValues.put(USER_PASSWORD, user.USER_PASSWORD);
        contentValues.put(USER_PHONE, user.USER_PHONE);
        contentValues.put(USER_FULL_NAME, user.USER_FULL_NAME);
        contentValues.put(USER_ADDRESS, user.USER_ADDRESS);

        //xin quyen ghi vao bang
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        result = sqLiteDatabase.insert(USER_TABLE, null, contentValues);
        sqLiteDatabase.close();

        return result;
    }

    public long updateUser(User user, String username) {
        long result = -1;
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME, user.USER_NAME);
        contentValues.put(USER_PHONE, user.USER_PHONE);
        contentValues.put(USER_FULL_NAME, user.USER_FULL_NAME);
        contentValues.put(USER_ADDRESS, user.USER_ADDRESS);

        //xin quyen ghi vao bang
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();

        result = sqLiteDatabase.update(USER_TABLE, contentValues, USER_NAME + "=?",
                new String[]{username});

        return result;
    }

    public long updatePass(User user, String username) {
        long result = -1;
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_PASSWORD, user.USER_PASSWORD);
        //xin quyen ghi vao bang
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();

        result = sqLiteDatabase.update(USER_TABLE, contentValues, USER_NAME + "=?",
                new String[]{username});

        return result;
    }


    public boolean checkLogin(String username, String password) {
        boolean result = false;
        String query = "select * from NguoiDung where Username = '" + username + "' and Password = '" + password + "' ";
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);
        if (c.getCount() > 0) {
            result = true;
        }
        c.close();
        return result;
    }


    public boolean checkUsername(String username) {
        boolean result = false;
        String query = "select * from NguoiDung where Username = '" + username + "' ";
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);
        if (c.getCount() > 0) {
            result = true;
        }
        c.close();
        return result;
    }

    public String getFullName(String username) {
        String hoten = "";
        String QUERY = "SELECT HoTen FROM NguoiDung  WHERE Username = '" + username + "'";
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(QUERY, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    hoten = cursor.getString(0);
                    cursor.moveToNext();
                }
                cursor.close();
                sqLiteDatabase.close();
            }
        }
        return hoten;
    }

    public String getPass(String username) {
        String pass = "";
        String QUERY = "SELECT Password FROM NguoiDung  WHERE Username = '" + username + "'";
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(QUERY, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    pass = cursor.getString(0);
                    cursor.moveToNext();
                }
                cursor.close();
                sqLiteDatabase.close();
            }
        }
        return pass;
    }

    public String getPhoneNumber(String username) {
        String phoneNumber = "";
        String QUERY = "SELECT Phone FROM NguoiDung  WHERE Username = '" + username + "'";
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(QUERY, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    phoneNumber = cursor.getString(0);
                    cursor.moveToNext();
                }
                cursor.close();
                sqLiteDatabase.close();
            }
        }
        return phoneNumber;
    }

    public String getAddress(String username) {
        String address = "";
        String QUERY = "SELECT Diachi FROM NguoiDung  WHERE Username = '" + username + "'";
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(QUERY, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    address = cursor.getString(0);
                    cursor.moveToNext();
                }
                cursor.close();
                sqLiteDatabase.close();
            }
        }
        return address;
    }


    //Lấy tất cả user
    public List<User> getAllUserlist() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM " + USER_TABLE;
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.getCount() > 0 ) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    User user = new User();
                    user.setUSER_NAME(cursor.getString(0));
                    user.setUSER_PASSWORD(cursor.getString(1));
                    user.setUSER_ADDRESS(cursor.getString(2));
                    user.setUSER_PHONE(cursor.getString(3));
                    user.setUSER_FULL_NAME(cursor.getString(4));
                    userList.add(user);
                    cursor.moveToNext();
                }
                cursor.close();
                sqLiteDatabase.close();
            }
        }
        return userList;
    }
}
