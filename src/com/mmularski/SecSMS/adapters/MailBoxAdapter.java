package com.mmularski.SecSMS.adapters;

import android.app.Activity;
import android.content.Context;
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
public class MailBoxAdapter extends ArrayAdapter<Contact> {
        Context context;
        List<Contact> categories;

        public MailBoxAdapter(Context context){
            super(context, R.layout.categories, ContactList.getInstance());
            this.context=context;
            this.categories = ContactList.getInstance();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            if(convertView==null){
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                convertView = inflater.inflate(R.layout.categories, parent, false);
            }

            TextView label = (TextView) convertView.findViewById(R.id.labelBig);
            ImageView image = (ImageView) convertView.findViewById(R.id.icon);
            label.setText(categories.get(position).getName());
            image.setImageResource(categories.get(position).getImage());

            return convertView;
    }
}
