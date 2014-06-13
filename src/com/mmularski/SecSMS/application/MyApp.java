package com.mmularski.SecSMS.application;

import android.app.Application;
import android.content.Context;
import com.mmularski.SecSMS.sms.MessageReceiver;

/**
 * Created by Mateusz Mularski
 * Copyright (c) 2014 Mateusz Mularski. All rights reserved.
 */
public class MyApp extends Application {
    private static MyApp instance;
    private MessageReceiver msgRecv;

    public static MyApp getInstance() {
        return instance;
    }

    public static Context getContext(){
        return instance;
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}
