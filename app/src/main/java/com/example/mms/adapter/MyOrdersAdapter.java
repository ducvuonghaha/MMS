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
import com.example.mms.model.MyOrders;

import java.io.ByteArrayInputStream;
import java.util.List;

import static com.example.mms.database.Constant.decimalFormat;

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.Holder> {

    Context context;
    List<MyOrders> myOrdersList;

    public MyOrdersAdapter(Context context, List<MyOrders> myOrdersList) {
        this.context = context;
        this.myOrdersList = myOrdersList;
    }

    @NonNull
    @Override
    public MyOrdersAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_orders_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrdersAdapter.Holder holder, int i) {
        holder.myOrders = myOrdersList.get(i);
        holder.tvNameMyOrders.setText(holder.myOrders.MY_ORDER_NAME);
        holder.tvPriceMyOrders.setText(decimalFormat.format(holder.myOrders.MY_ORDER_PRICE));
        holder.imgImageMyOrders.setImageBitmap(ByteArrayToBitmap(holder.myOrders.MY_ORDER_IMAGE));
    }

    @Override
    public int getItemCount() {
        if (myOrdersList == null) return 0;
        else
            return myOrdersList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private ImageView imgImageMyOrders;
        private TextView tvNameMyOrders;
        private TextView tvPriceMyOrders;
        private MyOrders myOrders;

        public Holder(@NonNull View itemView) {
            super(itemView);
            imgImageMyOrders = itemView.findViewById(R.id.imgImageMyOrders);
            tvNameMyOrders = itemView.findViewById(R.id.tvNameMyOrders);
            tvPriceMyOrders = itemView.findViewById(R.id.tvPriceMyOrders);

        }
    }

    public Bitmap ByteArrayToBitmap(byte[] byteArray) {
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(byteArray);
        Bitmap bitmap = BitmapFactory.decodeStream(arrayInputStream);
        return bitmap;
    }
}
