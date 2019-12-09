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
import com.example.mms.interfaces.LoginView;
import com.example.mms.model.User;
import com.example.mms.presenter.LoginPresenter;

import es.dmoral.toasty.Toasty;


public class LoginActivity extends BaseActivity implements LoginView {

    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnSignIn;
    private TextView tvForgotPass;
    private TextView tvDangKy;
    private UserDAO userDAO;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        loginPresenter = new LoginPresenter(LoginActivity.this);
    }

    public void initView() {
        tvForgotPass = findViewById(R.id.tvForgotPass);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        tvDangKy = findViewById(R.id.tvDangKy);
        userDAO = new UserDAO(this);
        btnSignIn = findViewById(R.id.btnSignIn);

        tvForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(LoginActivity.this);
                View mView = View.inflate(LoginActivity.this, R.layout.forget_password_item, null);
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
        });


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                saveUsername(username);
                boolean checkLogin = userDAO.checkLogin(username, password);
                if (username.isEmpty() || password.isEmpty()) {
                    showMessegeWarning(getResources().getString(R.string.acti_login_warnEmpty));
                } else if (checkLogin == true) {
                    showMessegeSuccess(getResources().getString(R.string.acti_login_successlogin));
                    loginPresenter.Login(username, password);

                } else if (checkLogin == false) {
                    showMessegeWarning(getResources().getString(R.string.acti_login_wronguserpass));
                }

            }
        });


        tvDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity(SignUpActivity.class);
            }
        });


//        edtUsername.setText("meo123");
//        edtPassword.setText("12345678");


    }


    private void saveUsername(String username) {
        SharedPreferences sharedPreferences = getSharedPreferences("USER", MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("NAME", username);
        edit.apply();
    }

    @Override
    public void CheckUsername() {
          showMessegeWarning(getResources().getString(R.string.acti_login_warnEmpty));

    }

    @Override
    public void CheckPassword() {
            showMessegeWarning(getResources().getString(R.string.acti_login_warnEmpty));
    }

    @Override
    public void navigateHome() {
        showMessegeSuccess(getResources().getString(R.string.acti_login_successlogin));
        startNewActivity(HomeActivity.class);
    }

    @Override
    public void LoginFail() {

    }
}
