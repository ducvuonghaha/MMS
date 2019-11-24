package com.example.mms.activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.mms.R;

public class SliderAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    private int[] slider_images = {
            R.drawable.cinema,
            R.drawable.payment,
            R.drawable.quality,
    };

    private String[] slider_headings = {
            "ĐA DẠNG THỂ LOẠI",
            "GIAO DỊCH NHANH CHÓNG",
            "CHẤT LƯỢNG TUYỆT ĐỐI"
    };

    private String[] slider_des = {
            "App cung cấp cho người dùng không chỉ đầy đủ những bộ phim và bài nhạc hot nhất mà còn đảm bảo chất lượng luôn đi đầu so với thị trường.",
            "App hiện đang liên kết với nhiều những ngân hàng trên toàn quốc giúp cho việc giao dịch diễn ra rất nhanh chóng. ",
            "Với app, bạn sẽ được thưởng thức những bộ phim và những  bản nhạc có độ phân giải vàchất lượng tốt nhất với mức giá vô cùng ưu đãi."
    };

    @Override
    public int getCount() {
        return slider_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout, container, false);

        ImageView sliderImageView = view.findViewById(R.id.imgSlider);
        TextView tvHeading = view.findViewById(R.id.tvHeading);
        TextView tvDes = view.findViewById(R.id.tvDescription);
        Button btnDiscover = view.findViewById(R.id.btnSlider);

        btnDiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,LoginActivity.class);
                context.startActivity(intent);
            }
        });

        sliderImageView.setImageResource(slider_images[position]);
        tvHeading.setText(slider_headings[position]);
        tvDes.setText(slider_des[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
