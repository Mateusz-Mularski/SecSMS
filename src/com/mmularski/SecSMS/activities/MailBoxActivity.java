package com.mmularski.SecSMS.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import com.mmularski.SecSMS.R;
import com.mmularski.SecSMS.adapters.MailBoxAdapter;
import com.mmularski.SecSMS.containers.CategoryList;
import com.mmularski.SecSMS.containers.ListItem;
import com.mmularski.SecSMS.listeners.MailCategoriesListener;

import java.util.LinkedList;

/**
 * Created by Mateusz Mularski
 * Copyright (c) 2014 Mateusz Mularski. All rights reserved.
 */
public class MailBoxActivity extends Activity {
    private ListView mails;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mailbox);

        mails = (ListView) findViewById(R.id.mailcategory);
        mails.setAdapter(new MailBoxAdapter(this));
        mails.setOnItemClickListener(new MailCategoriesListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mailbox_menu, menu);
        MenuItem newMail = menu.findItem(R.id.new_message);

        newMail.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent newMail = new Intent(MailBoxActivity.this, NewMailActivity.class);
                startActivity(newMail);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    public LinkedList<ListItem> getCategories(){
        return CategoryList.getInstance();
    }
}
