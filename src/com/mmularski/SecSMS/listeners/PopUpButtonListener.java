package com.mmularski.SecSMS.listeners;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import com.mmularski.SecSMS.activities.MailsActivity;
import com.mmularski.SecSMS.containers.Contact;

import java.util.Observer;

/**
 * Created by Mateusz Mularski
 * Copyright (c) 2014 Mateusz Mularski. All rights reserved.
 */
public class PopUpButtonListener implements View.OnClickListener {

    Context context;
    EditText editText;
    Contact contact;
    Observer observer=null;
    Dialog dialog;

    public PopUpButtonListener(Dialog dialog, EditText passwordField, Context context, Contact contact){
        this.context = context;
        this.editText = passwordField;
        this.contact = contact;
        this.dialog = dialog;
    }

    public void setObserver(Observer ob){
        this.observer = ob;
    }

    @Override
    public void onClick(View view) {
        if(observer==null){
            Intent newIntent = new Intent(context, MailsActivity.class);
            newIntent.putExtra("phoneNo", contact.getPhoneNo());
            newIntent.putExtra("pass", editText.getText().toString());
            context.startActivity(newIntent);
        }
        else{
            this.observer.update(null, editText.getText().toString());
        }
        dialog.hide();
    }
}
