package com.example.mms.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.mms.R;
import com.example.mms.interfaces.SliderView;
import com.example.mms.presenter.SliderPresenter;

public class SliderActivity extends AppCompatActivity implements SliderView {

    ViewPager viewPager;
    private LinearLayout mDotsLayout;
    private SliderAdapter sliderAdapter;
    private Button btnSkip;
    private TextView[] mDots;
    private SliderPresenter sliderPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);
        initView();
        sliderPresenter = new SliderPresenter(this);
        viewPager.setAdapter(sliderAdapter);
        viewPager.addOnPageChangeListener(viewListener);
        sliderPresenter.addDot(0);

    }




    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {
        }
        @Override
        public void onPageSelected(int i) {
            sliderPresenter.addDot(i);
        }
        @Override
        public void onPageScrollStateChanged(int i) {
        }
    };

    public void initView() {
        btnSkip = findViewById(R.id.btnSkip);
        viewPager = findViewById(R.id.sliderViewPager);
        mDotsLayout = findViewById(R.id.dotsLayout);
        sliderAdapter = new SliderAdapter(this);
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SliderActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void addDot() {
        int position = 0;
        mDots = new TextView[3];
        mDotsLayout.removeAllViews();
        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransprentWhite));
            mDotsLayout.addView(mDots[i]);
        }

        if (mDots.length > 0) {
            mDots[position].setTextColor(getResources().getColor(R.color.colorBlack));
        }
    }
}
