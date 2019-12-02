package com.example.mms.musicactivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mms.R;
import com.example.mms.musicfragment.EDMFragment;

public class EDMActivity extends AppCompatActivity {

    private FrameLayout fragmentEdm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edmmusic);
        fragmentEdm = (FrameLayout) findViewById(R.id.fragment_edm);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_edm, new EDMFragment()).commit();
    }
}
