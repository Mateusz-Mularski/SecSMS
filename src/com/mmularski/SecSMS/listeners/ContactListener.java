package com.mmularski.SecSMS.listeners;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import com.mmularski.SecSMS.R;
import com.mmularski.SecSMS.activities.ContactActivity;

/**
 * Created by Mateusz Mularski
 * Copyright (c) 2014 Mateusz Mularski. All rights reserved.
 */
public class ContactListener implements AdapterView.OnItemClickListener{
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TextView label = (TextView) view.findViewById(R.id.labelBig);
        String contactName = label.getText().toString();

        Intent newIntent = new Intent(view.getContext(), ContactActivity.class);
        newIntent.putExtra("name", contactName);
        view.getContext().startActivity(newIntent);
    }
}
