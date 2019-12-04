package com.example.mms.activity;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.mms.R;
import com.example.mms.base.AlarmReceiver;
import com.example.mms.base.BaseActivity;
import com.example.mms.dao.MyVoucherDAO;
import com.example.mms.dao.ProductDAO;
import com.example.mms.main_fragment.HomeFragment;
import com.example.mms.main_fragment.NotificationActivity;
import com.example.mms.main_fragment.ProfileFragment;
import com.example.mms.model.MyVoucher;
import com.example.mms.model.Product;
import com.facebook.login.LoginManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;


public class HomeActivity extends BaseActivity {
    private ProductDAO productDAO;
    private MyVoucherDAO myVoucherDAO;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectFragment ;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectFragment = new HomeFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, selectFragment).commit();
                    break;
                case R.id.navigation_notifications:
                    Intent intent = new Intent(HomeActivity.this, NotificationActivity.class);
                    startActivity(intent);
                    break;
                case R.id.navigation_profile:
                    selectFragment = new ProfileFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, selectFragment).commit();
                    break;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        try {

            PackageInfo info = getPackageManager().getPackageInfo(

                    "com.example.assignmentnc",

                    PackageManager.GET_SIGNATURES);

            for (Signature signature : info.signatures) {

                MessageDigest md = MessageDigest.getInstance("SHA");

                md.update(signature.toByteArray());

                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));

            }

        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.fisrtproject.facebookapi_androidad",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

        Intent notificationIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,14);
        cal.set(Calendar.MINUTE,51);
        cal.set(Calendar.SECOND,5);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new HomeFragment()).commit();
        productDAO = new ProductDAO(this);
        myVoucherDAO = new MyVoucherDAO(this);

        Bitmap voucher = convertDrawabletoBitmap(R.drawable.sale30);
        Bitmap voucher1 = convertDrawabletoBitmap(R.drawable.sale50);

        byte[] myvoucher = setByteArray(voucher);
        byte[] myvoucher1 = setByteArray(voucher1);

        myVoucherDAO.insertVouchers(new MyVoucher("SALE50",getRootUsername(),"20/11/2019","Giảm giá 30% tất cả phim nhân ngày Nhà Giáo",0.5,myvoucher));
        myVoucherDAO.insertVouchers(new MyVoucher("SALE30",getRootUsername(),"24/12/2019","Giảm giá 50% tất cả nhạc nhân dịp Giáng Sinh",0.7,myvoucher1));

        Bitmap mms = convertDrawabletoBitmap(R.drawable.quyan_f);
        Bitmap mms1 = convertDrawabletoBitmap(R.drawable.it_f);
        Bitmap mms2 = convertDrawabletoBitmap(R.drawable.dieubamekhongke_f);

        byte[] quyan_f = setByteArray(mms);
        byte[] it_f = setByteArray(mms1);
        byte[] dieubamekhongke_f = setByteArray(mms2);


        productDAO.insertProduct(new Product("ID01", "Quỷ Ấn", "Kinh Dị","Hot",
                100, 9000000, quyan_f,"quyan","A Bluebird in My Heart dựa trên tiểu thuyết The Dishwasher (Người Rửa Chén) của Dannie M Martin. Phim kể về câu chuyện của cựu tù là Daniel (Roland Moller), người làm công việc rửa chén trong nhà hàng và sống trong một nhà nghỉ cũ cố gắng để có một cuộc sống bình lặng. Tại đây, anh kết bạn với chủ nhà nghỉ là một người mẹ đơn thân Laurence (Veerle Baetens) và cô con gái Clara (Lola La Lann)."));

        productDAO.insertProduct(new Product("ID02", "Chú Hề Ma Quái 2", "Kinh Dị","Hot",
                100, 899000, it_f,"it2","It Chapter Two (Gã Hề Ma Quái 2) vẫn là câu chuyện về những cô cậu bé của nhóm The Losers Club, lúc này đã trưởng thành và đối mặt với vô số vấn đề trong cuộc sống. Chưa dừng lại ở đó, ám ảnh ma hề Pennywise cứ 27 năm lại xuất hiện một lần tại thị trấn Derry buộc 7 cô cậu bé ngày nào phải tiếp tục cuốn vào cuộc chạm trán tiếp theo giữa hai bên thiện và ác."));

        productDAO.insertProduct(new Product("ID03", "Điều Ba Mẹ Không Kể", "Lãng Mạn","Hot",
                100, 26900000, dieubamekhongke_f,"dieubamekhongke","Cho Nam-bong và Lee Mae-ja là cặp vợ chồng già cùng mắc căn bệnh mất trí nhớ. Sau 45 năm chung sống, họ chẳng thể nhớ lần cuối mình từng hạnh phúc là khi nào. Tuy nhiên, khi ký ức dần mất đi, tình yêu và ước mơ từng bị bỏ quên của họ lại dần nhen nhóm lại."));



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindDrawables(findViewById(R.id.container));
        System.gc();
    }

    private void unbindDrawables(View view) {
        if (view.getBackground() != null) {
            view.getBackground().setCallback(null);
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                unbindDrawables(((ViewGroup) view).getChildAt(i));
            }
            ((ViewGroup) view).removeAllViews();
        }
    }

    private String getRootUsername() {
        String name;
        name = getSharedPreferences("USER", MODE_PRIVATE).getString("NAME", null);
        return name;
    }


    @Override
    protected void onStart() {
        LoginManager.getInstance().logOut();
        super.onStart();
    }
}
