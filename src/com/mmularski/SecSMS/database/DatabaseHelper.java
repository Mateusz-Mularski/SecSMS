package com.mmularski.SecSMS.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.mmularski.SecSMS.containers.Contact;
import com.mmularski.SecSMS.containers.Message;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Mateusz Mularski
 * Copyright (c) 2014 Mateusz Mularski. All rights reserved.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private Context _context;
    private static final String DATABASE_NAME = "SecureSMS.db";
    private static final int DATABASE_VERSION = 2;

    private Dao<Contact, Integer> contactsDao = null;
    private Dao<Message, Integer> messageDao = null;

    private RuntimeExceptionDao<Contact, Integer> simpleContactsDao = null;
    private RuntimeExceptionDao<Message, Integer> simpleMessageDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        _context = context;
    }

    public Dao<Contact, Integer> getContactsDao() throws SQLException {
        if (contactsDao == null) {
            contactsDao = getDao(Contact.class);
        }
        return contactsDao;
    }

    public Dao<Message, Integer> getMessageDao() throws SQLException {
        if (messageDao == null) {
            messageDao = getDao(Message.class);
        }
        return messageDao;
    }

    public RuntimeExceptionDao<Contact, Integer> getSimpleContactsDao() {
        if (simpleContactsDao == null) {
            simpleContactsDao = getRuntimeExceptionDao(Contact.class);
        }
        return simpleContactsDao;
    }

    public RuntimeExceptionDao<Message, Integer> getSimpleMessageDao() {
        if (simpleMessageDao == null) {
            simpleMessageDao = getRuntimeExceptionDao(Message.class);
        }
        return simpleMessageDao;
    }

    //method for list of Contact
    public List<Contact> getContactsList()
    {
        DatabaseHelper helper = new DatabaseHelper(_context);
        RuntimeExceptionDao<Contact, Integer> simpleDao = helper.getSimpleContactsDao();
        List<Contact> list = simpleDao.queryForAll();
        return list;
    }

    public List<Message> getMessagesForContactId(int id){
        DatabaseHelper helper = new DatabaseHelper(_context);
        try {
            QueryBuilder<Message, Integer> queryBuilder = helper.getMessageDao().queryBuilder();
            queryBuilder.where().eq("con_id", id);
            return queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteMessagesForContactId(int id){
        DatabaseHelper helper = new DatabaseHelper(_context);
        try {
            QueryBuilder<Message, Integer> queryBuilder = helper.getMessageDao().queryBuilder();
            queryBuilder.where().eq("con_id", id);
            RuntimeExceptionDao<Message, Integer> dao = getSimpleMessageDao();
            dao.delete(queryBuilder.query());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteContactForContactId(int id){
        DatabaseHelper helper = new DatabaseHelper(_context);
        try {
            QueryBuilder<Contact, Integer> queryBuilder = helper.getContactsDao().queryBuilder();
            queryBuilder.where().eq("id", id);
            RuntimeExceptionDao<Contact, Integer> dao = getSimpleContactsDao();
            dao.delete(queryBuilder.query());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //method for insert data
    public int add(Contact Contact)
    {
        RuntimeExceptionDao<Contact, Integer> dao = getSimpleContactsDao();
        int i = dao.create(Contact);
        return i;
    }

    public int add(Message msg)
    {
        RuntimeExceptionDao<Message, Integer> dao = getSimpleMessageDao();
        int i = dao.create(msg);
        return i;
    }


    public int update(Contact contact){
            RuntimeExceptionDao<Contact, Integer> dao = getSimpleContactsDao();
            int i = dao.update(contact);
            return i;
    }

    public int update(Message msg){
        RuntimeExceptionDao<Message, Integer> dao = getSimpleMessageDao();
        int i = dao.update(msg);
        return i;
    }

    //method for delete all rows
    public void deleteAllContacts()
    {
        RuntimeExceptionDao<Contact, Integer> dao = getSimpleContactsDao();
        List<Contact> list = dao.queryForAll();
        dao.delete(list);
    }

    public void deleteAllMessages()
    {
        RuntimeExceptionDao<Message, Integer> dao = getSimpleMessageDao();
        List<Message> list = dao.queryForAll();
        dao.delete(list);
    }

    @Override
    public void close() {
        super.close();
        simpleContactsDao = null;
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, Contact.class);
            TableUtils.createTable(connectionSource, Message.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, Contact.class, true);
            TableUtils.dropTable(connectionSource, Message.class, true);
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }
}
