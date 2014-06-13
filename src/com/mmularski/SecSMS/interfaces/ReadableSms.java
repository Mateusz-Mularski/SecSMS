package com.mmularski.SecSMS.interfaces;

import android.widget.EditText;

/**
 * Created by Mateusz Mularski
 * Copyright (c) 2014 Mateusz Mularski. All rights reserved.
 */
public interface ReadableSms {
    public String getPhoneNo();
    public String getMessageText();
    public EditText getEditText();
}
