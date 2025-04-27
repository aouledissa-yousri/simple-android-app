package com.example.contactsmanager.modules;


import android.content.Context;

import com.example.contactsmanager.managers.ContactManager;
import com.example.contactsmanager.repositories.ContactRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class ManagerModule {

    @Provides
    public ContactManager provideContactManager(@ApplicationContext Context context) {
        return new ContactManager(
                new ContactRepository(context)
        );
    }
}
