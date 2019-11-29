package com.example.mms.movieactivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mms.R;
import com.example.mms.moviefragment.ScienceFragment;

public class ScienceMovie extends AppCompatActivity {

    private FrameLayout scienceFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_science_movie);
        scienceFragment = (FrameLayout) findViewById(R.id.science_fragment);
        getSupportFragmentManager().beginTransaction().replace(R.id.science_fragment, new ScienceFragment()).commit();

    }
}
