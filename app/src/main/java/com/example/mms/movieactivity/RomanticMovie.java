package com.example.mms.movieactivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mms.R;
import com.example.mms.moviefragment.RomanticFragment;

public class RomanticMovie extends AppCompatActivity {

    private FrameLayout fragmentRomantic;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_romantic_movie);
        fragmentRomantic = (FrameLayout) findViewById(R.id.fragment_romantic);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_romantic, new RomanticFragment()).commit();

    }
}
