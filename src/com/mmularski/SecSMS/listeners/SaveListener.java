package com.mmularski.SecSMS.listeners;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.mmularski.SecSMS.R;
import com.mmularski.SecSMS.containers.Contact;
import com.mmularski.SecSMS.containers.ContactList;

/**
 * Created by Mateusz Mularski
 * Copyright (c) 2014 Mateusz Mularski. All rights reserved.
 */
public class SaveListener implements View.OnClickListener {

    Activity actv;
    Contact thisContact;

    public SaveListener(Activity toChange, Contact thisContact){
        actv=toChange;
        this.thisContact = thisContact;
    }

    @Override
    public void onClick(View view) {
        String action = ((Button)view).getText().toString();
        String name = ((EditText)actv.findViewById(R.id.edit_name)).getText().toString();
        String phoneNo = ((EditText)actv.findViewById(R.id.edit_phoneno)).getText().toString();
        String pass = ((EditText)actv.findViewById(R.id.edit_pass)).getText().toString();
        int image = ((ImageView)actv.findViewById(R.id.c_image)).getImageAlpha();


        if(action.equals("Save changes")){
            thisContact.setName(name);
            thisContact.setPhoneNo(phoneNo);
            thisContact.setPassword(pass);
            ContactList.update(thisContact);
        }
        else
            ContactList.add(new Contact(name, phoneNo, image, pass));
    }
}
