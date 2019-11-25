package com.example.mms.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mms.R;
import com.example.mms.adapter.MyVoucherAdapter;
import com.example.mms.dao.MyVoucherDAO;
import com.example.mms.dao.UserDAO;
import com.example.mms.model.MyVoucher;
import com.example.mms.model.User;

import java.util.ArrayList;
import java.util.List;

public class MyVoucherActivity extends AppCompatActivity {

    private MyVoucherDAO myVoucherDAO;
    private MyVoucherAdapter myVoucherAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<MyVoucher> myVoucherList;
    private RecyclerView recyclerView;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_voucher);
        initView();
    }

    public void initView(){

        userDAO = new UserDAO(this);
        List<User> userList = new ArrayList<>();
        userList = userDAO.getAllUserlist();

        recyclerView = findViewById(R.id.rvMyVoucher);
        myVoucherList = new ArrayList<>();
        myVoucherDAO = new MyVoucherDAO(this);
        myVoucherList = myVoucherDAO.getAllMyVouchers(getRootUsername());
        Toast.makeText(this, String.valueOf(myVoucherList.size()), Toast.LENGTH_SHORT).show();
        linearLayoutManager = new LinearLayoutManager(this);
        myVoucherAdapter = new MyVoucherAdapter(this,myVoucherList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myVoucherAdapter);
        myVoucherAdapter.notifyDataSetChanged();
    }

    private String getRootUsername(){
        String name ;
        name = getSharedPreferences("USER",MODE_PRIVATE).getString("NAME",null);
        return name;
    }
}
