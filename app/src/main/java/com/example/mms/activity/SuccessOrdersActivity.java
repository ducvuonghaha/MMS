package com.example.mms.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mms.R;

public class SuccessOrdersActivity extends AppCompatActivity {
    private Button btnMyOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_orders);
        btnMyOrders = (Button) findViewById(R.id.btnMyOrders);
        btnMyOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuccessOrdersActivity.this, MyOrdersActivity.class);
                startActivity(intent);
            }
        });

    }
}
