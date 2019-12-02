package com.example.mms.musicactivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mms.R;
import com.example.mms.musicfragment.PopFragment;

public class PopActivity extends AppCompatActivity {

    private FrameLayout fragmentPop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);
        fragmentPop = (FrameLayout) findViewById(R.id.fragment_pop);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_pop, new PopFragment()).commit();
    }
}
