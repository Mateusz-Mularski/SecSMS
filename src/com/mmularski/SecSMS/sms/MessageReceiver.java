package com.mmularski.SecSMS.sms;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import com.mmularski.SecSMS.R;
import com.mmularski.SecSMS.activities.MailBoxActivity;
import com.mmularski.SecSMS.containers.ContactList;
import com.mmularski.SecSMS.containers.MessageList;

/**
 * Created by Mateusz Mularski
 * Copyright (c) 2014 Mateusz Mularski. All rights reserved.
 */
public class MessageReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();

        Log.d("lololol", context.toString());

        Object[] msgs = (Object[])bundle.get("pdus");
        SmsMessage[] sms=new SmsMessage[msgs.length];

        for(int i=0; i<msgs.length; i++)
            sms[i]=SmsMessage.createFromPdu((byte[]) msgs[i]);

        for(SmsMessage msg : sms){
                String from = msg.getOriginatingAddress();
               // String name = ContactList.findContactWithPhoneNo(from).getName();
                if(msg.getMessageBody().substring(0,8).equals("<SecSMS=")){
                    abortBroadcast();
                    String cryptedPart = msg.getMessageBody().substring(9, msg.getMessageBody().length()-2);
                    MessageList.add(from, "Me", cryptedPart);
                    lauchNotification(context, from);
                }
        }
    }

    private void lauchNotification(Context context, String from){

        Intent notificationIntent = new Intent(context, MailBoxActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Resources res = context.getResources();

        Notification mBuilder =
                new Notification.Builder(context).setDefaults(Notification.DEFAULT_ALL)
                        .setSmallIcon(R.drawable.ic_mail)
                        .setTicker("SecSMS: New encrypted message")
                        .setContentTitle("New message")
                        .setContentText( "from: "+ ContactList.findContactWithPhoneNo(from).getName())
                        .setAutoCancel(true).setContentIntent(contentIntent)
                        .build();
        nm.notify(1, mBuilder);
    }
}
