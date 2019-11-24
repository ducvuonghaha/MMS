package com.example.mms.activity;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mms.R;
import com.example.mms.base.BaseActivity;
import com.example.mms.dao.UserDAO;
import com.example.mms.model.User;

import es.dmoral.toasty.Toasty;


public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnSignIn;
    private TextView tvForgotPass;
    private TextView tvDangKy;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    public void initView() {
        tvForgotPass = findViewById(R.id.tvForgotPass);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        tvDangKy = findViewById(R.id.tvDangKy);
        userDAO = new UserDAO(this);
        btnSignIn = findViewById(R.id.btnSignIn);
        tvForgotPass.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);
        tvDangKy.setOnClickListener(this);
        edtUsername.setText("123");
        edtPassword.setText("meovuong201099");
    }

    @Override
    public void onClick(View v) {
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        if (v == btnSignIn) {

            boolean checkLogin = userDAO.checkLogin(username, password);
            if (username.isEmpty()  || password.isEmpty()) {
                showMessegeWarning(getResources().getString(R.string.acti_login_warnEmpty));
            } else if (checkLogin == false) {
                showMessegeWarning(getResources().getString(R.string.acti_login_wronguserpass));
            } else if (checkLogin == true) {
                saveUsername(username);
                showMessegeSuccess(getResources().getString(R.string.acti_login_successlogin));
                startNewActivity(HomeActivity.class);
            }
        } else if (v == tvDangKy) {
            startNewActivity(SignUpActivity.class);
        } else if (v == tvForgotPass) {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
            View mView = View.inflate(this, R.layout.forget_password_item, null);
            mBuilder.setView(mView);
            mBuilder.setCancelable(true);
            final AlertDialog dialog = mBuilder.create();
            dialog.show();

            final EditText edtYourUsername;
            final EditText edtNewPass;
            final EditText edtReNewPass;
            Button btnSaveNewPassword;
            Button btnCancelForgetPass;

            edtYourUsername = mView.findViewById(R.id.edtYourUsername);
            edtNewPass = mView.findViewById(R.id.edtNewPass);
            edtReNewPass = mView.findViewById(R.id.edtReNewPass);
            btnSaveNewPassword = mView.findViewById(R.id.btnSaveNewPassword);
            btnCancelForgetPass = mView.findViewById(R.id.btnCancelForgetPass);

            btnCancelForgetPass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            btnSaveNewPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String username = edtYourUsername.getText().toString();
                        boolean bl = userDAO.checkUsername(username);
                        String newpass = edtNewPass.getText().toString();
                        String repass = edtReNewPass.getText().toString();
                        if (username.isEmpty()) {
                            showMessegeWarning("Vui lòng nhập tên đăng nhập");
                        } else if (bl == false) {
                            showMessegeWarning("Tên người dùng không tồn tại");
                        } else if (newpass.isEmpty()) {
                            showMessegeWarning("Vui lòng nhập mật khẩu mới");
                        } else if (!newpass.matches(repass)) {
                            showMessegeWarning("Mật khẩu mới không trùng");
                        } else if (bl == true) {
                            userDAO.updatePass(new User("", newpass, "", "", ""), username);
                            showMessegeSuccess("Đổi mật khẩu thành công");
                            dialog.dismiss();
                        }
                    } catch (Exception e) {
                        Toasty.error(LoginActivity.this, "Lỗi " + e, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void saveUsername(String username) {
        SharedPreferences sharedPreferences = getSharedPreferences("USER", MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("NAME", username);
        edit.apply();
    }
}
