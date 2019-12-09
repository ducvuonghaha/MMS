package com.example.mms.presenter;

import com.example.mms.interfaces.PaymentsView;

public class PaymentPresenter {

    public PaymentsView paymentsView;

    public PaymentPresenter(PaymentsView paymentsView) {
        this.paymentsView = paymentsView;
    }


    public void checkVoucher() {
        paymentsView.checkVoucher();
    }

    public void orders() {
        paymentsView.orders();
    }
}
