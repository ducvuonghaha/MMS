package com.example.mms.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.example.mms.R;
import com.example.mms.base.BaseActivity;
import com.example.mms.dao.ProductCartDAO;
import com.example.mms.dao.ProductDAO;
import com.example.mms.databinding.ActivityProductDetailBinding;
import com.example.mms.interfaces.OrdersView;
import com.example.mms.model.ProductCart;
import com.example.mms.presenter.OrdersPresenter;

import java.io.ByteArrayInputStream;

public class ProductDetailActivity extends BaseActivity implements OrdersView {

    private TextView tvSPECIES;


    private OrdersPresenter ordersPresenter;
    private ImageView imgProductDetail;
    private TextView tvNameProductDetail;
    private TextView tvPriceProductDetail;
    private TextView tvDescriptionDetail;
    private ProductDAO productDAO;
    private ProductCartDAO productCartDAO;
    private Button btnAddProduct;
    private Button btnTrailer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityProductDetailBinding activityProductDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail);
//        setContentView(R.layout.activity_product_detail);
        initView();

        ordersPresenter = new OrdersPresenter(this);
        activityProductDetailBinding.setValidate(ordersPresenter);

        final Intent intent = getIntent();
        final String video = intent.getStringExtra("video");
        tvSPECIES.setText("Phim " + intent.getStringExtra("species"));

        btnTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailActivity.this, TrailerActivity.class);
                intent.putExtra("video",video);
                startActivity(intent);
            }
        });




        final byte[] image = productDAO.getImageProduct(intent.getStringExtra("id"));
        final String name = intent.getStringExtra("name");
        String price = intent.getStringExtra("price");
        final String description = intent.getStringExtra("description");

//        btnAddProduct.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ordersPresenter.validate();
//            }
//        });

        imgProductDetail.setImageBitmap(ByteArrayToBitmap(image));
        if (tvNameProductDetail != null)
            tvNameProductDetail.setText(name);
        if (tvPriceProductDetail != null)
            tvPriceProductDetail.setText(price);
        if (tvDescriptionDetail != null)
            tvDescriptionDetail.setText(description);

    }

    public Bitmap ByteArrayToBitmap(byte[] byteArray) {
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(byteArray);
        Bitmap bitmap = BitmapFactory.decodeStream(arrayInputStream);
        return bitmap;
    }

    @Override
    public void navigateMyOrders() {

    }

    @Override
    public void validate() {
        try {
            Intent intent = getIntent();
            byte[] image = productDAO.getImageProduct(intent.getStringExtra("id"));
            String name = intent.getStringExtra("name");
            String price1 = intent.getStringExtra("priceproduct");
            int priceproduct = Integer.parseInt(price1);
            if (productCartDAO.insertProductCart(new ProductCart(null, name, getRootUsername(), 1, priceproduct, image)) >= 0) {
                showMessegeSuccess("Thêm vào giỏ hàng thành công");
                sendBroadcast(new Intent("update"));
            }

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Lỗi" + e, Toast.LENGTH_SHORT).show();
        }
    }

    public void initView() {
        btnTrailer = (Button) findViewById(R.id.btnTrailer);
        tvSPECIES = (TextView) findViewById(R.id.tvSPECIES);
        productCartDAO = new ProductCartDAO(this);
        productDAO = new ProductDAO(this);
        imgProductDetail = findViewById(R.id.imgProductDetail);
        tvNameProductDetail = findViewById(R.id.tvNameProductDetail);
        tvPriceProductDetail = findViewById(R.id.tvPriceProductDetail);
        tvDescriptionDetail = findViewById(R.id.tvDescriptionDetail);
        btnAddProduct = findViewById(R.id.btnAddProduct);
    }

    private String getRootUsername() {
        String name;
        name = getSharedPreferences("USER", MODE_PRIVATE).getString("NAME", null);
        return name;
    }
}
