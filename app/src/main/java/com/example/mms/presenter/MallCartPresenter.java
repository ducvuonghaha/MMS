package com.example.mms.presenter;

import com.example.mms.interfaces.MallCartView;

public class MallCartPresenter {
    public MallCartView mallCartView;

    public MallCartPresenter(MallCartView mallCartView) {
        this.mallCartView = mallCartView;
    }

    public void MallSearch() {
        mallCartView.MovetoMallSearch();
    }

    public void Cart() {
        mallCartView.MovetoCart();
    }
}
