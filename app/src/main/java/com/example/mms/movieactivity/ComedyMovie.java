package com.example.mms.movieactivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mms.R;
import com.example.mms.moviefragment.ComedyFragment;

public class ComedyMovie extends AppCompatActivity {

    private FrameLayout fragmentComedy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comedy_movie);
        fragmentComedy = (FrameLayout) findViewById(R.id.fragment_comedy);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_comedy, new ComedyFragment()).commit();

    }
}
