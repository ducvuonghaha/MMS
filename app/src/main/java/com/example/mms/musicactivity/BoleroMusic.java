package com.example.mms.musicactivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mms.R;
import com.example.mms.musicfragment.BoleroFragment;

public class BoleroMusic extends AppCompatActivity {

    private FrameLayout fragmentBolero;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bolero_music);
        fragmentBolero = (FrameLayout) findViewById(R.id.fragment_bolero);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_bolero, new BoleroFragment()).commit();

    }
}
