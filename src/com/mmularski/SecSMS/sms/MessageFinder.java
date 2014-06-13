package com.mmularski.SecSMS.sms;

import android.database.Cursor;
import android.net.Uri;

/**
 * Created by Mateusz Mularski
 * Copyright (c) 2014 Mateusz Mularski. All rights reserved.
 */
public class MessageFinder {
    private static final Uri SMS_INBOX = Uri.parse("content://sms/inbox");
    private static Cursor cursor;

    public static void findEncryptedSms(){
        if(cursor==null){
            //...
        }
    }
}
