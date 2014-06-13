package com.mmularski.SecSMS.containers;

import android.content.Context;
import android.util.Log;
import com.mmularski.SecSMS.database.DatabaseHelper;

import java.util.LinkedList;
import java.util.List;
import java.util.Observer;

/**
 * Created by Mateusz Mularski
 * Copyright (c) 2014 Mateusz Mularski. All rights reserved.
 */
public class ContactList {

    private static Context context=null;
    private static DatabaseHelper dbHelper=null;
    private static LinkedList<Observer> observers=new LinkedList<Observer>();

    private ContactList(){};

    public static void add(String name, String phoneNo, int image, String password){
        dbHelper.add(new Contact(name, phoneNo, image, password));
        notifyAllObs();
    }

    public static void add(Contact contact){
        dbHelper.add(contact);
        notifyAllObs();
    }

    public static void update(Contact contact){
        dbHelper.update(contact);
    }

    public static Contact findContactWithName(String name) {
        for(Contact tmp : dbHelper.getContactsList())
            if(tmp.getName().equals(name))
                return tmp;
        return null;
    }

    public static Contact findContactWithPhoneNo(String phoneNo){
        if(dbHelper == null)
            dbHelper = new DatabaseHelper(context);

        for(Contact tmp : dbHelper.getContactsList()){

            Log.d("phones", ";"+phoneNo + "; : ;" + tmp.getPhoneNo()+";");
            if(!tmp.getPhoneNo().equals("") &&
                    (tmp.getPhoneNo().equals(phoneNo) ||
                    tmp.getPhoneNo().contains(phoneNo)||
                    phoneNo.contains(tmp.getPhoneNo()))){
                return tmp;
            }
        }
        return null;
    }

    public static void setContext(Context c){
        context=c;
        dbHelper = new DatabaseHelper(context);
    }

    public static DatabaseHelper getDbHelper(){
        return dbHelper;
    }

    public static List<Contact> getInstance(){
        if(dbHelper == null)
            dbHelper = new DatabaseHelper(context);
        return dbHelper.getContactsList();
    }

    public static void deleteContact(Contact c){
        dbHelper.deleteContactForContactId(c.getId());
    }

    public static void deletePasswords(){
        List<Contact> list = getInstance();
        for(Contact tmp:list){
            tmp.setPassword("");
            update(tmp);
        }
    }

    public static void notifyAllObs(){
        for(Observer obs : observers){
            obs.update(null, null);
        }
    }

    public static void addObserver(Observer o){
        observers.add(o);
    }

    public static void removeObserver(Observer o){
        observers.remove(o);
    }

    public static void deleteContacts(){
        dbHelper.deleteAllContacts();
    }
}
