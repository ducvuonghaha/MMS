package com.example.mms.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.mms.activity.MusicDetailActivity;
import com.example.mms.model.Product;

import java.io.ByteArrayInputStream;
import java.util.List;

import static com.example.mms.database.Constant.decimalFormat;

public class MusicDealAdapter extends RecyclerView.Adapter<MusicDealAdapter.Holder>  {

    public Context context;
    public List<Product> productList;
    int row_index = -1 ;

    public MusicDealAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public MusicDealAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item,parent,false);
        return new MusicDealAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicDealAdapter.Holder holder,final int i) {
        holder.product = productList.get(i);
        holder.tvNameProduct.setText(holder.product.PRODUCT_NAME);
        holder.tvPriceProduct.setText(decimalFormat.format(holder.product.PRODUCT_PRICE));
        holder.imgProduct.setImageBitmap(ByteArrayToBitmap(holder.product.PRODUCT_IMAGE));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MusicDetailActivity.class);
                Product p = productList.get(i);
                intent.putExtra("id",p.PRODUCT_ID);
                intent.putExtra("name",p.PRODUCT_NAME);
                intent.putExtra("species",p.PRODUCT_SPECIES);
                intent.putExtra("type", p.PRODUCT_TYPE);
                intent.putExtra("video", p.PRODUCT_VIDEO);
                intent.putExtra("price",decimalFormat.format(p.PRODUCT_PRICE));
                intent.putExtra("priceproduct",String.valueOf(p.PRODUCT_PRICE));
                intent.putExtra("description",p.PRODUCT_DESCRIPTION);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        if (productList == null) return 0;
        else
            return productList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView tvNameProduct;
        private TextView tvPriceProduct;
        private ImageView imgProduct;
        private Product product;

        public Holder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            tvNameProduct = itemView.findViewById(R.id.tvNameProduct);
            tvPriceProduct = itemView.findViewById(R.id.tvPriceProduct);
        }
    }
    public Bitmap ByteArrayToBitmap(byte[] byteArray) {
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(byteArray);
        Bitmap bitmap = BitmapFactory.decodeStream(arrayInputStream);
        return bitmap;
    }
}
