package com.example.mms.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mms.R;
import com.example.mms.dao.ProductDAO;

import java.io.ByteArrayInputStream;

public class ListenActivity extends AppCompatActivity {

    private ImageView imgMusic;
    MediaPlayer mediaPlayer;
    private VideoView vvTrailer ;
    private MediaController mediaController ;
    private int position = 0;
    private ProductDAO productDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen);
        vvTrailer = (VideoView) findViewById(R.id.vvTrailer);
        imgMusic = (ImageView) findViewById(R.id.imgMusic);


        Intent intent = getIntent();
        productDAO = new ProductDAO(this);
        final byte[] image = productDAO.getImageProduct(intent.getStringExtra("id"));
        imgMusic.setImageBitmap(ByteArrayToBitmap(image));



        // Tạo bộ điều khiển
        if (mediaController == null) {
            mediaController = new MediaController(ListenActivity.this);

            // Neo vị trí của MediaController với VideoView.
            mediaController.setAnchorView(vvTrailer);


            // Sét đặt bộ điều khiển cho VideoView.
            vvTrailer.setMediaController(mediaController);
        }


        try {
            // ID của file video.

            int id = this.getRawResIdByName(intent.getStringExtra("video"));
            vvTrailer.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + id));

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        vvTrailer.requestFocus();

        // Sự kiện khi file video sẵn sàng để chơi.
        vvTrailer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mediaPlayer) {


                vvTrailer.seekTo(position);
                if (position == 0) {
                    vvTrailer.start();
                }

                // Khi màn hình Video thay đổi kích thước
                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

                        // Neo lại vị trí của bộ điều khiển của nó vào VideoView.
                        mediaController.setAnchorView(vvTrailer);
                    }
                });
            }
        });

    }



    // Tìm ID ứng với tên của file nguồn (Trong thư mục raw).
    public int getRawResIdByName(String resName) {
        String pkgName = this.getPackageName();

        // Trả về 0 nếu không tìm thấy.
        int resID = this.getResources().getIdentifier(resName, "raw", pkgName);
        Log.i("AndroidVideoView", "Res Name: " + resName + "==> Res ID = " + resID);
        return resID;
    }

    public Bitmap ByteArrayToBitmap(byte[] byteArray) {
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(byteArray);
        Bitmap bitmap = BitmapFactory.decodeStream(arrayInputStream);
        return bitmap;
    }


    // Khi bạn xoay điện thoại, phương thức này sẽ được gọi
    // nó lưu trữ lại ví trí file video đang chơi.
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // Lưu lại vị trí file video đang chơi.
        savedInstanceState.putInt("CurrentPosition", vvTrailer.getCurrentPosition());
        vvTrailer.pause();
    }


    // Sau khi điện thoại xoay chiều xong. Phương thức này được gọi,
    // bạn cần tái tạo lại ví trí file nhạc đang chơi.
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Lấy lại ví trí video đã chơi.
        position = savedInstanceState.getInt("CurrentPosition");
        vvTrailer.seekTo(position);
    }
}

