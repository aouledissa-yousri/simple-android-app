package com.example.contactsmanager.fragments.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.contactsmanager.R;
import com.example.contactsmanager.adapters.ContactAdapter;
import com.example.contactsmanager.helpers.FormHelper;
import com.example.contactsmanager.managers.ContactManager;
import com.example.contactsmanager.models.Contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddContactDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
public class AddContactDialogFragment extends DialogFragment {

    @Inject protected ContactManager contactManager;

    private final ContactAdapter contactAdapter;

    private View dialogView;
    private Dialog dialog;

    private Button addContactButton;

    private final HashMap<String, EditText> inputs = new HashMap<>();


    public AddContactDialogFragment(ContactAdapter contactAdapter) {
        this.contactAdapter = contactAdapter;
    }




    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = requireActivity().getLayoutInflater();

        dialogView = layoutInflater.inflate(R.layout.fragment_dialog_add_contact, null);
        initUI();

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext()).setView(dialogView);
        dialog = builder.create();
        return dialog;
    }



    private void initUI() {
        initAddButton();
        initFormInputs();
    }


    private void initAddButton() {
        addContactButton = dialogView.findViewById(R.id.add_contact_button);
        addContactButton.setOnClickListener(addContact());
    }

    private void initFormInputs() {

        inputs.put("firstname",dialogView.findViewById(R.id.contact_firstname));
        inputs.put("lastname",dialogView.findViewById(R.id.contact_lastname));
        inputs.put("email",dialogView.findViewById(R.id.contact_email));
        inputs.put("age",dialogView.findViewById(R.id.contact_age));
        inputs.put("phone",dialogView.findViewById(R.id.contact_phone));

        for(EditText input : inputs.values()) input.addTextChangedListener(validateForm());
    }


    private View.OnClickListener addContact() {


        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact contact = contactManager.addContact(
                        Objects.requireNonNull(inputs.get("firstname")).getText().toString(),
                        Objects.requireNonNull(inputs.get("lastname")).getText().toString(),
                        Objects.requireNonNull(inputs.get("email")).getText().toString(),
                        Integer.parseInt(Objects.requireNonNull(inputs.get("age")).getText().toString()),
                        Objects.requireNonNull(inputs.get("phone")).getText().toString()
                );

                contactAdapter.addContact(contact);
                dialog.dismiss();
            }
        };
    }


    private TextWatcher validateForm() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                addContactButton.setEnabled(isFormValid());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };
    }

    private boolean isFormValid() {

        return FormHelper.isFormValid(new ArrayList<>() {{
            add(dialogView.findViewById(R.id.contact_firstname));
            add(dialogView.findViewById(R.id.contact_lastname));
            add(dialogView.findViewById(R.id.contact_email));
            add(dialogView.findViewById(R.id.contact_age));
            add(dialogView.findViewById(R.id.contact_phone));
        }});

    }
}