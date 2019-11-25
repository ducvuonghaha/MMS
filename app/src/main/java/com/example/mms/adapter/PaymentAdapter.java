package com.example.mms.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mms.R;
import com.example.mms.activity.PaymentActivity;
import com.example.mms.dao.ProductCartDAO;
import com.example.mms.model.ProductCart;

import java.io.ByteArrayInputStream;
import java.util.List;

import es.dmoral.toasty.Toasty;

import static com.example.mms.database.Constant.decimalFormat;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.Holder> {

    Context context;
    List<ProductCart> productCartList;

    public PaymentAdapter(Context context, List<ProductCart> productCartList) {
        this.context = context;
        this.productCartList = productCartList;
    }

    @NonNull
    @Override
    public PaymentAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.payment_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PaymentAdapter.Holder holder, final int i) {
        holder.productCart = productCartList.get(i);
        holder.tvNameCartProduct.setText(holder.productCart.PRODUCT_CART_NAME);
        holder.tvNumber.setText(String.valueOf(holder.productCart.PRODUCT_CART_NUMBER));
        holder.tvPriceCartProduct.setText(decimalFormat.format(holder.productCart.PRODUCT_CART_PRICE));
        holder.imgImageCartProduct.setImageBitmap(ByteArrayToBitmap(holder.productCart.PRODUCT_CART_IMAGE));

        holder.btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductCartDAO productCartDAO = new ProductCartDAO(context);
                holder.productCart.PRODUCT_CART_NUMBER++;
                productCartDAO.updateProductCartAmount(new ProductCart("", "", "", holder.productCart.PRODUCT_CART_NUMBER, 0, null), holder.productCart.PRODUCT_CART_ID);
                holder.tvNumber.setText(Integer.toString(holder.productCart.PRODUCT_CART_NUMBER));
                ((PaymentActivity) context).recreate();
            }
        });

        holder.btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductCartDAO productCartDAO = new ProductCartDAO(context);
                if (holder.productCart.PRODUCT_CART_NUMBER > 0) {
                    holder.productCart.PRODUCT_CART_NUMBER--;
                    productCartDAO.updateProductCartAmount(new ProductCart("", "", "", holder.productCart.PRODUCT_CART_NUMBER, 0, null), holder.productCart.PRODUCT_CART_ID);
                    holder.tvNumber.setText(Integer.toString(holder.productCart.PRODUCT_CART_NUMBER));
                    ((PaymentActivity) context).recreate();

                } else {
                    Toasty.warning(context, "Số lượng phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (productCartList == null) return 0;
        else
            return productCartList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private ImageView imgImageCartProduct;
        private TextView tvNameCartProduct;
        private TextView tvPriceCartProduct;
        private Button btnDecrease;
        private TextView tvNumber;
        private Button btnIncrease;
        private ProductCart productCart;

        public Holder(@NonNull View itemView) {
            super(itemView);
            imgImageCartProduct = itemView.findViewById(R.id.imgImageCartProduct);
            tvNameCartProduct = itemView.findViewById(R.id.tvNameCartProduct);
            tvPriceCartProduct = itemView.findViewById(R.id.tvPriceCartProduct);
            btnDecrease = itemView.findViewById(R.id.btnDecrease);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            btnIncrease = itemView.findViewById(R.id.btnIncrease);

        }
    }

    public Bitmap ByteArrayToBitmap(byte[] byteArray) {
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(byteArray);
        Bitmap bitmap = BitmapFactory.decodeStream(arrayInputStream);
        return bitmap;
    }
}
