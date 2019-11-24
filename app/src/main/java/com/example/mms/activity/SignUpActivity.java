package com.example.mms.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mms.R;
import com.example.mms.base.BaseActivity;
import com.example.mms.dao.UserDAO;
import com.example.mms.model.User;

public class SignUpActivity extends BaseActivity {

    private EditText edtSUUsername;
    private EditText edtSUFullName;
    private EditText edtSUPhone;
    private EditText edtSUPassword;
    private EditText edtSURepassword;
    private Button btnSignUp;
    private UserDAO userDAO;
    private EditText edtSUAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initView();
    }

    private void validate() {
        try {
            String user = edtSUUsername.getText().toString();
            String address = edtSUAddress.getText().toString();
            String password = edtSUPassword.getText().toString();
            String repassword = edtSURepassword.getText().toString();
            String hoten = edtSUFullName.getText().toString();
            String sdt = edtSUPhone.getText().toString();

            if (user.isEmpty()) {
                showMessegeWarning(getResources().getString(R.string.acti_signup_emptyUser));
            } else if (hoten.isEmpty()) {
                showMessegeWarning(getResources().getString(R.string.acti_signup_emptyfullname));
            } else if (address.isEmpty()) {
                showMessegeWarning(getResources().getString(R.string.acti_signup_emptyAdress));
            } else if (sdt.length() != 10) {
                showMessegeWarning(getResources().getString(R.string.acti_signup_emptyPhone));
            } else if (password.length() < 8) {
                showMessegeWarning(getResources().getString(R.string.acti_signup_warnLengthPass));
            } else if (!repassword.equals(password)) {
                showMessegeWarning(getResources().getString(R.string.acti_signup_warnCheckRepass));
            } else if (userDAO.insertUser(new User(user, password, address, sdt, hoten)) > 0) {
                clearAllEdt();
                showMessegeSuccess(getResources().getString(R.string.acti_signup_successSignUp));
            } else if (userDAO.insertUser(new User(user, password, address, sdt, hoten)) <= 0) {
                showMessegeError(getResources().getString(R.string.acti_signup_warnCheckUser));
            }
        } catch (NumberFormatException e) {
            showMessegeWarning(getResources().getString(R.string.acti_signup_warnPhone));
        }
    }

    private void clearAllEdt() {
        edtSUUsername.setText("");
        edtSURepassword.setText("");
        edtSUPassword.setText("");
        edtSUFullName.setText("");
        edtSUPhone.setText("");
        edtSUAddress.setText("");
    }

    private void initView() {
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle("Đăng Ký Thành Viên");
        edtSUAddress = findViewById(R.id.edtSU_Address);
        edtSUUsername = findViewById(R.id.edtSU_Username);
        edtSUFullName = findViewById(R.id.edtSU_FullName);
        edtSUPhone = findViewById(R.id.edtSU_Phone);
        edtSUPassword = findViewById(R.id.edtSU_Password);
        edtSURepassword = findViewById(R.id.edtSU_Repassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        userDAO = new UserDAO(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }

}
