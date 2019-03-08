package com.course.codechallengesmodule5.chat;

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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static com.course.codechallengesmodule5.chat.Message.RECEIVED;
import static com.course.codechallengesmodule5.chat.Message.SENT;

public class Terminal2 extends AppCompatActivity {

    public static final int REQUEST_CODE = Terminal1.REQUEST_CODE;
    public static final String FILE_NAME = Terminal1.FILE_NAME;
    private Button mSendButton;
    private EditText mMessageText;
    private LinearLayout mMessageContainer;
    public static final String SAVE_INSTANCE_KEY = "editMessage";
    private void initUi(){
        mSendButton=findViewById(R.id.send_button_2);
        mMessageText=findViewById(R.id.message_text_2);
        mMessageContainer=findViewById(R.id.message_container_2);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminal2);
        initUi();

    }

    private void displayMessages(){
        mMessageContainer.removeAllViews();
        try {
            FileInputStream stream = openFileInput(FILE_NAME);
            List<Message> messages = Message.readAllMessages(stream);
            LayoutInflater inflater = LayoutInflater.from(Terminal2.this);
            stream.close();
            for(Message message : messages){
                View msgView = null;
                TextView textView = null;
                int messageLayout=0;
                int textId=0;
                if(message.getMessageType() == SENT) {
                    messageLayout = R.layout.message_received;
                    textId = R.id.text_view_received;

                }
                if(message.getMessageType() == RECEIVED) {
                    messageLayout = R.layout.message_sent;
                    textId = R.id.text_view_sent;

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
    public void clear(View v) {
        deleteFile(FILE_NAME);
        mMessageContainer.removeAllViews();
    }
    protected void onPause(){
        super.onPause();

    }



    protected void onResume(){
        super.onResume();
        displayMessages();
    }
    private void addMessage(String msg, int msgType){
        Message message = new Message();
        message.setText(msg);
        message.setMessageType(msgType);
        FileOutputStream stream = null;
        try {
            stream = openFileOutput(FILE_NAME,MODE_APPEND);
            Message.to(message,stream);
            stream.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void send(View view) {
        String text = mMessageText.getText().toString();
        if(text == null || text.isEmpty()) return;
        Message message = new Message();
        message.setText(text);
        message.setMessageType(RECEIVED);
        addMessage(text,RECEIVED);
        setResult(REQUEST_CODE,null);
        finish();//todo finish and run code tomorrow


    }
}
