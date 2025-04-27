package com.example.contactsmanager.helpers;

import android.text.InputType;
import android.widget.EditText;

import java.util.List;

public abstract class FormHelper {


    private FormHelper() {}


    public static boolean validateInput(EditText input) {

        String value = input.getText().toString().trim();

        switch (input.getInputType()) {

            case InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS :
            case InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS:
                return !value.isEmpty() && value.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

            default:
                return !value.isEmpty();
        }
    }


    public static boolean isFormValid(List<EditText> inputs) {
        for(EditText input: inputs) if(!validateInput(input)) return false;
        return true;
    }
}
