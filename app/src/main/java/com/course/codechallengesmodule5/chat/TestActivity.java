package com.course.codechallengesmodule5.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.course.codechallengesmodule5.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static com.course.codechallengesmodule5.chat.Terminal1.FILE_NAME;
//TODO Complete code and run test
public class TestActivity extends AppCompatActivity {
    EditText mEditText;
    TextView mMessageText;
    TextView mMessageTypeText;
    RadioButton mSentButton;
    RadioButton mReceivedButton;

    FileInputStream messageStream;
    boolean streamOn = false;
    boolean canRead = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initUi();
    }

    private void initUi() {
        mEditText = findViewById(R.id.add_message_text);
        mSentButton = findViewById(R.id.sent_button);
        mReceivedButton = findViewById(R.id.received_button);
        mMessageText = findViewById(R.id.message_display_text);
        mMessageTypeText = findViewById(R.id.message_type_text);
        streamOn = false;
        File file = getFileStreamPath(FILE_NAME);
        if(file == null || !file.exists())
            canRead = false;
         else canRead = true;
    }

    public void putMessage(View v) {
        String text = mEditText.getText().toString();
        Message message = new Message();
        message.setText(text);
        if (mSentButton.isChecked()) {
         message.setMessageType(Message.SENT);
        }
        if (mReceivedButton.isChecked()) {
          message.setMessageType(Message.RECEIVED);
        }
        try {
            Message.to(message,openFileOutput(FILE_NAME,MODE_APPEND));
            canRead = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void nextMessage(View v) {
        if(!canRead) {mMessageText.setText("No messages"); return;}
        if(!streamOn){
            try {
                messageStream = openFileInput(FILE_NAME);
                streamOn = true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
        Message message = Message.from(messageStream);
        if(message == null) {mMessageText.setText("No more messages to display"); return;}
        mMessageText.setText(message.getText());
        if(message.getMessageType() == Message.RECEIVED)
        mMessageTypeText.setText("Received");
        else mMessageTypeText.setText("Sent");



    }

    public void clear(View v) {
        deleteFile(FILE_NAME);
        try {
            messageStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        streamOn = false;
        canRead = false;

    }
}
