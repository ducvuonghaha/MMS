package com.example.mms.main_fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mms.R;
import com.example.mms.activity.LoginActivity;
import com.example.mms.activity.MyOrdersActivity;
import com.example.mms.activity.MyVoucherActivity;
import com.example.mms.activity.QuestionsActivity;
import com.example.mms.activity.ShieldsActivity;
import com.example.mms.dao.UserDAO;
import com.example.mms.model.User;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import es.dmoral.toasty.Toasty;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {
    private LinearLayout llQuestions;
    private LinearLayout llShields;


    private LinearLayout llMyOrders;
    private TextView tvFullName;
    private ShareDialog shareDialog;
    private TextView tvPhoneNumber;
    private UserDAO userDAO;
    private Button btnEditProfile;
    private LinearLayout llChangePasss;
    private LinearLayout llMyVoucher;
    private LinearLayout llSignOut;
    private LinearLayout llShareFacebook;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        initView(view);
        return view;

    }

    public void initView(View view) {
        llQuestions = (LinearLayout) view.findViewById(R.id.llQuestions);
        llShields = (LinearLayout) view.findViewById(R.id.llShields);
        llShareFacebook = view.findViewById(R.id.llShareFacebook);
        llMyVoucher = view.findViewById(R.id.llMyVoucher);
        llChangePasss = view.findViewById(R.id.llChangePass);
        btnEditProfile = view.findViewById(R.id.btnEditProfile);
        tvFullName = view.findViewById(R.id.tvFullName);
        tvPhoneNumber = view.findViewById(R.id.tvPhoneNumber);
        llMyOrders = view.findViewById(R.id.llMyOrders);
        llSignOut = view.findViewById(R.id.llSignOut);
        userDAO = new UserDAO(getContext());


        llQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), QuestionsActivity.class);
                startActivity(intent);
            }
        });

        llShields.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShieldsActivity.class);
                startActivity(intent);
            }
        });

        llSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                mBuilder.setMessage("Bạn có muốn đăng xuất không?");
                mBuilder.setCancelable(true);
                mBuilder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                mBuilder.setNegativeButton("Đăng xuất", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getContext(), LoginActivity.class));
                        getActivity().finish();
                    }
                });
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });

        llShareFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                ProductCartDAO productCartDAO = new ProductCartDAO(getContext());
//                List<MyOrders> myOrders = new ArrayList<>();
//                myOrders = productCartDAO.getAllMyOrders(getRootUsername());
////                Uri product =



                shareDialog = new ShareDialog(getActivity());
                if (ShareDialog.canShow(SharePhotoContent.class)) {
                    SharePhotoContent linkContent = new SharePhotoContent.Builder()
                            .setContentUrl(Uri.parse("https://news.zing.vn/"))
                            .build();
                    shareDialog.show(linkContent);
                }
            }
        });

        llMyVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MyVoucherActivity.class));
            }
        });
        llMyOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MyOrdersActivity.class));
            }
        });

        if (tvFullName != null)
            tvFullName.setText(userDAO.getFullName(getRootUsername()));
        if (tvPhoneNumber != null)
            tvPhoneNumber.setText(userDAO.getPhoneNumber(getRootUsername()));

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                View mView = View.inflate(getContext(), R.layout.dialog_edt_user, null);
                mBuilder.setView(mView);
                mBuilder.setCancelable(true);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                final EditText edtEditUsername;
                final EditText edtEditFullName;
                final EditText edtEditAddress;
                final EditText edtEditPhone;
                final Button btnEditUser;
                final Button btnCancelEdt;

                edtEditUsername = mView.findViewById(R.id.edtEditUsername);
                edtEditFullName = mView.findViewById(R.id.edtEditFullName);
                edtEditAddress = mView.findViewById(R.id.edtEditAddress);
                edtEditPhone = mView.findViewById(R.id.edtEditPhone);
                btnEditUser = mView.findViewById(R.id.btnEditUser);
                btnCancelEdt = mView.findViewById(R.id.btnCancelEdt);

                try {
                    edtEditUsername.setText(getRootUsername());
                    edtEditFullName.setText(userDAO.getFullName(getRootUsername()));
                    edtEditPhone.setText(userDAO.getPhoneNumber(getRootUsername()));
                    edtEditAddress.setText(userDAO.getAddress(getRootUsername()));
                } catch (Exception e) {
                    Toasty.error(getContext(), "Lỗi " + e, Toast.LENGTH_SHORT).show();
                }


                btnCancelEdt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnEditUser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String diachi = edtEditAddress.getText().toString();
                        String username = edtEditUsername.getText().toString();
                        String hoten = edtEditFullName.getText().toString();
                        String phone = edtEditPhone.getText().toString();

                        try {
                            if (hoten.isEmpty()) {
                                Toasty.warning(getContext(), "Vui lòng không để trống họ tên", Toast.LENGTH_SHORT).show();
                            } else if (diachi.isEmpty()) {
                                Toasty.warning(getContext(), "Vui lòng nhập địa chỉ", Toast.LENGTH_SHORT).show();
                            } else if (phone.length() != 10) {
                                Toasty.warning(getContext(), "Số điện thoại phải có 10 ký tự", Toast.LENGTH_SHORT).show();
                            } else if (userDAO.updateUser(new User(username, "", diachi, phone, hoten), username) > 0) {
                                edtEditAddress.setText("");
                                edtEditUsername.setText("");
                                edtEditFullName.setText("");
                                edtEditPhone.setText("");
                                Toasty.success(getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                getFragmentManager().beginTransaction().detach(ProfileFragment.this).attach(ProfileFragment.this).commit();

                            } else if (userDAO.updateUser(new User(username, "", diachi, phone, hoten), username) <= 0) {
                                Toasty.error(getContext(), "Sửa thất bại", Toast.LENGTH_SHORT).show();
                            }
                        } catch (NumberFormatException e) {
                            Toasty.warning(getContext(), "Vui lòng nhập SĐT là số", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        llChangePasss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder1 = new AlertDialog.Builder(getContext());
                View mView1 = View.inflate(getContext(), R.layout.dialog_change_password, null);
                mBuilder1.setView(mView1);
                final AlertDialog dialog1 = mBuilder1.create();
                dialog1.show();

                final EditText edtCurrentPass;
                final EditText edtNewPass;
                final EditText edtReNewPass;
                final Button btnSaveNewPass;
                final Button btnCancelChangePass;

                edtCurrentPass = mView1.findViewById(R.id.edtCurrentPass);
                edtNewPass = mView1.findViewById(R.id.edtNewPass);
                edtReNewPass = mView1.findViewById(R.id.edtReNewPass);
                btnSaveNewPass = mView1.findViewById(R.id.btnSaveNewPass);
                btnCancelChangePass = mView1.findViewById(R.id.btnCancelChangePass);

                btnCancelChangePass.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog1.dismiss();
                    }
                });

                btnSaveNewPass.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String oldPass = userDAO.getPass(getRootUsername());
                        String currentPass = edtCurrentPass.getText().toString();
                        String newPass = edtNewPass.getText().toString();
                        String reNewPass = edtReNewPass.getText().toString();

                        try {
                            if (currentPass.isEmpty()) {
                                Toasty.warning(getContext(), "Vui lòng nhập mật khẩu cũ", Toast.LENGTH_SHORT).show();
                            } else if (!currentPass.matches(oldPass)) {
                                Toasty.warning(getContext(), "Vui lòng nhập lại mật khẩu cũ", Toast.LENGTH_SHORT).show();
                            } else if (newPass.isEmpty() && newPass.length() < 8) {
                                Toasty.warning(getContext(), "Vui lòng nhập mật khẩu mới có 8 ký tự trở lên", Toast.LENGTH_SHORT).show();
                            } else if (!reNewPass.equals(newPass)) {
                                Toasty.warning(getContext(), "Mật khẩu mới không trùng", Toast.LENGTH_SHORT).show();
                            } else if (currentPass.matches(oldPass)) {
                                userDAO.updatePass(new User("", newPass, "", "", ""), getRootUsername());
                                edtCurrentPass.setText("");
                                edtNewPass.setText("");
                                edtReNewPass.setText("");
                                Toasty.success(getContext(), "Lưu mật khẩu mới thành công", Toast.LENGTH_SHORT).show();
                                dialog1.dismiss();

                            }
                        } catch (Exception e) {
                            Toast.makeText(getContext(), "Lỗi " + e, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private String getRootUsername() {
        String name = "";
        name = getContext().getSharedPreferences("USER", MODE_PRIVATE).getString("NAME", null);
        return name;
    }
    }
