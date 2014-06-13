package com.mmularski.SecSMS.listeners;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.mmularski.SecSMS.R;
import com.mmularski.SecSMS.activities.MailsActivity;
import com.mmularski.SecSMS.containers.Contact;
import com.mmularski.SecSMS.containers.ContactList;
import com.mmularski.SecSMS.containers.SettingsData;

/**
 * Created by Mateusz Mularski
 * Copyright (c) 2014 Mateusz Mularski. All rights reserved.
 */
public class MailCategoriesListener implements AdapterView.OnItemClickListener{
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        TextView label = (TextView) view.findViewById(R.id.labelBig);
        String contactName = label.getText().toString();

        Contact contact = ContactList.findContactWithName(contactName);

        if(SettingsData.getRememberPasswords() && !contact.getPassword().equals("")){
            Intent newIntent = new Intent(view.getContext(), MailsActivity.class);
            newIntent.putExtra("phoneNo", contact.getPhoneNo());
            newIntent.putExtra("pass", contact.getPassword());
            view.getContext().startActivity(newIntent);
            return;
        }

        Dialog dialog = new Dialog(view.getContext());
        dialog.setContentView(R.layout.dialogbox);
        dialog.setTitle("Put password for "+contactName);
        Button button = (Button)dialog.findViewById(R.id.save);
        EditText editText = (EditText)dialog.findViewById(R.id.dialogpass);

        button.setOnClickListener(new PopUpButtonListener(dialog,
                                        editText,
                                        view.getContext(),
                                        ContactList.findContactWithName(contactName)));
        dialog.show();
    }
}
