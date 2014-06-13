package com.mmularski.SecSMS.testStuff;

import com.mmularski.SecSMS.R;
import com.mmularski.SecSMS.containers.CategoryList;

/**
 * Created by Mateusz Mularski
 * Copyright (c) 2014 Mateusz Mularski. All rights reserved.
 */
public class GenerateTestStuff {

    private static Boolean wasGenerated=false;

    private GenerateTestStuff(){}

    public static void generate(){
        if(!wasGenerated){
            CategoryList.add("Mail", R.drawable.ic_mail);
            CategoryList.add("Contacts", R.drawable.ic_contacts);
            CategoryList.add("Settings", R.drawable.ic_settings);

            wasGenerated=true;
        }
    }
}
