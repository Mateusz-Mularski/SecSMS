package com.mmularski.SecSMS.containers;

/**
 * Created by Mateusz Mularski
 * Copyright (c) 2014 Mateusz Mularski. All rights reserved.
 */
public class SettingsData {

    private static Boolean rememberPasswords=false;

    public static Boolean getRememberPasswords() {
        return rememberPasswords;
    }

    public static void setRememberPasswords(Boolean rememberPasswords) {
        SettingsData.rememberPasswords = rememberPasswords;
    }
}
