package com.course.codechallengesmodule5.library;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.course.codechallengesmodule5.R;

import java.io.Reader;

public class LibraryActivity extends AppCompatActivity {
    public static final String TEXT_CODE="textCode";
    public static final int TEXT_1=R.string.text1;
    public static final int TEXT_2=R.string.text2;
    public static final int TEXT_3=R.string.text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
    }
    //TODO Send bundle to Reader activity
    public void showTextOne(View view) {
        Bundle extras = new Bundle();
        extras.putInt(TEXT_CODE,TEXT_1);
        Intent intent = new Intent(LibraryActivity.this,ReaderActivity.class);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void showTextTwo(View view) {
        Bundle extras = new Bundle();
        extras.putInt(TEXT_CODE,TEXT_2);
        Intent intent = new Intent(LibraryActivity.this, ReaderActivity.class);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void showTextThree(View view) {
        Bundle extras = new Bundle();
        extras.putInt(TEXT_CODE,TEXT_3);
        Intent intent = new Intent(LibraryActivity.this, ReaderActivity.class);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
