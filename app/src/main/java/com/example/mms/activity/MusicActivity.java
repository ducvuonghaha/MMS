package com.example.mms.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.mms.R;
import com.example.mms.dao.ProductCartDAO;
import com.example.mms.databinding.ActivityMusicBinding;
import com.example.mms.interfaces.MallCartView;
import com.example.mms.presenter.MallCartPresenter;
import com.google.android.material.tabs.TabLayout;

public class MusicActivity extends AppCompatActivity implements MallCartView {

    private Button btnMallSearch;
    private ImageButton btnMallCart;
    private TextView tvNumberMallInCart;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ProductCartDAO productCartDAO;
    private BroadcastReceiver broadcastReceiver;
    private MallCartPresenter mallCartPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMusicBinding  activityMusicBinding = DataBindingUtil.setContentView(this,R.layout.activity_music);
        init();

        mallCartPresenter = new MallCartPresenter(this);
        activityMusicBinding.setMallSearch(mallCartPresenter);
        activityMusicBinding.setCart(mallCartPresenter);
//        tvNumberMallInCart.setText(String.valueOf(productCartDAO.getNumberInCart(getRootUsername())));

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (tvNumberMallInCart != null)
                    tvNumberMallInCart.setText(String.valueOf(productCartDAO.getNumberInCart(getRootUsername())));
            }
        };

//        btnMallSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });
        IntentFilter intentFilter = new IntentFilter("update");
        registerReceiver(broadcastReceiver, intentFilter);
//        btnMallCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//    }
//});


        FragmentManager manager = getSupportFragmentManager();
        PagerAdapter adapter = new com.example.mms.adapter.PagerMusicAdapter(manager);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }

    public void init() {
        btnMallSearch = (Button) findViewById(R.id.btnMallSearch);
        btnMallCart = (ImageButton) findViewById(R.id.btnMallCart);
        tvNumberMallInCart = (TextView) findViewById(R.id.tvNumberMallInCart);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);


    }

    private String getRootUsername() {
        String name;
        name = getSharedPreferences("USER", MODE_PRIVATE).getString("NAME", null);
        return name;
    }

    @Override
    public void onDestroy() {
        try {
            if (broadcastReceiver != null)
                unregisterReceiver(broadcastReceiver);
        } catch (Exception e) {
        }
        super.onDestroy();
    }

    @Override
    public void MovetoMallSearch() {
        startActivity(new Intent(MusicActivity.this, SearchActivity.class));

    }

    @Override
    public void MovetoCart() {
        Intent intent = new Intent(MusicActivity.this, CartActivity.class);
        startActivity(intent);
    }
}
