package com.example.contactsmanager.models;

import androidx.annotation.NonNull;

public class Contact {

    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private int age;
    private String phone;


    public Contact(
            int id,
            String firstname,
            String lastname,
            String email,
            int age,
            String phone
    ) {
        this.id = id;
        setFirstname(firstname);
        setLastname(lastname);
        setEmail(email);
        setAge(age);
        setPhone(phone);
    }

    public Contact(
            String firstname,
            String lastname,
            String email,
            int age,
            String phone
    ) {
        setFirstname(firstname);
        setLastname(lastname);
        setEmail(email);
        setAge(age);
        setPhone(phone);
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFullname() {
        return getFirstname() + " " + getLastname();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @NonNull
    @Override
    public String toString() {
        return "firstname: " + getFirstname() + "---" +
                "lastname: " + getLastname() + "---"  +
                "email: " + getEmail() + "---" +
                "age: " + getAge() + "---" +
                "phone: " + getPhone();

    }
}
