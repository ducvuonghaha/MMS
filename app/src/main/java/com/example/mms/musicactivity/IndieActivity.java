package com.example.mms.musicactivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mms.R;
import com.example.mms.musicfragment.IndieFragment;

public class IndieActivity extends AppCompatActivity {

    private FrameLayout fragmentIndie;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indie);
        fragmentIndie = (FrameLayout) findViewById(R.id.fragment_indie);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_indie, new IndieFragment()).commit();
    }
}
