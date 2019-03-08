package com.course.codechallengesmodule5.library;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.course.codechallengesmodule5.R;

public class ReaderActivity extends AppCompatActivity {
    TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);
        mTextView = findViewById(R.id.text_view);
        setText();
    }
    private void setText(){
        Intent intent=getIntent();
        Bundle extras = intent.getExtras();
        int textCode = extras.getInt(LibraryActivity.TEXT_CODE);
        mTextView.setText(textCode);


    }
}
