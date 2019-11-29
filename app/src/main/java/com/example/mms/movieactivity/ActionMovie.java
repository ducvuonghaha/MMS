package com.example.mms.movieactivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mms.R;
import com.example.mms.moviefragment.ActionFragment;

public class ActionMovie extends AppCompatActivity {
    private FrameLayout fragmentAction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_movie);
        fragmentAction = (FrameLayout) findViewById(R.id.fragment_action);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_action, new ActionFragment()).commit();

    }
}
