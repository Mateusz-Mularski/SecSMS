package com.mmularski.SecSMS.listeners;

import android.view.View;
import com.mmularski.SecSMS.containers.MessageList;

/**
 * Created by Mateusz Mularski
 * Copyright (c) 2014 Mateusz Mularski. All rights reserved.
 */
public class DeleteMessagesListener implements View.OnClickListener {

    public DeleteMessagesListener(){
    }

    @Override
    public void onClick(View view) {
        MessageList.deleteMessages();
    }
}
