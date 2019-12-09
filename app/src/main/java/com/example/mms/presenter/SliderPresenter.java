package com.example.mms.presenter;

import com.example.mms.interfaces.SliderView;

public class SliderPresenter {
    private SliderView sliderView ;

    public SliderPresenter(SliderView sliderView) {
        this.sliderView = sliderView;
    }

    public void addDot(int position) {
        sliderView.addDot();
    }
}
