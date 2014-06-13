package com.mmularski.SecSMS.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;
import com.mmularski.SecSMS.R;
import com.mmularski.SecSMS.adapters.CategoriesAdapter;
import com.mmularski.SecSMS.containers.ContactList;
import com.mmularski.SecSMS.containers.MessageList;
import com.mmularski.SecSMS.listeners.CategoryListener;
import com.mmularski.SecSMS.testStuff.GenerateTestStuff;
/**
 * Created by Mateusz Mularski
 * Copyright (c) 2014 Mateusz Mularski. All rights reserved.
 */
public class MainActivity extends Activity {
    private static Activity main;
    private ListView categories;
    //DatabaseHelper helper;
   // MessageReceiver msgRecv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basiclist);

        ContactList.setContext(this);
        MessageList.setContext(this);
        GenerateTestStuff.generate();

        categories = (ListView) findViewById(R.id.categories);
        categories.setAdapter(new CategoriesAdapter(this));
        categories.setOnItemClickListener(new CategoryListener());


/**
 *       String action = "android.provider.Telephony.SMS_RECEIVED";
 *      this.msgRecv = new MessageReceiver();
 *       IntentFilter intentFilter = new IntentFilter(action);
 *       intentFilter.setPriority(999);
 *
 *       registerReceiver(this.msgRecv, intentFilter);
 */

        main=this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        //unregisterReceiver(this.msgRecv);
        ContactList.getDbHelper().close();
        MessageList.getDbHelper().close();//
    }
}
