package com.mmularski.SecSMS.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.CheckBox;
import com.mmularski.SecSMS.R;
import com.mmularski.SecSMS.containers.SettingsData;
import com.mmularski.SecSMS.listeners.DeleteContactsListener;
import com.mmularski.SecSMS.listeners.DeleteMessagesListener;
import com.mmularski.SecSMS.listeners.DeletePassListener;
import com.mmularski.SecSMS.listeners.RememberPassListener;

/**
 * Created by Mateusz Mularski
 * Copyright (c) 2014 Mateusz Mularski. All rights reserved.
 */
public class SettingsActivity extends Activity {

    CheckBox rememberPasswords;
    Button deletePasswords;
    Button deleteContacts;
    Button deleteMessages;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        rememberPasswords = (CheckBox) findViewById(R.id.rememberPass);
        deletePasswords = (Button) findViewById(R.id.deletePass);
        deleteContacts = (Button) findViewById(R.id.deleteContacts);
        deleteMessages = (Button) findViewById(R.id.deleteMessages);

        deletePasswords.setOnClickListener(new DeletePassListener());
        deleteContacts.setOnClickListener(new DeleteContactsListener());
        deleteMessages.setOnClickListener(new DeleteMessagesListener());

        rememberPasswords.setOnClickListener(new RememberPassListener());
        rememberPasswords.setChecked(SettingsData.getRememberPasswords());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        return super.onCreateOptionsMenu(menu);
    }
}
