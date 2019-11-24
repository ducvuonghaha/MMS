package com.example.mms.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mms.database.Database;
import com.example.mms.model.MyOrders;
import com.example.mms.model.ProductCart;

import java.util.ArrayList;
import java.util.List;

import static com.example.mms.database.Constant.MY_ORDER_ID;
import static com.example.mms.database.Constant.MY_ORDER_IMAGE;
import static com.example.mms.database.Constant.MY_ORDER_NAME;
import static com.example.mms.database.Constant.MY_ORDER_NUMBER;
import static com.example.mms.database.Constant.MY_ORDER_PRICE;
import static com.example.mms.database.Constant.MY_ORDER_USERNAME;
import static com.example.mms.database.Constant.PRODUCT_CART_ID;
import static com.example.mms.database.Constant.PRODUCT_CART_IMAGE;
import static com.example.mms.database.Constant.PRODUCT_CART_NAME;
import static com.example.mms.database.Constant.PRODUCT_CART_NUMBER;
import static com.example.mms.database.Constant.PRODUCT_CART_PRICE;
import static com.example.mms.database.Constant.PRODUCT_CART_TABLE;
import static com.example.mms.database.Constant.PRODUCT_CART_USERNAME;

public class ProductCartDAO {
    private Database database;

    public ProductCartDAO(Context context) {
        this.database = new Database(context);
    }

    public long insertProductCart(ProductCart productCart) {
        long result = -1;
        ContentValues contentValues = new ContentValues();
        contentValues.put(PRODUCT_CART_ID, productCart.PRODUCT_CART_ID);
        contentValues.put(PRODUCT_CART_NAME, productCart.PRODUCT_CART_NAME);
        contentValues.put(PRODUCT_CART_USERNAME, productCart.PRODUCT_CART_USERNAME);
        contentValues.put(PRODUCT_CART_NUMBER, productCart.PRODUCT_CART_NUMBER);
        contentValues.put(PRODUCT_CART_IMAGE, productCart.PRODUCT_CART_IMAGE);
        contentValues.put(PRODUCT_CART_PRICE, productCart.PRODUCT_CART_PRICE);

        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        result = sqLiteDatabase.insert(PRODUCT_CART_TABLE, null, contentValues);
        sqLiteDatabase.close();

        return result;
    }

    public long updateProductCartAmount(ProductCart productCart, String masanpham) {
        long result = -1;
        ContentValues contentValues = new ContentValues();
        contentValues.put(PRODUCT_CART_NUMBER, productCart.PRODUCT_CART_NUMBER);

        //xin quyen ghi vao bang
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();

        result = sqLiteDatabase.update(PRODUCT_CART_TABLE, contentValues, PRODUCT_CART_ID + "=?",
                new String[]{masanpham});

        return result;
    }

    public long deleteProductCart(String productcart_id) {
        long result = -1;
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();

        result = sqLiteDatabase.delete(PRODUCT_CART_TABLE, PRODUCT_CART_ID + "=?",
                new String[]{productcart_id});

        return result;
    }

    public List<ProductCart> getAllProductCart(String username) {
        List<ProductCart> productCartList = new ArrayList<>();
        String sSQL = "SELECT * FROM GioHang WHERE Username like '" + username + "'";
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sSQL, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                //di chuyen toi vi tri dau tien cua con tro
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    String PRODUCT_CART_ID_ = cursor.getString(cursor.getColumnIndex(PRODUCT_CART_ID));
                    String PRODUCT_CART_NAME_ = cursor.getString(cursor.getColumnIndex(PRODUCT_CART_NAME));
                    String PRODUCT_CART_USERNAME_ = cursor.getString(cursor.getColumnIndex(PRODUCT_CART_USERNAME));
                    int PRODUCT_CART_PRICE_ = cursor.getInt(cursor.getColumnIndex(PRODUCT_CART_PRICE));
                    int PRODUCT_CART_NUMBER_ = cursor.getInt(cursor.getColumnIndex(PRODUCT_CART_NUMBER));
                    byte[] PRODUCT_CART_IMAGE_ = cursor.getBlob(cursor.getColumnIndex(PRODUCT_CART_IMAGE));

                    ProductCart productCart = new ProductCart(PRODUCT_CART_ID_, PRODUCT_CART_NAME_, PRODUCT_CART_USERNAME_, PRODUCT_CART_NUMBER_, PRODUCT_CART_PRICE_, PRODUCT_CART_IMAGE_);
                    //add user vao array users;
                    productCartList.add(productCart);
                    //di chuyen toi vi tri tiep theo
                    cursor.moveToNext();
                }
                cursor.close();
                sqLiteDatabase.close();
            }
        }
        return productCartList;
    }


    public double getTongTien(String username) {
        double tongtien = 0;
        String QUERY = "SELECT SUM(GiaThanh*SoLuong) FROM GioHang WHERE Username = '" + username + "' ";
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(QUERY, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    tongtien = cursor.getDouble(0);
                    cursor.moveToNext();
                }
                cursor.close();
                sqLiteDatabase.close();
            }
        }
        return tongtien;
    }

    public void insertMyOrders(String username) {
        String QUERY = "INSERT INTO DonHangCuaToi(Username,TenSanPham,SoLuong,GiaThanh,HinhAnh)" +
                " SELECT Username,TenSanPham,SoLuong,GiaThanh,HinhAnh FROM GioHang" +
                " WHERE Username like '" + username + "'";
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        sqLiteDatabase.execSQL(QUERY);
    }

    public void deleteCart(String username) {
        String QUERY = "DELETE FROM GioHang WHERE Username like '" + username + "'";
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        sqLiteDatabase.execSQL(QUERY);
    }

    public List<MyOrders> getAllMyOrders(String username) {
        List<MyOrders> myOrdersList = new ArrayList<>();
        String sSQL = "SELECT * FROM DonHangCuaToi WHERE Username like '" + username + "'";
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sSQL, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                //di chuyen toi vi tri dau tien cua con tro
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    String MY_ORDER_ID_ = cursor.getString(cursor.getColumnIndex(MY_ORDER_ID));
                    String MY_ORDER_NAME_ = cursor.getString(cursor.getColumnIndex(MY_ORDER_NAME));
                    String MY_ORDER_USERNAME_ = cursor.getString(cursor.getColumnIndex(MY_ORDER_USERNAME));
                    int MY_ORDER_PRICE_ = cursor.getInt(cursor.getColumnIndex(MY_ORDER_PRICE));
                    int MY_ORDER_NUMBER_ = cursor.getInt(cursor.getColumnIndex(MY_ORDER_NUMBER));
                    byte[] MY_ORDER_IMAGE_ = cursor.getBlob(cursor.getColumnIndex(MY_ORDER_IMAGE));

                    MyOrders myOrders = new MyOrders(MY_ORDER_ID_, MY_ORDER_NAME_, MY_ORDER_USERNAME_, MY_ORDER_NUMBER_, MY_ORDER_PRICE_, MY_ORDER_IMAGE_);
                    //add user vao array users;
                    myOrdersList.add(myOrders);
                    //di chuyen toi vi tri tiep theo
                    cursor.moveToNext();
                }
                cursor.close();
                sqLiteDatabase.close();
            }
        }
        return myOrdersList;
    }

    public int getNumberInCart(String username) {
        int soluong = 0;
        String QUERY = "SELECT COUNT(MaSanPham) FROM GioHang  WHERE Username = '" + username + "'";
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(QUERY, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    soluong = cursor.getInt(0);
                    cursor.moveToNext();
                }
                cursor.close();
                sqLiteDatabase.close();
            }
        }
        return soluong;
    }
}
