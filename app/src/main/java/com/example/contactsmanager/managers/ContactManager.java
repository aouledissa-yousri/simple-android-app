package com.example.contactsmanager.managers;


import android.util.Log;

import com.example.contactsmanager.adapters.ContactAdapter;
import com.example.contactsmanager.models.Contact;
import com.example.contactsmanager.repositories.ContactRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ContactManager {

    private final ContactRepository contactRepository;


    @Inject
    public ContactManager(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


    public List<Contact> getContacts() {
        return this.contactRepository.getContacts();
    }

    public Contact addContact(
            String firstname,
            String lastname,
            String email,
            int age,
            String phone
    ) {
        Contact contact = new Contact(
                firstname,
                lastname,
                email,
                age,
                phone
        );

        contactRepository.addContact(contact);
        return contact;
    }
}
