package com.example.mms.musicactivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mms.R;
import com.example.mms.musicfragment.RockFragment;

public class RockActivity extends AppCompatActivity {

    private FrameLayout fragmentRock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rock);
        fragmentRock = (FrameLayout) findViewById(R.id.fragment_rock);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_rock, new RockFragment()).commit();
    }
}
