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
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.mms.R;
import com.example.mms.base.AlarmReceiver;
import com.example.mms.base.BaseActivity;
import com.example.mms.dao.MyVoucherDAO;
import com.example.mms.dao.ProductDAO;
import com.example.mms.main_fragment.Home2Fragment;
import com.example.mms.main_fragment.HomeFragment;
import com.example.mms.main_fragment.NotificationActivity;
import com.example.mms.main_fragment.ProfileFragment;
import com.example.mms.model.Product;
import com.facebook.login.LoginManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

public class Home2Activity extends BaseActivity {
    private RelativeLayout container2;
    private FrameLayout fragmentLayout2;
    private BottomNavigationView navigation2;

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
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout2, selectFragment).commit();
                    break;
                case R.id.navigation_notifications:
                    Intent intent = new Intent(Home2Activity.this, NotificationActivity.class);
                    startActivity(intent);
                    break;
                case R.id.navigation_profile:
                    selectFragment = new ProfileFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout2, selectFragment).commit();
                    break;
            }
            return true;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        init();

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


        navigation2.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout2, new Home2Fragment()).commit();
        productDAO = new ProductDAO(this);

        //Pop
        Bitmap mms = convertDrawabletoBitmap(R.drawable.maimailamotloinoidoi_m);
        Bitmap mms1 = convertDrawabletoBitmap(R.drawable.hongnhanbacphan_m);
        Bitmap mms2 = convertDrawabletoBitmap(R.drawable.phiasaumotcogai_m);
        Bitmap mms3 = convertDrawabletoBitmap(R.drawable.pop_m);

        byte[] maimailamotloinoidoi_m = setByteArray(mms);
        byte[] hongnhanbacphan_m = setByteArray(mms1);
        byte[] phiasaumotcogai_m = setByteArray(mms2);
        byte[] pop_m = setByteArray(mms3);

        productDAO.insertProduct(new Product("ID100", "Mãi mãi là một lời nói dối", "Nhạc Trẻ", "HotMusic",
                100, 150000, maimailamotloinoidoi_m, "maimaimotloinoidoi","Mãi mãi là một lời nói dối\n" +
                "Chẳng ai cam tâm yêu ai cả đời\n" +
                "Em từng vì anh mà bỏ lỡ cả tuổi xuân\n" +
                "Đánh đổi yên bình\n" +
                "Nhận lấy riêng mình giông bão\n" +
                "\n" +
                "Mãi mãi là một lời nói dối\n" +
                "Gặp anh em cứ ngỡ gặp đúng người\n" +
                "Nhưng anh cũng giống bao người khác...."));

        productDAO.insertProduct(new Product("ID101", "Hồng nhan bạc phận", "Nhạc Trẻ", "HotMusic",
                100, 150000, hongnhanbacphan_m, "hongnhanbacphan", "Ai gieo tình này, ai mang tình này, để lệ trên khóe mi cay\n" +
                "Ai đưa về nhà, ai cho ngọc ngà, giờ người xa cách ta\n" +
                "Từng là một thời thiếu nữ trong vùng quê nghèo\n" +
                "Hồn nhiên cài hoa mái đầu\n" +
                "Dòng người vội vàng em hóa thân đời bẽ bàng\n" +
                "Rời xa tình anh năm tháng ...\n" +
                "Ôi phút giây tương phùng anh nhớ và mong\n" +
                "Dòng lưu bút năm xưa in dấu mãi đậm sâu\n" +
                "Trong nỗi đau anh mệt nhoài\n" +
                "Trong phút giây anh tìm hoài\n" +
                "Muốn giữ em ở lại một lần này vì anh mãi thương\n" +
                "Xa cách nhau thật rồi sương trắng chiều thu\n" +
                "Ngày em bước ra đi nước mắt ấy biệt li\n" +
                "Hoa vẫn rơi bên thềm nhà\n" +
                "Lá xát xơ đi nhiều và\n" +
                "Anh chúc em yên bình mối tình mình hẹn em kiếp sau ..."));

        productDAO.insertProduct(new Product("ID102", "Phía sau một cô gái", "Nhạc Trẻ", "Normal",
                100, 75000, phiasaumotcogai_m, "phiasaumotcogai", "Nhiều khi anh mong được một lần nói ra hết tất cả thay vì\n" +
                "Ngồi lặng im nghe em kể về anh ta bằng đôi mắt lấp lánh\n" +
                "Đôi lúc em tránh ánh mắt của anh\n" +
                "Vì dường như lúc nào em cũng hiểu thấu lòng anh\n" +
                "Không thể ngắt lời\n" +
                "Càng không thể để giọt lệ nào được rơi\n" +
                "Nên anh lùi bước về sau, để thấy em rõ hơn\n" +
                "Để có thể ngắm em từ xa âu yếm hơn\n" +
                "Cả nguồn sống bỗng chốc thu bé lại vừa bằng một cô gái\n" +
                "Hay anh vẫn sẽ lặng lẽ kế bên\n" +
                "Dù không nắm tay nhưng đường chung mãi mãi\n" +
                "Và từ ấy ánh mắt anh hồn nhiên đến lạ"));

        productDAO.insertProduct(new Product("ID103", "Nhạc pop", "Nhạc Trẻ", "Normal",
                100, 450000, pop_m, "phiasaumotcogai", "Nhiều khi anh mong được một lần nói ra hết tất cả thay vì\n" +
                "Ngồi lặng im nghe em kể về anh ta bằng đôi mắt lấp lánh\n" +
                "Đôi lúc em tránh ánh mắt của anh\n" +
                "Vì dường như lúc nào em cũng hiểu thấu lòng anh\n" +
                "Không thể ngắt lời\n" +
                "Càng không thể để giọt lệ nào được rơi\n" +
                "Nên anh lùi bước về sau, để thấy em rõ hơn\n" +
                "Để có thể ngắm em từ xa âu yếm hơn\n" +
                "Cả nguồn sống bỗng chốc thu bé lại vừa bằng một cô gái\n" +
                "Hay anh vẫn sẽ lặng lẽ kế bên\n" +
                "Dù không nắm tay nhưng đường chung mãi mãi\n" +
                "Và từ ấy ánh mắt anh hồn nhiên đến lạ"));

//
////        productDAO.insertProduct(new Product("ID07", "Căn bệnh quái ác", "Kinh Dị", "Normal",
////        100, 450000, canbenhquaiac_f, "canbenhquaiac", ""));
////
////        productDAO.insertProduct(new Product("ID07", "Căn bệnh quái ác", "Kinh Dị", "Normal",
////        100, 450000, canbenhquaiac_f, "canbenhquaiac", ""));
//
//
        //Trữ tình
        Bitmap mms6 = convertDrawabletoBitmap(R.drawable.dembuontinhle_m);
        Bitmap mms7 = convertDrawabletoBitmap(R.drawable.monganhyeuthatlong_m);
        Bitmap mms8 = convertDrawabletoBitmap(R.drawable.dapvocaydan_m);
        Bitmap mms9 = convertDrawabletoBitmap(R.drawable.bolero_m);

        byte[] dembuontinhle_m = setByteArray(mms6);
        byte[] monganhyeuthatlong_m = setByteArray(mms7);
        byte[] dapvocaydan_m = setByteArray(mms8);
        byte[] bolero_m = setByteArray(mms9);

        productDAO.insertProduct(new Product("ID106", "Đêm buồn tỉnh lẻ", "Trữ Tình", "HotMusic",
                  100, 150000, dembuontinhle_m, "dembuontinhle", "Đã lâu rồi đôi lứa cách đôi nơi tơ duyên xưa còn hay mất\n" +
                "Mái trường ơi em tôi còn học nữa hay ra đi từ độ nào\n" +
                "Ngày xưa đó ta hay đón dìu nhau đi trên con đường lẻ loi\n" +
                "Mấy năm qua rồi em anh không gặp nữa\n" +
                "Bao yêu thương và nhớ anh xin chép nên thơ\n" +
                "vào những đêm buồn\n" +
                "\n" +
                "Mưa mưa rơi từng đêm\n" +
                "Mưa triền miên trên đồn khuya\n" +
                "Lòng ai thương nhớ vô biên\n" +
                "Anh ra đi ngày đó\n" +
                "Ta nhìn nhau mắt hoen sầu\n" +
                "Không nói nên câu giã từ\n" +
                "\n" +
                "Mong anh mong làm sao\n" +
                "Cho tình duyên không nhạt phai\n" +
                "Theo năm tháng thoáng qua mau\n" +
                "Yêu, yêu em nhiều lắm\n" +
                "Nhưng tình ta vẫn không thành\n" +
                "Khi núi sông còn điêu linh"));

        productDAO.insertProduct(new Product("ID107", "Mong anh yêu thật lòng", "Trữ Tình", "HotMusic",
                 100, 150000, monganhyeuthatlong_m, "monganhyeuthatlong", "Là thân con gái em nào nghĩ gì\n" +
                "Đâu nghĩ rằng anh sang hèn thế nào\n" +
                "Nhưng cớ sao anh mau đổi thay\n" +
                "Anh vờ như là không hay\n" +
                "Bỏ mặc em gặm nhấm chua cay\n" +
                "Dù em đã biết anh là phũ phàng\n" +
                "Nhưng vẫn hoài mang\n" +
                "Nỗi niềm trong lòng\n" +
                "Đây trót yêu nên trao tất cả\n" +
                "Bạc tiền sự nghiệp công danh\n" +
                "Đổi lại bằng chỉ là số không\n" +
                "Anh bây giờ chắc là đang vui\n" +
                "Bên nhân tình có phải không anh\n" +
                "Người yêu anh ra sao có phải là như em\n" +
                "Khi yêu phải bán mua bằng tiền\n" +
                "Nhưng anh à chắc là không đâu\n" +
                "Do tim này quá là yêu anh\n" +
                "Giờ đây em buông tay\n" +
                "Và xin anh hãy thôi\n" +
                "Một khi đã yêu ai đừng gian dối\n" +
                "Tội nghiệp người ta\n" +
                "Giờ đây hai đứa xa biệt mất rồi\n" +
                "Duyên kiếp từ nay chẳng còn nữa rồi\n" +
                "Nhưng thấm sâu trong tim của em\n" +
                "Mong rằng anh được hạnh phúc\n" +
                "Với một người anh thật lòng yêu"));

        productDAO.insertProduct(new Product("ID108", "Đập vỡ cây đàn", "Trữ Tình", "Normal",
                 100, 85000, dapvocaydan_m, "dapvocaydan", "Đập vỡ cây đàn giận đời đập vỡ cây đàn\n" +
                "người ơi người ơi! Tình ơi tình ơi!\n" +
                "Đập vỡ cây đàn giận đời bạc trắng như vôi\n" +
                "giận người điên đảo quên lời\n" +
                "\n" +
                "Đập vỡ cây đàn giận người con gái yêu đàn\n" +
                "Buồn ơi buồn ơi! Làm sao để nguôi\n" +
                "Đập vỡ cây đàn giận người đổi trắng thay đen\n" +
                "giận đời trở như bàn tay\n" +
                "\n" +
                "ĐK:\n" +
                "Chuyện ngày qua, tôi gặp người con gái\n" +
                "Mang giọng ca thật buồn\n" +
                "Em bảo tôi rằng: Anh đi học đàn\n" +
                "Để đàn theo lúc em ca, những ngày hoa mộng đời ta\n" +
                "\n" +
                "Tôi yêu nàng nên vội vàng lên tỉnh\n" +
                "Đi tìm theo học đàn\n" +
                "Sau một năm trường, tôi trở về quê hương\n" +
                "Nhưng người em gái, ngày ấy đã đi rồi"));

        productDAO.insertProduct(new Product("ID109", "Trữ tình", "Trữ Tình", "Normal",
                 100, 450000, bolero_m, "canbenhquaiac", "Đập vỡ cây đàn giận đời đập vỡ cây đàn\n" +
                "người ơi người ơi! Tình ơi tình ơi!\n" +
                "Đập vỡ cây đàn giận đời bạc trắng như vôi\n" +
                "giận người điên đảo quên lời\n" +
                "\n" +
                "Đập vỡ cây đàn giận người con gái yêu đàn\n" +
                "Buồn ơi buồn ơi! Làm sao để nguôi\n" +
                "Đập vỡ cây đàn giận người đổi trắng thay đen\n" +
                "giận đời trở như bàn tay\n" +
                "\n" +
                "ĐK:\n" +
                "Chuyện ngày qua, tôi gặp người con gái\n" +
                "Mang giọng ca thật buồn\n" +
                "Em bảo tôi rằng: Anh đi học đàn\n" +
                "Để đàn theo lúc em ca, những ngày hoa mộng đời ta\n" +
                "\n" +
                "Tôi yêu nàng nên vội vàng lên tỉnh\n" +
                "Đi tìm theo học đàn\n" +
                "Sau một năm trường, tôi trở về quê hương\n" +
                "Nhưng người em gái, ngày ấy đã đi rồi"));
//
////        productDAO.insertProduct(new Product("ID07", "Căn bệnh quái ác", "Kinh Dị", "Normal",
////                 100, 450000, canbenhquaiac_f, "canbenhquaiac", ""));
//
////        productDAO.insertProduct(new Product("ID07", "Căn bệnh quái ác", "Kinh Dị", "Normal",
////                 100, 450000, canbenhquaiac_f, "canbenhquaiac", ""));
//
        //Rock

        Bitmap mms12 = convertDrawabletoBitmap(R.drawable.monster_m);
        Bitmap mms13 = convertDrawabletoBitmap(R.drawable.quenanhdi_m);
        Bitmap mms14 = convertDrawabletoBitmap(R.drawable.thaytoiyeucoay_m);
        Bitmap mms15 = convertDrawabletoBitmap(R.drawable.rock_m);

        byte[] monster_m = setByteArray(mms12);
        byte[] quenanhdi_m = setByteArray(mms13);
        byte[] thaytoiyeucoay_m = setByteArray(mms14);
        byte[] rock_m = setByteArray(mms15);

        productDAO.insertProduct(new Product("ID112", "Monster", "Nhạc Rock", "HotMusic",
                 100, 150000, monster_m, "monster", "Vậy là ta đã xa nhau thật sao\n" +
                "Từng dòng kí ức biến tan trong anh ngọt ngào\n" +
                "Lời yêu thương bên nhau em chẳng vương\n" +
                "Hỏi làm sao giữ khi không đi đến cuối đường\n" +
                "\n" +
                "Ngày chia tay trong tim em buồn không\n" +
                "Giọt lệ anh đã trót rơi khi em lạnh lùng\n" +
                "Bởi vì ai anh đau bởi vì ai\n" +
                "Dại khờ trao hết bao yêu thương xót lại\n" +
                "\n" +
                "[ĐK:]\n" +
                "Người chuốc say anh bao lời\n" +
                "Để đến khi em phản bội\n" +
                "Anh vẫn ngu ngơ chưa thể tin rằng em lừa dối\n" +
                "Tình chẳng có câu chung thủy"));

        productDAO.insertProduct(new Product("ID113", "Quên anh đi", "Nhạc Rock", "HotMusic",
                 100, 250000, quenanhdi_m, "quenanhdi", "Tin nhắn đêm qua anh vẫn còn chưa xem\n" +
                "Anh nhớ không ra, ta đã vui bao đêm\n" +
                "Giờ này em ở đâu\n" +
                "Không cần cho anh biết\n" +
                "Giữ lại những cảm xúc\n" +
                "Ta phải nhớ nhau chi\n" +
                "Tốt hơn em không nên dại khờ oh-oh-oh (Baby)\n" +
                "Tình yêu không như cầu vồng\n" +
                "Quên tên nhau là xong\n" +
                "Baby thôi em không nên chờ oh-oh-oh\n" +
                "Lòng tin anh như ngày hạ\n" +
                "Và tim anh như mùa đông\n" +
                "Baby tốt nhất em hãy quên a-a-a-anh\n" +
                "Tốt nhất em hãy quên a-a-a-anh\n" +
                "Tốt nhất em hãy quên a-a-a-anh\n" +
                "Tốt nhất em hãy quên a-a-a-anh\n" +
                "Thôi em không nên chờ\n" +
                "Thôi em không nên chờ\n" +
                "Quên tên nhau là xong, quên tên nhau đi baby (không nên chờ)\n" +
                "Thôi em không nên chờ\n" +
                "Thôi em không nên chờ\n" +
                "Quên tên nhau là xong, quên tên nhau đi baby"));

        productDAO.insertProduct(new Product("ID114", "Thay tôi yêu cô ấy", "Nhạc Rock", "Normal",
                 100, 120000, thaytoiyeucoay_m, "thaytoiyeucoay", "Ngày hôm nay cô ấy chẳng quan tâm gì mấy\n" +
                "Chỉ cần 1 người để yêu thương lúc này\n" +
                "Ngày hôm nay cô ấy muốn đi để mua 1 bó hoa\n" +
                "Trang trí nơi đẹp nhất trong nhà\n" +
                "Cô ấy hay buồn lúc mưa, thích nghe câu ca ngày xưa\n" +
                "Những lúc khi trời đông nhớ vòng tay ấm nồng và ôm cô ấy thật lâu\n" +
                "\n" +
                "DK\n" +
                "\n" +
                "Người ấy vì thương tôi chờ đợi tôi cũng lâu rồi\n" +
                "Mà tình yêu cứ xa vời nên cô ấy ngưng đợi\n" +
                "Ngày ấy gần bên tôi 1 giây thôi cũng không rời\n" +
                "Nhưng vì lo nghĩ cuộc đời nên mới cách xa thôi\n" +
                "Người hãy dần thay tôi gọi cô ấy thức giấc và\n" +
                "Chờ cô ấy trước ngôi nhà đưa cô ấy la cà\n" +
                "Người yêu của tôi ơi hãy tha thứ cho anh\n" +
                "Đã yêu em khi chẳng có chi trong cuộc sống!"));

        productDAO.insertProduct(new Product("ID115", "Rock", "Nhạc Rock", "Normal",
                 100, 450000, rock_m, "thaytoiyeucoay", "Ngày hôm nay cô ấy chẳng quan tâm gì mấy\n" +
                "Chỉ cần 1 người để yêu thương lúc này\n" +
                "Ngày hôm nay cô ấy muốn đi để mua 1 bó hoa\n" +
                "Trang trí nơi đẹp nhất trong nhà\n" +
                "Cô ấy hay buồn lúc mưa, thích nghe câu ca ngày xưa\n" +
                "Những lúc khi trời đông nhớ vòng tay ấm nồng và ôm cô ấy thật lâu\n" +
                "\n" +
                "DK\n" +
                "\n" +
                "Người ấy vì thương tôi chờ đợi tôi cũng lâu rồi\n" +
                "Mà tình yêu cứ xa vời nên cô ấy ngưng đợi\n" +
                "Ngày ấy gần bên tôi 1 giây thôi cũng không rời\n" +
                "Nhưng vì lo nghĩ cuộc đời nên mới cách xa thôi\n" +
                "Người hãy dần thay tôi gọi cô ấy thức giấc và\n" +
                "Chờ cô ấy trước ngôi nhà đưa cô ấy la cà\n" +
                "Người yêu của tôi ơi hãy tha thứ cho anh\n" +
                "Đã yêu em khi chẳng có chi trong cuộc sống!"));

//        productDAO.insertProduct(new Product("ID07", "Căn bệnh quái ác", "Kinh Dị", "Normal",
//                 100, 450000, canbenhquaiac_f, "canbenhquaiac", ""));

//        productDAO.insertProduct(new Product("ID07", "Căn bệnh quái ác", "Kinh Dị", "Normal",
//                 100, 450000, canbenhquaiac_f, "canbenhquaiac", ""));


        //Thiếu nhi

        Bitmap mms18 = convertDrawabletoBitmap(R.drawable.giadinhnhohanhphucto_m);
        Bitmap mms19 = convertDrawabletoBitmap(R.drawable.babyshark);
        Bitmap mms20 = convertDrawabletoBitmap(R.drawable.gapmetrongmo_m);
        Bitmap mms21 = convertDrawabletoBitmap(R.drawable.chuvoicon_m);

        byte[] giadinhnhohanhphucto_m = setByteArray(mms18);
        byte[] babyshark = setByteArray(mms19);
        byte[] gapmetrongmo_m = setByteArray(mms20);
        byte[] chuvoicon_m = setByteArray(mms21);

        productDAO.insertProduct(new Product("ID118", "Gia đình nhỏ, hạnh phúc to", "Thiếu Nhi", "HotMusic",
                 100, 150000, giadinhnhohanhphucto_m, "giadinhnhohanhphucto", "Một nụ cười bé, cha vui cả ngày\n" +
                "Một vài tiếng khóc, mẹ lo hằng đêm\n" +
                "Thầm cầu mong cho, con sẽ an lành\n" +
                "Chín tháng sinh thành, một đời yêu thương\n" +
                "Một vòng tay lớn, ôm con vào lòng\n" +
                "Một bàn chân to, cho con tập đi\n" +
                "Dù ngày mai kia, con lớn nên người\n" +
                "Nhưng với cha mẹ, vẫn mãi bé thơ.\n" +
                "\n" +
                "ĐK:\n" +
                "À ơi à ơi,\n" +
                "Con ngủ cho ngoan, giấc mơ sẽ mang đầy lời mẹ ru.\n" +
                "À ơi à ơi,\n" +
                "Mãi mãi chúng ta. Một gia đình nhỏ. Một hạnh phúc to."));

        productDAO.insertProduct(new Product("ID119", "Baby Shark", "Thiếu Nhi", "HotMusic",
                 100, 45000, babyshark, "babyshark", "Mommy shark, doo doo doo doo doo doo\n" +
                "Mommy shark, doo doo doo doo doo doo\n" +
                "Mommy shark, doo doo doo doo doo doo\n" +
                "Mommy shark!\n" +
                "\n" +
                "Daddy shark, doo doo doo doo doo doo\n" +
                "Daddy shark, doo doo doo doo doo doo\n" +
                "Daddy shark, doo doo doo doo doo doo\n" +
                "Daddy shark!\n" +
                "\n" +
                "Grandma shark, doo doo doo doo doo doo\n" +
                "Grandma shark, doo doo doo doo doo doo\n" +
                "Grandma shark, doo doo doo doo doo doo\n" +
                "Grandma shark!\n" +
                "\n" +
                "Grandpa shark, doo doo doo doo doo doo\n" +
                "Grandpa shark, doo doo doo doo doo doo\n" +
                "Grandpa shark, doo doo doo doo doo doo\n" +
                "Grandpa shark!\n" +
                "\n" +
                "Let’s go hunt, doo doo doo doo doo doo\n" +
                "Let’s go hunt, doo doo doo doo doo doo\n" +
                "Let’s go hunt, doo doo doo doo doo doo\n" +
                "Let’s go hunt!"));

        productDAO.insertProduct(new Product("ID120", "Gặp mẹ trong mơ", "Thiếu Nhi", "Normal",
                 100, 250000, gapmetrongmo_m, "gapmetrongmo", "Này bầu trời rộng lớn ơi, có nghe chăng tiếng em gọi\n" +
                "Mẹ giờ này ở chốn nao, con đang mong nhớ về mẹ\n" +
                "Mẹ ở phương trời xa xôi, hay sao sáng trên bầu trời\n" +
                "Mẹ dịu hiền về với con nhé, con nhớ mẹ\n" +
                "Lời nguyện cầu từ chốn xa, mong ước con yên bình\n" +
                "Mẹ thật hiền tựa nắng mai ấp ôm con tháng ngày.\n" +
                "Mẹ giờ này ở chốn rất xa, trong mơ con đã thấy mẹ\n" +
                "Mẹ dịu dàng hát khúc ca, sao con thấy mẹ buồn\n" +
                "Nhìn cánh đồng xa xanh, con nhớ mong về mẹ\n" +
                "Mẹ trở về với con ấm áp bên mái nhà\n" +
                "Và từ bầu trời rất cao, mong ước con yên bình\n" +
                "Mẹ ngồi buồn ở chốn xa nhớ thương con vắng mẹ.\n" +
                "Gửi về mẹ nhiều cánh hoa, thắm sương long lanh giữa núi đồi\n" +
                "Chợt giật mình tỉnh giấc mơ, sao không thấy mẹ\n" +
                "Nghẹn nghào thương mẹ bao la, mong đến bên mẹ hiền\n" +
                "Welcome to Yeucahat.com\n" +
                "Mẹ ở lại với con nhé, con đến với mẹ."));


        productDAO.insertProduct(new Product("ID121", "Chú voi con", "Thiếu Nhi", "Normal",
                 100, 450000, chuvoicon_m, "gapmetrongmo", "Này bầu trời rộng lớn ơi, có nghe chăng tiếng em gọi\n" +
                "Mẹ giờ này ở chốn nao, con đang mong nhớ về mẹ\n" +
                "Mẹ ở phương trời xa xôi, hay sao sáng trên bầu trời\n" +
                "Mẹ dịu hiền về với con nhé, con nhớ mẹ\n" +
                "Lời nguyện cầu từ chốn xa, mong ước con yên bình\n" +
                "Mẹ thật hiền tựa nắng mai ấp ôm con tháng ngày.\n" +
                "Mẹ giờ này ở chốn rất xa, trong mơ con đã thấy mẹ\n" +
                "Mẹ dịu dàng hát khúc ca, sao con thấy mẹ buồn\n" +
                "Nhìn cánh đồng xa xanh, con nhớ mong về mẹ\n" +
                "Mẹ trở về với con ấm áp bên mái nhà\n" +
                "Và từ bầu trời rất cao, mong ước con yên bình\n" +
                "Mẹ ngồi buồn ở chốn xa nhớ thương con vắng mẹ.\n" +
                "Gửi về mẹ nhiều cánh hoa, thắm sương long lanh giữa núi đồi\n" +
                "Chợt giật mình tỉnh giấc mơ, sao không thấy mẹ\n" +
                "Nghẹn nghào thương mẹ bao la, mong đến bên mẹ hiền\n" +
                "Welcome to Yeucahat.com\n" +
                "Mẹ ở lại với con nhé, con đến với mẹ."));

//        productDAO.insertProduct(new Product("ID07", "Căn bệnh quái ác", "Kinh Dị", "Normal",
//                 100, 450000, canbenhquaiac_f, "canbenhquaiac", ""));

//        productDAO.insertProduct(new Product("ID07", "Căn bệnh quái ác", "Kinh Dị", "Normal",
//                 100, 450000, canbenhquaiac_f, "canbenhquaiac", ""));



        //Nhạc EDM

        Bitmap mms24 = convertDrawabletoBitmap(R.drawable.vansutuyduyen_m);
        Bitmap mms25 = convertDrawabletoBitmap(R.drawable.buocquadoinhau_m);
        Bitmap mms26 = convertDrawabletoBitmap(R.drawable.motdemsay_m);
        Bitmap mms27 = convertDrawabletoBitmap(R.drawable.giacmolaky_m);

        byte[] vansutuyduyen_m = setByteArray(mms24);
        byte[] buocquadoinhau_m = setByteArray(mms25);
        byte[] motdemsay_m = setByteArray(mms26);
        byte[] giacmolaky_m = setByteArray(mms27);

        productDAO.insertProduct(new Product("ID124", "Vạn sự tùy duyên", "Nhạc EDM", "HotMusic",
                 100, 350000, vansutuyduyen_m, "vansutuyduyen", "Hỏi tình yêu là gì, mà đôi lứa chia ly\n" +
                "\n" +
                "Thấy đau vô bờ, trái tim muốn ngừng hơi thở\n" +
                "\n" +
                "Hỏi tình yêu là chi, sao khiến ta luôn mong chờ\n" +
                "\n" +
                "Nhìn về phía nơi khung trời xa, ngỡ rằng như em vẫn đây\n" +
                "\n" +
                "Tình đôi ta mới yêu ban đầu, sao luôn ấm nồng\n" +
                "\n" +
                "Gặp nhau chút thôi, mây mù cũng đẹp như nắng thu\n" +
                "\n" +
                "Rồi giông tố xóa tan tên gọi của anh trong trái tim người\n" +
                "\n" +
                "Chẳng nhớ thương, chẳng vấn vương sao về hai hướng\n" +
                "\n" +
                "Phía xa vời có anh đang chờ, đến giữa đường thấy em hững hờ\n" +
                "\n" +
                "Trời xanh bỗng làm cơn giông tố khiến em dừng lại\n" +
                "\n" +
                "Nếu muôn ngàn phút giây bên nhau, chẳng phai được lý do xa nhau\n" +
                "\n" +
                "Vậy tùy duyên để cho chúng ta, sẽ hợp hay tan\n" +
                "\n" +
                "Tình đôi ta mới yêu ban đầu, sao luôn ấm nồng\n" +
                "\n" +
                "Gặp nhau chút thôi, mây mù cũng đẹp như nắng thu\n" +
                "\n" +
                "Rồi giông tố xóa tan tên gọi của anh trong trái tim người\n" +
                "\n" +
                "Chẳng nhớ thương, chẳng vấn vương sao về hai hướng\n" +
                "\n" +
                "Phía xa vời có anh đang chờ, đến giữa đường thấy em hững hờ\n" +
                "\n" +
                "Trời xanh bỗng làm cơn giông tố khiến em dừng lại\n" +
                "\n" +
                "Nếu muôn ngàn phút giây bên nhau, chẳng phai được lý do xa nhau\n" +
                "\n" +
                "Vậy tùy duyên để cho chúng ta, sẽ hợp hay tan\n" +
                "\n" +
                "Một tình yêu, về hai lối."));

        productDAO.insertProduct(new Product("ID125", "Bước qua đời nhau", "Nhạc EDM", "HotMusic",
                 100, 150000, buocquadoinhau_m, "buocquadoinhau", "Mình bước qua đời nhau,\n" +
                "\n" +
                "Để làm nhau đau\n" +
                "\n" +
                "Để làm nhau khóc gặp nhau ko muốn chào.\n" +
                "\n" +
                "Mình bước đời nhau, để lại thương đau\n" +
                "\n" +
                "Để lại vệt sâu có xóa mờ đc đâu\n" +
                "\n" +
                "Mình đã từng hạnh phúc, phải ko anh ơi\n" +
                "\n" +
                "Mình từng chung lối khổ đau lẫn tiếng cười\n" +
                "\n" +
                "Mình đã luôn từng nói, à ko thể chưa\n" +
                "\n" +
                "Mình từng thế ước mãi ko bao giờ xa.\n" +
                "\n" +
                "Mà cớ sao ta giờ đây bước qua đời nhau\n" +
                "\n" +
                "Rồi trái tim ta phải đau nhớ nhung vì nhau\n" +
                "\n" +
                "Và có khi nào em thấy tiếc nuối về ngày xưa,\n" +
                "\n" +
                "vội vàng quá để mình mất nhau\n" +
                "\n" +
                "Ngày ấy giá em và anh chúng ta đừng cố chấp\n" +
                "\n" +
                "Ngày ấy giá anh và em chúng ta bình tĩnh hơn.\n" +
                "\n" +
                "Ngày đấy giá anh kìm nén mỗi khi mình cãi vã, để 2 ta 2 ta sẽ ko\n" +
                "\n" +
                "Bước qua đời nhau."));

        productDAO.insertProduct(new Product("ID126", "Một đêm say", "Nhạc EDM", "Normal",
                100, 450000, motdemsay_m, "motdemsay", "Khi đôi môi em còn đỏ mọng\n" +
                "Em muốn nói \"em yêu anh\"\n" +
                "Khi men còn trong hơi thở\n" +
                "Lại gần hôn anh đi\n" +
                "Khi con tim không còn trên đầu\n" +
                "Khi hai má em hây hây\n" +
                "Em nói em say tiếng đàn\n" +
                "Vậy lại gần hôn anh đi\n" +
                "\n" +
                "Lại gần hôn anh, anh sẽ để em mặt trời\n" +
                "Lại gần hôn anh, hay em để anh chơi vơi ?\n" +
                "Giờ còn đôi ta, kia là núi đây là nhà\n" +
                "Giờ còn đôi ta, em có muốn đi thật xa ?\n" +
                "\n" +
                "Ta chỉ sống một lần trên đời\n" +
                "Suy nghĩ lắm chi em ơi ?\n" +
                "Bao nhiêu yêu thương trên đời\n" +
                "Thành vị ngọt trên đôi môi\n" +
                "\n" +
                "Lại gần hôn anh, anh sẽ để em mặt trời\n" +
                "Lại gần hôn anh, hay em để anh chơi vơi ?\n" +
                "Giờ còn đôi ta, kia là núi đây là nhà\n" +
                "Giờ còn đôi ta, em có muốn đi thật xa ?"));

        productDAO.insertProduct(new Product("ID127", "Giấc mơ lạ kỳ", "Nhạc EDM", "Normal",
                 100, 15000, giacmolaky_m, "maimailamotloinoidoi", "Khi đôi môi em còn đỏ mọng\n" +
                "Em muốn nói \"em yêu anh\"\n" +
                "Khi men còn trong hơi thở\n" +
                "Lại gần hôn anh đi\n" +
                "Khi con tim không còn trên đầu\n" +
                "Khi hai má em hây hây\n" +
                "Em nói em say tiếng đàn\n" +
                "Vậy lại gần hôn anh đi\n" +
                "\n" +
                "Lại gần hôn anh, anh sẽ để em mặt trời\n" +
                "Lại gần hôn anh, hay em để anh chơi vơi ?\n" +
                "Giờ còn đôi ta, kia là núi đây là nhà\n" +
                "Giờ còn đôi ta, em có muốn đi thật xa ?\n" +
                "\n" +
                "Ta chỉ sống một lần trên đời\n" +
                "Suy nghĩ lắm chi em ơi ?\n" +
                "Bao nhiêu yêu thương trên đời\n" +
                "Thành vị ngọt trên đôi môi\n" +
                "\n" +
                "Lại gần hôn anh, anh sẽ để em mặt trời\n" +
                "Lại gần hôn anh, hay em để anh chơi vơi ?\n" +
                "Giờ còn đôi ta, kia là núi đây là nhà\n" +
                "Giờ còn đôi ta, em có muốn đi thật xa ?"));

//        productDAO.insertProduct(new Product("ID07", "Căn bệnh quái ác", "Kinh Dị", "Normal",
//                 100, 450000, canbenhquaiac_f, "canbenhquaiac", ""));

//        productDAO.insertProduct(new Product("ID07", "Căn bệnh quái ác", "Kinh Dị", "Normal",
//                 100, 450000, canbenhquaiac_f, "canbenhquaiac", ""));

        //Nhạc Indie

        Bitmap mms30 = convertDrawabletoBitmap(R.drawable.toska_m);
        Bitmap mms31 = convertDrawabletoBitmap(R.drawable.anhtakhongthichtoi_m);
        Bitmap mms32 = convertDrawabletoBitmap(R.drawable.tinhcu_m);
        Bitmap mms33 = convertDrawabletoBitmap(R.drawable.chotoitinhyeu_m);

        byte[] toska_m = setByteArray(mms30);
        byte[] anhtakhongthichtoi_m = setByteArray(mms31);
        byte[] tinhcu_m = setByteArray(mms32);
        byte[] chotoitinhyeu_m = setByteArray(mms33);

        productDAO.insertProduct(new Product("ID130", "TOSKA", "Nhạc Indie", "HotMusic",
                 100, 25000, toska_m, "toska", "You seem to think that you're over it\n" +
                "Your eyes imply you're the blameless one\n" +
                "Somehow as of now you think this is love\n" +
                "Try to read your life from memory\n" +
                "Perspective trickery\n" +
                "Bring on the he said she said he said she said\n" +
                "We all need a second chance\n" +
                "And a third and a fourth after that\n" +
                "Give a little and you get it back\n" +
                "It goes around like that\n" +
                "Try to read your life textually\n" +
                "Leave out the he said…"));

        productDAO.insertProduct(new Product("ID131", "Anh ta không thích tôi", "Nhạc Indie", "HotMusic",
                 100, 370000, anhtakhongthichtoi_m, "anhtakhongthichtoi", "Nàng là cô gái rất hay cười\n" +
                "Khóe môi chúm chím đôi mươi\n" +
                "Còn tôi chỉ như một ông già nua\n" +
                "Cau có giữa biết bao người\n" +
                "Nên nàng gọi tôi bằng chú\n" +
                "Còn tự xưng là cháu\n" +
                "Mà em đâu hay đã biến tôi thành trẻ con\n" +
                "Một hôm em ngỏ ý muốn tôi về nhà\n" +
                "Chúng ta sẽ xem phim và\n" +
                "Cùng nhau làm những điều hay\n" +
                "Mà tôi và em vẫn chưa từng làm\n" +
                "Nhưng chợt một âm thanh ở đâu đó\n" +
                "Rùng mình tôi quay lưng\n" +
                "Một anh chàng đen xì\n" +
                "Đứng lạnh lùng liếc tôi!\n" +
                "Anh ta không thích tôi\n" +
                "Anh ta rất ghét tôi\n" +
                "Không muốn tôi chạm vào em\n" +
                "Không muốn tôi trao cho em\n" +
                "Chiếc hôn nồng nàn\n" +
                "Hay dù chỉ là ánh mắt\n" +
                "Dù một câu yêu thương\n" +
                "Cũng khiến anh ta cau có..."));

        productDAO.insertProduct(new Product("ID132", "Tình cũ", "Nhạc Indie", "Normal",
                 100, 250000, tinhcu_m, "tinhcu", "Mười năm không gặp tưởng tình đã cũ\n" +
                "Mây bay bao năm tưởng mình đã quên\n" +
                "Như mưa bay đi một trời thương nhớ\n" +
                "Em ơi ! Bên kia có còn mắt buồn?\n" +
                "Mười năm cách biệt một lần bỡ ngỡ\n" +
                "Quên đi quên đi mộng buồn bấy lâu\n" +
                "Nhưng em yêu ơi! Một vùng ký ức\n" +
                "Vẫn còn trong ta cả một trời yêu\n" +
                "\n" +
                "Cả một trời yêu bao giờ trở lại\n" +
                "Ôi! Ta xa nhau tưởng chừng như đã\n" +
                "Ôi! Ta yêu nhau để lòng cứ ngỡ\n" +
                "Tình bất phân ly tình vẫn như mơ\n" +
                "Đành nhủ lòng thôi giã từ kỷ niệm\n" +
                "Cho qua bao năm mộng buồn quên dấu\n" +
                "Nhưng sao bao năm ngày dài qua mãi\n" +
                "Trong anh hôm nay thấy tình còn đây"));

        productDAO.insertProduct(new Product("ID133", "Cho tôi tình yêu", "Nhạc Indie", "Normal",
                 100, 250000, chotoitinhyeu_m, "chotoitinhyeu", "Ai cho tôi tình yêu\n" +
                "Của ngày thơ ngày mộng\n" +
                "Tôi xin dâng vòng tay mở rộng\n" +
                "Và đón người đi vào tim tôi\n" +
                "Bằng môi trên bờ môi\n" +
                "\n" +
                "Nhưng biết chỉ là mơ ...\n" +
                "Nên lòng nức nở, thương còn đi yêu thì chưa đến\n" +
                "Tên gọi tên tình chưa đỗ bến, nẻo mô mà tìm?\n" +
                "\n" +
                "Nằm nghe cô đơn, thoáng bước trong buồn\n" +
                "Giá buốt về tìm, sao rơi cuối đêm\n" +
                "Nhà vắng mang nhiều cay đắng, xua hồn đi hoang\n" +
                "\n" +
                "Ai cho tôi tình yêu, để làm duyên nụ cười\n" +
                "Tôi xin dâng tình tôi trọn đời\n" +
                "Người ơi người, xin đừng e ấp,\n" +
                "làm tim nghẹn ngào ...."));

//        productDAO.insertProduct(new Product("ID07", "Căn bệnh quái ác", "Kinh Dị", "Normal",
//                 100, 450000, canbenhquaiac_f, "canbenhquaiac", ""));

//        productDAO.insertProduct(new Product("ID07", "Căn bệnh quái ác", "Kinh Dị", "Normal",
//                 100, 450000, canbenhquaiac_f, "canbenhquaiac", ""));


    }

    public void init() {
        container2 = (RelativeLayout) findViewById(R.id.container2);
        fragmentLayout2 = (FrameLayout) findViewById(R.id.fragment_layout2);
        navigation2 = (BottomNavigationView) findViewById(R.id.navigation2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindDrawables(findViewById(R.id.container2));
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
