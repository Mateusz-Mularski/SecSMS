package com.mmularski.SecSMS.listeners;

import android.view.View;
import android.widget.CheckBox;
import com.mmularski.SecSMS.containers.SettingsData;

/**
 * Created by Mateusz Mularski
 * Copyright (c) 2014 Mateusz Mularski. All rights reserved.
 */
public class RememberPassListener implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        CheckBox rememberPass = (CheckBox) view;

        if(rememberPass.isChecked()) {
            SettingsData.setRememberPasswords(true);
        }
        else {
            SettingsData.setRememberPasswords(false);
        }
    }
}
