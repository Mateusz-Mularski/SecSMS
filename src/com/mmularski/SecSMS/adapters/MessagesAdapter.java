package com.mmularski.SecSMS.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.mmularski.SecSMS.R;
import com.mmularski.SecSMS.containers.Contact;
import com.mmularski.SecSMS.containers.ContactList;
import com.mmularski.SecSMS.containers.Message;
import com.mmularski.SecSMS.encryption.AESCrypter;
import com.mmularski.SecSMS.interfaces.ReadableSms;

import java.util.Date;
import java.util.List;


/**
 * Created by Mateusz Mularski
 * Copyright (c) 2014 Mateusz Mularski. All rights reserved.
 */
public class MessagesAdapter extends ArrayAdapter<Message> {
    ReadableSms context;
    List<Message> mails;
    String pass;

    public MessagesAdapter(ReadableSms context, List<Message> list, String pass){
        super((Context) context, R.layout.messages, list);
        this.context=context;
        this.pass = pass;
        mails=list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView==null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.messages, parent, false);
        }

        TextView info = (TextView) convertView.findViewById(R.id.info);
        TextView content = (TextView) convertView.findViewById(R.id.content);

        if(position<mails.size()){
            Date msgDate = mails.get(position).getDate();
            String from, to;
            String date = msgDate.toLocaleString();
            Contact c = ContactList.findContactWithPhoneNo(mails.get(position).getFrom());
            if(c != null){
                from = c.getName();
                to = "Me";
            }
            else {
                from = "Me";
                to = ContactList.findContactWithPhoneNo(mails.get(position).getTo()).getName();
            }
            info.setText(from + " -> " + to + ", " + date);

            byte[] bytes = Base64.decode(mails.get(position).getMessageText(), 0);
            content.setText(AESCrypter.decrypt(this.pass, bytes));


            if(from.equals("Me"))
                convertView.findViewById(R.id.content).setBackgroundResource(R.drawable.back2);
            else
                convertView.findViewById(R.id.content).setBackgroundResource(R.drawable.back);
        }
        return convertView;
    }
}
