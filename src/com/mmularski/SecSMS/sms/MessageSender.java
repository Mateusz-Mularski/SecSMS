package com.mmularski.SecSMS.sms;

import android.telephony.SmsManager;
import com.mmularski.SecSMS.containers.MessageList;
import com.mmularski.SecSMS.encryption.AESCrypter;

import java.util.ArrayList;

/**
 * Created by Mateusz Mularski
 * Copyright (c) 2014 Mateusz Mularski. All rights reserved.
 */
public class MessageSender {
    private static final SmsManager smsManager = SmsManager.getDefault();

    public static void sendSms(String phoneNo, String message, String pass) {

        String cryptedSMS = AESCrypter.encrypt(pass, message);
        String withLabel = "<SecSMS=\""+cryptedSMS+"\">";

        if(withLabel.length()>160){
            ArrayList<String> messageList = smsManager.divideMessage(withLabel);
            smsManager.sendMultipartTextMessage(phoneNo, null, messageList, null, null);
        }
        else
            smsManager.sendTextMessage(phoneNo, null, withLabel, null, null);
        MessageList.add("Me", phoneNo, cryptedSMS);
    }
}
