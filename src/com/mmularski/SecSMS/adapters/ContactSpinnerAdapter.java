package com.mmularski.SecSMS.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.mmularski.SecSMS.R;
import com.mmularski.SecSMS.containers.Contact;
import com.mmularski.SecSMS.containers.ContactList;

import java.util.List;

/**
 * Created by Mateusz Mularski
 * Copyright (c) 2014 Mateusz Mularski. All rights reserved.
 */
public class ContactSpinnerAdapter extends ArrayAdapter<Contact> {

    private Activity context;
    private List<Contact> contacts;

    public ContactSpinnerAdapter(Activity context)
    {
        super(context, android.R.layout.simple_spinner_dropdown_item, ContactList.getInstance());
        this.contacts = ContactList.getInstance();
        this.context=context;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent)
    {
        if(position<contacts.size()){
            View row = convertView;
            if(row == null)
            {
                LayoutInflater inflater = context.getLayoutInflater();
                row = inflater.inflate(R.layout.categories, parent, false);
            }

            TextView name = (TextView) row.findViewById(R.id.labelBig);
            ImageView img = (ImageView) row.findViewById(R.id.icon);

            img.setImageResource(contacts.get(position).getImage());
            name.setText(contacts.get(position).getName());
            return row;
        }
        return null;
    }
}
