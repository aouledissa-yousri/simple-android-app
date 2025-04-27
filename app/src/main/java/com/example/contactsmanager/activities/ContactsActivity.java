package com.example.contactsmanager.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactsmanager.R;
import com.example.contactsmanager.adapters.ContactAdapter;
import com.example.contactsmanager.fragments.dialogs.AddContactDialogFragment;
import com.example.contactsmanager.helpers.FormHelper;
import com.example.contactsmanager.managers.ContactManager;
import com.example.contactsmanager.repositories.ContactRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import dagger.internal.DaggerCollections;


@AndroidEntryPoint
public class ContactsActivity extends AppCompatActivity {


    @Inject protected ContactManager contactManager;

    private FloatingActionButton showAddContactButton;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        initUI();
    }


    private void initUI() {
        bindAddButtonEvent();
        initContactList();
    }


    private void bindAddButtonEvent() {
        showAddContactButton = findViewById(R.id.show_add_contact_button);

        showAddContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddContactDialog();
            }
        });
    }

    private void showAddContactDialog() {
        AddContactDialogFragment addContactDialogFragment = new AddContactDialogFragment((ContactAdapter) recyclerView.getAdapter());
        addContactDialogFragment.show(getSupportFragmentManager(), "Add Contact Dialog");
    }


    private void initContactList() {
        recyclerView = findViewById(R.id.contact_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ContactAdapter(this, contactManager.getContacts()));
    }



}