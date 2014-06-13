package com.mmularski.SecSMS.encryption;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;

/**
 * Created by Mateusz Mularski
 * Copyright (c) 2014 Mateusz Mularski. All rights reserved.
 */
public class AESCrypter {
    public static String encrypt(String key, String value) {

        byte[] raw = key.getBytes(Charset.forName("UTF8"));
        if (raw.length != 16) {
            raw = repairKey(raw);
        }


        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec,
                new IvParameterSpec(new byte[16]));
            return Base64.encodeToString(cipher.doFinal(value.getBytes(Charset.forName("UTF8"))), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String key, byte[] encrypted) {

        byte[] raw = key.getBytes(Charset.forName("UTF8"));
        if (raw.length != 16) {
            raw=repairKey(raw);
        }
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            try{
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec,
                    new IvParameterSpec(new byte[16]));
            byte[] original = cipher.doFinal(encrypted);
            return new String(original, Charset.forName("UTF8"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] repairKey(byte[] raw){
        if(raw.length<16){
            byte[] tmp=new byte[16];
            int i;
            for(i=0; i<raw.length; i++)
                tmp[i]=raw[i];
            if(i<16){
                for(int j=0; j+i<16; j++)
                    tmp[i+j]=Byte.MIN_VALUE;
            }
            return tmp;
        }
        else{
            byte[] tmp = new byte[16];
            for(int i=0; i<16; i++)
                tmp[i]=raw[i];
            return tmp;
        }
    }
}
