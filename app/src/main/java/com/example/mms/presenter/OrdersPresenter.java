package com.example.mms.presenter;

import com.example.mms.interfaces.OrdersView;

public class OrdersPresenter {
    private OrdersView ordersView ;

    public OrdersPresenter(OrdersView ordersView) {
        this.ordersView = ordersView;
    }

    public void ToMyOrders() {
        ordersView.navigateMyOrders();
    }

    public void validate() {
        ordersView.validate();
    }
}
