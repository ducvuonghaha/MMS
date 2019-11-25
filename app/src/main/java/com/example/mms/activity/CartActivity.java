package com.example.mms.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mms.R;
import com.example.mms.adapter.CartProductAdapter;
import com.example.mms.base.BaseActivity;
import com.example.mms.dao.ProductCartDAO;
import com.example.mms.model.ProductCart;

import java.util.ArrayList;
import java.util.List;

import static com.example.mms.database.Constant.decimalFormat;

public class CartActivity extends BaseActivity {

    private CartProductAdapter cartProductAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<ProductCart> productCartList;
    private ProductCartDAO productCartDAO;
    private RecyclerView recyclerView;
    private TextView tvSumPrice;
    private Button btnPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initView();

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (productCartList.size() <= 0) {
                    showMessegeWarning(getResources().getString(R.string.acti_cart_mesWarning));
                } else {
                    startNewActivity(PaymentActivity.class);
                }
            }
        });
    }

    private void initView() {
        btnPayment = findViewById(R.id.btnPayment);
        tvSumPrice = findViewById(R.id.tvSumPrice);
        recyclerView = findViewById(R.id.rvProductCart);
        productCartList = new ArrayList<>();
        productCartDAO = new ProductCartDAO(this);
        productCartList = productCartDAO.getAllProductCart(getRootUsername());
        linearLayoutManager = new LinearLayoutManager(this);
        cartProductAdapter = new CartProductAdapter(this, productCartList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(cartProductAdapter);
        cartProductAdapter.notifyDataSetChanged();
        double tongtien = productCartDAO.getTongTien(getRootUsername());
        if (tvSumPrice != null)
            tvSumPrice.setText(decimalFormat.format(tongtien));
    }

    private String getRootUsername() {
        String name;
        name = getSharedPreferences("USER", MODE_PRIVATE).getString("NAME", null);
        return name;
    }
}
