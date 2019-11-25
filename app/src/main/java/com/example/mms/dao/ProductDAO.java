package com.example.mms.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mms.database.Database;
import com.example.mms.model.Product;

import java.util.ArrayList;
import java.util.List;

import static com.example.mms.database.Constant.PRODUCT_DESCRIPTION;
import static com.example.mms.database.Constant.PRODUCT_ID;
import static com.example.mms.database.Constant.PRODUCT_IMAGE;
import static com.example.mms.database.Constant.PRODUCT_NAME;
import static com.example.mms.database.Constant.PRODUCT_PRICE;
import static com.example.mms.database.Constant.PRODUCT_SOLDNUMBER;
import static com.example.mms.database.Constant.PRODUCT_SPECIES;
import static com.example.mms.database.Constant.PRODUCT_TABLE;
import static com.example.mms.database.Constant.PRODUCT_TYPE;

public class ProductDAO {
    private Database database;

    public ProductDAO(Context context) {
        this.database = new Database(context);
    }

    public long insertProduct(Product product) {
        long result = -1;
        ContentValues contentValues = new ContentValues();
        contentValues.put(PRODUCT_ID, product.PRODUCT_ID);
        contentValues.put(PRODUCT_NAME, product.PRODUCT_NAME);
        contentValues.put(PRODUCT_SPECIES, product.PRODUCT_SPECIES);
        contentValues.put(PRODUCT_PRICE, product.PRODUCT_PRICE);
        contentValues.put(PRODUCT_IMAGE, product.PRODUCT_IMAGE);
        contentValues.put(PRODUCT_TYPE, product.PRODUCT_TYPE);
        contentValues.put(PRODUCT_SOLDNUMBER, product.PRODUCT_SOLDNUMBER);
        contentValues.put(PRODUCT_DESCRIPTION, product.PRODUCT_DESCRIPTION);

        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        result = sqLiteDatabase.insert(PRODUCT_TABLE, null, contentValues);
        sqLiteDatabase.close();

        return result;

    }

    //Get List sản phẩm tăng dần
    public List<Product> getAllProductASC(String type) {
        List<Product> courseList = new ArrayList<>();
        String sSQL = "SELECT * FROM SanPham WHERE TheLoai = '" + type + "' ORDER BY GiaThanh ASC ";
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sSQL, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                //di chuyen toi vi tri dau tien cua con tro
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    String PRODUCT_ID_ = cursor.getString(cursor.getColumnIndex(PRODUCT_ID));
                    String PRODUCT_NAME_ = cursor.getString(cursor.getColumnIndex(PRODUCT_NAME));
                    String PRODUCT_SPECIES_ = cursor.getString(cursor.getColumnIndex(PRODUCT_SPECIES));
                    String PRODUCT_DESCRIPTION_ = cursor.getString(cursor.getColumnIndex(PRODUCT_DESCRIPTION));
                    int PRODUCT_PRICE_ = cursor.getInt(cursor.getColumnIndex(PRODUCT_PRICE));
                    int PRODUCT_SOLDNUMBER_ = cursor.getInt(cursor.getColumnIndex(PRODUCT_SOLDNUMBER));
                    String PRODUCT_TYPE_ = cursor.getString(cursor.getColumnIndex(PRODUCT_TYPE));
                    byte[] PRODUCT_IMAGE_ = cursor.getBlob(cursor.getColumnIndex(PRODUCT_IMAGE));

                    Product product = new Product(PRODUCT_ID_, PRODUCT_NAME_, PRODUCT_SPECIES_, PRODUCT_TYPE_, PRODUCT_SOLDNUMBER_, PRODUCT_PRICE_, PRODUCT_IMAGE_, PRODUCT_DESCRIPTION_);

                    //add user vao array users;
                    courseList.add(product);
                    //di chuyen toi vi tri tiep theo
                    cursor.moveToNext();
                }
                cursor.close();
                sqLiteDatabase.close();
            }
        }
        return courseList;
    }

    //Get List sản phẩm giảm dần
    public List<Product> getAllProductDESC(String type) {
        List<Product> courseList = new ArrayList<>();
        String sSQL = "SELECT * FROM SanPham WHERE TheLoai = '" + type + "' ORDER BY GiaThanh DESC ";
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sSQL, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                //di chuyen toi vi tri dau tien cua con tro
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    String PRODUCT_ID_ = cursor.getString(cursor.getColumnIndex(PRODUCT_ID));
                    String PRODUCT_NAME_ = cursor.getString(cursor.getColumnIndex(PRODUCT_NAME));
                    String PRODUCT_SPECIES_ = cursor.getString(cursor.getColumnIndex(PRODUCT_SPECIES));
                    String PRODUCT_DESCRIPTION_ = cursor.getString(cursor.getColumnIndex(PRODUCT_DESCRIPTION));
                    int PRODUCT_PRICE_ = cursor.getInt(cursor.getColumnIndex(PRODUCT_PRICE));
                    int PRODUCT_SOLDNUMBER_ = cursor.getInt(cursor.getColumnIndex(PRODUCT_SOLDNUMBER));
                    String PRODUCT_TYPE_ = cursor.getString(cursor.getColumnIndex(PRODUCT_TYPE));
                    byte[] PRODUCT_IMAGE_ = cursor.getBlob(cursor.getColumnIndex(PRODUCT_IMAGE));

                    Product product = new Product(PRODUCT_ID_, PRODUCT_NAME_, PRODUCT_SPECIES_, PRODUCT_TYPE_, PRODUCT_SOLDNUMBER_, PRODUCT_PRICE_, PRODUCT_IMAGE_, PRODUCT_DESCRIPTION_);

                    //add user vao array users;
                    courseList.add(product);
                    //di chuyen toi vi tri tiep theo
                    cursor.moveToNext();
                }
                cursor.close();
                sqLiteDatabase.close();
            }
        }
        return courseList;
    }

    public List<Product> getAllProduct(String type) {
        List<Product> courseList = new ArrayList<>();
        String sSQL = "SELECT * FROM SanPham WHERE TheLoai = '" + type + "'";
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sSQL, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                //di chuyen toi vi tri dau tien cua con tro
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    String PRODUCT_ID_ = cursor.getString(cursor.getColumnIndex(PRODUCT_ID));
                    String PRODUCT_NAME_ = cursor.getString(cursor.getColumnIndex(PRODUCT_NAME));
                    String PRODUCT_SPECIES_ = cursor.getString(cursor.getColumnIndex(PRODUCT_SPECIES));
                    String PRODUCT_DESCRIPTION_ = cursor.getString(cursor.getColumnIndex(PRODUCT_DESCRIPTION));
                    int PRODUCT_PRICE_ = cursor.getInt(cursor.getColumnIndex(PRODUCT_PRICE));
                    int PRODUCT_SOLDNUMBER_ = cursor.getInt(cursor.getColumnIndex(PRODUCT_SOLDNUMBER));
                    String PRODUCT_TYPE_ = cursor.getString(cursor.getColumnIndex(PRODUCT_TYPE));
                    byte[] PRODUCT_IMAGE_ = cursor.getBlob(cursor.getColumnIndex(PRODUCT_IMAGE));

                    Product product = new Product(PRODUCT_ID_, PRODUCT_NAME_, PRODUCT_SPECIES_, PRODUCT_TYPE_, PRODUCT_SOLDNUMBER_, PRODUCT_PRICE_, PRODUCT_IMAGE_, PRODUCT_DESCRIPTION_);

                    //add user vao array users;
                    courseList.add(product);
                    //di chuyen toi vi tri tiep theo
                    cursor.moveToNext();
                }
                cursor.close();
                sqLiteDatabase.close();
            }
        }
        return courseList;
    }

    public byte[] getImageProduct(String id_product) {
        byte[] image = null;
        String QUERY = "SELECT HinhAnh FROM SanPham WHERE MaSanPham = '" + id_product + "'";
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(QUERY, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    image = cursor.getBlob(0);
                    cursor.moveToNext();
                }
                cursor.close();
                sqLiteDatabase.close();
            }
        }
        return image;

    }
}
