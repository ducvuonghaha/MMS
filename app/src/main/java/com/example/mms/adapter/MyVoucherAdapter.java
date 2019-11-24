package com.example.mms.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mms.R;
import com.example.mms.model.MyVoucher;

import java.io.ByteArrayInputStream;
import java.util.List;

public class MyVoucherAdapter extends RecyclerView.Adapter<MyVoucherAdapter.Holder> {

    Context context;
    List<MyVoucher> voucherList;

    public MyVoucherAdapter(Context context, List<MyVoucher> voucherList) {
        this.context = context;
        this.voucherList = voucherList;
    }
    @NonNull
    @Override
    public MyVoucherAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_voucher_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVoucherAdapter.Holder holder, int i) {
        holder.myVoucher = voucherList.get(i);
        holder.tvVoucherID.setText(holder.myVoucher.VOUCHER_NAME);
        holder.tvNameVoucher.setText(holder.myVoucher.VOUCHER_DESCRIPTION);
        holder.tvVoucherDate.setText(holder.myVoucher.VOUCHER_DATE);
        holder.imgVoucher.setImageBitmap(ByteArrayToBitmap(holder.myVoucher.VOUCHER_IMAGE));
    }

    @Override
    public int getItemCount() {
        if (voucherList == null) return 0;
        else
            return voucherList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private ImageView imgVoucher;
        private TextView tvNameVoucher;
        private TextView tvVoucherID;
        private TextView tvVoucherDate;
        private MyVoucher myVoucher;


        public Holder(@NonNull View itemView) {
            super(itemView);
            imgVoucher = itemView.findViewById(R.id.imgVoucher);
            tvNameVoucher = itemView.findViewById(R.id.tvNameVoucher);
            tvVoucherID = itemView.findViewById(R.id.tvVoucherID);
            tvVoucherDate = itemView.findViewById(R.id.tvVoucherDate);
        }
    }
    public Bitmap ByteArrayToBitmap(byte[] byteArray) {
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(byteArray);
        Bitmap bitmap = BitmapFactory.decodeStream(arrayInputStream);
        return bitmap;
    }
}
