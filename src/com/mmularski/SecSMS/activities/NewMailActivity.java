package com.mmularski.SecSMS.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.mmularski.SecSMS.R;
import com.mmularski.SecSMS.adapters.ContactSpinnerAdapter;
import com.mmularski.SecSMS.containers.Contact;
import com.mmularski.SecSMS.containers.ContactList;
import com.mmularski.SecSMS.interfaces.ReadableSms;
import com.mmularski.SecSMS.listeners.SendListener;

/**
 * Created by Mateusz Mularski
 * Copyright (c) 2014 Mateusz Mularski. All rights reserved.
 */
public class NewMailActivity extends Activity implements ReadableSms{
    EditText newMessage;
    Button send;
    Spinner sendTo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newmail);

        newMessage = (EditText) findViewById(R.id.new_message);
        send = (Button) findViewById(R.id.send);
        sendTo = (Spinner) findViewById(R.id.sendTo);
        sendTo.setAdapter(new ContactSpinnerAdapter(this));
        send.setOnClickListener(new SendListener(this, ContactList.findContactWithPhoneNo(getPhoneNo()), null));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public String getPhoneNo() {
        return ((Contact)sendTo.getSelectedItem()).getPhoneNo();
    }

    @Override
    public String getMessageText() {
        return newMessage.getText().toString();
    }

    @Override
    public EditText getEditText(){
        return this.newMessage;
    }

}
