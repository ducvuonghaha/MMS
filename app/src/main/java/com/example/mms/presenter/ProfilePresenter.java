package com.example.mms.presenter;

import com.example.mms.interfaces.ProfileView;

public class ProfilePresenter {
    public ProfileView profileView;

    public ProfilePresenter(ProfileView profileView) {
        this.profileView = profileView;
    }

    public void MyOrders() {
        profileView.MyOrders();
    }

    public void ChangePassword() {
            profileView.ChangePassword();
    }

    public void ShareFacebook() {
        profileView.ShareFacebook();
    }

    public void Voucher() {
        profileView.Voucher();
    }

    public void Question() {
        profileView.Question();
    }

    public void Shield() {
        profileView.Shield();
    }

    public void SignOut() {
        profileView.SignOut();
    }
}
