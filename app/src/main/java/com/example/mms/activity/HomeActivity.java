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


        //Kinh dị

        Bitmap mms = convertDrawabletoBitmap(R.drawable.quyan_f);
        Bitmap mms1 = convertDrawabletoBitmap(R.drawable.it_f);

        Bitmap mms3 = convertDrawabletoBitmap(R.drawable.canbenhquaiac_f);
        Bitmap mms4 = convertDrawabletoBitmap(R.drawable.thanhtrung_f);
        Bitmap mms5 = convertDrawabletoBitmap(R.drawable.nguoisoi_f);
        Bitmap mms6 = convertDrawabletoBitmap(R.drawable.tuvienkinhhoang_f);


        byte[] quyan_f = setByteArray(mms);
        byte[] it_f = setByteArray(mms1);

        byte[] canbenhquaiac_f = setByteArray(mms3);
        byte[] thanhtrung_f = setByteArray(mms4);
        byte[] nguoisoi_f = setByteArray(mms5);
        byte[] tuvienkinhhoang_f = setByteArray(mms6);


        productDAO.insertProduct(new Product("ID01", "Quỷ Ấn", "Kinh Dị","Hot",
                100, 900000, quyan_f,"quyan","A Bluebird in My Heart dựa trên tiểu thuyết The Dishwasher (Người Rửa Chén) của Dannie M Martin. Phim kể về câu chuyện của cựu tù là Daniel (Roland Moller), người làm công việc rửa chén trong nhà hàng và sống trong một nhà nghỉ cũ cố gắng để có một cuộc sống bình lặng. Tại đây, anh kết bạn với chủ nhà nghỉ là một người mẹ đơn thân Laurence (Veerle Baetens) và cô con gái Clara (Lola La Lann)."));

        productDAO.insertProduct(new Product("ID02", "Chú Hề Ma Quái 2", "Kinh Dị","Hot",
                100, 899000, it_f,"it2","It Chapter Two (Gã Hề Ma Quái 2) vẫn là câu chuyện về những cô cậu bé của nhóm The Losers Club, lúc này đã trưởng thành và đối mặt với vô số vấn đề trong cuộc sống. Chưa dừng lại ở đó, ám ảnh ma hề Pennywise cứ 27 năm lại xuất hiện một lần tại thị trấn Derry buộc 7 cô cậu bé ngày nào phải tiếp tục cuốn vào cuộc chạm trán tiếp theo giữa hai bên thiện và ác."));



        productDAO.insertProduct(new Product("ID04", "Căn bệnh quái ác", "Kinh Dị", "Normal",
                100, 350000, canbenhquaiac_f, "canbenhquaiac", "Ở phần này, mức độ tàn phá của căn bệnh đã tiến triển ở cấp độ cao và kẻ tâm thần đã nghĩ rằng loại người là loài virus nguy hiểm cần phải loại bỏ."));

        productDAO.insertProduct(new Product("ID05", "Thanh trừng", "Kinh Dị", "Normal",
                100, 470000, thanhtrung_f, "thanhtrung", "The Purge lấy bối cảnh khi Mỹ có tỷ lệ tội phạm và thất nghiệp ở mức rất thấp. Để giữ mức ổn định này, chính phủ hàng năm đều có một chiến dịch có tên gọi “12 giờ thanh trừng” để con người có thể tự do giải thoát mọi cảm xúc tiêu cực của họ. Trong 12 tiếng này, các hành vi phạm tội (bao gồm cả giết người) đều không bị truy xét trước pháp luật."));

        productDAO.insertProduct(new Product("ID06", "Người sói", "Kinh Dị", "Normal",
                100, 350000, nguoisoi_f, "nguoisoi", "Câu chuyện bắt đầu khi 2 thanh niên người Mỹ chuyển đến London và trên đường đi bộ trên đất trống họ tìm thấy quán rượu \"Con Cừu Bị Thịt\", gặp những người rất kỳ quặc với ngôi sao vẽ trên tường với 2 đèn cầy và khi Jack hỏi về cái đó thì những người trong quán có phản ứng rất đáng sợ và đuổi 2 người ra khỏi quán rồi nói 1 câu kỳ lạ \"Cẩn Thận Mặt Trăng\". Hai người họ đã đi trên bãi đất cho đến khi nghe tiếng động lạ thì họ quyết định quay lại quán rượu đó nhưng kết quả là bị lạc rồi một lúc sau họ nhìn thấy một thứ không có thật trong ngoài đời, nó không phải là chó cũng không giống cái gì cả kết quả là Jack bị nó ăn thịt còn David thì còn sống nhưng anh ta đã mắc phải Lời nguyền của Ma Sói khi gặp Jack giờ là 1 oan hồn và dự báo rằng ngày mai sẽ là trăng tròn nên hãy cẩn thận"));

        productDAO.insertProduct(new Product("ID08", "Tu viện kinh hoàng", "Kinh Dị", "Normal",
                100, 550000, tuvienkinhhoang_f, "tuvienkinhhoang", "Khi Mary mang trong bụng đứa con của mình, cũng là lúc cô tuyệt vọng nhất: phải bỏ lại người bạn trai không đủ khả năng để lập gia đình, bị xã hội khinh miệt và cô không có gì ngoài nghề lừa đảo. Mary tìm đến một tu viện bí ẩn, nơi có những ma sơ dang rộng vòng tay chào đón cô với câu nói: “chúng tôi chăm sóc những phụ nữ như cô”."));


        //Hài hước

        Bitmap mms7 = convertDrawabletoBitmap(R.drawable.ngoilanghanhphuc_f);
        Bitmap mms8 = convertDrawabletoBitmap(R.drawable.cauchuyenhonnhan_f);
        Bitmap mms9 = convertDrawabletoBitmap(R.drawable.covophuthuy_f);
        Bitmap mms10 = convertDrawabletoBitmap(R.drawable.comedy_f);


        byte[] ngoilanghanhphuc_f = setByteArray(mms7);
        byte[] cauchuyenhonnhan_f = setByteArray(mms8);
        byte[] covophuthuy_f = setByteArray(mms9);
        byte[] comedy_f = setByteArray(mms10);


        productDAO.insertProduct(new Product("ID10", "Ngôi làng hạnh phúc", "Hài Hước", "Hot",
                100, 450000, ngoilanghanhphuc_f, "ngoilanghanhphuc",
                "Bob là chú hải ly có cuộc sống rập khuôn hơn bao người bạn khác trong rừng. Cuộc sống của Bob vốn bình dị cho đến khi chú mèo Max xuất hiện. Khác với Bob, Max là chú mèo tràn đầy năng, luôn mơ ước một ngày mình là người nổi tiếng khắp thế giới. Kể từ ngày Max xuất hiện, cuộc sống Bob như bị đảo ngược."));

        productDAO.insertProduct(new Product("ID11", "Câu chuyện hôn nhân", "Hài Hước", "Hot",
                100, 350000, cauchuyenhonnhan_f, "cauchuyenhonnhan", "Bộ phim có cái nhìn sâu sắc và đồng cảm về một cuộc hôn nhân tan vỡ và một gia đình gắn bó bên nhau. Một tác phẩm của Noah Baumbach – đạo diễn từng được đề cử Oscar."));

        productDAO.insertProduct(new Product("ID12", "Cô vợ phù thủy", "Hài Hước", "Normal",
                100, 750000, covophuthuy_f, "covophuthuy", "Isabel vốn là một cô phù thuỷ chân chất đang cố gắng bịa đặt về thân thế của chính mình. Cô chối bỏ sức mạnh siêu nhiên của một phù thuỷ và mong muốn sống một cuộc đời bình dị. Cùng khu phố với cô là anh chàng Jack Wyatt, một nam diễn viên đẹp trai, tài năng đang thực hiện một bộ phim của chính mình dựa theo nguyên tác Bewitch - bộ phim hài tình cảm nổi tiếng của thập niên 1960, trong đó Jack sẽ thủ vai chính.\n" +
                "Định mệnh xui khiến khi Jack vô tình gặp Isabel. Anh ngay lập tức bị cô hút hồn với cái mũi giống của Elizabeth Montgomery - người đóng vai nữ chính Samantha trong phiên bản gốc bộ phim Bewitched"));

        productDAO.insertProduct(new Product("ID13", "Hài hước", "Hài Hước", "Normal",
                100, 450000, comedy_f, "covophuthuy",
                "Bob là chú hải ly có cuộc sống rập khuôn hơn bao người bạn khác trong rừng. Cuộc sống của Bob vốn bình dị cho đến khi chú mèo Max xuất hiện. Khác với Bob, Max là chú mèo tràn đầy năng, luôn mơ ước một ngày mình là người nổi tiếng khắp thế giới. Kể từ ngày Max xuất hiện, cuộc sống Bob như bị đảo ngược."));

//
//        productDAO.insertProduct(new Product("ID07", "Căn bệnh quái ác", "Kinh Dị", "Normal",
//                100, 450000, canbenhquaiac_f, "canbenhquaiac", ""));
//
//        productDAO.insertProduct(new Product("ID07", "Căn bệnh quái ác", "Kinh Dị", "Normal",
//                100, 450000, canbenhquaiac_f, "canbenhquaiac", ""));

        //Hành động

        Bitmap mms13 = convertDrawabletoBitmap(R.drawable.thegioingamwaaji_f);
        Bitmap mms14 = convertDrawabletoBitmap(R.drawable.nguoichuyenphatnhanh_f);
        Bitmap mms15 = convertDrawabletoBitmap(R.drawable.tudiabaothu_f);
        Bitmap mms16 = convertDrawabletoBitmap(R.drawable.action_f);


        byte[] thegioingamwaaji_f = setByteArray(mms13);
        byte[] nguoichuyenphatnhanh_f = setByteArray(mms14);
        byte[] tudiabaothu_f = setByteArray(mms15);
        byte[] action_f = setByteArray(mms16);

        productDAO.insertProduct(new Product("ID016", "Thế giới ngầm Waaji", "Hành Động", "Hot",
        100, 350000, thegioingamwaaji_f, "thegioingamwaaji", "Một cảnh sát chìm trở nên bị lôi kéo vào một trận chiến với những tên tội phạm đang gây chiến muốn giành được “hộp đen”, chìa khóa cuối cùng của họ đối với một kho báu."));

        productDAO.insertProduct(new Product("ID017", "Người chuyển phát nhanh", "Hành Động", "Hot",
                100, 450000, nguoichuyenphatnhanh_f, "nguoichuyenphatnhanh", "Bộ phim Người Chuyển Phát Nhanh hành động dữ dội này mở ra trong thời gian thực khi hai linh hồn bị lôi kéo chiến đấu cho cuộc sống của họ. Người chiến thắng giải thưởng Viện hàn lâm (R) Gary Oldman (nhượng quyền của Hiệp sĩ bóng đêm) đóng vai một tên trùm tội phạm độc ác ra tay để giết Nick, nhân chứng đơn độc được thiết lập để làm chứng chống lại anh ta"));

        productDAO.insertProduct(new Product("ID018", "Tử địa báo thù", "Hành Động", "Normal",
                100, 250000, tudiabaothu_f, "tudiabaothu",
                "40 tuổi, sát thủ hàng đầu Roy Cady bị chuẩn đoán mắc bệnh ung thư. Trước sự việc này, các ông trùm quyết định thủ tiêu Roy càng nhanh càng tốt vì anh đang nắm giữ quá nhiều bí mật của bọn chúng."));

        productDAO.insertProduct(new Product("ID019", "Hành động", "Hành Động", "Normal",
                100, 450000, canbenhquaiac_f, "canbenhquaiac",
                "40 tuổi, sát thủ hàng đầu Roy Cady bị chuẩn đoán mắc bệnh ung thư. Trước sự việc này, các ông trùm quyết định thủ tiêu Roy càng nhanh càng tốt vì anh đang nắm giữ quá nhiều bí mật của bọn chúng."));


//        productDAO.insertProduct(new Product("ID07", "Căn bệnh quái ác", "Kinh Dị", "Normal",
//                100, 450000, canbenhquaiac_f, "canbenhquaiac", ""));
//
//        productDAO.insertProduct(new Product("ID07", "Căn bệnh quái ác", "Kinh Dị", "Normal",
//                100, 450000, canbenhquaiac_f, "canbenhquaiac", ""));

        //Khoa học

        Bitmap mms19 = convertDrawabletoBitmap(R.drawable.tiphugapnan_f);
        Bitmap mms20 = convertDrawabletoBitmap(R.drawable.huongvitra_f);
        Bitmap mms21 = convertDrawabletoBitmap(R.drawable.hiepsigiangsinh_f);
        Bitmap mms22 = convertDrawabletoBitmap(R.drawable.science_f);


        byte[] tiphugapnan_f = setByteArray(mms19);
        byte[] huongvitra_f = setByteArray(mms20);
        byte[] hiepsigiangsinh_f = setByteArray(mms21);
        byte[] science_f = setByteArray(mms22);


        productDAO.insertProduct(new Product("ID022", "Tỉ phú gặp nạn", "Khoa Học", "Hot",
                100, 150000, tiphugapnan_f, "tiphugapnan", "Được chuyển thể từ tiểu thuyết cùng tên của nhà văn Don DeLillo, Cosmopolis là câu chuyện về một tỷ phú trẻ và cũng là \"phù thủy tài chính\" của nước Mỹ. Khi đi ngang qua khu Manhattan, anh định tới cắt tóc ở một cửa hàng quen nhưng chiếc xe sang trọng của anh bị mắc kẹt lại trên đường khi tổng thống tới thăm nơi đây"));

        productDAO.insertProduct(new Product("ID023", "Hương vị trà", "Khoa Học", "Hot",
                100, 250000, huongvitra_f, "huongvitra", "Bộ phim xoay quanh câu chuyện về gia đình Haruno sống trong một căn nhà cổ, ở một vùng nông thôn ngoại thành Tokyo, nơi mà vẻ đẹp thiên nhiên vẫn còn ngự trị. Haruno là kiểu gia đình thường gặp ở nước Nhật, hai đứa trẻ ngày ngày tới trường, người cha đi làm, người mẹ làm nội trợ, và ông nội sống chung với cả nhà."));

        productDAO.insertProduct(new Product("ID024", "Hiệp sĩ giáng sinh", "Khoa Học", "Normal",
                100, 350000, hiepsigiangsinh_f, "hiepsigiangsinh",
                "Phép thuật trung cổ đưa vị hiệp sĩ thế kỷ 14 đến Ohio thời hiện đại – nơi anh phải lòng một giáo viên khoa học trung học đã vỡ mộng về tình yêu."));

        productDAO.insertProduct(new Product("ID025", "Khoa học", "Khoa Học", "Normal",
                100, 450000, science_f, "canbenhquaiac",
                "Phép thuật trung cổ đưa vị hiệp sĩ thế kỷ 14 đến Ohio thời hiện đại – nơi anh phải lòng một giáo viên khoa học trung học đã vỡ mộng về tình yêu."));

//
//        productDAO.insertProduct(new Product("ID07", "Căn bệnh quái ác", "Kinh Dị", "Normal",
//                100, 450000, canbenhquaiac_f, "canbenhquaiac", ""));
//
//        productDAO.insertProduct(new Product("ID07", "Căn bệnh quái ác", "Kinh Dị", "Normal",
//                100, 450000, canbenhquaiac_f, "canbenhquaiac", ""));

        //Lãng mạn

        Bitmap mms2 = convertDrawabletoBitmap(R.drawable.dieubamekhongke_f);
        Bitmap mms25 = convertDrawabletoBitmap(R.drawable.chuyentinhchungtoi_f);
        Bitmap mms26 = convertDrawabletoBitmap(R.drawable.giongloaibidiet_f);
        Bitmap mms27 = convertDrawabletoBitmap(R.drawable.romantic_f);


        byte[] dieubamekhongke_f = setByteArray(mms2);
        byte[] chuyentinhchungtoi_f = setByteArray(mms25);
        byte[] giongloaibidiet_f = setByteArray(mms26);
        byte[] romantic_f = setByteArray(mms27);

        productDAO.insertProduct(new Product("ID03", "Điều Ba Mẹ Không Kể", "Lãng Mạn","Hot",
                100, 269000, dieubamekhongke_f,"dieubamekhongke","Cho Nam-bong và Lee Mae-ja là cặp vợ chồng già cùng mắc căn bệnh mất trí nhớ. Sau 45 năm chung sống, họ chẳng thể nhớ lần cuối mình từng hạnh phúc là khi nào. Tuy nhiên, khi ký ức dần mất đi, tình yêu và ước mơ từng bị bỏ quên của họ lại dần nhen nhóm lại."));

        productDAO.insertProduct(new Product("ID028", "Chuyện tình chúng tôi", "Lãng Mạn", "Hot",
                100, 240000, chuyentinhchungtoi_f, "chuyentinhchungtoi", "Câu chuyện về cặp đôi Primo và George, họ đã có mối quan hệ lâu dài và đang trong quá trình xây dựng tương lai cùng với nhau. Nhưng tình yêu của họ bị thử thách khi phải đối mặt với những trở ngại - từ sự hiểu lầm đến những con đường sự nghiệp khác nhau."));

        productDAO.insertProduct(new Product("ID029", "Giống loài bị diệt", "Lãng Mạn", "Normal",
                100, 150000, giongloaibidiet_f, "giongloaibidiet",
                "Tina là một phụ nữ xấu xí, có khả năng đánh hơi kỳ dị để phát hiện cảm xúc của người khác. Cô hỗ trợ cho cảnh sát trong chuyên án phá đường dây phim ấu dâm.\n" +
                "Một ngày nọ, cô gặp Vore - một người đàn ông có vẻ ngoài và khả năng tương tự. Họ bắt đầu một mối quan hệ đầy bản năng. Từ đó cô phát hiện ra nguồn gốc của bản thân, cũng như những điều kì lạ trong cơ thể của mình"));

        productDAO.insertProduct(new Product("ID030", "Lãng mạn", "Lãng Mạn", "Normal",
                100, 450000, romantic_f, "canbenhquaiac",
                "Tina là một phụ nữ xấu xí, có khả năng đánh hơi kỳ dị để phát hiện cảm xúc của người khác. Cô hỗ trợ cho cảnh sát trong chuyên án phá đường dây phim ấu dâm.\n" +
                        "Một ngày nọ, cô gặp Vore - một người đàn ông có vẻ ngoài và khả năng tương tự. Họ bắt đầu một mối quan hệ đầy bản năng. Từ đó cô phát hiện ra nguồn gốc của bản thân, cũng như những điều kì lạ trong cơ thể của mình"));

//        productDAO.insertProduct(new Product("ID07", "Căn bệnh quái ác", "Kinh Dị", "Normal",
//                100, 450000, canbenhquaiac_f, "canbenhquaiac", ""));

//        productDAO.insertProduct(new Product("ID07", "Căn bệnh quái ác", "Kinh Dị", "Normal",
//                100, 450000, canbenhquaiac_f, "canbenhquaiac", ""));


        //Tài liệu

        Bitmap mms30 = convertDrawabletoBitmap(R.drawable.truytimbigfoot_f);
        Bitmap mms31 = convertDrawabletoBitmap(R.drawable.timlaichinhminh_f);
        Bitmap mms32 = convertDrawabletoBitmap(R.drawable.congxuonghoaky_f);
        Bitmap mms33 = convertDrawabletoBitmap(R.drawable.document_f);

        byte[] truytimbigfoot_f = setByteArray(mms30);
        byte[] timlaichinhminh_f = setByteArray(mms31);
        byte[] congxuonghoaky_f = setByteArray(mms32);
        byte[] document_f = setByteArray(mms33);

        productDAO.insertProduct(new Product("ID033", "Truy tìm Big Foot", "Tài Liệu", "Hot",
                100, 650000, truytimbigfoot_f, "truytimbigfoot", "Theo chân Todd Standing, tiến sĩ Jeffrey Meldrum và các nhà nghiên cứu khác tiến vào vùng hoang dã với nỗ lực tìm ra bằng chứng xác thực về sự tồn tại của Chân to"));

        productDAO.insertProduct(new Product("ID034", "Tìm lại chính mình", "Tài Liệu", "Hot",
                100, 350000, timlaichinhminh_f, "timlaichinhminh", "Alex bị mất trí nhớ và tin tưởng câu chuyện quá khứ do anh sinh đôi Marcus kể lại. Nhưng Marcus đang che giấu một bí mật gia đình đen tối..."));

        productDAO.insertProduct(new Product("ID035", "Công xưởng Hoa Kỳ", "Tài Liệu", "Normal",
                100, 450000, congxuonghoaky_f, "congxuonghoaky",
                "Bối cảnh phim xảy ra vào năm 2014, khi một tỉ phú Trung Quốc mở nhà máy Fuyao tại nơi hãng xe hơi General Motors từng đặt nhà máy ở thành phố Dayton, bang Ohio tạo ra hàng ngàn công việc cho người dân địa phương."));

        productDAO.insertProduct(new Product("ID036", "Tài liệu", "Tài Liệu", "Normal",
                100, 450000, document_f, "canbenhquaiac",
                "Bối cảnh phim xảy ra vào năm 2014, khi một tỉ phú Trung Quốc mở nhà máy Fuyao tại nơi hãng xe hơi General Motors từng đặt nhà máy ở thành phố Dayton, bang Ohio tạo ra hàng ngàn công việc cho người dân địa phương."));


//        productDAO.insertProduct(new Product("ID07", "Căn bệnh quái ác", "Kinh Dị", "Normal",
//                100, 450000, canbenhquaiac_f, "canbenhquaiac", ""));

//        productDAO.insertProduct(new Product("ID07", "Căn bệnh quái ác", "Kinh Dị", "Normal",
//                100, 450000, canbenhquaiac_f, "canbenhquaiac", ""));



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
