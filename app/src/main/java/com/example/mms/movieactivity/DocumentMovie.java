package com.example.mms.movieactivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mms.R;
import com.example.mms.moviefragment.DocumentFragment;

public class DocumentMovie extends AppCompatActivity {
    private FrameLayout documentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_movie);
        documentFragment = (FrameLayout) findViewById(R.id.document_fragment);
        getSupportFragmentManager().beginTransaction().replace(R.id.document_fragment, new DocumentFragment()).commit();

    }
}
