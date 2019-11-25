package com.example.mms.main_fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mms.R;
import com.example.mms.activity.CartActivity;
import com.example.mms.activity.SearchActivity;
import com.example.mms.adapter.MMSDealsAdapter;
import com.example.mms.dao.ProductCartDAO;
import com.example.mms.dao.ProductDAO;
import com.example.mms.model.Product;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

//    private ViewFlipper viewFlipper;
    private GridLayoutManager gridLayoutManager;
    private ProductDAO productDAO;
    private MMSDealsAdapter mmsDealsAdapter;
    private List<Product> productList;
    private RecyclerView recyclerView;
    private ImageButton btnCart;
    private TextView tvNumberInCart;
    private ProductCartDAO productCartDAO;
    private BroadcastReceiver broadcastReceiver;
    private Button btnSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
//        int images[] = {R.drawable.banner1, R.drawable.banner3, R.drawable.banner4};
//        for (int image : images) {
//            flipperImages(image);
//        }
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CartActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

//    public void flipperImages(int image) {
//        ImageView imageView = new ImageView(getContext());
//        imageView.setBackgroundResource(image);
//        viewFlipper.addView(imageView);
//        viewFlipper.setFlipInterval(2500);
//        viewFlipper.setAutoStart(true);
//
//        //
//        viewFlipper.setInAnimation(getContext(), android.R.anim.fade_in);
//    }

    private void initView(View view) {
        btnSearch = view.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SearchActivity.class));
            }
        });
        tvNumberInCart = view.findViewById(R.id.tvNumberInCart);
        productCartDAO = new ProductCartDAO(getContext());
        tvNumberInCart.setText(String.valueOf(productCartDAO.getNumberInCart(getRootUsername())));

        //Đăng kí BroadcastReceiver để nhận update từ giỏ hàng
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (tvNumberInCart != null)
                    tvNumberInCart.setText(String.valueOf(productCartDAO.getNumberInCart(getRootUsername())));
            }
        };
        IntentFilter intentFilter = new IntentFilter("update");
        getActivity().registerReceiver(broadcastReceiver, intentFilter);


        btnCart = view.findViewById(R.id.btnCart);

        recyclerView = view.findViewById(R.id.rvWakaDeal);
        productList = new ArrayList<>();
        productList.clear();
        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        productDAO = new ProductDAO(getContext());
        productList = productDAO.getAllProduct("Hot");
        mmsDealsAdapter = new MMSDealsAdapter(getContext(), productList);
        recyclerView.setAdapter(mmsDealsAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        mmsDealsAdapter.notifyDataSetChanged();
    }

    private String getRootUsername() {
        String name;
        name = getContext().getSharedPreferences("USER", getActivity().MODE_PRIVATE).getString("NAME", null);
        return name;
    }

    @Override
    public void onDestroy() {
        try {
            if (broadcastReceiver != null)
                getContext().unregisterReceiver(broadcastReceiver);
        } catch (Exception e) {
        }
        super.onDestroy();
    }


}
