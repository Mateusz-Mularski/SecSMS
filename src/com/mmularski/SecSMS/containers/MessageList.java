package com.mmularski.SecSMS.containers;

import android.content.Context;
import com.mmularski.SecSMS.database.DatabaseHelper;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Observer;

/**
 * Created by Mateusz Mularski
 * Copyright (c) 2014 Mateusz Mularski. All rights reserved.
 */
public class MessageList {

    private static Context context=null;
    private static DatabaseHelper dbHelper=null;
    private static LinkedList<Observer> observers = new LinkedList<Observer>();

    private MessageList(){};

    public static void add(String from, String to, String body){
        dbHelper.add(new Message(from, to, body, new Date()));
        if(from.equals("Me"))
            notifyAllObs(ContactList.findContactWithPhoneNo(to));
        else
            notifyAllObs(ContactList.findContactWithPhoneNo(from));
    }

    public static void add(Message msg){
        dbHelper.add(msg);
        if(msg.getFrom().equals("Me"))
            notifyAllObs(ContactList.findContactWithPhoneNo(msg.getTo()));
        else
            notifyAllObs(ContactList.findContactWithPhoneNo(msg.getFrom()));
    }

    public static void update(Message msg){
        dbHelper.update(msg);
    }

    public static List<Message> findMessagesForContactId(int id){
        return dbHelper.getMessagesForContactId(id);
    }

    public static void setContext(Context c){
        context=c;
        dbHelper = new DatabaseHelper(context);
    }

    public static void deleteMessages(){
        dbHelper.deleteAllMessages();
    }

    public static DatabaseHelper getDbHelper(){
        return dbHelper;
    }

    public static void notifyAllObs(Object o){
        for(Observer obs : observers){
            obs.update(null, o);
        }
    }

    public static void addObserver(Observer o){
        observers.add(o);
    }

    public static void removeObserver(Observer o){
        observers.remove(o);
    }

    public static void deleteMessageForContact(Contact c){
        dbHelper.deleteMessagesForContactId(c.getId());
        notifyAllObs(c);
    }
}
