package com.course.codechallengesmodule5.chat;

public class ByteUtils {

    public static byte[] intToBytes(int i){

        byte[] result = new byte[4];
        byte b;

        b=(byte)i; result[3]=b; i>>=8;
        b=(byte)i; result[2]=b; i>>=8;
        b=(byte)i; result[1]=b; i>>=8;
        b=(byte)i; result[0]=b;

        return result;

    }
    public static int bytesToInt(byte[] bytes){
        int val = 0;

        for (int i = 0; i < 4; i++) {
            val=val<<8;
            val=val|(bytes[i] & 0xFF);
        }
        return val;

    }

}
