package com.example.mms.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mms.R;
import com.example.mms.adapter.PaymentAdapter;
import com.example.mms.base.BaseActivity;
import com.example.mms.dao.MyVoucherDAO;
import com.example.mms.dao.ProductCartDAO;
import com.example.mms.dao.UserDAO;
import com.example.mms.databinding.ActivityPaymentBinding;
import com.example.mms.interfaces.PaymentsView;
import com.example.mms.model.ProductCart;
import com.example.mms.presenter.PaymentPresenter;

import java.util.ArrayList;
import java.util.List;

import static com.example.mms.database.Constant.decimalFormat;

public class PaymentActivity extends BaseActivity implements PaymentsView {
    private TextView tvFullNamePayment;
    private TextView tvAddressPayment;
    private EditText edtVoucherCode;
    private Button btnCheckVoucher;
    private TextView tvPhonePayment;
    private RecyclerView rvPayment;
    private MyVoucherDAO myVoucherDAO;
    private TextView tvSumPricePayment;
    private PaymentAdapter paymentAdapter;
    private Button btnOrder;
    private List<ProductCart> productCartList;
    private LinearLayoutManager linearLayoutManager;
    private ProductCartDAO productCartDAO;
    private UserDAO userDAO;
    private PaymentPresenter paymentPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityPaymentBinding activityPaymentBinding = DataBindingUtil.setContentView(this, R.layout.activity_payment);

//        setContentView(R.layout.activity_payment);
        initView();
        paymentPresenter = new PaymentPresenter(this);
        activityPaymentBinding.setCheckVoucher(paymentPresenter);
        activityPaymentBinding.setOrder(paymentPresenter);
    }

    private void initView() {
        myVoucherDAO = new MyVoucherDAO(this);
        edtVoucherCode = findViewById(R.id.edtVoucherCode);
        btnCheckVoucher = findViewById(R.id.btnCheckVoucher);
        userDAO = new UserDAO(this);
        productCartDAO = new ProductCartDAO(this);
        tvFullNamePayment = findViewById(R.id.tvFullNamePayment);
        tvAddressPayment = findViewById(R.id.tvAddressPayment);
        tvPhonePayment = findViewById(R.id.tvPhonePayment);
        tvSumPricePayment = findViewById(R.id.tvSumPricePayment);
        double tongtien = productCartDAO.getTongTien(getRootUsername());
        if (tvSumPricePayment != null)
            tvSumPricePayment.setText(decimalFormat.format(tongtien));

//        btnCheckVoucher.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                paymentPresenter.checkVoucher();
//            }
//        });


        try {
            tvFullNamePayment.setText(userDAO.getFullName(getRootUsername()));
            tvAddressPayment.setText(userDAO.getAddress(getRootUsername()));
            tvPhonePayment.setText(userDAO.getPhoneNumber(getRootUsername()));
        } catch (NullPointerException e) {
            showMessegeSuccess("Lỗi " + e);
        }

        rvPayment = findViewById(R.id.rvPayment);
        tvSumPricePayment = findViewById(R.id.tvSumPricePayment);
        btnOrder = findViewById(R.id.btnOrder);
        productCartList = new ArrayList<>();
        productCartList = productCartDAO.getAllProductCart(getRootUsername());
        linearLayoutManager = new LinearLayoutManager(this);
        paymentAdapter = new PaymentAdapter(this, productCartList);
        rvPayment.setLayoutManager(linearLayoutManager);
        rvPayment.setAdapter(paymentAdapter);
        paymentAdapter.notifyDataSetChanged();
//        btnOrder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                paymentPresenter.orders();
//            }
//        });
    }

    private String getRootUsername() {
        String name;
        name = getSharedPreferences("USER", MODE_PRIVATE).getString("NAME", null);
        return name;
    }

    @Override
    public void orders() {
        productCartDAO.insertMyOrders(getRootUsername());
        productCartDAO.deleteCart(getRootUsername());
        sendBroadcast(new Intent("update"));
        startNewActivity(SuccessOrdersActivity.class);
    }

    @Override
    public void checkVoucher() {
        String voucherCode = edtVoucherCode.getText().toString();
        boolean check = myVoucherDAO.checkVoucher(voucherCode);
        if (check == true) {
            showMessegeSuccess(getResources().getString(R.string.acti_payment_successVoucher));
            double tongtienn = productCartDAO.getTongTien(getRootUsername());
            double discout = myVoucherDAO.getPercentDiscount(voucherCode);
            double priceAfterDiscount = tongtienn * discout;
            if (tvSumPricePayment != null)
                tvSumPricePayment.setText(decimalFormat.format(priceAfterDiscount));
        } else {
            showMessegeWarning(getResources().getString(R.string.acti_payment_warningVoucher));
        }
    }
}
