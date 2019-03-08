package com.course.codechallengesmodule5.chat;

import android.os.CountDownTimer;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.course.codechallengesmodule5.R;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Message {
    public static final int SENT = 1;
    public static final int RECEIVED = -1;
    public static final String NEW_MESSAGE = "newMessage";
    public static final int LINE_LENGTH = 16;



    public static Message from(FileInputStream stream) {
        Message result = new Message();
        try {
            DataInputStream fin = new DataInputStream(stream);
            int msgType = fin.readInt();
            String text = fin.readUTF();
            result.setText(text);
            result.setMessageType(msgType);
        } catch (EOFException e) {
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return result;

    }

    public static void to(Message message, FileOutputStream stream) {
        try {
            int msgType = message.getMessageType();
            String msgText = message.getText();
            DataOutputStream fout = new DataOutputStream(stream);
            fout.writeInt(msgType);
            fout.writeUTF(msgText);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Message> readAllMessages(FileInputStream stream) {
        ArrayList<Message> messages = new ArrayList<>();
        Message currentMessage = from(stream);
        while (currentMessage != null) {
            messages.add(currentMessage);
            currentMessage = from(stream);
        }
        return messages;

    }

    private static String formatMessageString(String msgText) {
        int length = msgText.length();
        int baseIndex = 0;
        for(baseIndex = 0;baseIndex<=length;baseIndex+=LINE_LENGTH);{
            //TODO FUN APP and finish formatting string
        }
        return null;

    }


    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public void setMessageType(int msgType) {
        if (msgType >= 0) mMessageType = SENT;
        else mMessageType = RECEIVED;
    }

    private String mText;

    private int mMessageType;

    public int getMessageType() {
        return mMessageType;

    }


}
