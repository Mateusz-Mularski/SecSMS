package com.mmularski.SecSMS.listeners;

import android.view.View;
import com.mmularski.SecSMS.containers.ContactList;

/**
 * Created by Mateusz Mularski
 * Copyright (c) 2014 Mateusz Mularski. All rights reserved.
 */
public class DeleteContactsListener implements View.OnClickListener {

    public DeleteContactsListener(){
    }

    @Override
    public void onClick(View view) {
        ContactList.deleteContacts();
    }
}
