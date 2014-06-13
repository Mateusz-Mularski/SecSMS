package com.mmularski.SecSMS.listeners;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import com.mmularski.SecSMS.activities.ContactListActivity;
import com.mmularski.SecSMS.activities.MailBoxActivity;
import com.mmularski.SecSMS.activities.SettingsActivity;

/**
 * Created by Mateusz Mularski
 * Copyright (c) 2014 Mateusz Mularski. All rights reserved.
 */
public class CategoryListener implements AdapterView.OnItemClickListener{
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i){
            case 0:
                Intent mailbox = new Intent(view.getContext(), MailBoxActivity.class);
                view.getContext().startActivity(mailbox);
                break;
            case 1:
                Intent contacts = new Intent(view.getContext(), ContactListActivity.class);
                view.getContext().startActivity(contacts);
                break;
            case 2:
                Intent settings = new Intent(view.getContext(), SettingsActivity.class);
                view.getContext().startActivity(settings);
                break;
            default:
                return;
        }
    }
}
