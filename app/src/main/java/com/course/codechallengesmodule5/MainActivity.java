package com.course.codechallengesmodule5;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.course.codechallengesmodule5.chat.Terminal1;
import com.course.codechallengesmodule5.library.LibraryActivity;

public class MainActivity extends AppCompatActivity {

    public static final boolean DEBUG = false;
    public static boolean cleared = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!cleared && DEBUG){deleteFile(Terminal1.FILE_NAME); cleared = true;}
    }
    public void navigateLibrary(View view) {
        Intent intent = new Intent(MainActivity.this, LibraryActivity.class);
        startActivity(intent);
    }

    public void navigateChat(View view) {
        Intent intent = new Intent(MainActivity.this, Terminal1.class);
        startActivity(intent);
    }
}
