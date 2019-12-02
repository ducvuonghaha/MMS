package com.example.mms.musicactivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mms.R;
import com.example.mms.musicfragment.ChildrenMusicFragment;

public class ChildrenMusicActivity extends AppCompatActivity {

    private FrameLayout fragmentChildrenmusic;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_children_music);
        fragmentChildrenmusic = (FrameLayout) findViewById(R.id.fragment_childrenmusic);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_childrenmusic, new ChildrenMusicFragment()).commit();
    }
}
