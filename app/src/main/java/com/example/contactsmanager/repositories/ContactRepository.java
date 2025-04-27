package com.example.contactsmanager.repositories;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.contactsmanager.datasources.SQLiteDatasource;
import com.example.contactsmanager.models.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class ContactRepository {

    private final SQLiteDatasource contactSQLiteDatasource;

    @Inject
    public ContactRepository(Context context) {
        contactSQLiteDatasource = new SQLiteDatasource(
                context,
                "contacts",
                new String[]{"id", "firstname", "lastname", "email" ,"phone", "age"},
                "CREATE TABLE contacts (id INTEGER PRIMARY KEY AUTOINCREMENT, firstname TEXT, lastname TEXT, email TEXT, phone TEXT, age INTEGER)"
        );
    }



    public List<Contact> getContacts() {
        List<Contact> contacts = new ArrayList<>();
        Cursor contactsCursor = contactSQLiteDatasource.getAllRecords();

        if(contactsCursor != null && contactsCursor.moveToFirst())
            do{

                contacts.add(
                        new Contact(
                                contactsCursor.getInt(contactsCursor.getColumnIndexOrThrow("id")),
                                contactsCursor.getString(contactsCursor.getColumnIndexOrThrow("firstname")),
                                contactsCursor.getString(contactsCursor.getColumnIndexOrThrow("lastname")),
                                contactsCursor.getString(contactsCursor.getColumnIndexOrThrow("email")),
                                contactsCursor.getInt(contactsCursor.getColumnIndexOrThrow("age")),
                                contactsCursor.getString(contactsCursor.getColumnIndexOrThrow("phone"))
                        )
                );

            }while(contactsCursor.moveToNext());

        Objects.requireNonNull(contactsCursor).close();
        return contacts;
    }


    public void addContact(Contact contact) {
        ContentValues values = new ContentValues();

        values.put("firstname", contact.getFirstname());
        values.put("lastname", contact.getLastname());
        values.put("age", contact.getAge());
        values.put("email", contact.getEmail());
        values.put("phone", contact.getPhone());


        contactSQLiteDatasource.insert(values);
    }



}
