package com.example.mms.presenter;

import com.example.mms.interfaces.HomeView;

public class HomePresenter {
    public HomeView homeView ;

    public HomePresenter(HomeView homeView) {
        this.homeView = homeView;
    }

    public void Next() {
        homeView.Next();
    }

    public void Horror_Pop() {
        homeView.Horror_Pop();
    }

    public void Comedy_Bolero() {
        homeView.Comedy_Bolero();
    }

    public void Action_Rock() {
        homeView.Action_Rock();
    }

    public void Science_Children() {
        homeView.Science_Children();
    }

    public void Document_Indie() {
        homeView.Document_Indie();
    }

    public void Romantic_EDM() {
        homeView.Romantic_EDM();
    }

    public void Music_Movie() {
        homeView.Music_Movie();
    }

    public void Search() {
        homeView.Search();
    }

    public void Cart() {
        homeView.Cart();
    }


}
