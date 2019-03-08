package com.course.codechallengesmodule5.chat;

import android.content.ContextWrapper;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.course.codechallengesmodule5.R;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.course.codechallengesmodule5.chat.Message.NEW_MESSAGE;
import static com.course.codechallengesmodule5.chat.Message.RECEIVED;
import static com.course.codechallengesmodule5.chat.Message.SENT;

public class Terminal1 extends AppCompatActivity {

    //region Ui fields
    private Button mSendButton;
    private EditText mMessageText;
    private LinearLayout mMessageContainer;
    public static final String FILE_NAME = "messages";
    public static final int REQUEST_CODE = 256;
    public static final String SAVE_INSTANCE_KEY = "editMessage";
    //endregion
    private Message[] mMessages;

    private void initUi() {
        mSendButton = findViewById(R.id.send_button_1);
        mMessageText = findViewById(R.id.message_text_1);
        mMessageContainer = findViewById(R.id.message_container_1);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminal1);
        initUi();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null || resultCode == RESULT_CANCELED) return;
        //This method is not doing anything, just implemented it for startActivityForResult()



    }

    protected void onResume() {
        super.onResume();
        displayMessages();
    }

    private void addMessage(String msg, int msgType) {


        Message message = new Message();
        message.setMessageType(msgType);
        message.setText(msg);
        try {
            FileOutputStream stream = openFileOutput(FILE_NAME,MODE_APPEND);
            Message.to(message, stream);
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void clear(View v){
        deleteFile(FILE_NAME);
        mMessageContainer.removeAllViews();
    }
    private void displayMessages() {
        //TODO Clear and fetch data from internal storage to ui
        mMessageContainer.removeAllViews();
        try {
            FileInputStream stream = openFileInput(FILE_NAME);
            List<Message> messages = Message.readAllMessages(stream);
            LayoutInflater inflater = LayoutInflater.from(Terminal1.this);
            stream.close();
            for(Message message : messages){
                View msgView = null;
                TextView textView = null;
                int messageLayout=0;
                int textId=0;
                if(message.getMessageType() == SENT) {
                    messageLayout = R.layout.message_sent;
                    textId = R.id.text_view_sent;

                }
                if(message.getMessageType() == RECEIVED) {
                    messageLayout = R.layout.message_received;
                    textId = R.id.text_view_received;

                }
                msgView = inflater.inflate(messageLayout,mMessageContainer,false);
                mMessageContainer.addView(msgView);
                textView = msgView.findViewById(textId);
                textView.setText(message.getText());


            }
        } catch (FileNotFoundException expected) {


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void send(View view) {
        String text = mMessageText.getText().toString();
        if(text.isEmpty() || text == null) return;
        int msgType = SENT;
        addMessage(text,msgType);


        Intent intent = new Intent(Terminal1.this, Terminal2.class);
        startActivityForResult(intent, REQUEST_CODE);
    }
}
