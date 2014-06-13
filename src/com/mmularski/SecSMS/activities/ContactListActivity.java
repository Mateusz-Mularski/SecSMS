package com.mmularski.SecSMS.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import com.mmularski.SecSMS.R;
import com.mmularski.SecSMS.adapters.ContactListAdapter;
import com.mmularski.SecSMS.listeners.ContactListener;

/**
 * Created by Mateusz Mularski
 * Copyright (c) 2014 Mateusz Mularski. All rights reserved.
 */
public class ContactListActivity extends Activity {

    ListView contacts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basiclist);

        contacts = (ListView) findViewById(R.id.categories);
        contacts.setAdapter(new ContactListAdapter(this));
        contacts.setOnItemClickListener(new ContactListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contact_menu, menu);
        MenuItem newContact = menu.findItem(R.id.new_contact);

        newContact.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent newContact = new Intent(ContactListActivity.this, ContactActivity.class);

                startActivity(newContact);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
