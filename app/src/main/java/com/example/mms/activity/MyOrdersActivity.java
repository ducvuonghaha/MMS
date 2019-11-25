package com.example.mms.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mms.R;
import com.example.mms.adapter.MyOrdersAdapter;
import com.example.mms.dao.ProductCartDAO;
import com.example.mms.model.MyOrders;

import java.util.ArrayList;
import java.util.List;

public class MyOrdersActivity extends AppCompatActivity {

    private RecyclerView rvMyOrders;
    private MyOrdersAdapter myOrdersAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<MyOrders> myOrdersList;
    private ProductCartDAO productCartDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        initView();
    }

    public void initView(){
        rvMyOrders = findViewById(R.id.rvMyOrders);
        myOrdersList = new ArrayList<>();
        productCartDAO = new ProductCartDAO(this);
        myOrdersList = productCartDAO.getAllMyOrders(getRootUsername());

        Toast.makeText(this, String.valueOf(myOrdersList.size()), Toast.LENGTH_SHORT).show();
        linearLayoutManager = new LinearLayoutManager(this);
        myOrdersAdapter = new MyOrdersAdapter(this,myOrdersList);
        rvMyOrders.setLayoutManager(linearLayoutManager);
        rvMyOrders.setAdapter(myOrdersAdapter);
        myOrdersAdapter.notifyDataSetChanged();
    }

    private String getRootUsername(){
        String name ;
        name = getSharedPreferences("USER",MODE_PRIVATE).getString("NAME",null);
        return name;
    }
}
