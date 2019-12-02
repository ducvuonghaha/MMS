package com.example.mms.movieactivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mms.R;
import com.example.mms.moviefragment.HorrorFragment;

public class HorrorActivity extends AppCompatActivity {

    private FrameLayout fragmentHorror;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horror);
        fragmentHorror = (FrameLayout) findViewById(R.id.fragment_horror);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_horror, new HorrorFragment()).commit();
    }
}
