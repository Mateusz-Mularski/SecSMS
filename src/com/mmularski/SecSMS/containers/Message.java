package com.mmularski.SecSMS.containers;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by Mateusz Mularski
 * Copyright (c) 2014 Mateusz Mularski. All rights reserved.
 */
@DatabaseTable(tableName="Message")
public class Message {

    @DatabaseField(generatedId = true, canBeNull = false)
    private int mess_id;
    @DatabaseField(canBeNull = false)
    private Integer con_id;
    @DatabaseField(canBeNull = true)
    private String from;
    @DatabaseField(canBeNull = true)
    private String to;
    @DatabaseField(canBeNull = true)
    private String messageText;
    @DatabaseField(canBeNull = true)
    private Date date;

    private Message(){}

    public Message(String from, String to, String messageText, Date date) {
        this.from=from;
        this.to=to;
        this.messageText=messageText;
        this.date=date;
        if(!from.equals("Me"))
            this.con_id=ContactList.findContactWithPhoneNo(from).getId();
        else
            this.con_id=ContactList.findContactWithPhoneNo(to).getId();
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
