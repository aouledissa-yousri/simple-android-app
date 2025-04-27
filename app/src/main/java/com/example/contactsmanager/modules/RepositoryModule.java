package com.example.contactsmanager.modules;

import android.content.Context;

import com.example.contactsmanager.repositories.ContactRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;


@Module
@InstallIn(SingletonComponent.class)
public class RepositoryModule {

    @Provides
    public ContactRepository provideContactRepository(@ApplicationContext Context context) {
        return new ContactRepository(context);
    }

}
