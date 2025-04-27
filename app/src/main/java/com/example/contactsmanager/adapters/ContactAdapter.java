package com.example.contactsmanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.contactsmanager.R;
import com.example.contactsmanager.models.Contact;

import java.util.List;

public class ContactAdapter extends Adapter<ContactAdapter.ViewHolder> {

    private final Context context;
    private final List<Contact> contacts;

    public ContactAdapter(Context context, List<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {
        Contact contact = contacts.get(position);

        holder.fullname.setText(contact.getFullname());
        holder.email.setText(contact.getEmail());
        holder.age.setText(String.valueOf(contact.getAge()));
        holder.phone.setText(contact.getPhone());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        notifyItemInserted(0);
    }



    public static class  ViewHolder extends RecyclerView.ViewHolder {

        private final TextView fullname;
        private final TextView email;
        private final TextView phone;
        private final TextView age;

        public ViewHolder(@NonNull View contactView) {
            super(contactView);
            this.fullname = contactView.findViewById(R.id.contact_fullanme);
            this.email = contactView.findViewById(R.id.contact_email);
            this.phone = contactView.findViewById(R.id.contact_phone);
            this.age = contactView.findViewById(R.id.contact_age);
        }

    }
}
