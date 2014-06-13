package com.mmularski.SecSMS.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.mmularski.SecSMS.R;
import com.mmularski.SecSMS.containers.Contact;
import com.mmularski.SecSMS.containers.ContactList;
import com.mmularski.SecSMS.listeners.SaveListener;

/**
 * Created by Mateusz Mularski
 * Copyright (c) 2014 Mateusz Mularski. All rights reserved.
 */
public class ContactActivity extends Activity {
    private EditText name;
    private EditText pass;
    private EditText pNo;
    private ImageView image;
    private Button save;
    private Contact thisContact;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);

        Intent dataIntent = getIntent();
        this.thisContact = ContactList.findContactWithName(dataIntent.getStringExtra("name"));

        name = (EditText) findViewById(R.id.edit_name);
        pass = (EditText) findViewById(R.id.edit_pass);
        pNo = (EditText) findViewById(R.id.edit_phoneno);
        save = (Button) findViewById(R.id.save);
        image = (ImageView) findViewById(R.id.c_image);

        if(thisContact!=null){
            name.setText(thisContact.getName());
            pNo.setText(thisContact.getPhoneNo());
            pass.setText(thisContact.getPassword());
            image.setImageResource(thisContact.getImage());
        }
        else
            save.setText("Add user");
        save.setOnClickListener(new SaveListener(this, thisContact));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.delete_menu, menu);
        MenuItem delete = menu.findItem(R.id.delete);

        delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                ContactList.deleteContact(thisContact);

                startActivity(new Intent(ContactActivity.this, ContactListActivity.class));

                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
