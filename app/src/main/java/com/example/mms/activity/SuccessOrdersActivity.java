package com.example.mms.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mms.R;
import com.example.mms.interfaces.OrdersView;
import com.example.mms.presenter.OrdersPresenter;

public class SuccessOrdersActivity extends AppCompatActivity implements OrdersView {

    private Button btnMyOrders;
    private OrdersPresenter ordersPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_orders);
        btnMyOrders = (Button) findViewById(R.id.btnMyOrders);

        ordersPresenter = new OrdersPresenter(this);

        btnMyOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ordersPresenter.ToMyOrders();
            }
        });

    }

    @Override
    public void navigateMyOrders() {
        Intent intent = new Intent(this,MyOrdersActivity.class);
        startActivity(intent);
    }

    @Override
    public void validate() {

    }
}
