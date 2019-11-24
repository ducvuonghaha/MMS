package com.example.mms.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mms.R;
import com.example.mms.adapter.MMSDealsAdapter;
import com.example.mms.dao.ProductDAO;
import com.example.mms.model.Product;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private GridLayoutManager gridLayoutManager;
    private ProductDAO productDAO;
    private MMSDealsAdapter mmsDealsAdapter;
    private List<Product> productList;
    private ImageButton btnBack;
    private AutoCompleteTextView actvNameProduct;
    private Button btnSearchProduct;
    private RecyclerView rvSearchProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, CATEGORY);
        actvNameProduct.setAdapter(adapter);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        btnSearchProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = actvNameProduct.getEditableText().toString();
                getProduct(category);
            }
        });


    }

    private void getProduct(String category) {
        productList = new ArrayList<>();
        productList.clear();
        gridLayoutManager = new GridLayoutManager(this, 2);
        productDAO = new ProductDAO(this);
        productList = productDAO.getAllProduct(category);
        mmsDealsAdapter = new MMSDealsAdapter(this, productList);
        rvSearchProduct.setAdapter(mmsDealsAdapter);
        rvSearchProduct.setLayoutManager(gridLayoutManager);
        rvSearchProduct.setHasFixedSize(true);
        rvSearchProduct.setNestedScrollingEnabled(false);
        mmsDealsAdapter.notifyDataSetChanged();
    }

    private void initView() {
        actvNameProduct = findViewById(R.id.actvNameProduct);
        btnBack = findViewById(R.id.btnBack);
        btnSearchProduct = findViewById(R.id.btnSearchProduct);
        rvSearchProduct = findViewById(R.id.rvSearchProduct);
    }

    private static final String[] CATEGORY = new String[]{"Kinh Dị", "Hài Hước", "Hành Động", "Khoa Học", "Lãng Mạn", "Tài Liệu"};
}
