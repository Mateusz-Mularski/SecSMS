package com.mmularski.SecSMS.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.mmularski.SecSMS.R;
import com.mmularski.SecSMS.adapters.MessagesAdapter;
import com.mmularski.SecSMS.containers.Contact;
import com.mmularski.SecSMS.containers.ContactList;
import com.mmularski.SecSMS.containers.Message;
import com.mmularski.SecSMS.containers.MessageList;
import com.mmularski.SecSMS.interfaces.ReadableSms;
import com.mmularski.SecSMS.listeners.SendListener;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Mateusz Mularski
 * Copyright (c) 2014 Mateusz Mularski. All rights reserved.
 */
public class MailsActivity extends Activity implements ReadableSms, Observer{

    private ListView mails;
    private EditText message;
    private Button send;
    private String phoneNo;
    private String pass;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mailsforcontact);

        Intent intent = getIntent();
        phoneNo = intent.getStringExtra("phoneNo");
        pass = intent.getStringExtra("pass");
        Log.d("phoneNo", phoneNo);

        Contact c = ContactList.findContactWithPhoneNo(phoneNo);
        List<Message> list = MessageList.findMessagesForContactId(c.getId());

        mails = (ListView) findViewById(R.id.listView);
        message = (EditText) findViewById(R.id.editText);
        send = (Button) findViewById(R.id.send);
        send.setOnClickListener(new SendListener(this, c, pass));
        mails.setAdapter(new MessagesAdapter(this, list, pass));
        MessageList.addObserver(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.delete_menu, menu);
        MenuItem delete = menu.findItem(R.id.delete);

        delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                MessageList.deleteMessageForContact(ContactList.findContactWithPhoneNo(phoneNo));
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public String getPhoneNo() {
        return phoneNo;
    }

    @Override
    public String getMessageText() {
        return message.getText().toString();
    }

    @Override
    public EditText getEditText(){
        return this.message;
    }

    @Override
    public void update(Observable observable, Object o) {
        Contact contact = (Contact)o;
        if(contact.getPhoneNo().equals(phoneNo) ||
                contact.getPhoneNo().contains(phoneNo) ||
                phoneNo.contains(contact.getPhoneNo())){
            List<Message> list = MessageList.findMessagesForContactId(contact.getId());
            mails.setAdapter(new MessagesAdapter(this, list, pass));
        }
    }
}
