package com.holmesglen.assesment2.Database;

import com.holmesglen.assesment2.Models.Contact;

import java.util.ArrayList;

public class PhonebookDb {
    private final String TAG = this.getClass().getSimpleName();

    // static variable single_instance of type Singleton
    private static com.holmesglen.assesment2.Database.PhonebookDb db_instance = null;

    // variable of type ArrayList
    private ArrayList<Contact> phonebook;

    // private constructor restricted to this class itself
    private PhonebookDb()
    {
        phonebook = new ArrayList<Contact>();

        // init data
        phonebook.add(new Contact("aohn", "Smith", "123 456 789", "01/01/2020"));
        phonebook.add(new Contact("Aane", "Doe", "987 654 321","01/01/2020"));
        phonebook.add(new Contact("Bason", "Bourne", "0534684", "01/01/2020"));
        phonebook.add(new Contact("Cason", "Courne", "0534684", "01/01/2020"));
        phonebook.add(new Contact("Dason", "Dourne", "0534684", "01/01/2020"));
        phonebook.add(new Contact("Eason", "Kurne", "0534684", "01/01/2020"));
        phonebook.add(new Contact("Fason", "Wourne", "0534684", "01/01/2020"));
        phonebook.add(new Contact("Gason", "Fourne", "0534684", "01/01/2020"));
        phonebook.add(new Contact("Hason", "Yourne", "0534684", "01/01/2020"));
        phonebook.add(new Contact("Jason", "Vourne", "0534684", "01/01/2020"));
        phonebook.add(new Contact("Kason", "Mourne", "0534684", "01/01/2020"));
        phonebook.add(new Contact("Lason", "Nourne", "0534684", "01/01/2020"));
        phonebook.add(new Contact("Pason", "Zourne", "0534684", "01/01/2020"));
        phonebook.add(new Contact("Oason", "Journe", "0534684", "01/01/2020"));
        phonebook.add(new Contact("Tason", "Lourne", "0534684", "01/01/2020"));
        phonebook.add(new Contact("Rason", "Rourne", "0534684", "01/01/2020"));
        phonebook.add(new Contact("Wason", "Uourne", "0534684", "01/01/2020"));
        phonebook.add(new Contact("Sason", "Oourne", "0534684", "01/01/2020"));
        phonebook.add(new Contact("Zason", "Pourne", "0534684", "01/01/2020"));
        phonebook.add(new Contact("Xason", "Iourne", "0534684", "01/01/2020"));





    }

    public int count() {
        return phonebook.size();
    }

    // static method to create instance of Singleton class
    public static com.holmesglen.assesment2.Database.PhonebookDb getInstance()
    {
        if (db_instance == null)
            db_instance = new com.holmesglen.assesment2.Database.PhonebookDb();

        return db_instance;
    }

    public void update(int id, Contact contact) {
        if (id >= 0 && id < phonebook.size()){
            phonebook.get(id).setLastName(contact.getLastName());
            phonebook.get(id).setPhone(contact.getPhone());
        }

        return;

    }

    public void delete(int id) {
        if (id >= 0 && id < phonebook.size()) {
            phonebook.remove(id);
        }
        return;
    }

    // add a element to the end of list
    public void add(Contact newContact) {
        phonebook.add(newContact);
        return;
    }

    // get a element with index number
    public Contact getContact(int index) {
        Contact c = null;
        if(index >= 0 && index < phonebook.size()) {
            c = phonebook.get(index);
        }
        return c;
    }

    public ArrayList<Contact> getAll() {
        return (ArrayList<Contact>)phonebook.clone();
    }
    // dump db data to logcat for debug purpose
    public void dump() {
        System.out.println("Dump phonebook data ");
        if (phonebook == null) {
            System.out.println("db not initialized");
        } else {
            System.out.println("number of contact: " + phonebook.size());
            for (int i = 0; i < phonebook.size(); i++){
                Contact c = phonebook.get(i);
                System.out.println(c.toString());
            }
        }
        return;
    }
}
