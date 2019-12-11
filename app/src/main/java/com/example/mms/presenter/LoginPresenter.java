package com.example.mms.presenter;

import com.example.mms.interfaces.LoginView;

public class LoginPresenter {
    private LoginView loginView;


    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    public void Login(String username, String password) {


        if (username.isEmpty()) {
            loginView.CheckUsername();
        } else if (password.isEmpty()) {
            loginView.CheckPassword();

        } else {
            loginView.navigateHome();

        }
    }

    public void SignUp() {
        loginView.SignUp();
    }


}
