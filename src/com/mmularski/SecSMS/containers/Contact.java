package com.mmularski.SecSMS.containers;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.mmularski.SecSMS.R;

/**
 * Created by Mateusz Mularski
 * Copyright (c) 2014 Mateusz Mularski. All rights reserved.
 */
@DatabaseTable(tableName="Contact")
public class Contact {

    @DatabaseField(generatedId=true, canBeNull = false)
    private int id;
    @DatabaseField(canBeNull = true)
    private String name;
    @DatabaseField(canBeNull = true)
    private String phoneNo;
    private int image = R.drawable.ic_contacts;
    @DatabaseField(canBeNull = true)
    private String password;

    public Contact(){}

    public Contact(String name, String phoneNo, int image, String password) {
        this.name=name;
        this.phoneNo = phoneNo;
        this.image=image;
        this.password=password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString(){
        return name+" ("+phoneNo+")";
    }
}
