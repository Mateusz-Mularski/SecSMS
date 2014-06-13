package com.mmularski.SecSMS.listeners;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import com.mmularski.SecSMS.R;
import com.mmularski.SecSMS.containers.Contact;
import com.mmularski.SecSMS.containers.ContactList;
import com.mmularski.SecSMS.containers.SettingsData;
import com.mmularski.SecSMS.interfaces.ReadableSms;
import com.mmularski.SecSMS.sms.MessageSender;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Mateusz Mularski
 * Copyright (c) 2014 Mateusz Mularski. All rights reserved.
 */
public class SendListener implements View.OnClickListener, Observer{

    private ReadableSms actv=null;
    private Contact c;
    private String pass;

    public SendListener(ReadableSms actv, Contact c, String pass) {
        this.actv = actv;
        this.c = c;
        this.pass = pass;
    }

    @Override
    public void onClick(View view) {
        if(this.pass != null){
            MessageSender.sendSms(actv.getPhoneNo(), actv.getMessageText(), pass);
            clearEditText();
        }
        else{
            if(SettingsData.getRememberPasswords() && !c.getPassword().equals("")){
                MessageSender.sendSms(actv.getPhoneNo(), actv.getMessageText(), c.getPassword());
                return;
            }

            Dialog dialog = new Dialog(((Activity)actv));
            dialog.setContentView(R.layout.dialogbox);
            dialog.setTitle("Put password for "+actv.getPhoneNo());
            Button button = (Button)dialog.findViewById(R.id.save);
            button.setText("Send");
            EditText editText = (EditText)dialog.findViewById(R.id.dialogpass);

            PopUpButtonListener popUpButtonListener = new PopUpButtonListener(dialog,editText,
                    ((Activity)actv),
                    ContactList.findContactWithPhoneNo(actv.getPhoneNo()));
            popUpButtonListener.setObserver(this);
            button.setOnClickListener(popUpButtonListener);
            dialog.show();
        }
    }

    public void clearEditText(){
        EditText editText = actv.getEditText();
        editText.setText("");
        InputMethodManager imm = (InputMethodManager) ((Activity)actv).getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    @Override
    public void update(Observable observable, Object o) {
        MessageSender.sendSms(actv.getPhoneNo(), actv.getMessageText(), (String)o);
        clearEditText();
    }
}
