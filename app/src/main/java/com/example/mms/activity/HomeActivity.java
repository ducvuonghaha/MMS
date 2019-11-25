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
//        Bitmap mms3 = convertDrawabletoBitmap(R.drawable.deal_applewatch);

        byte[] quyan_f = setByteArray(mms);
        byte[] it_f = setByteArray(mms1);
        byte[] dieubamekhongke_f = setByteArray(mms2);
//        byte[] wakadeal3 = setByteArray(mms3);


        productDAO.insertProduct(new Product("ID01", "Quỷ Ấn", "Kinh Dị","Hot",
                100, 9000000, quyan_f,"quyan","A Bluebird in My Heart dựa trên tiểu thuyết The Dishwasher (Người Rửa Chén) của Dannie M Martin. Phim kể về câu chuyện của cựu tù là Daniel (Roland Moller), người làm công việc rửa chén trong nhà hàng và sống trong một nhà nghỉ cũ cố gắng để có một cuộc sống bình lặng. Tại đây, anh kết bạn với chủ nhà nghỉ là một người mẹ đơn thân Laurence (Veerle Baetens) và cô con gái Clara (Lola La Lann)."));

//        productDAO.insertProduct(new Product("ID02", "Chú Hề Ma Quái 2", "Kinh Dị","Hot",
//                100, 899000, it_f,"quyan","It Chapter Two (Gã Hề Ma Quái 2) vẫn là câu chuyện về những cô cậu bé của nhóm The Losers Club, lúc này đã trưởng thành và đối mặt với vô số vấn đề trong cuộc sống. Chưa dừng lại ở đó, ám ảnh ma hề Pennywise cứ 27 năm lại xuất hiện một lần tại thị trấn Derry buộc 7 cô cậu bé ngày nào phải tiếp tục cuốn vào cuộc chạm trán tiếp theo giữa hai bên thiện và ác."));
//        productDAO.insertProduct(new Product("ID03", "Điều Ba Mẹ Không Kể", "Lãng Mạn","Hot",
//                100, 26900000, dieubamekhongke_f,"quyan","Cho Nam-bong và Lee Mae-ja là cặp vợ chồng già cùng mắc căn bệnh mất trí nhớ. Sau 45 năm chung sống, họ chẳng thể nhớ lần cuối mình từng hạnh phúc là khi nào. Tuy nhiên, khi ký ức dần mất đi, tình yêu và ước mơ từng bị bỏ quên của họ lại dần nhen nhóm lại."));








//        productDAO.insertProduct(new Product("ID04", "Đồng Hồ Apple Watch Series 4 GPS", "WakaDeals",
//                100, 100, 11990000, wakadeal3,
//                "Đồng Hồ Thông Minh Apple Watch Series 4 GPS Sport band VN/A sở hữu thiết kế không viền nhờ phần màn hình lớn hơn và được bo cong ở các góc. Nhờ vậy, kích thước của màn hình đã tăng 30%"));


//        //PHONE
//        Bitmap phone = convertDrawabletoBitmap(R.drawable.phone_huawei3i);
//        Bitmap phone1 = convertDrawabletoBitmap(R.drawable.phone_hueweip9);
//        Bitmap phone2 = convertDrawabletoBitmap(R.drawable.phone_ip8plus);
//        Bitmap phone3 = convertDrawabletoBitmap(R.drawable.phone_m20);
//        Bitmap phone4 = convertDrawabletoBitmap(R.drawable.phone_oppof9);
//        Bitmap phone5 = convertDrawabletoBitmap(R.drawable.phone_ssa50);
//        Bitmap phone6 = convertDrawabletoBitmap(R.drawable.phone_ip6splus);
//        Bitmap phone7 = convertDrawabletoBitmap(R.drawable.phone_oppof11);
//
//        byte[] deal_phone = setByteArray(phone);
//        byte[] deal_phone1 = setByteArray(phone1);
//        byte[] deal_phone2 = setByteArray(phone2);
//        byte[] deal_phone3 = setByteArray(phone3);
//        byte[] deal_phone4 = setByteArray(phone4);
//        byte[] deal_phone5 = setByteArray(phone5);
//        byte[] deal_phone6 = setByteArray(phone6);
//        byte[] deal_phone7 = setByteArray(phone7);
//
//        productDAO.insertProduct(new Product("ID05", "Điện thoại Huawei Nova 3i Gray", "Phone",
//                100, 100, 5490000, deal_phone,
//                "Nova 3i sở hữu mặt lưng kính chuyển màu gradient kiểu như điện thoại Huawei P20 Pro, nhờ thiết kế mặt lưng kính ấn tượng đã đem lại cho máy một thiết kế hiện đại và tinh tế. Chiếc điện thoại Huawei này sở hữu cụm camera kép ở mặt lưng với cảm biến chính 16 MP và cảm biến phụ 2 MP có khả năng lấy nét rất nhanh"));
//        productDAO.insertProduct(new Product("ID06", "Điện thoại Huawei Y9 (2019) Blue", "Phone",
//                100, 100, 4490000, deal_phone1,
//                "Huawei Y9 2019 mang trong mình tới 4 camera bao gồm camera kép phía trước độ phân giải 16 MP (chính) + 2 MP (phụ) và camera kép phía sau là 13 MP (chính) + 2 MP (phụ), cả hai cụm camera đều tích hợp với công nghệ chụp ảnh AI (trí tuệ nhân tạo)"));
//        productDAO.insertProduct(new Product("ID07", "Điện thoại Iphone 8 Plus 128GB", "Phone",
//                100, 100, 15490000, deal_phone2,
//                "Smartphone iPhone 8 Plus giữ nguyên hoàn toàn những đường nét thiết kế đã hoàn thiện từ thế hệ trước nhưng sử dụng phong cách 2 mặt kính cường lực kết hợp bộ khung kim loại. Mặt lưng kính bo cong 2.5D thời thượng, khung kim loại được gia cố cứng cáp, bền bỉ hơn với 7 lớp xử lý màu sơn, hạn chế bong tróc"));
//        productDAO.insertProduct(new Product("ID08", "Điện thoại Samsung Galaxy M20", "Phone",
//                100, 100, 7490000, deal_phone3,
//                "Thiết bị chạy trên hệ điều hành Android 8.1 Oreo với giao diện Samsung Experience 9.5, cùng với chip mới, Galaxy M20 sẽ cho khả năng chơi game tốt với các tựa game nổi tiếng như Liên quân Moblile hay PUBG Mobile sau khi chỉnh lại cấu hình đồ hoạ xuống thấp."));
//        productDAO.insertProduct(new Product("ID09", "Điện thoại OPPO F9 6GB Sky", "Phone",
//                100, 100, 15900000, deal_phone4,
//                "Là chiếc điện thoại OPPO được nâng cấp cấu hình, cụ thể là RAM lên tới 6 GB, OPPO F9 6GB còn trang bị nhiều tính năng đột phá như sở hữu công nghệ sạc VOOC mới nhất, mặt lưng chuyển màu độc đáo, màn hình tràn viền giọt nước và camera chụp chân dung tích hợp trí tuệ nhân tạo A.I hoàn hảo"));
//        productDAO.insertProduct(new Product("ID10", "Điện thoại Samsung Galaxy A50 128GB", "Phone",
//                100, 100, 8900000, deal_phone5,
//                "Thiết kế màn hình Infinity-U độc đáo - Samsung Galaxy A50 là mẫu smartphone đầu tiên mà Samsung sử dụng màn hình Infinity-U với phần notch nhỏ gọn hình giọt nước.Trên chiếc smartphone Samsung mới này thì hãng sử dụng thiết kế gọi là \"3D Glasstic\" với bộ khung kim loại chắc chắn với các góc cạnh được bo cong nhẹ để mang lại cảm giác cầm nắm dễ chịu."));
//        productDAO.insertProduct(new Product("ID11", "Điện thoại iPhone 6s Plus 32GB", "Phone",
//                100, 100, 16900000, deal_phone6,
//                "Điện thoại iPhone 6s Plus 32 GB được nâng cấp độ phân giải camera sau lên 12 MP (thay vì 8 MP như trên iPhone 6 Plus), camera cho tốc độ lấy nét và chụp nhanh, thao tác chạm để chụp nhẹ nhàng. Chất lượng ảnh trong các điều kiện chụp khác nhau tốt."));
//        productDAO.insertProduct(new Product("ID12", "Điện thoại Oppo F11 Pro Đen Thạch Anh", "Phone",
//                100, 100, 22490000, deal_phone7,
//                "Với việc trang bị camera selfie trượt độc đáo, F11 pro sở hữu màn hình toàn cảnh Panoramic hoàn hảo với khung viền siêu mỏng, kích thước màn hình 6.5 inch, tỉ lệ hiển thị 90,9% mang đến trải nghiệm hình ảnh cực chất"));


//        //LAPTOP
//        Bitmap laptop = convertDrawabletoBitmap(R.drawable.laptop_macbookpro);
//        Bitmap laptop1 = convertDrawabletoBitmap(R.drawable.laptop_asuss15);
//        Bitmap laptop2 = convertDrawabletoBitmap(R.drawable.laptop_hp);
//        Bitmap laptop3 = convertDrawabletoBitmap(R.drawable.laptop_macbookgold);
//
//        byte[] deal_lap = setByteArray(laptop);
//        byte[] deal_lap1 = setByteArray(laptop1);
//        byte[] deal_lap2 = setByteArray(laptop2);
//        byte[] deal_lap3 = setByteArray(laptop3);
//
//        productDAO.insertProduct(new Product("ID13", "Macbook Pro 13 inch 128GB (2017)", "Laptop",
//                100, 100, 33990000, deal_lap,
//                "Thế hệ MacBook Pro 13 inch 2017 ngoài việc cập nhật bộ vi xử lý Intel thế hệ thứ 7 (Kaby Lake) thì còn được nâng cấp gấp đôi dung lượng bộ nhớ. Ngoài ra thiết kế cũng như một số tính năng nổi bật vẫn không có thay đổi so với dòng MacBook 2016"));
//        productDAO.insertProduct(new Product("ID14", "Asus Vivobook S530UN-BQ026T i5", "Laptop",
//                100, 100, 18790000, deal_lap1,
//                "ASUS VivoBook S15 S530UN là mẫu máy tính xách tay nói lên cá tính riêng của bạn với thiết kế táo bạo, màu sắc độc đáo, màn hình NanoEdge viền mỏng và bản lề ErgoLift đột phá. ASUS VivoBook S15 có kiểu dáng thanh thoát, khác biệt so với các laptop trên thị trường"));
//        productDAO.insertProduct(new Product("ID15", "HP Pavilion 14-ce1008TU/Core i5-8265U", "Laptop",
//                100, 100, 15290000, deal_lap2,
//                "Được thừa hưởng nhiều đường nét thiết kế từ series laptop HP Envy cao cấp, HP Pavilion 14-ce1008TU tỏ ra hết sức cân đối khi sở hữu bộ khung vỏ chỉ tương tự như những dòng máy tính xách tay có màn hình 13 inch trên thị trường. HP đã trau chuốt từng chi tiết trong ngoại hình của sản phẩm, đặc biệt là ở phần bản lề và loa ngoài để tạo nên nét nhấn nhá về mặt thẩm mĩ"));
//        productDAO.insertProduct(new Product("ID16", "Macbook Air 13 128GB 2018 Pink", "Laptop",
//                100, 100, 28790000, deal_lap3,
//                "Tình yêu dành cho MacBook Air đã quay trở lại với thiết kế mới. Bạn sẽ có 3 phiên bản màu bạc, xám và vàng. MacBook Air mới mỏng hơn, nhẹ hơn, sở hữu màn hình Retina rực rỡ, cảm biến vân tay Touch ID, bàn phím cách bướm thế hệ mới nhất và bàn di chuột cảm ứng lực Force Touch. Với chất liệu làm từ 100% nhôm có thể tái chế, MacBook Air 2018 là chiếc laptop thân thiện nhất với môi trường"));
//
//        //Tablet
//        Bitmap tablet = convertDrawabletoBitmap(R.drawable.tablet_galaxya);
//        Bitmap tablet1 = convertDrawabletoBitmap(R.drawable.tablet_ipadpro11);
//        Bitmap tablet2 = convertDrawabletoBitmap(R.drawable.tablet_galaxya6);
//        Bitmap tablet3 = convertDrawabletoBitmap(R.drawable.tablet_ipadpro105);
//
//        byte[] deal_tab = setByteArray(tablet);
//        byte[] deal_tap1 = setByteArray(tablet1);
//        byte[] deal_tap2 = setByteArray(tablet2);
//        byte[] deal_tap3 = setByteArray(tablet3);
//
//        productDAO.insertProduct(new Product("ID17", "Samsung Galaxy Tab A 10.5 inch", "Tablet",
//                100, 100, 9490000, deal_tab,
//                "Samsung Galaxy Tab A 10.5 inch được trang bị màn hình IPS LCD với độ phân giải 1.920 x 1.200 pixels, viền bezels được làm mỏng cho hình ảnh hiển thị chi tiết dễ dàng đọc sách hay xem phim phụ đề mà không sợ bị mỏi mắt.Được sở hữu bộ vi xử lý Snapdragon 450 với tốc độ 1.8GHz kết hợp với 3 GB RAM, 32 GB ROM cho hiệu năng khá ổn định khi thực hiện các tác vụ cơ bản."));
//        productDAO.insertProduct(new Product("ID18", "iPad Pro 11 inch Wifi 64GB (2018)", "Tablet",
//                100, 100, 2190000, deal_tap1,
//                "iPad Pro 11 inch 64GB Wifi (2018) sở hữu ngoại hình hoàn toàn mới, viền màn hình được thu hẹp hơn, 4 cạnh được vát mạnh đã khiến máy trở thành một trong những chiếc máy tính bảng đẹp nhất, đẳng cấp nhất hiện tại.Nút Home trên thế hệ mới đã được loại bỏ, các thao tác về lại màn hình chính, mở thanh thông báo, mở đa nhiệm, bảng điều khiển,... đều có thể sử dụng dễ dàng thông qua các thao tác kéo vuốt thả trên màn hình chính."));
//        productDAO.insertProduct(new Product("ID19", "Samsung Galaxy Tab A6 10.1 Spen", "Tablet",
//                100, 100, 7490000, deal_tap2,
//                "Được trang bị chip Exynos 7870 8 nhân 3 GB RAM cùng 16 GB bộ nhớ trong, Samsung Galaxy Tab A6 10.1 Spen có hiệu năng khá ổn định so với mức giá.Khả năng đa nhiệm của máy được tối ưu với RAM lớn và màn hình lớn thuận tiện cho các ứng dụng văn phòng, các công cụ giải trí...Máy cũng hỗ trợ khe cắm thẻ nhớ mở rộng tối đa lên tới 256 GB cùng 1 khe cắm Nano Sim và kết nối 4G tốc độ cao giúp bạn truy cập internet với tốc độ nhanh chóng."));
//        productDAO.insertProduct(new Product("ID20", "iPad Pro 10.5 inch Wifi 64GB (2017)", "Tablet",
//                100, 100, 16590000, deal_tap3,
//                "Điểm khác biệt lớn nhất ở mẫu iPad Pro 10.5 inch Wifi 64GB (2017) so với các thế hệ iPad cũ chính là phần viền màn hình của máy giờ đây đã được làm mỏng hơn rất nhiều so với các đàn anh đi trước.Cấu hình cực mạnh chính là điểm đáng chú ý nhất của máy khi sử dụng con chip mới Apple A10X 6 nhân 64-bit cho hiệu năng tăng ấn tượng và khả năng tiết kiệm pin tốt hơn."));
//
//         // Watch
//        Bitmap watch = convertDrawabletoBitmap(R.drawable.watchh_ss);
//        Bitmap watch1 = convertDrawabletoBitmap(R.drawable.watchh_40s4);
//        Bitmap watch2 = convertDrawabletoBitmap(R.drawable.watchh_42s3);
//        Bitmap watch3 = convertDrawabletoBitmap(R.drawable.watchh_44s4);
//
//        byte[] deal_watch = setByteArray(watch);
//        byte[] deal_watch1 = setByteArray(watch1);
//        byte[] deal_watch2 = setByteArray(watch2);
//        byte[] deal_watch3 = setByteArray(watch3);
//
//        productDAO.insertProduct(new Product("ID21", "Samsung Galaxy Watch 46mm Black", "Watch",
//                100, 100, 7490000, deal_watch,
//                "Tổng thể Samsung Galaxy Watch 46mm có màu đen huyền sang trọng đi cùng thiết kế truyền thống với mặt đồng hồ tròn cổ điển, vòng xoay bezel cho khả năng điều hướng dễ dàng.Samsung mang đến dây đeo với chất liệu silicone cho bạn cảm giác thoải mái khi đeo trong thời gian dài. Bên cạnh đó, các nút cài trên dây cho bạn khả năng đều chỉnh đồng hồ phù hợp với độ rộng của cổ tay."));
//        productDAO.insertProduct(new Product("ID22", "Apple Watch S4 GPS 40mm White", "Watch",
//                100, 100, 11990000, deal_watch1,
//                "Apple Watch S4 GPS 40mm viền nhôm dây cao su màu trắng có thiết kế với dây đeo sử dụng chất liệu cao su với nút cài, tiện lợi điều chỉnh độ rộng của dây phù hợp với cổ tay người đeo.Bốn cạnh trên màn hình của Apple S4 GPS 40mm được bo cong mềm mại, chất lượng hình ảnh, thông tin hiển thị trên màn hình cũng rõ ràng hơn, màu sắc sắc nét, dễ nhìn dưới ánh nắng mặt trời."));
//        productDAO.insertProduct(new Product("ID23", "Apple Watch S3 GPS 42mm Gray", "Watch",
//                100, 100, 8490000, deal_watch2,
//                "Về mặt tổng thể đồng hồ thông minh Apple Watch S3 GPS 42mm có kích thước tương tự như Apple Watch Series 2. Sản phẩm chú trọng thêm về mặt thẩm mỹ với thiết kế sang trọng, năng động, dây đeo có nhiều màu sắc khác nhau.Dây đeo của Apple Watch S3 GPS, 42mm viền nhôm được thiết kế với khả năng dễ dàng tháo rời, cho phép người dùng linh hoạt thay đổi bằng các dây đeo màu sắc khác."));
//        productDAO.insertProduct(new Product("ID24", "Apple Watch S4 GPS 44mm Pink", "Watch",
//                100, 100, 12490000, deal_watch3,
//                "Apple Watch S4 GPS 44mm viền nhôm dây vải màu hồng có thiết kế khá đơn giản và nổi bật. Sử dụng dây từ chất liệu vải, giúp đồng hồ cá tính hơn, nhẹ nhàng hơn khi đeo trong thời gian dài. Ngoài ra dây vải còn giúp bạn thấy dễ chịu hơn khi tay ra mồ hôi lúc vận động nhiều.Mang một thiết kế thanh thoát hơn với màn hình lớn, nhưng vẫn giữ được kích thước cũ, cùng với dây đeo vải giúp cho trọng lượng khi đeo Apple Watch vô cùng nhẹ nhàng. Thân đồng hồ mỏng hơn cũng giúp tăng phần thời trang khi đeo đồng hồ. "));
//
//
//        //Accessories
//        Bitmap accessories = convertDrawabletoBitmap(R.drawable.accessories_cap);
//        Bitmap accessories1 = convertDrawabletoBitmap(R.drawable.accessories_loa);
//        Bitmap accessories2 = convertDrawabletoBitmap(R.drawable.accessories_mouse);
//        Bitmap accessories3 = convertDrawabletoBitmap(R.drawable.accessories_pin);
//
//        byte[] deal_accessories = setByteArray(accessories);
//        byte[] deal_accessories1 = setByteArray(accessories1);
//        byte[] deal_accessories2 = setByteArray(accessories2);
//        byte[] deal_accessories3 = setByteArray(accessories3);
//
//        productDAO.insertProduct(new Product("ID25", "Dây cáp Micro USB 1 m eSaver DS118", "Accessories",
//                100, 100, 51000, deal_accessories,
//                "Thiết kế đơn giản, chiều dài dây thích hợp.Với chiều dài dây 1 m có thế tối ưu hóa khả năng công việc bằng cách vừa kết nối với máy tính để truyền dữ liệu, vừa sử dụng điện thoại cùng 1 lúc.Cáp có phần đầu micro USB kết nối với hầu hết cổng điện thoại hiện nay; đầu cáp USB tương thích với mọi adapter hay cổng trên tivi, laptop, pin sạc."));
//        productDAO.insertProduct(new Product("ID26", "Loa bluetooth Sony SRS-XB01", "Accessories",
//                100, 100, 799000, deal_accessories1,
//                "Loa bluetooth Sony SRS-XB01 với thiết kế nhỏ gọn, di động, dễ mang lại di chuyển. Khả năng chống thấm nước đạt chuẩn IPX5. Tích hợp các nút điều khiển trên loa: bật nguồn, phát nhạc, tăng âm lượng. Với micro tích hợp, nhận cuộc gọi trên điện thoại thông minh qua loa chưa bao giờ dễ dàng đến thế.Loa có thiết kế tròn cùng công nghệ Extra Bass cho âm thanh bùng nổ và sôi động hơn với chất âm được phát ra ở mặt trước của loa."));
//        productDAO.insertProduct(new Product("ID27", "Chuột Bluetooth Logitech M337", "Accessories",
//                100, 100, 510000, deal_accessories2,
//                "- Thiết kế nhỏ gọn, dễ dàng mang đi hay cất giữ.\n" +
//                        "- Nhờ cảm biến quang học laze, M337 hoạt động trên nhiều bề mặt.\n" +
//                        "- Hình dáng uốn lượn với phần tay cầm bọc cao su, giúp người dùng thoải mái trong quá trình sử dụng.\n" +
//                        "- Tương thích với nhiều thiết bị: Windows® 7, Windows 8, Windows 10 trở lên, Mac OS X 10.8 trở lên, Chrome OS™, Android™ 3.2 trở lên."));
//        productDAO.insertProduct(new Product("ID28", "Pin sạc dự phòng 5.000 mAh eSaver", "Accessories",
//                100, 100, 169000, deal_accessories3,
//                "Thiết kế vỏ nhôm sang trọng nhưng vẫn mang lại cảm giác chắc chắn. Mọi chi tiết đều được hoàn thiện tinh tế, hai cạnh bên bo cong giúp bạn thoải mái cầm nắm trong lòng bàn tay. Đặc biệt, ở phiên bản mới này Xiaomi bổ sung thêm màu xanh đen cho sản phẩm, màu lạ nhưng rất bắt mắt.Với ngân sách tiết kiệm, ở tầm giá 329.000 đ (đang được khuyến mãi từ mức giá 399.000 đ), Xiaomi 10.000 mAh Gen 2 là một lựa chọn thông minh dành cho bạn."));

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
